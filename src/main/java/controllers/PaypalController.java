package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;
import services.SupplierService;

import domain.Booking;
import domain.Company;
import domain.PetOwner;
import domain.Supplier;



@Controller
@RequestMapping("/paypal")
public class PaypalController {

	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private BookingService bookingService;
	
	
	
	
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public ModelAndView paypal(@RequestParam int id) {
		ModelAndView result;
		Booking booking = bookingService.findOne(id);
		
		
		try{
			result = new ModelAndView("paypal/paypal");
			//este tio es el que debe recibir el dinero (tenemos su email que deberá de ser el de paypal)
			PetOwner petOwner = booking.getPetOwner();
			String emailpetOwner = petOwner.getEmail();
			
			//este tio es el que paga (este introduce su email que debe de estar conectado con paypal)
			//este tio no es problema
			//creo que ni lo necesitamos
			Supplier supplier = booking.getSupplier();
			
			
			//Calculamos las comisiones que nos vamos a llevar
			Double comision;
			comision = booking.getPrice()*10/100;//esto es lo que nosotros nos llevamos
			
			//esto es lo que tiene que pagar el supplier
			//que es el precio del booking+lo que nosotros nos llevamos de comision
			Double pago = booking.getPrice()+comision; 
			booking.setPagadoPetOwner(true);
			bookingService.save(booking);
			result.addObject("booking", booking);
			result.addObject("emailpetOwner", emailpetOwner);
			result.addObject("comision", comision);
			result.addObject("pago", pago);
			result.addObject("requestURI", "paypal/pay.do");
		
			
		}catch (Exception oops){
			result = new ModelAndView("welcome/index");
			
			result.addObject("message", "commit.operation");
			
		}
		
		return result;
		

	}
	
	
	@RequestMapping(value = "/payAdmin", method = RequestMethod.GET)
	public ModelAndView paypalAdmin(@RequestParam int id) {
		ModelAndView result;
		Booking booking = bookingService.findOne(id);
		
		
		try{
			result = new ModelAndView("paypal/paypalAdmin");
			//este tio es el que debe recibir el dinero (tenemos su email que deberá de ser el de paypal)
			PetOwner petOwner = booking.getPetOwner();
			String emailpetOwner = petOwner.getEmail();
			
			//este tio es el que paga (este introduce su email que debe de estar conectado con paypal)
			//este tio no es problema
			//creo que ni lo necesitamos
			Supplier supplier = booking.getSupplier();
			
			
			//Calculamos las comisiones que nos vamos a llevar
			Double comision;
			comision = booking.getPrice()*10/100;//esto es lo que nosotros nos llevamos
			
			//esto es lo que tiene que pagar el supplier
			//que es el precio del booking+lo que nosotros nos llevamos de comision
			Double pago = booking.getPrice()+comision; 
			booking.setPagadoAdmin(true);
			bookingService.save(booking);
			result.addObject("booking", booking);
			result.addObject("emailpetOwner", emailpetOwner);
			result.addObject("comision", comision);
			result.addObject("pago", pago);
			result.addObject("requestURI", "paypal/payAdmin.do");
		
			
		}catch (Exception oops){
			result = new ModelAndView("welcome/index");
			
			result.addObject("message", "commit.operation");
			
		}
		
		return result;
		

	}
	
	@RequestMapping(value = "/paySuccessful", method = RequestMethod.GET)
	public ModelAndView paypalsuccessful(@RequestParam int id) {
		ModelAndView result;

		Booking booking = bookingService.findOne(id);
		try{
			
			
			booking.setPagadoPetOwner(true);
			bookingService.save(booking);
			result = new ModelAndView("welcome/index");
			
		}catch (Exception oops){
			result = new ModelAndView("welcome/index");
			
			result.addObject("message", "commit.operation");
			
		}
		return result;
		

	}


}
