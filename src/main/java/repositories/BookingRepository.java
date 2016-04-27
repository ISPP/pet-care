package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{


    @Query("select b from Booking b where b.supplier.id = ?3 and b.status = 'ACCEPTED' and"
    		+ "((b.startMoment <= ?1 and b.endMoment > ?1) or "
    		+ "(b.startMoment >= ?1 and b.endMoment <= ?2) or "
    		+ "(b.startMoment < ?2 and b.endMoment >= ?2) or "
    		+ "(b.startMoment < ?1 and b.endMoment > ?2))")
    Collection<Booking> findByDateSupplier(Date startDate, Date endDate, int sitterId);
    
    
    @Query("select b from Booking b where b.status='ACCEPTED' and b.supplier.id=?1 order by b.startMoment")
	Collection<Booking> findBokkingAcceptedBySupplierId(Integer id);
    
    @Query("select b from Booking b where b.petOwner.id=?1 and b.status='PENDING' or b.status='ACCEPTED' and b.cancelled is false and CURRENT_TIMESTAMP<b.startMoment-b.supplier.daysBeforeCancel*3600*1000")
    Collection<Booking> findBookingCanCancelByPetOwnerId(Integer id);
    
    @Query("select b from Booking b where b.supplier.id=?1 and b.status='PENDING' order by b.status")
    Collection<Booking> findBookingCanAceptRejectedByCustomerId(Integer id);


    @Query("select b from Booking b where b.petOwner.id=?1 and b.status='ACCEPTED'")
	Collection<Booking> findBookingByPetOwnerId(int id);


    @Query("select b from Booking b where b.petOwner.id=?1 and b.payByPetOwner=false and b.cancelled=false")//and b.status='ACCEPTED' and b.cancelled=false
	Collection<Booking> findBookingNotPayBySupplierId(int id);

    @Query("select b from Booking b where b.payByPetOwner=true and b.payByAdmin=false and b.status='ACCEPTED' and b.cancelled=false and b.endMoment<CURRENT_TIMESTAMP")
	Collection<Booking> findAllToPayByAdmin();

    @Query("select b from Booking b where b.petOwner.id=?1 and b.payByPetOwner=true")
	Collection<Booking> findBookingPayBySupplierId(int id);
    
    @Query("select b from Booking b where b.payByPetOwner=true and b.status!='ACCEPTED' and b.startMoment>CURRENT_TIMESTAMP")
    Collection<Booking> findBookingToRembolse();

}
