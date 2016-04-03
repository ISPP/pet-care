package controllers;

import java.util.Collection;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

//	@RequestMapping(value = "/listSitters", method = RequestMethod.POST, params = "search")
//	public ModelAndView listSitters(@Valid SearchSittersForm searchSittersForm) {
//		ModelAndView result;
//		Collection<PetSitter> sitters;
//		
//		sitters = petSitterService.searchSitters(searchSittersForm.getStartDate(), 
//				searchSittersForm.getEndDate(), searchSittersForm.getAddress());
//		
//		result = new ModelAndView("search/listSitters");
//		result.addObject("sitters", sitters);
//		result.addObject("searchSittersForm", searchSittersForm);
//		result.addObject("requestURI", "search/listSitters.do");
//
//		return result;
//	}
}
