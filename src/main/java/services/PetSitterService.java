package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Booking;
import domain.PetSitter;

import repositories.PetSitterRepository;

@Service
@Transactional
public class PetSitterService {

	public PetSitterService(){
		super();
	}
	
	//Managed repository ----------------------------------------------
	
	@Autowired
	private PetSitterRepository petSitterRepository;
	
	//Supporting services ----------------------------------------------
	
	@Autowired
	private BookingService bookingService;
	
	// CRUD methods
	
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

	//Other business methods
	public List<PetSitter> searchSitters(Date startDate, Date endDate, String address){
		List<PetSitter> result;
		List<PetSitter> sitters;
		List<Booking> bookings;
		Date current;
		
		current = new Date();
		Assert.isTrue(current.before(startDate));
		Assert.isTrue(startDate.before(endDate));
		
		result = new ArrayList<PetSitter>();
		sitters = new ArrayList<PetSitter>(petSitterRepository.searchSitters(address));
		
		for(PetSitter i: sitters){
			bookings = bookingService.findByDateSitter(startDate, endDate, i.getId());
			if(bookings.isEmpty()){
				result.add(i);
			}
		}
		
		return result;
	}

}
