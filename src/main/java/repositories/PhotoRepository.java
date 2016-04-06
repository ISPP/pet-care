package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Photo;


@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer>{
	
	
}
