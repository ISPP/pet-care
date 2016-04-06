/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SupplierService;
import domain.Supplier;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------
	
	public AdministratorController() {
		super();
	}
		
	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping(value="/dashboard",method = RequestMethod.GET)
	public ModelAndView dashboard(){
		ModelAndView result;
		Supplier supplierWithMoreBookings = new ArrayList<Supplier>(supplierService.findSupplierWithMoreBookings()).get(0);
		Supplier supplierWithZeroBookings = new ArrayList<Supplier>(supplierService.findSupplierWithZeroBookings()).get(0);
		
		result = new ModelAndView("administrator/dashboard");
		result.addObject("supplierWithMoreBookings", supplierWithMoreBookings);
		result.addObject("supplierBookingsName", supplierWithMoreBookings.getBookings().size());
		result.addObject("supplierWithZeroBookings", supplierWithZeroBookings);
		result.addObject("requestURI", "administrator/dashboard.do");
		
		return result;
	}
	
}