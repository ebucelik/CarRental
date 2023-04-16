package ac.at.fhcampuswien.carrental.entity.repository;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
