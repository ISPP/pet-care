package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.*;

@Repository
public interface PetShipperRepository extends JpaRepository<PetShipper, Integer>{

	@Query("select p from PetShipper p where p.user.id = ?1")
	PetShipper findOneByPrincipal(int id);
	
	@Query("select a from PetShipper a where a.invitationCode=?1")
	PetSitter findPetShipperByInvitationCode(String invitationCode);

}
