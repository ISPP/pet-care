/* CustomerController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import repositories.CustomerRepository;
import security.Authority;
import services.CustomerService;
import services.PhotoService;

import domain.Booking;
import domain.Customer;
import domain.Pet;
import domain.PetOwner;
import domain.Photo;
import forms.PhotoForm;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private PhotoService photoService;

	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	// images part

	@RequestMapping(value = "/addPhoto", method = RequestMethod.GET)
	public ModelAndView listToPay() {
		ModelAndView result;

		result = new ModelAndView("customer/addPhoto");

		result.addObject("requestURI", "customer/addPhoto.do");

		return result;
	}

	@RequestMapping(value = "/addPhoto", method = RequestMethod.POST, params = "save")
	public ModelAndView savePhoto(@RequestParam MultipartFile file) {
		ModelAndView result = null;
		byte[] byteArr = null;
		Customer customer;
		Photo photo = new Photo();

		customer = customerService.getLoggedCustomer();

		if (!file.getContentType().contains("jpeg")
				&& !file.getContentType().contains("png")
				&& !file.getContentType().contains("jpg")) {
			result = createAddPhotoModelAndView("pet.photo.wrongType");
		} else if (file.getSize() > 2097152) {
			result = createAddPhotoModelAndView("pet.photo.wrongSize");
		} else {

			if (file != null && file.getSize() > 0) {
				try {
					byteArr = file.getBytes();
					photo.setContent(byteArr);
					customerService.addPhoto(customer, photo);
					List<Authority> authorities = new ArrayList<Authority>(customer.getUser()
							.getAuthorities());
					Authority auth = authorities.get(0);
					String authority = auth.getAuthority();
					result = selectProfile(authority);

				} catch (IOException e) {
					e.printStackTrace();
					result = createAddPhotoModelAndView("pet.commit.error");
				} catch (Throwable oops) {
					result = createAddPhotoModelAndView("pet.commit.error");
				}
			} else {
				result = createAddPhotoModelAndView("pet.commit.error");
			}
		}

		return result;

	}

	@RequestMapping(value = "/viewPhoto")
	public void displayImage(@RequestParam int photoId,
			HttpServletResponse response) {
		byte[] image = photoService.findOne(photoId).getContent();

		if (image != null) {
			response.setContentType("image/jpeg");
			try {
				response.getOutputStream().write(image);
				response.getOutputStream().close();
				response.getOutputStream().flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// protected ModelAndView createAddPhotoModelAndView(Customer pet) {
	// ModelAndView result;
	// result = createAddPhotoModelAndView(pet, null);
	// return result;
	// }

	protected ModelAndView createAddPhotoModelAndView(String message) {
		ModelAndView result;
		String requestURI;

		requestURI = "customer/addPhoto.do";

		result = new ModelAndView("customer/addPhoto");

		result.addObject("message", message);
		result.addObject("requestURI", requestURI);

		return result;

	}

	@RequestMapping(value = "/selectAvatar", method = RequestMethod.GET)
	public ModelAndView selectAvatar(@RequestParam int photoId) {
		ModelAndView result = null;
		Customer pet;
		Photo photo;

		pet = customerService.getLoggedCustomer();
		photo = photoService.findOne(photoId);
		List<Authority> authorities = new ArrayList<Authority>(pet.getUser()
				.getAuthorities());
		Authority auth = authorities.get(0);
		String authority = auth.getAuthority();
		try {
			customerService.changeAvatar(pet, photo);
			// result = new ModelAndView("redirect:/pet/see.do?petId=" + petId);

			result = selectProfile(authority);

		} catch (Throwable oops) {
			result = selectPtofile(authority, "pet.commit.error");
		}

		return result;

	}

	protected ModelAndView selectProfile(String authority) {
		ModelAndView result;
		result = selectPtofile(authority, null);
		return result;
	}

	protected ModelAndView selectPtofile(String authority, String message) {

		ModelAndView result = null;

		if (authority.equals("PETOWNER")) {
			result = new ModelAndView(
					"redirect:../petOwner/petOwner/displayOwn.do");
		}else{
			if (authority.equals("PETSITTER")) {
				result = new ModelAndView(
						"redirect:../petSitter/petSitter/displayOwn.do");
			}else{
				
				if (authority.equals("PETSHIPPER")) {
					result = new ModelAndView(
							"redirect:../petShipper/petShipper/displayOwn.do");
				
			}
			}
			
		}
		result.addObject("message", message);

		return result;
	}
}