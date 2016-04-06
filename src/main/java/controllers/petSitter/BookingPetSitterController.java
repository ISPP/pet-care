package controllers.petSitter;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;

import controllers.AbstractController;
import domain.Booking;


@Controller
@RequestMapping("/booking/petSitter")
public class BookingPetSitterController extends AbstractController{
	@Autowired
	private BookingService bookingService;
	
	@RequestMapping(value="/listBookingAccepted",method=RequestMethod.GET)
	public ModelAndView listBookingAccepted(){
		ModelAndView result;
		Collection<Booking> bookings;
		result = new ModelAndView("booking/listBookingAccepted");
		bookings = bookingService.findBokkingAcceptedByPetSitterId();
		
		result.addObject("bookings",bookings);
		result.addObject("requestURI", "booking/petSitter/listBookingAccepted.do");
		
		return result;
	}

}
