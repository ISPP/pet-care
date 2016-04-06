package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Booking;
import domain.Pet;


@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{
	
	@Query("select b from Booking b join b.petOwner.pets p where p.id = ?1 and b.startMoment >= CURRENT_DATE and b.status = 'ACCEPTED'")
	Collection<Booking> findCurrentBookingsByPet(int petId);

	@Query("select p from Pet p where p.petOwner.id = ?1")
	Collection<Pet> findOwnerMyPets(int ownerId);
	
	@Query("select p from Pet p where p.petSitter.id = ?1")
	Collection<Pet> findSitterMyPets(int sitterId);
	
}
