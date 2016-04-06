package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Access(AccessType.PROPERTY)

public class Photo extends DomainEntity{
	
	private Boolean avatar;
	private byte[] content;
	
	public Photo(){
		super();
	}
	
	public Boolean getAvatar() {
		return avatar;
	}

	public void setAvatar(Boolean avatar) {
		this.avatar = avatar;
	}

	@Lob
	@Column(length=2097152)
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
	
}
