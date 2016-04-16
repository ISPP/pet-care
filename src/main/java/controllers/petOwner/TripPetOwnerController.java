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
import domain.PetOwner;
import domain.PetSitter;
import domain.Review;
import domain.Trip;
import forms.BookingForm;

@Controller
@RequestMapping("/trip/petOwner")
public class TripPetOwnerController extends AbstractController {

	@Autowired
	private TripService tripService;
	@Autowired
	private RegistrationService registrationService;


	@RequestMapping(value = "/listToRegister", method = RequestMethod.GET)
	public ModelAndView listToRegister() {
		ModelAndView result;
		Collection<Trip> trips;

		result = new ModelAndView("trip/listToRegister");
		
		trips = tripService.findAll();

		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/petOwner/listToRegister.do");

		return result;
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int tripId) {
		ModelAndView result;
		try{
			
			Trip trip;
			trip=tripService.findOne(tripId);
			registrationService.registerToTrip(trip);
			result=new ModelAndView("welcome/index");
			
		}catch(IllegalArgumentException exc){
			
			result=new ModelAndView("redirect:listToRegistration.do");
			result.addObject("message", "trip.error.timeAfter");
			
		}catch(Throwable exc){
			result=new ModelAndView("redirect:listToRegistration.do");
			result.addObject("message", "trip.error.operation");
		}
		
				

		return result;
	}


	

}
