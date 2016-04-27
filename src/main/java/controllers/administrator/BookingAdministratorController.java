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
@RequestMapping("/booking/administrator")
public class BookingAdministratorController {

	@Autowired
	private BookingService bookingService;
	@Autowired
	private RegistrationService registrationService;
	@RequestMapping(value = "/listToPay", method = RequestMethod.GET)
	public ModelAndView listToPay() {
		ModelAndView result;
		Collection<Registration> registrations = registrationService.findRegistrationNotPayByAdmin();
		Collection<Booking> bookings = bookingService.findBookingNotPayByAdmin();
		Collection<Booking> bookingsRembolse = bookingService.findBookingToRembolse();
		result = new ModelAndView("paypal/listAdmin");
		result.addObject("bookings", bookings);
		result.addObject("registrations", registrations);
		result.addObject("bookingsRembolse", bookingsRembolse);
		result.addObject("requestURI", "booking/administrator/listToPay.do");

		return result;
	}
	
}
