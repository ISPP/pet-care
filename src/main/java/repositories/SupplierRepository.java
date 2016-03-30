package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.*;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

	@Query("select a from Supplier a where a.user.username=?1")
	Supplier findSupplierByUsername(String username );
	
	
}
