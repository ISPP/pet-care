package controllers.petShipper;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.VehicleService;
import controllers.AbstractController;
import domain.Vehicle;

@Controller
@RequestMapping("/vehicle/petShipper")
public class VehiclePetShipperController extends AbstractController{

	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		List<Vehicle> vehicles;
		int petShipperId;
		
		result = new ModelAndView("vehicle/list");
		petShipperId = LoginService.getPrincipal().getId();
		vehicles = new ArrayList<Vehicle>(vehicleService.findByPetShipperId(petShipperId));
		
		result.addObject("vehicles", vehicles);
		
		return result;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Vehicle vehicle;
		List<String> sizes;
		
		result = new ModelAndView("vehicle/create");
		vehicle = vehicleService.create();
		sizes = new ArrayList<String>();
		
		sizes.add("XL");
		sizes.add("L");
		sizes.add("M");
		sizes.add("S");
		
		result.addObject("mode", "create");
		result.addObject("vehicle", vehicle);
		result.addObject("isOwner", true);
		
		return result;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST, params = "create")
	public ModelAndView create(@Valid Vehicle vehicle, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			List<String> sizes;
			
			result = new ModelAndView("vehicle/create");
			sizes = new ArrayList<String>();
			
			sizes.add("XL");
			sizes.add("L");
			sizes.add("M");
			sizes.add("S");
			
			result.addObject("mode", "create");
			result.addObject("vehicle", vehicle);
			result.addObject("isOwner", true);
			result.addObject("sizes", sizes);
			
		}else{
			try{
				vehicleService.save(vehicle);
				result = new ModelAndView("redirect:/list.do");
			}catch(Throwable oops){
				List<String> sizes;
				
				result = new ModelAndView("vehicle/create");
				sizes = new ArrayList<String>();
				
				sizes.add("XL");
				sizes.add("L");
				sizes.add("M");
				sizes.add("S");
				
				result.addObject("mode", "create");
				result.addObject("vehicle", vehicle);
				result.addObject("isOwner", true);
				result.addObject("sizes", sizes);
				result.addObject("message", "vehicle.commit.error");
				
			}
		}
		
		return result;
	}
}
