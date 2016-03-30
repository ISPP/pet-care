package domain;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import domain.Supplier;



@Entity
@Access(AccessType.PROPERTY)
public class PetShipper extends Supplier{
	public PetShipper(){
		super();
	}
	
	
	private Collection<Vehicle> vehicles;

@OneToMany(mappedBy="petShipper")
	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}


	public void setVehicles(Collection<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

}
