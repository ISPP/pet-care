package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PhotoRepository;
import domain.Pet;
import domain.Photo;
import forms.PhotoForm;

@Service
@Transactional
public class PhotoService {
	
	// Managed repository -----------------------------------------------------
	
		@Autowired
		private PhotoRepository photoRepository;
		
		// Supporting services-----------------------------------------------------
		
		// Constructors -----------------------------------------------------------
		
		public PhotoService() {
			super();
		}

		// Simple CRUD methods-------------------------------------------------------------

		public Photo findOne(Integer id) {
			return photoRepository.findOne(id);
		}

		public Collection<Photo> findAll() {
			return photoRepository.findAll();
		}
		
		public Photo save(Photo photo){
			Assert.notNull(photo);
			photo.setAvatar(false);
			return photoRepository.save(photo);
		}
		public Photo saveAvatar(Photo photo){
			Assert.notNull(photo);
			return photoRepository.save(photo);
		}

		// Other business methods -------------------------------------------------
		public Photo reconstruct (PhotoForm photoForm){
			Assert.notNull(photoForm);
			Photo result;
			
			result = new Photo();
			
			result.setContent(photoForm.getContent());
			result.setId(photoForm.getId());
			
			return result;
		}

		public void changeAvatar(Pet pet, Photo photo) {
			Assert.notNull(pet);
			Assert.notNull(photo);
			for(Photo p : pet.getPhotos()){
				if(p.getAvatar()==true){
					p.setAvatar(false);
					saveAvatar(p);
					break;
				}
			}
			photo.setAvatar(true);
			saveAvatar(photo);
		}
	
}
