package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;


@Entity
@Access(AccessType.PROPERTY)
public class Place extends DomainEntity{
	
	public Place(){
		super();
	}
	
	
	private String name, description, address,city, building;
	private Boolean hasGarden, hasPatio;
	private Gps location;
	
	@Valid
	public Gps getLocation() {
		return location;
	}
	public void setLocation(Gps location) {
		this.location = location;
	}
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	@NotBlank
	@Pattern(regexp = "^FLAT|HOUSE|PETLODGE|OTHERS$")
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public Boolean getHasGarden() {
		return hasGarden;
	}
	public void setHasGarden(Boolean hasGarden) {
		this.hasGarden = hasGarden;
	}
	public Boolean getHasPatio() {
		return hasPatio;
	}
	public void setHasPatio(Boolean hasPatio) {
		this.hasPatio = hasPatio;
	}
	
	
	//----------------------
	private PetSitter petSitter;
	
	@ManyToOne(optional=false)
	public PetSitter getPetSitter() {
		return petSitter;
	}
	public void setPetSitter(PetSitter petSitter) {
		this.petSitter = petSitter;
	}
	@Override
	public String toString() {
		return "Place [name=" + name + ", description=" + description
				+ ", address=" + address + ", city=" + city + ", building="
				+ building + ", hasGarden=" + hasGarden + ", hasPatio="
				+ hasPatio + "]";
	}
	

}
