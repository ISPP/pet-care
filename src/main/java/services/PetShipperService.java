package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Pet;
import domain.PetShipper;

import repositories.PetShipperRepository;


@Service
@Transactional
public class PetShipperService {
	public PetShipperService(){
		super();
	}
	
	@Autowired
	private PetShipperRepository petShipperRepository;
	

	public PetShipper create() {
		PetShipper result;
		result = new PetShipper();
		return result;
	}

	public PetShipper save(PetShipper petShipper) {
		PetShipper result;
		result = petShipperRepository.saveAndFlush(petShipper);
		return result;
	}

	public void delete(PetShipper petShipper) {
		petShipperRepository.delete(petShipper);
	}

	public Collection<PetShipper> findAll() {
		Collection<PetShipper> result;
		result = petShipperRepository.findAll();
		return result;
	}

	public PetShipper findOne(Integer id) {
		PetShipper result;
		result = petShipperRepository.findOne(id);
		return result;
	}


}
