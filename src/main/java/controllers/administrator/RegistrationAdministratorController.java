package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;
import services.RegistrationService;
import domain.Booking;
import domain.Registration;


@Controller
@RequestMapping("/registration/administrator")
public class RegistrationAdministratorController {
	
	@Autowired
	private RegistrationService registrationService;
	
	
	
	@RequestMapping(value = "/listToPay", method = RequestMethod.GET)
	public ModelAndView listToPay() {
		ModelAndView result;

		Collection<Registration> registrations = registrationService.findRegistrationNotPayByAdmin();
		result = new ModelAndView("paypal/listAdmin");
		result.addObject("registrations", registrations);
		result.addObject("requestURI", "registration/administrator/listToPay.do");

		return result;
	}

}
