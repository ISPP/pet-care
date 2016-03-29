package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Comment;
import domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

}
