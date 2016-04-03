package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.*;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, Integer>{

	@Query("select p from PetOwner p where p.user.id = ?1")
	PetOwner findOneByPrincipal(int id);

}
