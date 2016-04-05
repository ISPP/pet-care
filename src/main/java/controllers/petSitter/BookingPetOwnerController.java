package controllers.petSitter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;
import services.PetOwnerService;
import services.ReviewService;
import controllers.AbstractController;
import domain.Booking;
import domain.PetOwner;
import domain.Review;

@Controller
@RequestMapping("/booking/petOwner")
public class BookingPetOwnerController extends AbstractController{
	
	@Autowired
	private BookingService bookingService;
	@Autowired
	private PetOwnerService petOwnerService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Booking> bookings;
		
		result = new ModelAndView("booking/list");
		PetOwner petOwner;
		petOwner=petOwnerService.findOneByPrincipal();
		bookings=petOwner.getBookings();
		
		result.addObject("bookings",bookings);
		result.addObject("requestURI", "booking/petOwner/list.do");
		
		return result;
	}

}
