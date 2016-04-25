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

import services.CompanyService;
import domain.Company;
import forms.CompanyForm;
import forms.InvitationForm;
import forms.PetShipperForm;

@Controller
@RequestMapping("/company")
public class CompanyController extends AbstractController {
	@Autowired
	private CompanyService companyService;

	// list
	@RequestMapping(value = "/listToBook", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Company> companies;
		companies = companyService.findAll();

		result = new ModelAndView("company/list");
		result.addObject("companies", companies);
		result.addObject("toBook", true);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam String invitationCode) {
		ModelAndView result;
		CompanyForm companyForm;

		result = new ModelAndView("company/edit");
		companyForm = new CompanyForm();
		companyForm.setInvitationCode(invitationCode);
		companyForm.setDaysBeforeCancel(1);
		result.addObject("companyForm", companyForm);
		result.addObject("register", true);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "create")
	public ModelAndView create(@Valid CompanyForm companyForm,
			BindingResult binding) {
		ModelAndView result;
		Company company;

		if (binding.hasErrors()) {
			result = new ModelAndView("company/edit");
			result.addObject("companyForm", companyForm);
			result.addObject("message", null);
			result.addObject("register", true);
		} else {
			try {
				if (companyForm.getExpirationYear() < Calendar.getInstance()
						.get(Calendar.YEAR)) {
					result = new ModelAndView("company/edit");
					result.addObject("companyForm", companyForm);
					result.addObject("oldYear", true);
					result.addObject("register", true);
				} else if (!companyForm.getPassword().equals(
						companyForm.getPasswordConfirm())) {
					result = new ModelAndView("company/edit");
					result.addObject("companyForm", companyForm);
					result.addObject("message", "company.commit.password");
					result.addObject("register", true);
				} else {
					company = companyService.reconstruct(companyForm);
					companyService.register(company,companyForm.getInvitationCode());
					result = new ModelAndView("redirect:../index.do");
				}
			} catch (Throwable oops) {
				
				result=createEditModelAndView(companyForm, "company.commit.error");
				oops.printStackTrace();
				
			}
		}

		return result;
	}
	
	
	protected ModelAndView createEditModelAndView(CompanyForm companyForm){
		ModelAndView result;
		
		result = createEditModelAndView(companyForm, null);
	
		return result;
	}
	
	
	protected ModelAndView createEditModelAndView(CompanyForm companyForm, String message){
		ModelAndView result;
		
		result = new ModelAndView("company/edit");
		result.addObject("companyForm",companyForm);
		result.addObject("message", message);
		result.addObject("register", true);
		
		return result;
	}
	

}
