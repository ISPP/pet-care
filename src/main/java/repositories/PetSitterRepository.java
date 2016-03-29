package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.PetSitter;

@Repository
public interface PetSitterRepository extends JpaRepository<PetSitter, Integer>{

}
