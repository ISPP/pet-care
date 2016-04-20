package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Supplier extends Customer {

	public Supplier() {
		super();
	}

	private double rating;
	private boolean blocked;
	private Integer daysBeforeCancel;
	private String invitationCode;

	@Min(1)
	public Integer getDaysBeforeCancel() {
		return daysBeforeCancel;
	}

	public void setDaysBeforeCancel(Integer daysBeforeCancel) {
		this.daysBeforeCancel = daysBeforeCancel;
	}

	@Min(0)
	@Max(5)
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	
	// Relationships
	private Collection<Booking> bookings;
	private Collection<Review> reviews;

	@OneToMany(mappedBy = "supplier")
	public Collection<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Collection<Booking> bookings) {
		this.bookings = bookings;
	}
	
	
	@OneToMany(mappedBy = "reviewed")
	public Collection<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Collection<Review> reviews) {
		this.reviews = reviews;
	}
	
}