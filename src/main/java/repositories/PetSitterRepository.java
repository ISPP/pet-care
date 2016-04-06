package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.PetSitter;

@Repository
public interface PetSitterRepository extends JpaRepository<PetSitter, Integer>{

	@Query("select ps from PetSitter ps where ps.address like %?1%")
	Collection<PetSitter> searchSitters(String address);

	@Query("select a from PetSitter a where a.user.username=?1")
	PetSitter findPetSitterByUsername(String username );
	
	

}
