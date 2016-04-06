package controllers.petSitter;

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
import domain.PetSitter;
import domain.Review;
import forms.PetSitterForm;
import services.PetSitterService;

@Controller
@RequestMapping("/petSitter/petSitter")
public class PetSitterPetSitterController extends AbstractController{
	
	// Constructors -----------------------------------------------------------

	public PetSitterPetSitterController() {
		super();
	}
	
	// Services -----------------------------------------------------------------
	
	@Autowired
	private PetSitterService petSitterService;
	
	// Saving ---------------------------------------------------------------
	
	@RequestMapping(value="/create", method = RequestMethod.POST, params="create")
	public ModelAndView create(@Valid PetSitterForm petSitterForm, BindingResult binding){
		ModelAndView result;
		PetSitter petSitter;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(petSitterForm);
		}else{
			try{
				if(petSitterForm.getExpirationYear()< Calendar.getInstance().get(Calendar.YEAR)){
					result = createEditModelAndView(petSitterForm);
					result.addObject("oldYear", true);
				}else if(!petSitterForm.getPassword().equals(petSitterForm.getPasswordConfirm())){
					result = createEditModelAndView(petSitterForm,"petSitter.commit.password");
				}else{
					petSitter = petSitterService.reconstructEdited(petSitterForm);
					petSitterService.saveEdited(petSitter);
					result = new ModelAndView("redirect:/../Petcare/petSitter/petSitter/displayOwn.do");
				}
			}catch(Throwable oops){
				result = createEditModelAndView(petSitterForm,"petSitter.commit.error");
			}
		}

		return result;		
	}

	// Displaying ------------------------------------------------------------
	
	@RequestMapping(value = "/displayOwn", method = RequestMethod.GET)
	public ModelAndView displayOwn() {
		ModelAndView result;
		PetSitter sitter;
		String requestURI;
		Collection<Review> reviews;
		
		sitter = petSitterService.findOneByPrincipal();
		reviews = petSitterService.findReviews(sitter.getId());
		requestURI = "petSitter/petSitter/display.do?petSitterId="+sitter.getId();

		result = new ModelAndView("petSitter/display");
		result.addObject("sitter", sitter);
		result.addObject("reviews", reviews);
		result.addObject("requestURI", requestURI);
		result.addObject("principal", true);

		return result;
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int petSitterId) {
		ModelAndView result;
		PetSitter petSitter;
		String requestURI;
		Collection<Review> reviews;
		boolean principal;
		
		principal = petSitterService.isPrincipal(petSitterId);
		petSitter = petSitterService.findOne(petSitterId);
		reviews = petSitterService.findReviews(petSitterId);
		requestURI = "petSitter/petSitter/display.do?petSitterId="+petSitterId;

		result = new ModelAndView("petSitter/display");
		result.addObject("petSitter", petSitter);
		result.addObject("reviews", reviews);
		result.addObject("requestURI", requestURI);
		result.addObject("principal", principal);

		return result;
	}
	
	// Editing -----------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int petSitterId){
	ModelAndView result;
		PetSitter petSitter;
		PetSitterForm petSitterForm;
			
		petSitter = petSitterService.findOne(petSitterId);
		petSitterForm = petSitterService.fragment(petSitter);
		
		Assert.notNull(petSitter);
		result = new ModelAndView("petSitter/profile");
		result.addObject("petSitterForm", petSitterForm);
				
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
			
		result = new ModelAndView("petSitter/profile");
		result.addObject("petSitterForm", petSitterForm);
		result.addObject("message", message);
			
		return result;
	}
}