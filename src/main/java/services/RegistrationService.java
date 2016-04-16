package services;

import java.sql.Date;
import java.util.Collection;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.PetOwner;
import domain.PetSitter;
import domain.Registration;
import domain.Trip;

import repositories.RegistrationRepository;
import repositories.TripRepository;


@Service
@Transactional
public class RegistrationService {
	public RegistrationService(){
		super();
	}
	

	@Autowired
	private RegistrationRepository registrationRepository;
	@Autowired
	private TripService tripService;
	
	@Autowired
	private PetOwnerService petOwnerService;
	
	
	public Trip create() {
		Trip result;
		result = new Trip();
		return result;
	}

	public Registration save(Registration registration) {
		Registration result;
		result = registrationRepository.save(registration);
		return result;
	}

	public void delete(Registration registration) {
		registrationRepository.delete(registration);
	}

	public Collection<Registration> findAll() {
		Collection<Registration> result;
		result = registrationRepository.findAll();
		return result;
	}

	public Registration findOne(Integer id) {
		Registration result;
		result = registrationRepository.findOne(id);
		return result;
	}
	
	public Registration registerToTrip(Trip trip) {
		Registration result;
		PetOwner petOwner;
		petOwner=petOwnerService.findOneByPrincipal();
		result=new Registration();
		Date current=new Date(System.currentTimeMillis()-1000);
		Assert.isTrue(current.before(trip.getMoment()));
		result.setMoment(current);
		result.setTrip(trip);
		result.setPetOwner(petOwner);
		result = save(result);
		return result;
	}


}
