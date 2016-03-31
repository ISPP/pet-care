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

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Complaint;

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
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ComplaintForm complaintForm,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(complaintForm);
			System.out.println(binding.getAllErrors());
		} else {
			try {
				Complaint complaint;
				complaint = complaintService.reconstruct(complaintForm);
				complaintService.save(complaint);
				result = new ModelAndView("redirect:listComplaintCustomerIdNotSolved.do");
			} catch (Throwable oops) {

				result = createEditModelAndView(complaintForm,
						"complaint.error.operation");
				oops.printStackTrace();
			}
		}
		return result;
	}
	
	
	@RequestMapping(value = "/listComplaintCustomerId", method = RequestMethod.GET)
	public ModelAndView listComplaintByPetSitterId() {
		ModelAndView result;
		

		Collection<Complaint> complaints = complaintService.findComplaintByCustommerIdAndResolution();

		result = new ModelAndView("complaint/listComplaintCustomerId");
		result.addObject("complaints", complaints);
		result.addObject("requestURI", "complaint/customer/listComplaintCustomerId.do");

		return result;
	}
	
	
	
	@RequestMapping(value = "/listComplaintCustomerIdNotSolved", method = RequestMethod.GET)
	public ModelAndView listComplaintByPetSitterIdNotSolved() {
		ModelAndView result;
		

		Collection<Complaint> complaints = complaintService.findComplaintByCustommerIdAndNotResolution();

		result = new ModelAndView("complaint/listComplaintCustomerIdNotSolved");
		result.addObject("complaints", complaints);
		result.addObject("requestURI", "complaint/customer/listComplaintCustomerIdNotSolved.do");

		return result;
	}
	
	protected ModelAndView createEditModelAndView(ComplaintForm complaintForm) {
		ModelAndView result;

		result = createEditModelAndView(complaintForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(ComplaintForm complaintForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("complaint/edit");
		result.addObject("complaintForm", complaintForm);
		result.addObject("message", message);
		return result;
	}
}