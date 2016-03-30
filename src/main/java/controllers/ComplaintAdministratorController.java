package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Complaint;

import services.ComplaintService;


@Controller
@RequestMapping("/complaint/administrator")
public class ComplaintAdministratorController extends AbstractController{
	
	@Autowired
	private ComplaintService complaintService;
	
	
	
	@RequestMapping(value = "/listComplaintAdminNotSolved", method = RequestMethod.GET)
	public ModelAndView listComplaintByPetSitterIdNotSolved() {
		ModelAndView result;
		

		Collection<Complaint> complaints = complaintService.findComplaintByAdministratorIdAndNotResolution();

		result = new ModelAndView("complaint/listComplaintAdminNotSolved");
		result.addObject("complaints", complaints);
		result.addObject("requestURI", "complaint/administrator/listComplaintAdminNotSolved.do");

		return result;
	}
	
	

}
