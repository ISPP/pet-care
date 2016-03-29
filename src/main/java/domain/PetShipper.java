package domain;

import java.util.Collection;

import javax.persistence.OneToMany;

public class PetShipper {
	
	
	private Collection<Vehicle> vehicles;

	@OneToMany(mappedBy="petShipper")
	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Collection<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

}
