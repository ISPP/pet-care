package controllers.administrator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SupplierService;
import controllers.AbstractController;
import domain.Supplier;

@Controller
@RequestMapping("/supplier/administrator")
public class SupplierAdministratorController extends AbstractController{
	
	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping(value="/blockSitter", method = RequestMethod.POST)
	public ModelAndView block(int supplierId){
		ModelAndView result;
		Supplier supplier;
		
		result = new ModelAndView("redirect:list.do");
		supplier = supplierService.findOne(supplierId);
		
		supplierService.blockSupplier(supplier);
		
		return result;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		List<Supplier> suppliers;
				
		result = new ModelAndView("supplier/list");
		suppliers = new ArrayList<Supplier>(supplierService.findAll());
		
		result.addObject("requestURI", "supplier/administrator/list.do");
		result.addObject("suppliers", suppliers);
		
		return result;
	}

}