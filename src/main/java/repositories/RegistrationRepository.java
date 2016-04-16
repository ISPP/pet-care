package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.*;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
	@Query("select r from Registration r where r.trip.id=?1")
	Collection<Registration> findRegistrationsByTrip(Integer id);

}
