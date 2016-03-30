package domain;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;



@Access(AccessType.PROPERTY)
@Embeddable
public class Gps {
	public Gps(){
		super();
	}
	
	//Attributes----------------------------------------------

	private double longitude, latitude;
	
	
	@Digits(integer=2,fraction=50)
	@Min(value=-90)
	@Max(value=90)
	public double getLongitude() {

		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	

	@Digits(integer=2,fraction=50)
	@Min(value=-180)
	@Max(value=180)
	public double getLatitude() {
		return this.latitude ;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
