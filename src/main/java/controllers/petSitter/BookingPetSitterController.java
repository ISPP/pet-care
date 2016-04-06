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

import controllers.AbstractController;
import domain.Booking;
import domain.Review;


@Controller
@RequestMapping("/booking/petSitter")
public class BookingPetSitterController extends AbstractController{
	@Autowired
	private BookingService bookingService;
	
	

}
