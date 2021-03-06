package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.*;


@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
	@Query("select t from Trip t where t.startCity like %?1% and t.endCity like %?2%")
	Collection<Trip> findTripsByStartAndEndCities(String startCity, String endCity);
	
	@Query("select t from Trip t where t.startCity like %?1% and t.endCity like %?2% and t.moment >= ?3")
	Collection<Trip> findTripsByStartAndEndCitiesMoment(String startCity, String endCity, Date moment);
	
	@Query("select t from Trip t where (t.vehicle.petShipper.id=?1 and t.registrations.size>0)")
	Collection<Trip> findTripsWithRegistrations(Integer id);

	@Query("select t.trips from Vehicle t where t.petShipper.id = ?1")
	Collection<Trip> findAllPrincipal(int petShipperId);
	
}
