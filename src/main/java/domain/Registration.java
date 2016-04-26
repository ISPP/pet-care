package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Access(AccessType.PROPERTY)
public class Registration extends DomainEntity{

	
	public Registration(){
		super();
	}
	
	
	private Date moment;
	private Boolean payByPetOwner, payByAdmin;

	
	
	public Boolean getPayByPetOwner() {
		return payByPetOwner;
	}
	public void setPayByPetOwner(Boolean payByPetOwner) {
		this.payByPetOwner = payByPetOwner;
	}
	public Boolean getPayByAdmin() {
		return payByAdmin;
	}
	public void setPayByAdmin(Boolean payByAdmin) {
		this.payByAdmin = payByAdmin;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")	
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	
	
	
	
	//------------------------------------------
	private Trip trip;

	@ManyToOne(optional=false)
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	
	private PetOwner petOwner;

	@ManyToOne(optional=false)
	public PetOwner getPetOwner() {
		return petOwner;
	}
	public void setPetOwner(PetOwner petOwner) {
		this.petOwner = petOwner;
	}
	
	
}
