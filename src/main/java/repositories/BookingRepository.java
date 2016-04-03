package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{
    @Query("select b from Booking b where b.petOwner.id=?1")
    Collection<Booking> findAllByOwner(int ownerId);

    @Query("select b from Booking b where b.supplier.id=?1")
    Collection<Booking> findAllBySitter(int sitterId);

    @Query("select b from Booking b where b.supplier.id=?1")
    Collection<Booking> findAllByShipper(int shipperId);

    @Query("select b from Booking b where b.supplier.id=?1")
    Collection<Booking> findAllByCompany(int companyId);

    @Query("select b from Booking b where b.petOwner.user.id = ?1")
    Collection<Booking> findByOwnerIsCurrentUser();
    
    @Query("select b from Booking b where b.supplier.user.id = ?1")
    Collection<Booking> findBySupplierIsCurrentUser();

    @Query("select b from Booking b where b.supplier.id = ?3 and b.status = 'ACCEPTED' and"
    		+ "((b.arrivalMoment <= ?1 and b.departureMoment > ?1) or "
    		+ "(b.arrivalMoment >= ?1 and b.departureMoment <= ?2) or "
    		+ "(b.arrivalMoment < ?2 and b.departureMoment >= ?2) or "
    		+ "(b.arrivalMoment < ?1 and b.departureMoment > ?2))")
    Collection<Booking> findByDateSitter(Date startDate, Date endDate, int sitterId);
}
