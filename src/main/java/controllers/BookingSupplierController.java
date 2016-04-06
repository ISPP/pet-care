package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;
import domain.Booking;


@Controller
@RequestMapping("/booking/supplier")
public class BookingSupplierController extends AbstractController{
	
	@Autowired
	private BookingService bookingService;
	
	
	@RequestMapping(value="/listBookingAccepted",method=RequestMethod.GET)
	public ModelAndView listBookingAccepted(){
		ModelAndView result;
		Collection<Booking> bookings;
		result = new ModelAndView("booking/listBookingAccepted");
		bookings = bookingService.findBokkingAcceptedBySupplierId();
		
		result.addObject("bookings",bookings);
		result.addObject("requestURI", "booking/supplier/listBookingAccepted.do");
		
		return result;
	}
	@RequestMapping(value="/listPendingSupplier",method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Booking> bookings;
		
		result = new ModelAndView("booking/listPendingSupplier");
		
		bookings=bookingService.findBookingCanAceptRejectedByCustomerId();
		
		result.addObject("bookings",bookings);
		result.addObject("requestURI", "booking/supplier/listPendingSupplier.do");
		
		return result;
	}
	
	@RequestMapping(value="/accept",method=RequestMethod.GET)
	public ModelAndView accept(@RequestParam("id") int id){
		
		bookingService.aceptedBooking(id);
		ModelAndView result;result = new ModelAndView("welcome/index");
		return result;
	}
	
	@RequestMapping(value="/rejected",method=RequestMethod.GET)
	public ModelAndView rejected(@RequestParam("id") int id){
		
		bookingService.rejectedBooking(id);
		ModelAndView result;result = new ModelAndView("welcome/index");
		return result;
	}

}
