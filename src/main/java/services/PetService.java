package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PetRepository;
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
		//Collection<Photo> photos;
		
		principal = customerService.getLoggedCustomer();
		Assert.notNull(principal);
		//photos = new ArrayList<Photo>();
		
		result = new Pet();
		//result.setPhotos(photos);
		if(principal.getUser().equals("PETOWNER")){
			result.setPetOwner((PetOwner) principal);
		}
		else if (principal.getUser().equals("PETSITTER")){
			result.setPetSitter((PetSitter)principal);
		}
		return result;
	}

	public Pet save(Pet pet) {
		Pet result;
		Customer principal;
		
		principal = customerService.getLoggedCustomer();
		Assert.notNull(principal);
		
		result = petRepository.save(pet);
		return result;
	}

	public void delete(Pet pet) {
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


}
