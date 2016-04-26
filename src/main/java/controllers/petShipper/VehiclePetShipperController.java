package controllers.petShipper;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PetShipperService;
import services.VehicleService;
import controllers.AbstractController;
import domain.Vehicle;

@Controller
@RequestMapping("/vehicle/petShipper")
public class VehiclePetShipperController extends AbstractController{

	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private PetShipperService petShipperService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		List<Vehicle> vehicles;
		int petShipperId;
		
		result = new ModelAndView("vehicle/list");
		petShipperId = petShipperService.findOneByPrincipal().getId();
		vehicles = new ArrayList<Vehicle>(vehicleService.findByPetShipperId(petShipperId));
		
		result.addObject("vehicles", vehicles);
		
		return result;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Vehicle vehicle;
		
		result = new ModelAndView("vehicle/create");
		vehicle = vehicleService.create();
		
		result.addObject("mode", "create");
		result.addObject("vehicle", vehicle);
		result.addObject("isOwner", true);
		
		return result;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST, params = "create")
	public ModelAndView create(@Valid Vehicle vehicle, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			
			result = new ModelAndView("vehicle/create");
			
			result.addObject("mode", "create");
			result.addObject("vehicle", vehicle);
			result.addObject("isOwner", true);
			
		}else{
			try{
				vehicleService.save(vehicle);
				result = new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result = new ModelAndView("vehicle/create");
				
				result.addObject("mode", "create");
				result.addObject("vehicle", vehicle);
				result.addObject("isOwner", true);
				result.addObject("message", "vehicle.commit.error");
				
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int vehicleId){
		ModelAndView result;
		Vehicle vehicle;
		
		vehicle = vehicleService.findOne(vehicleId);
		result = new ModelAndView("vehicle/edit");
		
		if(vehicle!=null && vehicleService.checkOwner(vehicle)){
			result.addObject("isOwner", true);
		}
		
		result.addObject("vehicle", vehicle);
		result.addObject("mode", "edit");
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "edit")
	public ModelAndView edit(@Valid Vehicle vehicle, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = new ModelAndView("vehicle/edit");
			
			result.addObject("vehicle", vehicle);
			result.addObject("mode", "edit");
			result.addObject("isOwner", true);
		}else{
			try{
				vehicleService.save(vehicle);
				result = new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result = new ModelAndView("vehicle/edit");
				
				result.addObject("vehicle", vehicle);
				result.addObject("mode", "edit");
				result.addObject("isOwner", true);
				result.addObject("message", "vehicle.commit.error");
			}
		}
		
		return result;
	}
}
