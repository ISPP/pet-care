package domain;
import javax.persistence.Entity;
import javax.persistence.Access;
import javax.persistence.AccessType;
@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity{
	private String name;
	private String surname;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
