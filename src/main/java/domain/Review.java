package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Access(AccessType.PROPERTY)
public class Review extends DomainEntity{
	private double rating;
	private String description;
	private Date creationMoment;

	@NotNull
	@Range(min=0,max=5)
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@SafeHtml
	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getCreationMoment() {
		return creationMoment;
	}

	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}

	// ------------------

	private Booking booking;
	private Supplier reviewed;
	private PetOwner reviewer;

	@OneToOne
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@ManyToOne(optional=false)
	public Supplier getReviewed() {

		return reviewed;
	}

	public void setReviewed(Supplier reviewed) {
		this.reviewed = reviewed;
	}
	@ManyToOne(optional=false)
	public PetOwner getReviewer() {
		return reviewer;
	}

	public void setReviewer(PetOwner reviewer) {
		this.reviewer = reviewer;
	}

}
