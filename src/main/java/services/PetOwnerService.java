package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.PetOwner;
import domain.Review;
import repositories.PetOwnerRepository;
import security.LoginService;

@Service
@Transactional
public class PetOwnerService {
	public PetOwnerService(){
		super();
	}
	
	@Autowired
	private PetOwnerRepository petOwnerRepository;
	
	

	public PetOwner create() {
		PetOwner result;
		result = new PetOwner();
		return result;
	}

	public PetOwner save(PetOwner petOwner) {
		PetOwner result;
		result = petOwnerRepository.saveAndFlush(petOwner);
		return result;
	}

	public void delete(PetOwner petOwner) {
		petOwnerRepository.delete(petOwner);
	}

	public Collection<PetOwner> findAll() {
		Collection<PetOwner> result;
		result = petOwnerRepository.findAll();
		return result;
	}

	public PetOwner findOne(Integer id) {
		PetOwner result;
		result = petOwnerRepository.findOne(id);
		return result;
	}

	public PetOwner findOneByPrincipal() {
		PetOwner result;
		
		result = petOwnerRepository.findOneByPrincipal(LoginService.getPrincipal().getId());
		
		return result;
	}

	public Collection<Review> findReviews(int petOwnerId) {
		Collection<Review> result;
		
		result = petOwnerRepository.findOne(petOwnerId).getReviews();
		
		return result;
	}

	public boolean isPrincipal(int petOwnerId) {
		boolean result;
		
		result = findOne(petOwnerId).getUser().equals(LoginService.getPrincipal());
				
		return result;
	}



}
