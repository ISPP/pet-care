package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Vehicle;

import repositories.VehicleRepository;


@Service
@Transactional
public class VehicleService {
	public VehicleService(){
		super();
	}
	
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public Vehicle create() {
		Vehicle result;
		result = new Vehicle();
		return result;
	}

	public Vehicle save(Vehicle vehicle) {
		Vehicle result;
		result = vehicleRepository.saveAndFlush(vehicle);
		return result;
	}

	public void delete(Vehicle vehicle) {
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


}
