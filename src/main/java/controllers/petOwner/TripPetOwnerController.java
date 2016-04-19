package controllers.petOwner;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;
import services.PetOwnerService;
import services.PetSitterService;
import services.RegistrationService;
import services.TripService;
import controllers.AbstractController;
import domain.Booking;
import domain.Pet;
import domain.PetOwner;
import domain.PetSitter;
import domain.Review;
import domain.Trip;
import forms.BookingForm;
import forms.RegistrationForm;

@Controller
@RequestMapping("/trip/petOwner")
public class TripPetOwnerController extends AbstractController {

	@Autowired
	private TripService tripService;
	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = "/findTrips", method = RequestMethod.GET)
	public ModelAndView findTrips() {
		ModelAndView result;
		RegistrationForm registrationF = new RegistrationForm();

		result = new ModelAndView("trip/findToRegister");

		result.addObject("registrationF", registrationF);
		result.addObject("requestURI", "trip/petOwner/findToRegister.do");

		return result;
	}

	@RequestMapping(value = "/findToRegister", method = RequestMethod.POST)
	public ModelAndView findToRegister(@Valid RegistrationForm registrationF,
			BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = new ModelAndView("trip/findToRegister");
			result.addObject("registrationF", registrationF);
			result.addObject("requestURI", "trip/petOwner/findToRegister.do");

		} else {
			Collection<Trip> trips;

			result = new ModelAndView("trip/listToRegister");

			trips = tripService.findTripsStartAndEndCities(
					registrationF.getStartCity(), registrationF.getEndCity());

			result.addObject("trips", trips);
			result.addObject("requestURI", "trip/petOwner/listToRegister.do?"
					+ "startCity=" + registrationF.getStartCity() + "&endCity="
					+ registrationF.getEndCity());
		}
		return result;
	}

	@RequestMapping(value = "/listToRegister", method = RequestMethod.GET)
	public ModelAndView listToRegister(@RequestParam() String startCity,
			@RequestParam() String endCity) {
		ModelAndView result;

		Collection<Trip> trips;

		result = new ModelAndView("trip/listToRegister");

		trips = tripService.findTripsStartAndEndCities(startCity, endCity);

		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/petOwner/register.do");

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int tripId) {
		ModelAndView result;
		try {

			Trip trip;
			trip = tripService.findOne(tripId);
			registrationService.registerToTrip(trip);
			result = new ModelAndView("welcome/index");

		} catch (IllegalArgumentException exc) {

			result = new ModelAndView("trip/findToRegister");
			RegistrationForm registrationF=new RegistrationForm();
			result.addObject("registrationF", registrationF);
			result.addObject("requestURI", "trip/petOwner/findToRegister.do");
			result.addObject("message", "trip.error.timeAfter");

		} catch (Throwable exc) {
			result = new ModelAndView("trip/findToRegister");
			RegistrationForm registrationF=new RegistrationForm();
			result.addObject("registrationF", registrationF);
			result.addObject("requestURI", "trip/petOwner/findToRegister.do");
			result.addObject("message", "trip.error.operation");
		}

		return result;
	}

}
