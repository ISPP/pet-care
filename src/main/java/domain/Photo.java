package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)

public class Photo extends DomainEntity{
	
	private String picture;
	private Boolean avatar;
	
	public Photo(){
		super();
	}
	
	@SafeHtml
	@NotBlank
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}


	public Boolean getAvatar() {
		return avatar;
	}

	public void setAvatar(Boolean avatar) {
		this.avatar = avatar;
	}
	
}
