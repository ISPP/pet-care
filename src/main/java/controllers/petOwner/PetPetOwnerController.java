package controllers.petOwner;

import java.io.IOException;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import services.PetService;
import services.PhotoService;
import controllers.AbstractController;
import domain.Pet;
import domain.Photo;
import forms.PhotoForm;

@Controller
@RequestMapping("/pet/petOwner")
public class PetPetOwnerController extends AbstractController {

	@Autowired
	private PetService petService;

	@Autowired
	private PhotoService photoService;

	// Methods

	// Listing -----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Pet> pets;

		pets = petService.findMyPets();

		result = new ModelAndView("pet/list");
		result.addObject("pets", pets);
		result.addObject("requestURI", "pet/petOwner/list.do");

		return result;
	}

	// Displaying

	@RequestMapping(value = "/see", method = RequestMethod.GET)
	public ModelAndView see(@RequestParam int petId) {
		ModelAndView result;
		Pet pet;
		Boolean isOwner;

		String requestURI;

		requestURI = "pet/petOwner/see.do?petId=" + petId;
		pet = petService.findOne(petId);
		isOwner = petService.isOwner(pet);

		result = new ModelAndView("pet/see");
		result.addObject("pet", pet);
		result.addObject("requestURI", requestURI);
		result.addObject("isOwner", isOwner);

		return result;
	}

	// Creating-----------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Pet pet;

		pet = petService.create();

		result = new ModelAndView("pet/create");
		result.addObject("pet", pet);
		result.addObject("isOwner", true);

		return result;
	}

	// Editing-------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int petId) {
		ModelAndView result;
		Pet pet;
		Boolean isOwner;
		Boolean deleteable;

		pet = petService.findOne(petId);
		isOwner = petService.isOwner(pet);
		//We check if the pet has bookings. If it has,we can't delete it for now
		deleteable = petService.findCurrentBookingsByPet(petId).isEmpty();

		result = createEditModelAndView(pet);
		result.addObject("isOwner", isOwner);
		result.addObject("deleteable",deleteable);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Pet pet, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(pet);
			result.addObject("isOwner", true);
		} else {
			try {
				petService.save(pet);
				result = new ModelAndView("redirect:/pet/petOwner/list.do");

			} catch (Throwable oops) {
				result = createEditModelAndView(pet, "pet.commit.error");
				result.addObject("isOwner", true);
			}
		}
		return result;
	}

	// Deleting ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Pet pet, BindingResult binding) {
		ModelAndView result;

		try {
			petService.delete(pet);
			result = new ModelAndView("redirect:/pet/petOwner/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(pet, "pet.commit.error");
		}
		return result;
	}

	// Add photo
	// ----------------------------------------------------------------

	@RequestMapping(value = "/addPhoto", method = RequestMethod.GET)
	public ModelAndView addPhoto(@RequestParam int petId) {
		ModelAndView result;
		PhotoForm photoForm;
		Pet pet;
		Boolean isOwner;

		photoForm = new PhotoForm();

		photoForm.setPetId(petId);

		pet = petService.findOne(petId);
		isOwner = petService.isOwner(pet);

		result = new ModelAndView("pet/addPhoto");

		result.addObject("photoForm", photoForm);
		result.addObject("isOwner", isOwner);

		return result;

	}

	@RequestMapping(value = "/addPhoto", method = RequestMethod.POST, params = "add")
	public ModelAndView savePhoto(@Valid PhotoForm photoForm,
			@RequestParam MultipartFile file, BindingResult binding) {
		ModelAndView result;
		byte[] byteArr = null;
		Pet pet;
		Photo photo;
		//Photo savedPhoto;
		
		pet = petService.findOne(photoForm.getPetId());
		
		if (!file.getContentType().contains("jpeg") && !file.getContentType().contains("png") && !file.getContentType().contains("jpg")) {
			result = createAddPhotoModelAndView(pet, "pet.photo.wrongType");
		} else if (file.getSize() > 2097152) {
			result = createAddPhotoModelAndView(pet, "pet.photo.wrongSize");
		} else {

			if (file != null && file.getSize() > 0) { // comprobamos que no sea
														// null y tamaño > 0
				try {
					byteArr = file.getBytes(); // así pasamos de MultipartFile a
												// byte[]
					photoForm.setContent(byteArr); // asignamos el archivo
													// subido al atributo

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (binding.hasErrors()) {
				result = createEditModelAndView(pet);
			} else {
				try {

					photo = photoService.reconstruct(photoForm);
					//savedPhoto = photoService.save(photo);
				//	petService.addPhoto(pet, savedPhoto);
					petService.addPhoto(pet, photo);
					result = new ModelAndView("redirect:/pet/see.do?petId="
							+ photoForm.getPetId());

				} catch (Throwable oops) {
					result = createEditModelAndView(pet, "pet.commit.error");
				}
			}
		}
		return result;

	}

	// Select avatar
	// ----------------------------------------------------------------

	@RequestMapping(value = "/selectAvatar", method = RequestMethod.GET)
	public ModelAndView selectAvatar(@RequestParam int petId, int photoId) {
		ModelAndView result;
		Pet pet;
		Photo photo;

		pet = petService.findOne(petId);
		photo = photoService.findOne(photoId);
		try {
			photoService.changeAvatar(pet, photo);
			result = new ModelAndView("redirect:/pet/see.do?petId=" + petId);

		} catch (Throwable oops) {
			result = createEditModelAndView(pet, "pet.commit.error");
		}

		return result;

	}

	// Ancillary
	// methods-----------------------------------------------------------

	protected ModelAndView createEditModelAndView(Pet pet) {
		ModelAndView result;
		result = createEditModelAndView(pet, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Pet pet, String message) {
		ModelAndView result;
		String requestURI;
		Boolean isOwner;

		if (pet.getId() != 0) {
			isOwner = petService.isOwner(pet);
		} else {
			isOwner = true;
		}
		requestURI = "pet/petOwner/edit.do";

		result = new ModelAndView("pet/edit");
		result.addObject("pet", pet);
		result.addObject("message", message);
		result.addObject("requestURI", requestURI);
		result.addObject("isOwner", isOwner);

		return result;

	}
	
	protected ModelAndView createAddPhotoModelAndView(Pet pet) {
		ModelAndView result;
		result = createEditModelAndView(pet, null);
		return result;
	}
	
	protected ModelAndView createAddPhotoModelAndView(Pet pet, String message) {
		ModelAndView result;
		String requestURI;
		
		requestURI = "pet/petOwner/addPhoto.do";

		result = new ModelAndView("pet/addPhoto");
		result.addObject("pet", pet);
		result.addObject("message", message);
		result.addObject("requestURI", requestURI);

		return result;

	}

}
