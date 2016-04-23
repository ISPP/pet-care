package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.PetSitterService;
import services.SupplierService;
import domain.PetSitter;
import domain.Review;
import domain.Supplier;
import forms.SearchSittersForm;

@Controller
@RequestMapping("/search")
public class SearchController extends AbstractController{
	
	// Constructors -----------------------------------------------------------

	public SearchController() {
		super();
	}
	
	// Services -----------------------------------------------------------------
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private PetSitterService petSitterService;
	
	@Autowired
	private SupplierService supplierService;

	// List sitters ---------------------------------------------------------------		

	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<PetSitter> sitters;
		String requestURI;
		
		requestURI= "search/list.do";
		sitters = petSitterService.findAll();
		
		result = new ModelAndView("search/list");
		result.addObject("sitters", sitters);
		result.addObject("requestURI", requestURI );
		
		return result;
	}
	
	// Searching -----------------------------------------------------------------
	
	@RequestMapping(value = "/searchSitters", method = RequestMethod.GET)
	public ModelAndView search(){
		ModelAndView result;
		SearchSittersForm searchSittersForm;
		
		searchSittersForm = new SearchSittersForm();
		
		result = new ModelAndView("search/searchSitters");
		result.addObject("requestURI", "search/searchSitters.do");
		result.addObject("searchSittersForm", searchSittersForm);
														
		return result;
	}

	@RequestMapping(value = "/searchSitters", method = RequestMethod.POST, params = "search")
	public ModelAndView searchSitters(@Valid SearchSittersForm searchSittersForm, BindingResult binding) {
		ModelAndView result;
		Collection<PetSitter> sitters;
		Date d1,d2;
		boolean toBook;
		
		try{
			actorService.findActorByUsername();
			toBook = true;
		}catch(Throwable t){
			toBook = false;
		}
		
		if (binding.hasErrors()) {
			result = new ModelAndView("search/searchSitters");
			result.addObject("requestURI", "search/searchSitters.do");
			result.addObject("searchSittersForm", searchSittersForm);
			result.addObject("message", null);
		}else{
			
			try{
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				d1 = format.parse(searchSittersForm.getStartDate());
				d2 = format.parse(searchSittersForm.getEndDate());
				
				sitters = petSitterService.searchSitters(d1, 
						d2, searchSittersForm.getAddress());
				
				result = new ModelAndView("search/list");
				result.addObject("sitters", sitters);
				result.addObject("toBook", toBook);
				result.addObject("index", true);
				result.addObject("searchSittersForm", searchSittersForm);
				result.addObject("requestURI", "search/searchSitters.do");
			}catch(Throwable oops){
				result = new ModelAndView("search/searchSitters");
				result.addObject("requestURI", "search/searchSitters.do");
				result.addObject("searchSittersForm", searchSittersForm);
				result.addObject("message", "search.commit.error");
			}
		}
		
		return result;
	}
	
	// Displaying ------------------------------------------------------------
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int supplierId) {
		ModelAndView result;
		Supplier supplier;
		String cancelURI, requestURI;
		Collection<Review> reviews;

		supplier = supplierService.findOne(supplierId);
		reviews = supplierService.findReviews(supplierId);
		cancelURI = "search/list.do";
		requestURI = "search/display.do?supplierIdId="+supplierId;

		result = new ModelAndView("search/display");
		result.addObject("supplier", supplier);
		result.addObject("reviews", reviews);
		result.addObject("cancelURI", cancelURI);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
