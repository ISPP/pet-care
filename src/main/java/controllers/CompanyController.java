package controllers;

import java.util.Calendar;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CompanyService;
import domain.Company;
import forms.CompanyForm;

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
	public ModelAndView create() {
		ModelAndView result;
		CompanyForm companyForm;

		result = new ModelAndView("company/create");
		companyForm = new CompanyForm();

		result.addObject("companyForm", companyForm);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "create")
	public ModelAndView create(@Valid CompanyForm companyForm,
			BindingResult binding) {
		ModelAndView result;
		Company company;

		if (binding.hasErrors()) {
			result = new ModelAndView("company/create");
			result.addObject("companyForm", companyForm);
			result.addObject("message", null);
		} else {
			try {
				if (companyForm.getExpirationYear() < Calendar.getInstance()
						.get(Calendar.YEAR)) {
					result = new ModelAndView("company/create");
					result.addObject("companyForm", companyForm);
					result.addObject("oldYear", true);
				} else if (!companyForm.getPassword().equals(
						companyForm.getPasswordConfirm())) {
					result = new ModelAndView("company/create");
					result.addObject("companyForm", companyForm);
					result.addObject("message", "company.commit.password");
				} else {
					company = companyService.reconstruct(companyForm);
					companyService.save(company);//TODO: test this method and create views
					result = new ModelAndView("redirect:../security/login.do");
				}
			} catch (Throwable oops) {
				result = new ModelAndView("company/create");
				result.addObject("companyForm", companyForm);
				result.addObject("message", "company.commit.error");
			}
		}

		return result;
	}
}
