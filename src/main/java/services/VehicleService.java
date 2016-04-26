package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.VehicleRepository;
import security.Authority;
import domain.PetShipper;
import domain.Trip;
import domain.Vehicle;


@Service
@Transactional
public class VehicleService {
	public VehicleService(){
		super();
	}
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private ActorService actorService;
	
	public Vehicle create() {
		Vehicle result;
		PetShipper petShipper;
		List<Trip> trips;
		
		//Check if the actor is a PetShipper
		Authority authority = new Authority();
		authority.setAuthority(Authority.PETSHIPPER);
		Assert.isTrue(actorService.findActorByUsername().getUser().getAuthorities().contains(authority));
		result = new Vehicle();
		petShipper = (PetShipper) actorService.findActorByUserId();
		trips = new ArrayList<Trip>();
		
		result.setPetShipper(petShipper);
		result.setTrips(trips);
		
		return result;
	}

	public Vehicle save(Vehicle vehicle) {
		Vehicle result;
		result = vehicleRepository.save(vehicle);
		return result;
	}

	public void delete(Vehicle vehicle) {
		//We check if the vehicle has trips. If the vehicle as it we don't delete it
		Assert.isTrue(vehicle.getTrips().isEmpty(),"vehicle.trips.error");
		vehicleRepository.delete(vehicle);
	}

	public Collection<Vehicle> findAll() {
		Collection<Vehicle> result;
		result = vehicleRepository.findAll();
		return result;
	}

	public Vehicle findOne(Integer id) {
		Vehicle result;
		result = vehicleRepository.findOne(id);
		return result;
	}

	public Collection<Vehicle> findByPetShipperId(int petShipperId) {
		return vehicleRepository.findByPetShipperId(petShipperId);
	}

	public boolean checkOwner(Vehicle vehicle) {
		PetShipper petShipper;
		
		petShipper = (PetShipper) actorService.findActorByUserId();
		
		return petShipper.equals(vehicle.getPetShipper());
	}


}
