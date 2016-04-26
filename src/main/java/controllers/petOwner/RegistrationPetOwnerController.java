package controllers.petOwner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RegistrationService;

import controllers.AbstractController;


@Controller
@RequestMapping("/registration/petOwner")
public class RegistrationPetOwnerController extends AbstractController{
	
	@Autowired
	private RegistrationService registrationService;
	
	@RequestMapping(value = "/cancelRegistration", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam("id") int id) {

		registrationService.cancelRegistration(id);
		ModelAndView result;
		result = new ModelAndView("redirect:../../welcome/index.do");
		return result;
	}

}
