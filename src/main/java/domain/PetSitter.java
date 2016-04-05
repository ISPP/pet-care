package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity
@Access(AccessType.PROPERTY)
public class PetSitter extends Supplier{
	
	
	public PetSitter(){
		super();
		
	}
	
	private Double priceNight, priceHour;

	
	@Min(0)
	@Max(10)
	public Double getPriceNight() {
		return priceNight;
	}

	public void setPriceNight(Double priceNight) {
		this.priceNight = priceNight;
	}

	
	@Min(0)
	@Max(5)
	public Double getPriceHour() {
		return priceHour;
	}

	public void setPriceHour(Double priceHour) {
		this.priceHour = priceHour;
	}
	
	//--------------------------------
	private Collection<Place>places;
	private Collection<Pet> pets;

	@OneToMany(mappedBy="petSitter")
	public Collection<Pet> getPets() {
		return pets;
	}

	public void setPets(Collection<Pet> pets) {
		this.pets = pets;
	}

	@OneToMany(mappedBy="petSitter")
	public Collection<Place> getPlaces() {
		return places;
	}

	public void setPlaces(Collection<Place> places) {
		this.places = places;
	}
	
	
	

}
