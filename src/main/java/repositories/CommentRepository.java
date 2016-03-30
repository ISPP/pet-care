package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.*;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

	@Query("select c from Comment c where c.complaint.id=?1")
    Collection<Comment> findCommentByComplaintId(Long id);
	
	
}
