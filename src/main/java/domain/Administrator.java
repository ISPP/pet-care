package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor{
	
	private Collection<Complaint> complaints;
	@OneToMany(mappedBy="administrator")
	public Collection<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Collection<Complaint> complaints) {
		this.complaints = complaints;
	}

}
