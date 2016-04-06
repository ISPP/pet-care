package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Supplier;

@Repository
public interface AdministratorRepository extends
		JpaRepository<Administrator, Integer> {
	@Query("select a from Administrator a where a.user.username=?1")
	Administrator findAdminByUsername(String username);

	// A list of suppliers who have more than 10 reviews which rating is 0 to
	// decide whether or not he/she is providing a bad service.
	@Query("select s from Supplier s join s.reviews r where s.bookings.size>=?1 and r.rating=?2")
	Collection<Supplier> findSuppliersWithNumberReviewWithRating(int reviewsNumber,int rating);
	
	//The supplier/s who has/have more bookings in the system.
	@Query("select s from Supplier s where s.bookings.size=(select max(s.bookings.size) from Supplier s)")
	Supplier findSupplierWithMoreBookings();
	
	//The supplier/s who doesn’t/don’t have any booking.
	@Query("select s from Supplier s where s.bookings.size=?1")
	Supplier findSupplierWithLessBookingsThan(int bookingsNumber);
}
