/* LoginController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import forms.SearchSuppliersForm;

@Controller
@RequestMapping("/security")
public class LoginController extends AbstractController {

	// Supporting services ----------------------------------------------------
	
	@Autowired
	LoginService service;
	
	// Constructors -----------------------------------------------------------
	
	public LoginController() {
		super();
	}
	
	// Login ------------------------------------------------------------------

	@RequestMapping("/login")
	public ModelAndView login(
			@Valid @ModelAttribute Credentials credentials,
			BindingResult bindingResult,
			@RequestParam(required = false) boolean showError) {
		Assert.notNull(credentials);
		Assert.notNull(bindingResult);
		
		ModelAndView result;
		SearchSuppliersForm searchSuppliersForm;
		
		searchSuppliersForm = new SearchSuppliersForm();
		
		//result = new ModelAndView("security/login");
		//result.addObject("credentials", credentials);
		//result.addObject("showError", showError);
		
		result = new ModelAndView("welcome/index");
		result.addObject("credentials", credentials);
		result.addObject("showError", true);
		result.addObject("index", true);
		result.addObject("searchSuppliersForm", searchSuppliersForm);

		return result;
	}
	
	// LoginFailure -----------------------------------------------------------

	@RequestMapping("/loginFailure")
	public ModelAndView failure() {
		ModelAndView result;

		result = new ModelAndView("redirect:../welcome/index.do?showError=true");

		return result;
	}

}