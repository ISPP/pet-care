package controllers.petShipper;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Trip;
import domain.Vehicle;
import forms.TripForm;
import services.TripService;
import services.VehicleService;

@Controller
@RequestMapping("/trip/petShipper")
public class TripPetShipperController extends AbstractController {

	@Autowired
	private TripService tripService;
	
	@Autowired
	private VehicleService vehicleService;

	@RequestMapping(value = "/listWithRegistrations", method = RequestMethod.GET)
	public ModelAndView listWithRegistrations() {
		ModelAndView result;

		Collection<Trip> trips;

		result = new ModelAndView("trip/listWithRegistrations");

		trips = tripService.findTripsWithRegistrations();

		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/petShipper/listWithRegistrations.do");

		return result;
	}
	
	// List all
	
	@RequestMapping(value = "/listByShipper", method = RequestMethod.GET)
	public ModelAndView listByShipper(@RequestParam int petShipperId) {
		ModelAndView result;

		Collection<Trip> trips;

		result = new ModelAndView("trip/list");

		trips = tripService.findAllByShipper(petShipperId);

		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/petShipper/listByShipper.do");

		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Trip> trips;

		result = new ModelAndView("trip/list");

		trips = tripService.findAllPrincipal();

		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/petShipper/list.do");

		return result;
	}

	// Displaying

	@RequestMapping(value = "/see", method = RequestMethod.GET)
	public ModelAndView see(@RequestParam int tripId) {
		ModelAndView result;
		Trip trip;
		boolean deletable;
		
		String requestURI;

		requestURI = "trip/petShipper/see.do?tripId=" + tripId;
		trip = tripService.findOne(tripId);
		deletable = tripService.isDeletable(trip);

		result = new ModelAndView("trip/see");
		result.addObject("trip", trip);
		result.addObject("deletable", deletable);
		result.addObject("requestURI", requestURI);

		return result;
	}

	// Creating-----------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		TripForm tripForm;
		Collection<Vehicle> vehicles;

		tripForm = new TripForm();
		vehicles = tripService.getVehiclesOwner();
		
		result = new ModelAndView("trip/create");
		result.addObject("tripForm", tripForm);
		result.addObject("isOwner", true);
		result.addObject("vehicles", vehicles);
		result.addObject("deletable", false);
		result.addObject("requestURI", "trip/petShipper/edit.do");

		return result;
	}

	// Editing-------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int tripId) {
		ModelAndView result;
		TripForm tripForm;
		Boolean isOwner;
		boolean deletable;

		tripForm = tripService.fragment(tripService.findOne(tripId));
		isOwner = tripService.isOwner(tripService.findOne(tripId));
		deletable = tripService.isDeletable(tripService.findOne(tripId));

		result = createEditModelAndView(tripForm);
		result.addObject("isOwner", isOwner);
		result.addObject("deletable", deletable);
		result.addObject("showDeleteButton", deletable && tripService.findOne(tripId).getRegistrations().isEmpty());

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid TripForm tripForm, BindingResult binding) {
		ModelAndView result;
		Trip trip;

		if (binding.hasErrors()) {
			result = createEditModelAndView(tripForm);
		} else {
			try {
				trip = tripService.reconstruct(tripForm);
				tripService.save(trip);
				result = new ModelAndView("redirect:/trip/petShipper/list.do");

			}catch(IllegalStateException oops){
				result = createEditModelAndView(tripForm, "trip.date.error");
			}catch(IllegalArgumentException oops){
				result = createEditModelAndView(tripForm, "trip.cost.error");
			}
			
			catch (Throwable oops) {
				result = createEditModelAndView(tripForm, "trip.error.operation");
				oops.printStackTrace();
			}
		}
		return result;
	}

	// Deleting ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid TripForm tripForm, BindingResult binding) {
		ModelAndView result;

		try {
			tripService.delete(tripForm);
			result = new ModelAndView("redirect:/trip/petShipper/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(tripForm, "trip.error.operation");
		}
		return result;
	}

	// Ancillary
	// methods-----------------------------------------------------------

	protected ModelAndView createEditModelAndView(TripForm tripForm) {
		ModelAndView result;
		result = createEditModelAndView(tripForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(TripForm tripForm, String message) {
		ModelAndView result;
		String requestURI;
		Boolean isOwner;
		Collection<Vehicle> vehicles;
		boolean deletable;
		
		if (tripForm.getId() != 0) {
			isOwner = tripService.isOwner(tripService.findOne(tripForm.getId()));
			deletable = tripService.isDeletable(tripService.findOne(tripForm.getId()));
		} else {
			isOwner = true;
			deletable = false;
		}
		requestURI = "trip/petShipper/edit.do?tripId="+tripForm.getId();
		vehicles = vehicleService.findOne(tripForm.getVehicleId()).getPetShipper().getVehicles();
		

		result = new ModelAndView("trip/edit");
		result.addObject("tripForm", tripForm);
		result.addObject("message", message);
		result.addObject("requestURI", requestURI);
		result.addObject("vehicles", vehicles);
		result.addObject("isOwner", isOwner);
		result.addObject("deletable", deletable);

		return result;

	}

}
