package controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PetService;
import services.PhotoService;
import domain.Pet;
import domain.Photo;

@Controller
@RequestMapping("/pet")
public class PetController extends AbstractController {

	@Autowired
	private PetService petService;
	
	@Autowired
	private PhotoService photoService;
	
	
	// Methods

	
	//Displaying
	
	@RequestMapping(value = "/see", method = RequestMethod.GET)
	public ModelAndView see(@RequestParam int petId) {
		ModelAndView result;
		Pet pet;
		Integer numberOfPhotos;
		String requestURI;
		Photo avatar = null;
		
		requestURI	= "pet/see.do?petId=" + petId;		
		pet = petService.findOne(petId);
		
		if(pet.getPhotos()!=null){
			numberOfPhotos = pet.getPhotos().size();
		}
		else{
			numberOfPhotos=0;
		}
		for(Photo p: pet.getPhotos()){
			if(p.getAvatar()){
				avatar = p;
				break;
			}
		}
		result= new ModelAndView("pet/see");
		result.addObject("pet",pet);
		result.addObject("requestURI", requestURI);
		result.addObject("numberOfPhotos",numberOfPhotos);
		result.addObject("avatar",avatar);
		
		try{
			//This may throw a NullPointerException
			Boolean isOwner = petService.isOwner(pet);
			result.addObject("isOwner", isOwner);
		}catch(Throwable oops){
			//If thrown we know there is no principal
		}

		return result;
	}
	
	@RequestMapping(value = "/displayPhoto")
	public void displayImage(@RequestParam int photoId, HttpServletResponse response) {
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
	
}
