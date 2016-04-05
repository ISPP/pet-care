package forms;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

import domain.PetOwner;
import domain.Review;
import domain.Supplier;

public class BookingForm {

	// Attributes----------------------------------------------
		private Date creationMoment, arrivalMoment, departureMoment;
		private String code, status;
		
		private boolean night;
	//	private double price;
		private boolean cancelled;



		@NotNull
		@Temporal(TemporalType.TIMESTAMP)
		@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
		public Date getArrivalMoment() {
			return arrivalMoment;
		}

		public void setArrivalMoment(Date arrivalMoment) {
			this.arrivalMoment = arrivalMoment;
		}
		
		
		
		
		
		
		@NotNull
		@Temporal(TemporalType.TIMESTAMP)
		@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
		public Date getDepartureMoment() {
			return departureMoment;
		}

		public void setDepartureMoment(Date departureMoment) {
			this.departureMoment = departureMoment;
		}
		

//		@Min(0)
//		public double getPrice() {
//			return price;
//		}
//
//		public void setPrice(double price) {
//			this.price = price;
//		}

	

		@NotBlank
		@Pattern(regexp="^(PENDING|ACCEPTED|REJECTED)$")
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public boolean isNight() {
			return night;
		}

		public void setNight(boolean night) {
			this.night = night;
		}

		


		public boolean isCancelled() {
			return cancelled;
		}

		public void setCancelled(boolean cancelled) {
			this.cancelled = cancelled;
		}

		

		// Relantionships------------------------------------------
		
		private Supplier supplier;
		
		public Supplier getSupplier() {
			return supplier;
		}

		public void setSupplier(Supplier supplier) {
			this.supplier = supplier;
		}

		



}