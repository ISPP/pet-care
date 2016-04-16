package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CompanyService;
import services.PetSitterService;

import domain.Company;
import domain.PetSitter;
@Controller
@RequestMapping("/company")
public class CompanyController extends AbstractController {
	@Autowired
	private CompanyService companyService;
	//list
		@RequestMapping(value="/listToBook", method=RequestMethod.GET)
		public ModelAndView list(){
			ModelAndView result;
			
			Collection<Company> companies;
			companies=companyService.findAll();
			
			result = new ModelAndView("company/list");
			result.addObject("companies",companies);
			result.addObject("toBook", true);
			
			return result;
		}
}
