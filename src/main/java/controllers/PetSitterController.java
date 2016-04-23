package controllers;

import java.util.Calendar;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.EmailService;
import services.PetSitterService;
import services.SupplierService;
import domain.Customer;
import domain.PetSitter;
import forms.InvitationForm;
import forms.PetSitterForm;
import forms.SearchSittersForm;
@Controller
@RequestMapping("/petSitter")
public class PetSitterController extends AbstractController {
	
	@Autowired
	private PetSitterService petSitterService;
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private EmailService emailService;

	// Constructors -----------------------------------------------------------
	
	public PetSitterController() {
		super();
	}
		
	
	//list
	@RequestMapping(value="/listToBook", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		
		Collection<PetSitter> petSitters;
		petSitters=petSitterService.findAll();
		
		result = new ModelAndView("petSitter/list");
		result.addObject("petSitters",petSitters);
		result.addObject("toBook", true);
		
		return result;
	}
	
	
	// Edition-----------------------------------------------------------------
	

	
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(@RequestParam String invitationCode){
		ModelAndView result;
		PetSitterForm petSitterForm;
		
		petSitterForm = new PetSitterForm();
		petSitterForm.setDaysBeforeCancel(1);
		petSitterForm.setInvitationCode(invitationCode);
		
		result = createEditModelAndView(petSitterForm);
		
		result.addObject("register", true);
		
		return result;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST, params="create")
	public ModelAndView create(@Valid PetSitterForm petSitterForm, BindingResult binding){
		ModelAndView result;
		PetSitter petSitter;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(petSitterForm);
			System.out.println(binding.getAllErrors());
		}else{
			try{
				if(petSitterForm.getExpirationYear()< Calendar.getInstance().get(Calendar.YEAR)){
					result = createEditModelAndView(petSitterForm);
					result.addObject("oldYear", true);
				}
//				else if(!petSitterForm.getAcceptTermsAndConditions()){
//					result = createEditModelAndView(petSitterForm);
//					result.addObject("termsNotAccepted", true);
//				}
				else if(!petSitterForm.getPassword().equals(petSitterForm.getPasswordConfirm())){
					result = createEditModelAndView(petSitterForm,"petSitter.commit.password");
				}else{
					petSitter = petSitterService.reconstruct(petSitterForm);
					petSitterService.register(petSitter,petSitterForm.getInvitationCode());
					result = new ModelAndView("redirect:../security/login.do");
				}
			}catch(Throwable oops){
				result = createEditModelAndView(petSitterForm,"petSitter.commit.error");
			}
		}

		result.addObject("register", true);
		
		return result;		
	}
	
	
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
		
		
		return result;
	}

	@RequestMapping(value = "/invite", method = RequestMethod.POST)
	public ModelAndView sendIntation( @Valid InvitationForm invitationForm,
			BindingResult binding) {
		ModelAndView result;
		result = new ModelAndView();
		PetSitter petSitter;
		if (binding.hasErrors()) {
			result = createInvitationModelAndView(invitationForm);
			
		} else {
			result = new ModelAndView("welcome/index");
			SearchSittersForm searchSittersForm;
			
			searchSittersForm = new SearchSittersForm();
			
			result.addObject("searchSittersForm", searchSittersForm);
			try {
				petSitter = petSitterService.findOneByPrincipal();
				emailService.sendToAFriend(petSitter, invitationForm.getEmail());

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
		
		result = new ModelAndView("petSitter/invite");
		result.addObject("invitationForm", invitationForm);
		result.addObject("message", message);
		result.addObject("requestURI", "petSitter/invite.do");
		return result;
	}
}
