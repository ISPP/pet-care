package controllers.administrator;

import java.util.Calendar;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import services.AdministratorService;
import services.CompanyService;
import services.EmailService;
import domain.Administrator;
import domain.Company;
import domain.PetShipper;
import forms.CompanyForm;
import forms.InvitationForm;

@Controller
@RequestMapping("/company/administrator")
public class CompanyAdministratorController extends AbstractController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AdministratorService administratorService;

	@RequestMapping(value = "/invite", method = RequestMethod.GET)
	public ModelAndView invite() {
		ModelAndView result;

		//Customer customer;
		//customer=customerService.getLoggedCustomer();
		InvitationForm iForm;
		iForm=new InvitationForm();
		result = createInvitationModelAndView(iForm);
		
		
		return result;
	}

	@RequestMapping(value = "/invite", method = RequestMethod.POST)
	public ModelAndView sendIntation( @Valid InvitationForm invitationForm,
			BindingResult binding) {
		ModelAndView result;
		result = new ModelAndView();
		Administrator administrator;
		if (binding.hasErrors()) {
			result = createInvitationModelAndView(invitationForm);
			
		} else {
			result = new ModelAndView("welcome/index");
			
			try {
				administrator = administratorService.findOneByPrincipal();
				emailService.sendToCompany(administrator, invitationForm.getEmail());

			} catch (Exception e) {

				
				result.addObject("message", "error.operationInvite");
			}
		}
		return result;
	}
	
	
	protected ModelAndView createInvitationModelAndView(InvitationForm invitationForm) {
		ModelAndView result;

		result = createInvitationModelAndView(invitationForm, null);

		return result;
	}
	
	protected ModelAndView createInvitationModelAndView(InvitationForm invitationForm, String message){
		ModelAndView result;
		
		result = new ModelAndView("company/invite");
		result.addObject("invitationForm", invitationForm);
		result.addObject("message", message);
		result.addObject("requestURI", "company/administrator/invite.do");
		return result;
	}
	


}
