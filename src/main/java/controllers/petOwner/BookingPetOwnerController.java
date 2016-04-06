package controllers.petOwner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;
import services.PetOwnerService;
import services.PetSitterService;
import services.ReviewService;
import controllers.AbstractController;
import domain.Booking;
import domain.Complaint;
import domain.PetOwner;
import domain.PetSitter;
import domain.Review;
import forms.BookingForm;
import forms.ComplaintForm;

@Controller
@RequestMapping("/booking/petOwner")
public class BookingPetOwnerController extends AbstractController{
	
	@Autowired
	private BookingService bookingService;
	@Autowired
	private PetOwnerService petOwnerService;
	@Autowired
	private PetSitterService petSitterService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Booking> bookings;
		
		result = new ModelAndView("booking/list");
		PetOwner petOwner;
		petOwner=petOwnerService.findOneByPrincipal();
		bookings=petOwner.getBookings();
		
		result.addObject("bookings",bookings);
		result.addObject("requestURI", "booking/petOwner/list.do");
		
		return result;
	}
	
	
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView solve(@RequestParam("petSitterId") int petSitterId) {
		ModelAndView result;
		BookingForm bookingForm;
		bookingForm=bookingService.create();
		PetSitter petSitter = petSitterService.findOne(petSitterId);
		bookingForm.setSupplier(petSitter);
		
		result = new ModelAndView("booking/edit");
		result.addObject("bookingForm",bookingForm);
		result.addObject("requestURI", "booking/petOwner/create.do");

		return result;

	}
	

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid BookingForm bookingForm,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(bookingForm);
			System.out.println(binding.getAllErrors());
		} else {
			try {
				Booking booking;
				booking = bookingService.reconstruct(bookingForm);
				bookingService.registerPetSitterBooking(booking);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {

				result = createEditModelAndView(bookingForm,
						"booking.error.operation");
				
			}
		}
		return result;
	}
	

	protected ModelAndView createEditModelAndView(BookingForm bForm) {
		ModelAndView result;

		result = createEditModelAndView(bForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(BookingForm bForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("booking/edit");
		result.addObject("bookingForm", bForm);
		result.addObject("message", message);
		result.addObject("requestURI", "booking/petOwner/create.do");
		return result;
	}

}
