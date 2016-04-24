package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;

import domain.Booking;



@Controller
@RequestMapping("/booking/administrator")
public class BookingAdministratorController {

	@Autowired
	private BookingService bookingService;
	@RequestMapping(value = "/listToPay", method = RequestMethod.GET)
	public ModelAndView listToPay() {
		ModelAndView result;

		Collection<Booking> bookings = bookingService.findBookingNotPayByAdmin();
		result = new ModelAndView("paypal/listAdmin");
		result.addObject("bookings", bookings);
		result.addObject("requestURI", "booking/administrator/listToPay.do");

		return result;
	}
	
}
