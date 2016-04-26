package forms;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import domain.Supplier;

public class BookingForm {

	// Attributes----------------------------------------------
		private Date  startMoment, endMoment, startHour, endHour;
	
		private boolean cancelled;
		private boolean night;
	//	private double price;
		



		@NotNull
		@Temporal(TemporalType.TIMESTAMP)
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		public Date getStartMoment() {
			return startMoment;
		}

		public void setStartMoment(Date startMoment) {
			this.startMoment = startMoment;
		}
		
		
		
		
		
		
		@NotNull
		@Temporal(TemporalType.TIMESTAMP)
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		public Date getendMoment() {
			return endMoment;
		}

		public void setendMoment(Date endMoment) {
			this.endMoment = endMoment;
		}
		
		
		

		
		@Temporal(TemporalType.TIMESTAMP)
		@DateTimeFormat(pattern = "HH:mm")
		public Date getStartHour() {
			return startHour;
		}

		public void setStartHour(Date startHour) {
			this.startHour = startHour;
		}

		@Temporal(TemporalType.TIMESTAMP)
		@DateTimeFormat(pattern = "HH:mm")
		public Date getEndHour() {
			return endHour;
		}

		public void setEndHour(Date endHour) {
			this.endHour = endHour;
		}
		
		

//		@Min(0)
//		public double getPrice() {
//			return price;
//		}
//
//		public void setPrice(double price) {
//			this.price = price;
//		}

	

	

		public boolean isNight() {
			return night;
		}

		public void setNight(boolean night) {
			this.night = night;
		}

		



		

		// Relantionships------------------------------------------
		
		private Supplier supplier;
		
		public Supplier getSupplier() {
			return supplier;
		}

		public void setSupplier(Supplier supplier) {
			this.supplier = supplier;
		}

		public boolean isCancelled() {
			return cancelled;
		}

		public void setCancelled(boolean cancelled) {
			this.cancelled = cancelled;
		}

		



}