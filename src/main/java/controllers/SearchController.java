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
import forms.SearchSuppliersForm;

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
	
	@RequestMapping(value = "/searchSuppliers", method = RequestMethod.GET)
	public ModelAndView search(){
		ModelAndView result;
		SearchSuppliersForm searchSuppliersForm;
		
		searchSuppliersForm = new SearchSuppliersForm();
		
		result = new ModelAndView("search/searchSuppliers");
		result.addObject("requestURI", "search/searchSuppliers.do");
		result.addObject("searchSuppliersForm", searchSuppliersForm);
														
		return result;
	}

	@RequestMapping(value = "/searchSuppliers", method = RequestMethod.POST, params = "search")
	public ModelAndView searchSuppliers(@Valid SearchSuppliersForm searchSuppliersForm, BindingResult binding) {
		ModelAndView result;
		
		Collection<Supplier> suppliers;
		Date d1,d2;
		boolean toBook;
		
		try{
			actorService.findActorByUsername();
			toBook = true;
		}catch(Throwable t){
			toBook = false;
		}
		
		if (binding.hasErrors()) {
			result = new ModelAndView("search/searchSuppliers");
			result.addObject("requestURI", "search/searchSuppliers.do");
			result.addObject("searchSuppliersForm", searchSuppliersForm);
			result.addObject("message", null);
		}else{
			
			try{
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				d1 = format.parse(searchSuppliersForm.getStartDate());
				d2 = format.parse(searchSuppliersForm.getEndDate());
				
				suppliers = supplierService.searchSuppliers(d1, 
						d2, searchSuppliersForm);
				
				result = new ModelAndView("search/list");
				result.addObject("suppliers", suppliers);
				result.addObject("toBook", toBook);
				result.addObject("index", true);
				result.addObject("searchSuppliersForm", searchSuppliersForm);
				result.addObject("requestURI", "search/searchSuppliers.do");
			}catch(Throwable oops){
				result = new ModelAndView("search/searchSuppliers");
				result.addObject("requestURI", "search/searchSuppliers.do");
				result.addObject("searchSuppliersForm", searchSuppliersForm);
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
		requestURI = "search/display.do?supplierId="+supplierId;

		result = new ModelAndView("search/display");
		result.addObject("supplier", supplier);
		result.addObject("reviews", reviews);
		result.addObject("cancelURI", cancelURI);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
