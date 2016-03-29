package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.*;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer>{

}
