package controllers.petOwner;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;
import services.CompanyService;
import services.PetOwnerService;
import services.PetSitterService;
import controllers.AbstractController;
import domain.Booking;
import domain.Company;
import domain.PetOwner;
import domain.PetSitter;
import forms.BookingForm;

@Controller
@RequestMapping("/booking/petOwner")
public class BookingPetOwnerController extends AbstractController {

	@Autowired
	private BookingService bookingService;
	@Autowired
	private PetOwnerService petOwnerService;
	@Autowired
	private PetSitterService petSitterService;
	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/listToPay", method = RequestMethod.GET)
	public ModelAndView listToPay() {
		ModelAndView result;

		Collection<Booking> bookings = bookingService
				.findBookingNotPayByPetOwnerId();
		result = new ModelAndView("paypal/list");
		result.addObject("bookings", bookings);
		result.addObject("requestURI", "booking/petOwner/listToPay.do");

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Booking> bookings;

		result = new ModelAndView("booking/list");
		PetOwner petOwner;
		petOwner = petOwnerService.findOneByPrincipal();
		bookings = petOwner.getBookings();

		result.addObject("bookings", bookings);
		result.addObject("requestURI", "booking/petOwner/list.do");

		return result;
	}

	@RequestMapping(value = "/listBookingCanCancelPetOwner", method = RequestMethod.GET)
	public ModelAndView listBookingCanCancelPetOwner() {
		ModelAndView result;
		Collection<Booking> bookings;

		result = new ModelAndView("booking/listBookingCanCancelPetOwner");

		bookings = bookingService.findBookingCanCancelByPetOwnerId();

		result.addObject("bookings", bookings);
		result.addObject("requestURI",
				"booking/petOwner/listBookingCanCancelPetOwner.do");

		return result;
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam("id") int id) {

		bookingService.cancelBooking(id);
		ModelAndView result;
		result = new ModelAndView("redirect:../../welcome/index.do");
		return result;
	}
	
	@RequestMapping(value = "/cancelBooking", method = RequestMethod.GET)
	public ModelAndView cancel2(@RequestParam("id") int id) {

		bookingService.cancelBooking2(id);
		ModelAndView result;
		result = new ModelAndView("redirect:../../welcome/index.do");
		return result;
	}

	// ----PetSitters booking
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView solve(@RequestParam("petSitterId") int petSitterId,
			@RequestParam(required=false) String startMoment, @RequestParam(required=false) String endMoment) {
		ModelAndView result;
		BookingForm bookingForm;
		bookingForm = bookingService.create();
		PetSitter petSitter = petSitterService.findOne(petSitterId);
		bookingForm.setSupplier(petSitter);
		try {
			if (startMoment!=null && endMoment!=null){
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			bookingForm.setStartMoment(formatter.parse(startMoment));
			bookingForm.setendMoment(formatter.parse(endMoment));
			}
		} catch (Throwable t) {
			
		}

		result = new ModelAndView("booking/edit");
		result.addObject("bookingForm", bookingForm);
		result.addObject("requestURI", "booking/petOwner/create.do");
		result.addObject("forCompany", false);

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid BookingForm bookingForm,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(bookingForm, "PetSitter");
			System.out.println(binding.getAllErrors());
		} else {
			try {
				Booking booking;
				booking = bookingService.reconstruct(bookingForm);
				bookingService.registerPetSitterBooking(booking);

				result = new ModelAndView("redirect:list.do");

			} catch (IllegalArgumentException oops) {

				result = createEditModelAndView(bookingForm,
						"booking.typebookingError", "PetSitter");
			} catch (IllegalStateException oops) {

				result = createEditModelAndView(bookingForm,
						"booking.dateError", "PetSitter");

			} catch (Throwable oops) {

				result = createEditModelAndView(bookingForm,
						"booking.error.operation", "PetSitter");

			}
		}
		return result;
	}

	// company-----------

	@RequestMapping(value = "/createForCompany", method = RequestMethod.GET)
	public ModelAndView createForCompany(
			@RequestParam("companyId") int companyId) {
		ModelAndView result;
		BookingForm bookingForm;
		bookingForm = bookingService.create();
		Company company = companyService.findOne(companyId);
		bookingForm.setSupplier(company);
		bookingForm.setNight(true);

		result = new ModelAndView("booking/edit");
		result.addObject("bookingForm", bookingForm);
		result.addObject("requestURI", "booking/petOwner/createForCompany.do");
		result.addObject("forCompany", true);

		return result;

	}

	@RequestMapping(value = "/createForCompany", method = RequestMethod.POST, params = "save")
	public ModelAndView saveForCompany(@Valid BookingForm bookingForm,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(bookingForm, "Company");
			System.out.println(binding.getAllErrors());
		} else {
			try {
				Booking booking;
				booking = bookingService.reconstruct(bookingForm);
				booking.setNight(true);
				bookingService.registerCompanyBooking(booking);
				result = new ModelAndView("redirect:listToPay.do");
			} catch (IllegalArgumentException oops) {

				result = createEditModelAndView(bookingForm,
						"booking.errorForcompany", "Company");
			} catch (IllegalStateException oops) {

				result = createEditModelAndView(bookingForm,
						"booking.dateError", "Company");

			} catch (Throwable oops) {

				result = createEditModelAndView(bookingForm,
						"booking.error.operation", "Company");

			}
		}
		return result;
	}

	// -----------ancillary

	protected ModelAndView createEditModelAndView(BookingForm bForm,
			String token) {
		ModelAndView result;

		result = createEditModelAndView(bForm, null, token);

		return result;
	}

	protected ModelAndView createEditModelAndView(BookingForm bForm,
			String message, String token) {
		ModelAndView result;

		result = new ModelAndView("booking/edit");
		result.addObject("bookingForm", bForm);
		result.addObject("message", message);
		if (token.equals("PetSitter")) {
			result.addObject("requestURI", "booking/petOwner/create.do");
			result.addObject("forCompany", false);
		} else {
			result.addObject("requestURI",
					"booking/petOwner/createForCompany.do");
			result.addObject("forCompany", true);
		}
		return result;
	}

	// for companyf

}
