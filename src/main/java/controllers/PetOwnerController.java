package controllers;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PetOwnerService;

import domain.PetOwner;

import forms.PetOwnerForm;


@Controller
@RequestMapping("/petOwner")
public class PetOwnerController extends AbstractController {
	
	@Autowired
	private PetOwnerService petOwnerService;

	// Constructors -----------------------------------------------------------
	
	public PetOwnerController() {
		super();
	}
		
	
	// Edition-----------------------------------------------------------------
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		PetOwnerForm petOwnerForm;
		
		petOwnerForm = new PetOwnerForm();
		result = createEditModelAndView(petOwnerForm);
		
		result.addObject("register", true);
		
		return result;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST, params="create")
	public ModelAndView create(@Valid PetOwnerForm petOwnerForm, BindingResult binding){
		ModelAndView result;
		PetOwner petOwner;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(petOwnerForm);
		}else{
			try{
				if(petOwnerForm.getExpirationYear()< Calendar.getInstance().get(Calendar.YEAR)){
					result = createEditModelAndView(petOwnerForm);
					result.addObject("oldYear", true);
				}
//				else if(!petOwnerForm.getAcceptTermsAndConditions()){
//					result = createEditModelAndView(petOwnerForm);
//					result.addObject("termsNotAccepted", true);
//				}
				else if(!petOwnerForm.getPassword().equals(petOwnerForm.getPasswordConfirm())){
					result = createEditModelAndView(petOwnerForm,"petOwner.commit.password");
				}else{
					petOwner = petOwnerService.reconstruct(petOwnerForm);
					petOwnerService.save(petOwner);
					result = new ModelAndView("redirect:../security/login.do");
				}
			}catch(Throwable oops){
				result = createEditModelAndView(petOwnerForm,"petOwner.commit.error");
			}
		}

		result.addObject("register", true);
		
		return result;		
	}
	
	
	// Ancillary methods-------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(PetOwnerForm petOwnerForm){
		ModelAndView result;
		
		result = createEditModelAndView(petOwnerForm, null);
	
		return result;
	}
	
	
	protected ModelAndView createEditModelAndView(PetOwnerForm petOwnerForm, String message){
		ModelAndView result;
		
		result = new ModelAndView("petOwner/edit");
		result.addObject("petOwnerForm", petOwnerForm);
		result.addObject("message", message);
		
		return result;
	}
	
}