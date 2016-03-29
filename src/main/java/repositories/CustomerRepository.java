package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.*;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	@Query("select a from Customer a where a.user.username=?1")
	Customer findActorByUsername(String username );
}
