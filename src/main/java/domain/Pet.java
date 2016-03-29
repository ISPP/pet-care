package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;


@Entity
@Access(AccessType.PROPERTY)
public class Pet extends DomainEntity{
	public Pet(){
		super();
	}
	
	private String name, description,breed,kind;

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	@NotBlank
	@Pattern(regexp = "^DOG|CAT|BIRD|OTHER$")
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	//--------------------------
	private PetOwner petOwner;

	@ManyToOne(optional=false)
	public PetOwner getPetOwner() {
		return petOwner;
	}

	public void setPetOwner(PetOwner petOwner) {
		this.petOwner = petOwner;
	}
	

}
