package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.*;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

	Collection<Vehicle> findByPetShipperId(int petShipperId);

}
