package controllers.petOwner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;
import services.ReviewService;
import controllers.AbstractController;
import domain.Booking;
import domain.Review;

@Controller
@RequestMapping("/review/petOwner")
public class ReviewPetOwnerController extends AbstractController{
	
	// Constructors -----------------------------------------------------------

	public ReviewPetOwnerController() {
		super();
	}
	
	// Services -----------------------------------------------------------------
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private BookingService bookingService;
	
	// Saving ---------------------------------------------------------------
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int bookingId){
		ModelAndView result;
		Booking booking;
		Review review;
		String requestUri;
		
		booking = bookingService.findOne(bookingId);
		reviewService.checkValidDate(booking);
		review = reviewService.create(booking);
		requestUri = "review/petOwner/create.do";
		
		result = new ModelAndView("review/create");
		result.addObject("review", review);
		result.addObject("requestUri", requestUri);
		result.addObject("principal", true);

		return result;		
	}
	
	

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Review review,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(review);
		} else {
			try {
				reviewService.save(review);
				result = new ModelAndView("redirect:../welcome/index.do");
			} catch (Throwable oops) {

				result = createEditModelAndView(review,
						"review.error.operation");
			}
		}
		return result;
	}
	
	// Ancillary methods-------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Review review){
		ModelAndView result;
			
		result = createEditModelAndView(review, null);
		
		return result;
	}
		
		
	protected ModelAndView createEditModelAndView(Review review, String message){
		ModelAndView result;
			
		result = new ModelAndView("review/create");
		result.addObject("review", review);
		result.addObject("message", message);
			
		return result;
	}
}