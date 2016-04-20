package controllers.petShipper;

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
import domain.PetShipper;
import domain.Review;
import forms.PetShipperForm;
import services.PetShipperService;

@Controller
@RequestMapping("/petShipper/petShipper")
public class PetShipperPetShipperController extends AbstractController{
	
	// Constructors -----------------------------------------------------------

	public PetShipperPetShipperController() {
		super();
	}
	
	// Services -----------------------------------------------------------------
	
	@Autowired
	private PetShipperService petShipperService;
	
	// Saving ---------------------------------------------------------------
	
	@RequestMapping(value="/create", method = RequestMethod.POST, params="create")
	public ModelAndView create(@Valid PetShipperForm petShipperForm, BindingResult binding){
		ModelAndView result;
		PetShipper petShipper;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(petShipperForm);
		}else{
			try{
				if(petShipperForm.getExpirationYear()< Calendar.getInstance().get(Calendar.YEAR)){
					result = createEditModelAndView(petShipperForm);
					result.addObject("oldYear", true);
				}else if(!petShipperForm.getPassword().equals(petShipperForm.getPasswordConfirm())){
					result = createEditModelAndView(petShipperForm,"petShipper.commit.password");
				}else{
					petShipper = petShipperService.reconstructEdited(petShipperForm);
					petShipperService.saveEdited(petShipper);
					result = new ModelAndView("redirect:/../Petcare/petShipper/petShipper/displayOwn.do");
				}
			}catch(Throwable oops){
				result = createEditModelAndView(petShipperForm,"petShipper.commit.error");
			}
		}

		return result;		
	}

	// Displaying ------------------------------------------------------------
	
	@RequestMapping(value = "/displayOwn", method = RequestMethod.GET)
	public ModelAndView displayOwn() {
		ModelAndView result;
		PetShipper shipper;
		String requestURI;
		Collection<Review> reviews;
		
		shipper = petShipperService.findOneByPrincipal();
		reviews = petShipperService.findReviews(shipper.getId());
		requestURI = "petShipper/petShipper/display.do?petShipperId="+shipper.getId();

		result = new ModelAndView("petShipper/display");
		result.addObject("shipper", shipper);
		result.addObject("reviews", reviews);
		result.addObject("requestURI", requestURI);
		result.addObject("principal", true);

		return result;
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int petShipperId) {
		ModelAndView result;
		PetShipper petShipper;
		String requestURI;
		Collection<Review> reviews;
		boolean principal;
		
		principal = petShipperService.isPrincipal(petShipperId);
		petShipper = petShipperService.findOne(petShipperId);
		reviews = petShipperService.findReviews(petShipperId);
		requestURI = "petShipper/petShipper/display.do?petShipperId="+petShipperId;

		result = new ModelAndView("petShipper/display");
		result.addObject("petShipper", petShipper);
		result.addObject("reviews", reviews);
		result.addObject("requestURI", requestURI);
		result.addObject("principal", principal);

		return result;
	}
	
	// Editing -----------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int petShipperId){
	ModelAndView result;
		PetShipper petShipper;
		PetShipperForm petShipperForm;
			
		petShipper = petShipperService.findOne(petShipperId);
		petShipperForm = petShipperService.fragment(petShipper);
		
		Assert.notNull(petShipper);
		result = new ModelAndView("petShipper/profile");
		result.addObject("petShipperForm", petShipperForm);
				
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
			
		result = new ModelAndView("petShipper/profile");
		result.addObject("petShipperForm", petShipperForm);
		result.addObject("message", message);
			
		return result;
	}
}