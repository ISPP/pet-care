package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;


@Entity
@Access(AccessType.PROPERTY)
public class Company extends Supplier{
	public Company(){
		super();
	}
	
	private String tic;
	private Double pricePerDay;

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getTic() {
		return tic;
	}

	public void setTic(String tic) {
		this.tic = tic;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	

}
