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
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Access(AccessType.PROPERTY)
public class Trip extends DomainEntity{

	
	public Trip(){
		super();
	}
	
	private String descriptionText,startCity,endCity,distance;
	private Date moment;
	private double cost;
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescriptionText() {
		return descriptionText;
	}
	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	
	@NotBlank
	@Pattern(regexp = "^SHORT|MEDIUM|LARGE$")
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	@Min(0)
	@Max(100)
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
	
	
	//------------------------------------------
	private Vehicle vehicle;

	@ManyToOne(optional=false)
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
}
