package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Vehicle;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

	@Query("select v from Vehicle v where v.petShipper.id = ?1")
	Collection<Vehicle> findByPetShipperId(int petShipperId);

}
