/* ProfileController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import forms.ComplaintForm;

import services.ComplaintService;
import services.CustomerService;

@Controller
@RequestMapping("/complaint/customer")
public class ComplaintCustomerController extends AbstractController {
	@Autowired
	private ComplaintService complaintService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ComplaintForm complaintForm;

		complaintForm = complaintService.createComplaintForm();

		result = new ModelAndView("complaint/edit");
		result.addObject("complaintForm", complaintForm);
		result.addObject("requestURI", "complaint/customer/create.do");

		return result;
	}
	
}