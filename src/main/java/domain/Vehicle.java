package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;


@Entity
@Access(AccessType.PROPERTY)
public class Vehicle extends DomainEntity{
	
	
	public Vehicle(){
		super();
	}

	
	private String title, description, size;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@NotBlank
	@Pattern(regexp = "^XL|L|M|S$")
	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}
	
	
	//----------------------------------
	private PetShipper petShipper;
	
	@ManyToOne(optional=false)
	public PetShipper getPetShipper() {
		return petShipper;
	}


	public void setPetShipper(PetShipper petShipper) {
		this.petShipper = petShipper;
	}


	private Collection<Trip> trips;

	



	@OneToMany(mappedBy="vehicle")
	public Collection<Trip> getTrips() {
		return trips;
	}


	public void setTrips(Collection<Trip> trips) {
		this.trips = trips;
	}
	
}
