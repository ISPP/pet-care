package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ComplaintService;
import controllers.AbstractController;
import domain.Complaint;
import forms.ComplaintForm;

@Controller
@RequestMapping("/complaint/administrator")
public class ComplaintAdministratorController extends AbstractController {

	@Autowired
	private ComplaintService complaintService;

	@RequestMapping(value = "/listComplaintAdminNotSolved", method = RequestMethod.GET)
	public ModelAndView listComplaintByPetSitterIdNotSolved() {
		ModelAndView result;

		Collection<Complaint> complaints = complaintService
				.findComplaintByAdministratorIdAndNotResolution();

		result = new ModelAndView("complaint/listComplaintAdminNotSolved");
		result.addObject("complaints", complaints);
		result.addObject("requestURI",
				"complaint/administrator/listComplaintAdminNotSolved.do");
		result.addObject("toAssign", false);
		result.addObject("toSolve", false);
		return result;
	}

	@RequestMapping(value = "/listWithoutAdmin", method = RequestMethod.GET)
	public ModelAndView listComplaintsWithoutAdmin() {
		ModelAndView result;

		Collection<Complaint> complaints = complaintService
				.findComplaintNotAdministrator();

		result = new ModelAndView("complaint/listComplaintToAssign");
		result.addObject("complaints", complaints);
		result.addObject("requestURI",
				"complaint/administrator/listWithoutAdmin.do");
		result.addObject("toAssign", true);
		result.addObject("toSolve", false);
		return result;
	}

	@RequestMapping(value = "/listToSolve", method = RequestMethod.GET)
	public ModelAndView listComplaintsToSolve() {
		ModelAndView result;

		Collection<Complaint> complaints = complaintService
				.findComplaintByAdministratorIdAndNotResolution();

		result = new ModelAndView("complaint/listComplaintAssigned");
		result.addObject("complaints", complaints);
		result.addObject("requestURI",
				"complaint/administrator/listWithoutAdmin.do");
		result.addObject("toAssign", false);
		result.addObject("toSolve", true);
		return result;
	}

	@RequestMapping(value = "/assign", method = RequestMethod.GET)
	public ModelAndView assign(@RequestParam("id") int id) {
		ModelAndView result;
		Complaint complaint;
		complaint = complaintService.findOne(id);

		complaintService.assignAdminToComplaint(complaint);
		result = new ModelAndView("welcome/index");

		return result;

	}
	
	
	@RequestMapping(value = "/solve", method = RequestMethod.GET)
	public ModelAndView solve(@RequestParam("id") int id) {
		ModelAndView result;
		Complaint complaint;
		complaint = complaintService.findOne(id);
		ComplaintForm complaintForm;
		complaintForm=complaintService.solveComplaintForm(complaint);
		result = new ModelAndView("complaint/solve");
		result.addObject("complaintForm",complaintForm);
		result.addObject("requestURI", "complaint/administrator/solve.do");

		return result;

	}
	

	@RequestMapping(value = "/solve", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ComplaintForm complaintForm,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(complaintForm);
			System.out.println(binding.getAllErrors());
		} else {
			try {
				Complaint complaint;
				complaint = complaintService.reconstructToSolve(complaintForm);
				complaintService.solveComplaint(complaint);
				result = new ModelAndView("redirect:listToSolve.do");
			
			}catch(IllegalArgumentException e){
				result = createEditModelAndView(complaintForm,
						"complaint.error.admin");
			
			} catch (Throwable oops) {

				result = createEditModelAndView(complaintForm,
						"complaint.error.operation");
				//oops.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	protected ModelAndView createEditModelAndView(ComplaintForm complaintForm) {
		ModelAndView result;

		result = createEditModelAndView(complaintForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(ComplaintForm complaintForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("complaint/solve");
		result.addObject("complaintForm", complaintForm);
		result.addObject("requestURI", "complaint/administrator/solve.do");
		result.addObject("message", message);
		
		return result;
	}

	
	
	
	
	

}