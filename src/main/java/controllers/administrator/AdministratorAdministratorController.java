package controllers.administrator;

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
import domain.Administrator;
import forms.AdministratorForm;
import services.AdministratorService;

@Controller
@RequestMapping("/administrator/administrator")
public class AdministratorAdministratorController extends AbstractController{
	
	// Constructors -----------------------------------------------------------

	public AdministratorAdministratorController() {
		super();
	}
	
	// Services -----------------------------------------------------------------
	
	@Autowired
	private AdministratorService administratorService;
	
	// Saving ---------------------------------------------------------------
	
	@RequestMapping(value="/create", method = RequestMethod.POST, params="create")
	public ModelAndView create(@Valid AdministratorForm administratorForm, BindingResult binding){
		ModelAndView result;
		Administrator administrator;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(administratorForm);
		}else{
			try{
				if(!administratorForm.getPassword().equals(administratorForm.getPasswordConfirm())){
					result = createEditModelAndView(administratorForm,"administrator.commit.password");
				}else{
					administrator = administratorService.reconstructEdited(administratorForm);
					administratorService.saveEdited(administrator);
					result = new ModelAndView("redirect:/../Petcare/administrator/administrator/displayOwn.do");
				}
			}catch(Throwable oops){
				result = createEditModelAndView(administratorForm,"administrator.commit.error");
			}
		}

		return result;		
	}

	// Displaying ------------------------------------------------------------
	
	@RequestMapping(value = "/displayOwn", method = RequestMethod.GET)
	public ModelAndView displayOwn() {
		ModelAndView result;
		Administrator administrator;
		String requestURI;
		
		administrator = administratorService.findOneByPrincipal();
		requestURI = "administrator/administrator/display.do?administratorId="+administrator.getId();

		result = new ModelAndView("administrator/display");
		result.addObject("administrator", administrator);
		result.addObject("requestURI", requestURI);
		result.addObject("principal", true);

		return result;
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int administratorId) {
		ModelAndView result;
		Administrator administrator;
		String requestURI;
		boolean principal;
		
		principal = administratorService.isPrincipal(administratorId);
		administrator = administratorService.findOne(administratorId);
		requestURI = "administrator/administrator/display.do?administratorId="+administratorId;

		result = new ModelAndView("administrator/display");
		result.addObject("administrator", administrator);
		result.addObject("requestURI", requestURI);
		result.addObject("principal", principal);

		return result;
	}
	
	// Editing -----------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int administratorId){
	ModelAndView result;
		Administrator administrator;
		AdministratorForm administratorForm;
			
		administrator = administratorService.findOne(administratorId);
		administratorForm = administratorService.fragment(administrator);
		
		Assert.notNull(administrator);
		result = new ModelAndView("administrator/profile");
		result.addObject("administratorForm", administratorForm);
				
		return result;
	}
	
	// Ancillary methods-------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(AdministratorForm administratorForm){
		ModelAndView result;
			
		result = createEditModelAndView(administratorForm, null);
		
		return result;
	}
		
		
	protected ModelAndView createEditModelAndView(AdministratorForm administratorForm, String message){
		ModelAndView result;
			
		result = new ModelAndView("administrator/profile");
		result.addObject("administratorForm", administratorForm);
		result.addObject("message", message);
			
		return result;
	}
}