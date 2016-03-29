package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity
@Access(AccessType.PROPERTY)
public class Supplier extends Customer{

	
	public Supplier(){
		super();
	}
	
	private Double rating;
	private Boolean blocked;
	
	@Min(0)
	@Max(5)
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public Boolean getBlocked() {
		return blocked;
	}
	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}
}
