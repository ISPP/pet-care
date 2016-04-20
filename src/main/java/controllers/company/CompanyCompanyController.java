package controllers.company;

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
import domain.Company;
import domain.Review;
import forms.CompanyForm;
import services.CompanyService;

@Controller
@RequestMapping("/company/company")
public class CompanyCompanyController extends AbstractController{
	
	// Constructors -----------------------------------------------------------

	public CompanyCompanyController() {
		super();
	}
	
	// Services -----------------------------------------------------------------
	
	@Autowired
	private CompanyService companyService;
	
	// Saving ---------------------------------------------------------------
	
	@RequestMapping(value="/create", method = RequestMethod.POST, params="create")
	public ModelAndView create(@Valid CompanyForm companyForm, BindingResult binding){
		ModelAndView result;
		Company company;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(companyForm);
		}else{
			try{
				if(companyForm.getExpirationYear()< Calendar.getInstance().get(Calendar.YEAR)){
					result = createEditModelAndView(companyForm);
					result.addObject("oldYear", true);
				}else if(!companyForm.getPassword().equals(companyForm.getPasswordConfirm())){
					result = createEditModelAndView(companyForm,"company.commit.password");
				}else{
					company = companyService.reconstructEdited(companyForm);
					companyService.saveEdited(company);
					result = new ModelAndView("redirect:/../Petcare/company/company/displayOwn.do");
				}
			}catch(Throwable oops){
				result = createEditModelAndView(companyForm,"company.commit.error");
			}
		}

		return result;		
	}

	// Displaying ------------------------------------------------------------
	
	@RequestMapping(value = "/displayOwn", method = RequestMethod.GET)
	public ModelAndView displayOwn() {
		ModelAndView result;
		Company company;
		String requestURI;
		Collection<Review> reviews;
		
		company = companyService.findOneByPrincipal();
		reviews = companyService.findReviews(company.getId());
		requestURI = "company/company/display.do?companyId="+company.getId();

		result = new ModelAndView("company/display");
		result.addObject("company", company);
		result.addObject("reviews", reviews);
		result.addObject("requestURI", requestURI);
		result.addObject("principal", true);

		return result;
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int companyId) {
		ModelAndView result;
		Company company;
		String requestURI;
		Collection<Review> reviews;
		boolean principal;
		
		principal = companyService.isPrincipal(companyId);
		company = companyService.findOne(companyId);
		reviews = companyService.findReviews(companyId);
		requestURI = "company/company/display.do?companyId="+companyId;

		result = new ModelAndView("company/display");
		result.addObject("company", company);
		result.addObject("reviews", reviews);
		result.addObject("requestURI", requestURI);
		result.addObject("principal", principal);

		return result;
	}
	
	// Editing -----------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int companyId){
	ModelAndView result;
		Company company;
		CompanyForm companyForm;
			
		company = companyService.findOne(companyId);
		companyForm = companyService.fragment(company);
		
		Assert.notNull(company);
		result = new ModelAndView("company/profile");
		result.addObject("companyForm", companyForm);
				
		return result;
	}
	
	// Ancillary methods-------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(CompanyForm companyForm){
		ModelAndView result;
			
		result = createEditModelAndView(companyForm, null);
		
		return result;
	}
		
		
	protected ModelAndView createEditModelAndView(CompanyForm companyForm, String message){
		ModelAndView result;
			
		result = new ModelAndView("company/profile");
		result.addObject("companyForm", companyForm);
		result.addObject("message", message);
			
		return result;
	}
}