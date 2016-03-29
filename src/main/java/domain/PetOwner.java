package domain;

import java.util.Collection;

import javax.persistence.OneToMany;

public class PetOwner extends Customer {

	public PetOwner() {
		super();
	}

	private Collection<Booking> bookings;
	private Collection<Pet> pets;

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

}
