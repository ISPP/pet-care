package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface AdministratorRepository extends
		JpaRepository<Administrator, Integer> {
	@Query("select a from Administrator a where a.user.username=?1")
	Administrator findAdminByUsername(String username );

	@Query("select p from Administrator p where p.user.id = ?1")
	Administrator findOneByPrincipal(int id);
}
