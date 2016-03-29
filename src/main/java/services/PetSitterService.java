package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.PetShipper;
import domain.PetSitter;

import repositories.PetSitterRepository;



@Service
@Transactional
public class PetSitterService {

	public PetSitterService(){
		super();
	}
	
	@Autowired
	private PetSitterRepository petSitterRepository;
	
	public PetSitter create() {
		PetSitter result;
		result = new PetSitter();
		return result;
	}

	public PetSitter save(PetSitter petSitter) {
		PetSitter result;
		result = petSitterRepository.saveAndFlush(petSitter);
		return result;
	}

	public void delete(PetSitter petSitter) {
		petSitterRepository.delete(petSitter);
	}

	public Collection<PetSitter> findAll() {
		Collection<PetSitter> result;
		result = petSitterRepository.findAll();
		return result;
	}

	public PetSitter findOne(Integer id) {
		PetSitter result;
		result = petSitterRepository.findOne(id);
		return result;
	}


}
