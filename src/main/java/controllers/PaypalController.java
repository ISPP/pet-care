package controllers;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;
import services.PetOwnerService;
import services.RegistrationService;
import services.SupplierService;

import domain.Booking;
import domain.Company;
import domain.PetOwner;
import domain.Registration;
import domain.Supplier;



@Controller
@RequestMapping("/paypal")
public class PaypalController {

	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private PetOwnerService petOwnerService;
	@Autowired
	private RegistrationService registrationService;
	
	@RequestMapping(value = "/listPay", method = RequestMethod.GET)
	public ModelAndView listToPay() {
		ModelAndView result;

		Collection<Booking> bookings = bookingService.findBookingPayByPetOwnerId();
		result = new ModelAndView("paypal/listPay");
		result.addObject("bookings", bookings);
		result.addObject("requestURI", "paypal/listPay.do");

		return result;
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Booking> bookings = bookingService.findBookinByPetOwnerId();
		result = new ModelAndView("booking/list");
		result.addObject("bookings", bookings);
		result.addObject("requestURI", "paypal/list.do");

		return result;
	}
	
	
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
			booking.setUpdateMoment(new Date(System.currentTimeMillis() - 1000));
			booking.setPayByPetOwner(true);			
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
			booking.setPayByAdmin(true);
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
	
	@RequestMapping(value = "/payRegistration", method = RequestMethod.GET)
	public ModelAndView payRegistration(@RequestParam int id) {
		ModelAndView result;
		Registration registration = registrationService.findOne(id);
		
		
		try{
			result = new ModelAndView("paypal/paypalRegistration");
					
			//Calculamos las comisiones que nos vamos a llevar
			Double comision;
			comision = registration.getTrip().getCost()*10/100;//esto es lo que nosotros nos llevamos
			
			//esto es lo que tiene que pagar el supplier
			//que es el precio del booking+lo que nosotros nos llevamos de comision
			Double pago = registration.getTrip().getCost()+comision; 
			
			registration.setPayByPetOwner(true);			
			registrationService.save(registration);
			result.addObject("registration", registration);
			result.addObject("pago", pago);
			result.addObject("requestURI", "paypal/payRegistration.do");
		
			
		}catch (Exception oops){
			result = new ModelAndView("welcome/index");
			
			result.addObject("message", "commit.operation");
			
		}

		
		return result;
		

	}
	
	@RequestMapping(value = "/payAdminRegistration", method = RequestMethod.GET)
	public ModelAndView paypalAdminRegistration(@RequestParam int id) {
		ModelAndView result;
		Registration registration = registrationService.findOne(id);
		
		
		try{
			result = new ModelAndView("paypal/paypalAdminRegistration");
			registration.setPayByAdmin(true);
			registrationService.save(registration);
			result.addObject("registration", registration);
			result.addObject("requestURI", "paypal/payAdminRegistration.do");		
			
		}catch (Exception oops){
			result = new ModelAndView("welcome/index");			
			result.addObject("message", "commit.operation");			
		}
		
		return result;
		

	}
	
	/*@RequestMapping(value = "/paySuccessful", method = RequestMethod.GET)
	public ModelAndView paypalsuccessful() {
		ModelAndView result;
		PetOwner petOwner = petOwnerService.getLogged();
		Booking booking = bookingService.findBookingLastUpdate(petOwner);
		try{
			
			
			booking.setPayByPetOwner(true);
			bookingService.save(booking);
			petOwnerService.save(petOwner);
			result = new ModelAndView("redirect:list.do");
			
		}catch (Exception oops){
			result = new ModelAndView("redirect:../../welcome/index.do");
			
			result.addObject("message", "commit.operation");
			
		}
		return result;
		

	}*/


}
