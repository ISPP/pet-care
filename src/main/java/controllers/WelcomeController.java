/* WelcomeController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import forms.SearchSuppliersForm;
import security.Authority;
import security.Credentials;
import services.ActorService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------
	
	public WelcomeController() {
		super();
	}
		
	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index(@Valid @ModelAttribute Credentials credentials,
			BindingResult bindingResult,
			@RequestParam(required = false) boolean showError) {
		Assert.notNull(credentials);
		Assert.notNull(bindingResult);
		
		Actor actor;
		Authority a1,a2,a3;
		ModelAndView result;
		SearchSuppliersForm searchSuppliersForm;
		
		searchSuppliersForm = new SearchSuppliersForm();
		
		actor = null;
		
		a1 = new Authority();
		a2 = new Authority();
		a3 = new Authority();
		a1.setAuthority(Authority.PETSITTER);
		a2.setAuthority(Authority.PETSHIPPER);
		a3.setAuthority(Authority.PETOWNER);
		try{
			actor = actorService.findActorByUserId();
			result = null;
			if(actor.getUser().getAuthorities().contains(a1)){
				result = new ModelAndView("welcome/index");
				result.addObject("credentials", credentials);
				result.addObject("showError", showError);
				result.addObject("index", true);
				result.addObject("searchSuppliersForm", searchSuppliersForm);
			}else if(actor.getUser().getAuthorities().contains(a2)){
				result = new ModelAndView("welcome/index");
				result.addObject("credentials", credentials);
				result.addObject("showError", showError);
				result.addObject("index", true);
				result.addObject("searchSuppliersForm", searchSuppliersForm);
			}else if(actor.getUser().getAuthorities().contains(a3)){
				result = new ModelAndView("welcome/index");
				result.addObject("credentials", credentials);
				result.addObject("showError", showError);
				result.addObject("index", true);
				result.addObject("searchSuppliersForm", searchSuppliersForm);
			}else{
				result = new ModelAndView("welcome/index");
				result.addObject("credentials", credentials);
				result.addObject("showError", showError);
				result.addObject("index", true);
				result.addObject("searchSuppliersForm", searchSuppliersForm);
			}
		}catch (Throwable t){
			result = new ModelAndView("welcome/index");
			result.addObject("credentials", credentials);
			result.addObject("showError", showError);
			result.addObject("index", true);
			result.addObject("searchSuppliersForm", searchSuppliersForm);
		}
		
		if(actor==null){
			result = new ModelAndView("welcome/index");
			result.addObject("credentials", credentials);
			result.addObject("showError", showError);
			result.addObject("index", true);
			result.addObject("searchSuppliersForm", searchSuppliersForm);
		}
		
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
	//
//		@RequestMapping(value = "/searchSitters", method = RequestMethod.POST, params = "search")
//		public ModelAndView searchSitters(@Valid SearchSittersForm searchSittersForm, BindingResult binding) {
//			ModelAndView result;
//			Collection<PetSitter> sitters;
//			
//			if (binding.hasErrors()) {
//				result = new ModelAndView("search/searchSitters");
//				result.addObject("requestURI", "search/searchSitters.do");
//				result.addObject("searchSittersForm", searchSittersForm);
//				result.addObject("message", null);
//			}else{
//				try{
//					sitters = petSitterService.searchSitters(searchSittersForm.getStartDate(), 
//							searchSittersForm.getEndDate(), searchSittersForm.getAddress());
//					
//					result = new ModelAndView("search/list");
//					result.addObject("sitters", sitters);
//					result.addObject("searchSittersForm", searchSittersForm);
//					result.addObject("requestURI", "search/searchSitters.do");
//				}catch(Throwable oops){
//					result = new ModelAndView("search/searchSitters");
//					result.addObject("requestURI", "search/searchSitters.do");
//					result.addObject("searchSittersForm", searchSittersForm);
//					result.addObject("message", "search.commit.error");
//				}
//			}
//			
//			return result;
//		}
		
	
	
	// LoginFailure -----------------------------------------------------------

		@RequestMapping("/loginFailure")
		public ModelAndView failure() {
			ModelAndView result;

			result = new ModelAndView("redirect:index.do?showError=true");
			result.addObject("showError",true);
			return result;
		}
	
}