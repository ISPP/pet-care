package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Registration;
import domain.Trip;
import domain.Vehicle;
import forms.TripForm;
import repositories.TripRepository;
import security.LoginService;


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
	@Autowired
	private VehicleService vehicleService;
	
	public Trip create() {
		Trip result;
		Collection<Registration> registrations;
		
		result = new Trip();
		registrations = new ArrayList<Registration>();
		result.setRegistrations(registrations);
		
		return result;
	}

	public Trip save(Trip trip) {
		Trip result;
		if(!isOwner(trip)){
			throw new IllegalAccessError();
		}
		result = null;
		Date date = new Date();
		date.setDate(date.getDate()+7);
		Assert.state(trip.getMoment().after(date));
		
		// Viaje corto menos de 20 euros, medio menos de 30 euros
		
		if(trip.getDistance().equals("SHORT")){
			Assert.isTrue(trip.getCost() < 20);
		}else if(trip.getDistance().equals("MEDIUM")){
			Assert.isTrue(trip.getCost() < 30);
		}
		
		result = tripRepository.saveAndFlush(trip);
		
		return result;
	}

	public void delete(TripForm tripForm) {
		Date date = new Date();
		date.setDate(date.getDate()+7);
		Assert.state(tripForm.getMoment().after(date));
		Assert.isTrue(isOwner(findOne(tripForm.getId())));
		tripRepository.delete(findOne(tripForm.getId()));
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
	
	public Collection<Trip> findTripsStartAndEndCitiesMoment(String startCity, String endCity, Date moment) {
		Collection<Trip> result;
		result = tripRepository.findTripsByStartAndEndCitiesMoment(startCity,endCity, moment);
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

	public Trip findOneOwned(int tripId) {
		Trip result;
		
		result = tripRepository.findOne(tripId);
		Assert.isTrue(result.getVehicle().getPetShipper().getUser().equals(LoginService.getPrincipal()));
		
		return result;
	}

	public Boolean isOwner(Trip trip) {
		return trip.getVehicle().getPetShipper().getUser().equals(LoginService.getPrincipal());
	}

	public Collection<Vehicle> getVehiclesOwner() {
		Collection<Vehicle> vehicles;
		
		vehicles = petShipperService.findOneByPrincipal().getVehicles();
				
		return vehicles;
	}

	public Collection<Trip> findAllPrincipal() {
		Collection<Trip> trips;
		
		trips = tripRepository.findAllPrincipal(petShipperService.findOneByPrincipal().getId());
		
		return trips;
	}

	public TripForm fragment(Trip trip) {
		Assert.notNull(trip);
		
		TripForm result;

		result = new TripForm();
		
		result.setId(trip.getId());
		result.setCost(trip.getCost());
		result.setDescriptionText(trip.getDescriptionText());;
		result.setDistance(trip.getDistance());
		result.setEndCity(trip.getEndCity());
		result.setStartCity(trip.getStartCity());
		result.setMoment(trip.getMoment());
		result.setVehicleId(trip.getVehicle().getId());

		return result;
	}

	public Trip reconstruct(TripForm tripForm) {
		Assert.notNull(tripForm);
		Trip result;
		
		result = create();
		
		result.setId(tripForm.getId());
		result.setCost(tripForm.getCost());
		result.setDescriptionText(tripForm.getDescriptionText());;
		result.setDistance(tripForm.getDistance());
		result.setEndCity(tripForm.getEndCity());
		result.setStartCity(tripForm.getStartCity());
		result.setMoment(tripForm.getMoment());
		result.setVehicle(vehicleService.findOne(tripForm.getVehicleId()));
		
		return result;
	}

	public boolean isDeletable(Trip trip) {
		boolean result;
		Date date = new Date();
		
		date.setDate(date.getDate()+7);
		
		result = trip.getMoment().after(date) && isOwner(trip);
		
		return result;
	}

	public Collection<Trip> findAllByShipper(int petShipperId) {
		Collection<Trip> trips;
		
		trips = tripRepository.findAllPrincipal(petShipperId);
		
		return trips;
	}
	
}
