package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	@Query("select r from Review r where r.reviewed.id=?1")
	Collection<Review> findReviewBySupplierId(Integer id);

	@Query("select r from Review r where r.reviewer.id=?1")
	Collection<Review> findReviewByPetOwnerId(int id);

}
