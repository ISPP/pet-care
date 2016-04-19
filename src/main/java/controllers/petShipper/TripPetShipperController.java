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
@RequestMapping("/trip/petShipper")
public class TripPetShipperController extends AbstractController {

	@Autowired
	private TripService tripService;
	@Autowired
	private RegistrationService registrationService;

	

	

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



}
