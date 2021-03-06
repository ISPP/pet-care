package controllers.petOwner;

import java.util.Calendar;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.PetOwner;
import domain.Review;
import forms.PetOwnerForm;
import services.PetOwnerService;

@Controller
@RequestMapping("/petOwner/petOwner")
public class PetOwnerPetOwnerController extends AbstractController{
	
	// Constructors -----------------------------------------------------------

	public PetOwnerPetOwnerController() {
		super();
	}
	
	// Services -----------------------------------------------------------------
	
	@Autowired
	private PetOwnerService petOwnerService;
	
	// Saving ---------------------------------------------------------------
	
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
				}else if(!petOwnerForm.getPassword().equals(petOwnerForm.getPasswordConfirm())){
					result = createEditModelAndView(petOwnerForm,"petOwner.commit.password");
				}else{
					petOwner = petOwnerService.reconstructEdited(petOwnerForm);
					petOwnerService.saveEdited(petOwner);
					result = new ModelAndView("redirect:/../Petcare/petOwner/petOwner/displayOwn.do");
				}
			}catch(Throwable oops){
				result = createEditModelAndView(petOwnerForm,"petOwner.commit.error");
			}
		}

		return result;		
	}

	// Displaying ------------------------------------------------------------
	
	@RequestMapping(value = "/displayOwn", method = RequestMethod.GET)
	public ModelAndView displayOwn() {
		ModelAndView result;
		PetOwner petOwner;
		String requestURI;
		Collection<Review> reviews;
		
		petOwner = petOwnerService.findOneByPrincipal();
		reviews = petOwnerService.findReviews(petOwner.getId());
		requestURI = "petOwner/petOwner/display.do?petOwnerId="+petOwner.getId();

		result = new ModelAndView("petOwner/display");
		result.addObject("petOwner", petOwner);
		result.addObject("reviews", reviews);
		result.addObject("requestURI", requestURI);
		result.addObject("principal", true);

		return result;
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int petOwnerId) {
		ModelAndView result;
		PetOwner petOwner;
		String requestURI;
		Collection<Review> reviews;
		boolean principal;
		
		principal = petOwnerService.isPrincipal(petOwnerId);
		petOwner = petOwnerService.findOne(petOwnerId);
		reviews = petOwnerService.findReviews(petOwnerId);
		requestURI = "petOwner/petOwner/display.do?petOwnerId="+petOwnerId;

		result = new ModelAndView("petOwner/display");
		result.addObject("petOwner", petOwner);
		result.addObject("reviews", reviews);
		result.addObject("requestURI", requestURI);
		result.addObject("principal", principal);

		return result;
	}
	
	// Editing -----------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int petOwnerId){
	ModelAndView result;
		PetOwner petOwner;
		PetOwnerForm petOwnerForm;
			
		petOwner = petOwnerService.findOne(petOwnerId);
		petOwnerForm = petOwnerService.fragment(petOwner);
		
		Assert.notNull(petOwner);
		result = new ModelAndView("petOwner/profile");
		result.addObject("petOwnerForm", petOwnerForm);
				
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
			
		result = new ModelAndView("petOwner/profile");
		result.addObject("petOwnerForm", petOwnerForm);
		result.addObject("message", message);
			
		return result;
	}
}