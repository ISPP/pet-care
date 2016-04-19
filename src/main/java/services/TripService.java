package services;

import java.util.Collection;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.PetShipper;
import domain.PetSitter;
import domain.Trip;

import repositories.TripRepository;


@Service
@Transactional
public class TripService {
	public TripService(){
		super();
	}
	
	@Autowired
	private TripRepository tripRepository;
	@Autowired
	private PetShipperService petShipperService;
	
	public Trip create() {
		Trip result;
		result = new Trip();
		return result;
	}

	public Trip save(Trip trip) {
		Trip result;
		result = tripRepository.saveAndFlush(trip);
		return result;
	}

	public void delete(Trip trip) {
		tripRepository.delete(trip);
	}

	public Collection<Trip> findAll() {
		Collection<Trip> result;
		result = tripRepository.findAll();
		return result;
	}

	public Trip findOne(Integer id) {
		Trip result;
		result = tripRepository.findOne(id);
		return result;
	}

	public Collection<Trip> findTripsStartAndEndCities(String startCity, String endCity) {
		Collection<Trip> result;
		result = tripRepository.findTripsByStartAndEndCities(startCity,endCity);
		return result;
	}
	
	public Collection<Trip> findTripsWithRegistrations() {
		Collection<Trip> result;
		int id;
		id=petShipperService.findOneByPrincipal().getId();
		result = tripRepository.findTripsWithRegistrations(id);;
		return result;
	}


}
