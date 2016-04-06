package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class PetOwner extends Customer {

	private Collection<Booking> bookings;
	private Collection<Pet> pets;
	private Collection<Review> reviews;
	
	public PetOwner() {
		super();
	}
	
	@OneToMany(mappedBy = "petOwner")
	public Collection<Pet> getPets() {
		return pets;
	}

	public void setPets(Collection<Pet> pets) {
		this.pets = pets;
	}

	@OneToMany(mappedBy = "petOwner")
	public Collection<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Collection<Booking> bookings) {
		this.bookings = bookings;
	}

	@OneToMany(mappedBy = "reviewer")
	public Collection<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Collection<Review> reviews) {
		this.reviews = reviews;
	}


}
