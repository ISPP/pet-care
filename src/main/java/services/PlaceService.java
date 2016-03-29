package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PlaceRepository;

import domain.PetSitter;
import domain.Place;


@Service
@Transactional
public class PlaceService {

	public PlaceService(){
		super();
	}
	
	@Autowired
	private PlaceRepository placeRepository;
	
	public Place create() {
		Place result;
		result = new Place();
		return result;
	}

	public Place save(Place place) {
		Place result;
		result = placeRepository.saveAndFlush(place);
		return result;
	}

	public void delete(Place place) {
		placeRepository.delete(place);
	}

	public Collection<Place> findAll() {
		Collection<Place> result;
		result = placeRepository.findAll();
		return result;
	}

	public Place findOne(Integer id) {
		Place result;
		result = placeRepository.findOne(id);
		return result;
	}
}
