package controllers;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.PetSitterService;

import domain.Customer;
import domain.PetSitter;

import forms.InvitationForm;
import forms.PetSitterForm;


@Controller
@RequestMapping("/petSitter")
public class PetSitterController extends AbstractController {
	
	@Autowired
	private PetSitterService petSitterService;
	@Autowired
	private CustomerService customerService;

	// Constructors -----------------------------------------------------------
	
	public PetSitterController() {
		super();
	}
		
	
	// Edition-----------------------------------------------------------------
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		PetSitterForm petSitterForm;
		
		petSitterForm = new PetSitterForm();
		result = createEditModelAndView(petSitterForm);
		
		result.addObject("register", true);
		
		return result;
	}
	
//	@RequestMapping(value="/create", method = RequestMethod.POST, params="create")
//	public ModelAndView create(@Valid PetSitterForm petSitterForm, BindingResult binding){
//		ModelAndView result;
//		PetSitter petSitter;
//		
//		if(binding.hasErrors()){
//			result = createEditModelAndView(petSitterForm);
//		}else{
//			try{
//				if(petSitterForm.getExpirationYear()< Calendar.getInstance().get(Calendar.YEAR)){
//					result = createEditModelAndView(petSitterForm);
//					result.addObject("oldYear", true);
//				}
////				else if(!petSitterForm.getAcceptTermsAndConditions()){
////					result = createEditModelAndView(petSitterForm);
////					result.addObject("termsNotAccepted", true);
////				}
//				else if(!petSitterForm.getPassword().equals(petSitterForm.getPasswordConfirm())){
//					result = createEditModelAndView(petSitterForm,"petSitter.commit.password");
//				}else{
//					petSitter = petSitterService.reconstruct(petSitterForm);
//					petSitterService.save(petSitter);
//					result = new ModelAndView("redirect:../security/login.do");
//				}
//			}catch(Throwable oops){
//				result = createEditModelAndView(petSitterForm,"petSitter.commit.error");
//			}
//		}
//
//		result.addObject("register", true);
//		
//		return result;		
//	}
	
	
	// Ancillary methods-------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(PetSitterForm petSitterForm){
		ModelAndView result;
		
		result = createEditModelAndView(petSitterForm, null);
	
		return result;
	}
	
	
	protected ModelAndView createEditModelAndView(PetSitterForm petSitterForm, String message){
		ModelAndView result;
		
		result = new ModelAndView("petSitter/edit");
		result.addObject("petSitterForm", petSitterForm);
		result.addObject("message", message);
		
		return result;
	}
	
	
	@RequestMapping(value = "/invite", method = RequestMethod.GET)
	public ModelAndView invite() {
		ModelAndView result;

		//Customer customer;
		//customer=customerService.getLoggedCustomer();
		InvitationForm iForm;
		iForm=new InvitationForm();
		result = createInvitationModelAndView(iForm);
		//result.addObject("singleCode", customer.getInvitationCode());
		
		return result;
	}

	protected ModelAndView createInvitationModelAndView(InvitationForm invitationForm) {
		ModelAndView result;

		result = createInvitationModelAndView(invitationForm, null);

		return result;
	}
	
	protected ModelAndView createInvitationModelAndView(InvitationForm invitationForm, String message){
		ModelAndView result;
		
		result = new ModelAndView("petSitter/invite");
		result.addObject("invitationForm", invitationForm);
		result.addObject("message", message);
		result.addObject("requestURI", "petCare/invite.do");
		return result;
	}

}