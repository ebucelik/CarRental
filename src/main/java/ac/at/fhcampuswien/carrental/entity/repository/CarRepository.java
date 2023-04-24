package ac.at.fhcampuswien.carrental.entity.repository;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM cars c WHERE c.id NOT IN :list", nativeQuery = true)
    List<Car> findCarsNotInList(@Param("list") List<Long> ids);

    @Query(value ="SELECT * FROM cars c WHERE c.id = :id",nativeQuery = true)
    Car getCarById(@Param("id") long id);
}
