package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PetRepository;
import security.Authority;
import domain.Booking;
import domain.Customer;
import domain.Pet;
import domain.PetOwner;
import domain.PetSitter;
import domain.Photo;

@Service
@Transactional
public class PetService {
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private CustomerService customerService;
	
	//Constructor----------------------------------
	public PetService(){
		super();
	}
		
	public Pet create() {
		Pet result;
		Customer principal;
		Collection<Photo> photos;
		
		principal = customerService.getLoggedCustomer();
		Assert.notNull(principal);
		
		photos = new ArrayList<Photo>();		
		result = new Pet();
		
		for(Authority a : principal.getUser().getAuthorities() ){
			if (a.getAuthority().equals("PETOWNER")){
				result.setPetOwner((PetOwner)principal);
			}else if (a.getAuthority().equals("PETSITTER")){
				result.setPetSitter((PetSitter)principal);
			}
		}
		
		result.setPhotos(photos);		
		
		return result;
	}

	public Pet save(Pet pet) {
		Pet result;
		Customer principal;
		
		principal = customerService.getLoggedCustomer();
		Assert.notNull(principal);
		
		if(pet.getId()!=0){
			isOwner(pet);
		}
		
		result = petRepository.save(pet);
		return result;
	}

	public void delete(Pet pet) {
		Assert.notNull(pet);
		Assert.isTrue(isOwner(pet));
		hasCurrentBookings(pet);
		petRepository.delete(pet);
	}

	public Collection<Pet> findAll() {
		Collection<Pet> result;
		result = petRepository.findAll();
		return result;
	}

	public Pet findOne(Integer id) {
		Pet result;
		result = petRepository.findOne(id);
		return result;
	}
	
	public boolean isOwner(Pet pet){
		Customer principal;
		Boolean isOwner = false;
		
		principal = customerService.getLoggedCustomer();
		Assert.notNull(principal);
		
		for(Authority a : principal.getUser().getAuthorities() ){
			if (a.getAuthority().equals("PETOWNER")){
				isOwner = pet.getPetOwner().equals(principal);
			}else if (a.getAuthority().equals("PETSITTER")){
				isOwner =pet.getPetSitter().equals(principal);
			}
		}
		return isOwner;
		
	}
	
	private void hasCurrentBookings(Pet pet){
		Collection<Booking> bookings;
		
		bookings = findCurrentBookingsByPet(pet.getId());
		
		Assert.isTrue(bookings.isEmpty());
	}
	
	public Collection<Booking> findCurrentBookingsByPet(int petId){
		Collection<Booking> result;
		
		result = petRepository.findCurrentBookingsByPet(petId);
		
		return result;
		
	}

	public Collection<Pet> findMyPets() {
		Collection<Pet> result;
		Customer principal;

		result = new ArrayList<Pet>();
		principal = customerService.getLoggedCustomer();
		Assert.notNull(principal);
		
		for(Authority a : principal.getUser().getAuthorities() ){
			if (a.getAuthority().equals("PETOWNER")){
				result = petRepository.findOwnerMyPets(principal.getId());
			}else if (a.getAuthority().equals("PETSITTER")){
				result = petRepository.findSitterMyPets(principal.getId());
			}
		}		
		return result;
	}

	public void addPhoto(Pet pet, Photo photo) {
		Assert.notNull(pet);
		Assert.notNull(photo);
		
		Collection<Photo> photos;
		
		if(pet.getPhotos()!=null){
			photos = pet.getPhotos();
		}
		else{
			photos = new ArrayList<Photo>();
		}
		Assert.isTrue(isOwner(pet));
		
		photos.add(photo);
		pet.setPhotos(photos);
		
		petRepository.save(pet);
	}

}
