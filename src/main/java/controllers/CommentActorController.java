package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Comment;
import domain.Complaint;

import services.CommentService;
import services.ComplaintService;


@Controller
@RequestMapping("/comment/actor")
public class CommentActorController extends AbstractController{
	
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private ComplaintService complaintService;
	
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public ModelAndView listAll(@RequestParam int id) {
		ModelAndView result;			
		result = new ModelAndView("comment/list");	
		result.addObject("comments", commentService.findCommentByActorId(id));
		result.addObject("requestURI","/comment/actor/list.do");		
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int id) {
		ModelAndView result;
		Comment comment;
		Complaint complaint;
		complaint = complaintService.findOne(id);
		comment = commentService.create(complaint);	
		
	
		result = new ModelAndView("comment/edit");
		result.addObject("comment", comment);
		result.addObject("requestURI", "comment/actor/edit.do");
		
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Comment comment, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(comment);
			System.out.println(binding.getAllErrors());
		} else {
			try {

				commentService.save(comment);
				
				result = new ModelAndView("redirect:list.do?id="+comment.getComplaint().getId());
			}catch (IllegalStateException e) {
				// end date before start date
				result = createEditModelAndView(comment, "commit.comment.errorCreate");
				
			} catch (Throwable oops) {

				result = createEditModelAndView(comment, "commit.comment.error");
			}
		}
		return result;
	}
	
	//auxiliary
		// method---------------------------------------------------------------

		protected ModelAndView createEditModelAndView(Comment comment) {
			ModelAndView result;

			result = createEditModelAndView(comment, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(Comment comment,
				String message) {
			ModelAndView result;

			result = new ModelAndView("comment/edit");
			result.addObject("comment", comment);
			result.addObject("message", message);
			return result;
		}
	

}
