package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Pet;

import repositories.PetRepository;

@Service
@Transactional
public class PetService {
	public PetService(){
		super();
	}
	
	
	@Autowired
	private PetRepository petRepository;
	
	
	public Pet create() {
		Pet result;
		result = new Pet();
		return result;
	}

	public Pet save(Pet pet) {
		Pet result;
		result = petRepository.saveAndFlush(pet);
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
