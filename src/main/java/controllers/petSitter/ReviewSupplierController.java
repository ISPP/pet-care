package controllers.petSitter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ReviewService;
import controllers.AbstractController;
import domain.Review;

@Controller
@RequestMapping("/review/supplier")
public class ReviewSupplierController extends AbstractController{
	
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		List<Review> reviews;
		
		result = new ModelAndView("review/list");
		reviews = new ArrayList<Review>(reviewService.findReviewBySupplierId());
		
		result.addObject("reviews",reviews);
		result.addObject("requestURI", "review/supplier/list.do");
		
		return result;
	}

}
