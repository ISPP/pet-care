package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.PetSitter;
import forms.SearchSittersForm;
import services.PetSitterService;

@Controller
@RequestMapping("/search")
public class SearchController extends AbstractController{
	
	// Constructors -----------------------------------------------------------

	public SearchController() {
		super();
	}
	
	// Services -----------------------------------------------------------------
	
	@Autowired
	private PetSitterService petSitterService;

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
	
	/*@RequestMapping(value = "/searchSitters", method = RequestMethod.GET)
	public ModelAndView search(){
		ModelAndView result;
											
		result = new ModelAndView("search/searchSitters");
														
		return result;
	}
		
	@RequestMapping(value = "/searchSitters", method = RequestMethod.POST, params = "search")
	public ModelAndView searchSitters(String startDate, String endDate, String address){
		ModelAndView result;
					
		result = new ModelAndView("redirect:list.do?startDate="+startDate+"&endDate="+endDate+"&address="+address);
		
		return result;
	}*/
	
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
		
		if (binding.hasErrors()) {
			result = new ModelAndView("search/searchSitters");
			result.addObject("requestURI", "search/searchSitters.do");
			result.addObject("searchSittersForm", searchSittersForm);
			result.addObject("message", null);
		}else{
			try{
				sitters = petSitterService.searchSitters(searchSittersForm.getStartDate(), 
						searchSittersForm.getEndDate(), searchSittersForm.getAddress());
				
				result = new ModelAndView("search/list");
				result.addObject("sitters", sitters);
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
}
