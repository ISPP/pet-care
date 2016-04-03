package controllers.petOwner;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PetService;
import controllers.AbstractController;
import domain.Pet;

@Controller
@RequestMapping("/pet/petOwner")
public class PetPetOwnerController extends AbstractController {

	@Autowired
	private PetService petService;
	
	
	// Methods

	// Listing -----------------------------------------------------------------
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Pet> pets;
		
		pets = petService.findAll();
		
		result = new ModelAndView("pet/list");
		result.addObject("pets", pets);
		result.addObject("requestURI", "pet/petOwner/list.do");

		return result;
	}

	//Displaying
	
	@RequestMapping(value = "/see", method = RequestMethod.GET)
	public ModelAndView see(@RequestParam int petId) {
		ModelAndView result;
		Pet pet;
		
		String requestURI;
		
		requestURI	= "pet/petOwner/see.do?petId=" + petId;		
		pet = petService.findOne(petId);
	
		result= new ModelAndView("pet/see");
		result.addObject("pet",pet);
		result.addObject("requestURI", requestURI);

		return result;
	}
	
	//Creating-----------------------------------------------	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Pet pet;
		
		pet = petService.create();
	
		result = new ModelAndView("pet/create");
		result.addObject("pet", pet);

		return result;
	}

	//Editing-------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int petId){
		ModelAndView result;
		Pet pet;
		
		pet = petService.findOne(petId);
		
		result = createEditModelAndView(pet);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Pet pet, BindingResult binding){
		ModelAndView result;

		if(binding.hasErrors()){
			result = createEditModelAndView(pet);	
		}else{
			try{
				petService.save(pet);
				result = new ModelAndView("redirect:/pet/petOwner/list.do");

			}catch (Throwable oops){
				result = createEditModelAndView(pet, "pet.commit.error");
			}
		}
		return result;
	}

	// Deleting ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Pet pet, BindingResult binding) {
		ModelAndView result;
		
		try {
			petService.delete(pet);
			result = new ModelAndView("redirect:/pet/petOwner/list.do"); 	
		} catch (Throwable oops) {
			result = new ModelAndView("pet/edit");
			result.addObject("pet", pet);
			result.addObject("message","pet.commit.error");
		}
		return result;
	}
	

	// Ancillary methods-----------------------------------------------------------

	protected ModelAndView createEditModelAndView(Pet pet) {
		ModelAndView result;
		result = createEditModelAndView(pet,null);
		return result;
	}
	
	protected  ModelAndView createEditModelAndView(Pet pet, String message) {
		ModelAndView result;
		String requestURI;
			
		requestURI = "pet/petOwner/edit.do";
	
		result = new ModelAndView("pet/edit");
		result.addObject("pet",pet);
		result.addObject("message",message);
		result.addObject("requestURI", requestURI);

		return result;
	
	}

}
