package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.*;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

	@Query("select p from Company p where p.user.id = ?1")
	Company findOneByPrincipal(int id);

}
