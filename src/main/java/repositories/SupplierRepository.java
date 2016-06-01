package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.*;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	@Query("select a from Supplier a where a.user.username=?1")
	Supplier findSupplierByUsername(String username);

	// A list of suppliers who have more than 10 reviews which rating is 0 to
	// decide whether or not he/she is providing a bad service.
	@Query("select s from Supplier s join s.reviews r where s.bookings.size>=?1 and r.rating=?2")
	Collection<Supplier> findSuppliersWithNumberReviewWithRating(
			int reviewsNumber, int rating);

	// The supplier/s who has/have more bookings in the system.
	@Query("select s from Supplier s where s.bookings.size=(select max(s.bookings.size) from Supplier s)")
	Collection<Supplier> findSupplierWithMoreBookings();

	// The supplier/s who doesn’t/don’t have any booking.
	@Query("select s from Supplier s where s.bookings.size=?1")
	Collection<Supplier> findSupplierWithBookings(int bookingsNumber);
	
	@Query("select ps from PetSitter ps where ps.address like %?1%")
	Collection<PetSitter> searchSitters(String address);
	
	@Query("select ps from PetShipper ps where ps.address like %?1%")
	Collection<PetShipper> searchShippers(String address);
	
	@Query("select ps from Company ps where ps.address like %?1%")
	Collection<Company> searchCompanies(String address);
	
	@Query("select ps from Supplier ps where ps.address like %?1%")
	Collection<Supplier> searchSuppliers(String address);
@Query("select a from Supplier a where a.invitationCode=?1")
	Supplier findSuppByInvitationCode(String invitationCode);
}
