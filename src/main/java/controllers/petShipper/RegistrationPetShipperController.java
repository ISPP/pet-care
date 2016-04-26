package controllers.petShipper;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RegistrationService;
import services.TripService;
import controllers.AbstractController;
import domain.Registration;
import domain.Trip;

@Controller
@RequestMapping("/registration/petShipper")
public class RegistrationPetShipperController extends AbstractController {

	@Autowired
	private TripService tripService;
	@Autowired
	private RegistrationService registrationService;
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listWithRegistrations(@RequestParam() int tripId) {
		ModelAndView result;

		Collection<Registration> registrations;

		result = new ModelAndView("registration/list");
		Trip trip;
		trip=tripService.findOne(tripId);
		registrations = registrationService.findRegistrationByTrip(trip);

		result.addObject("registrations", registrations);
		result.addObject("requestURI", "registration/petShipper/list.do");

		return result;
	}



}
