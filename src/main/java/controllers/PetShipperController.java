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
import services.PetShipperService;
import services.PetSitterService;
import services.SupplierService;
import domain.Customer;
import domain.PetShipper;
import domain.PetSitter;
import forms.InvitationForm;
import forms.PetShipperForm;
import forms.PetSitterForm;
import forms.SearchSittersForm;
@Controller
@RequestMapping("/petShipper")
public class PetShipperController extends AbstractController {
	
	@Autowired
	private PetShipperService petShipperService;
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private EmailService emailService;

	// Constructors -----------------------------------------------------------
	
	public PetShipperController() {
		super();
	}
		
	
	//list
	@RequestMapping(value="/listToBook", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		
		Collection<PetShipper> petShippers;
		petShippers=petShipperService.findAll();
		
		result = new ModelAndView("petShipper/list");
		result.addObject("petShippers",petShippers);
		result.addObject("toBook", true);
		
		return result;
	}
	
	
	// Edition-----------------------------------------------------------------
	

	
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(@RequestParam String invitationCode){
		ModelAndView result;
		PetShipperForm petShipperForm;
		
		petShipperForm = new PetShipperForm();
		petShipperForm.setDaysBeforeCancel(1);
		petShipperForm.setInvitationCode(invitationCode);
		
		result = createEditModelAndView(petShipperForm);
		
		result.addObject("register", true);
		
		return result;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST, params="create")
	public ModelAndView create(@Valid PetShipperForm petShipperForm, BindingResult binding){
		ModelAndView result;
		PetShipper petShipper;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(petShipperForm);
			System.out.println(binding.getAllErrors());
		}else{
			try{
				if(petShipperForm.getExpirationYear()< Calendar.getInstance().get(Calendar.YEAR)){
					result = createEditModelAndView(petShipperForm);
					result.addObject("oldYear", true);
				}
//				else if(!petSitterForm.getAcceptTermsAndConditions()){
//					result = createEditModelAndView(petSitterForm);
//					result.addObject("termsNotAccepted", true);
//				}
				else if(!petShipperForm.getPassword().equals(petShipperForm.getPasswordConfirm())){
					result = createEditModelAndView(petShipperForm,"petShipper.commit.password");
				}else{
					petShipper = petShipperService.reconstruct(petShipperForm);
					petShipperService.register(petShipper,petShipperForm.getInvitationCode());
					result = new ModelAndView("redirect:../index.do");
				}
			}catch(Throwable oops){
				result = createEditModelAndView(petShipperForm,"petShipper.commit.error");
			}
		}

		result.addObject("register", true);
		
		return result;		
	}
	
	
	// Ancillary methods-------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(PetShipperForm petShipperForm){
		ModelAndView result;
		
		result = createEditModelAndView(petShipperForm, null);
	
		return result;
	}
	
	
	protected ModelAndView createEditModelAndView(PetShipperForm petShipperForm, String message){
		ModelAndView result;
		
		result = new ModelAndView("petShipper/edit");
		result.addObject("petShipperForm", petShipperForm);
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
		PetShipper petShipper;
		if (binding.hasErrors()) {
			result = createInvitationModelAndView(invitationForm);
			
		} else {
			result = new ModelAndView("welcome/index");
			
			try {
				petShipper = petShipperService.findOneByPrincipal();
				emailService.sendToPetShipper(petShipper, invitationForm.getEmail());

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
		result.addObject("requestURI", "petCare/invite.do");
		return result;
	}
}
