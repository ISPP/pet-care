package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.*;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer>{

}
