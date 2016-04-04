package controllers.petOwner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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
		result = createEditModelAndView(petOwnerForm);
				
		return result;
	}
}