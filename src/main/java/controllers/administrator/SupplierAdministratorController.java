package controllers.administrator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PetSitterService;
import controllers.AbstractController;
import domain.PetSitter;

@Controller
@RequestMapping("/supplier/administrator")
public class SupplierAdministratorController extends AbstractController{
	
	@Autowired
	private PetSitterService petSitterService;
	
	@RequestMapping(value="/blockSitter", method = RequestMethod.POST)
	public ModelAndView block(int supplierId){//TODO:Add PetShipper in 3rd sprint
		ModelAndView result;
		PetSitter petSitter;
		
		result = new ModelAndView("redirect:list.do");
		petSitter = petSitterService.findOne(supplierId);
		
		if(petSitter!=null){
			petSitterService.block(petSitter);
		}
		
		return result;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){//TODO:Add PetShipper in 3rd sprint
		ModelAndView result;
		List<PetSitter> petSitters;
		
		result = new ModelAndView("supplier/list");
		petSitters = new ArrayList<PetSitter>(petSitterService.findAll());
		
		result.addObject("requestURI", "supplier/administrator/list.do");
		result.addObject("petSitters", petSitters);
		
		return result;
	}

}