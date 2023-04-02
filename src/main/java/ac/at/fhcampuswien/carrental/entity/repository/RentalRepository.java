package ac.at.fhcampuswien.carrental.entity.repository;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.models.Customer;
import ac.at.fhcampuswien.carrental.entity.models.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query(value = "SELECT r.car_id FROM rentals r WHERE NOT (r.start_day >= :startDate AND r.end_day <= :endDate OR r.start_day <= :startDate AND r.end_day >= :endDate OR r.start_day <= :endDate AND r.end_day >= :endDate OR r.start_day <= :startDate AND r.end_day >= :startDate)", nativeQuery = true)
    List<Long> findAllAvailableCarsBetweenDates(
            @Param("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate, @Param("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate endDate);

    //    @Query(value = "SELECT c.* FROM cars c WHERE c.id NOT IN (SELECT r.car_id FROM rentals r WHERE NOT (r.start_day >= :startDate AND r.end_day <= :endDate OR r.start_day <= :startDate AND r.end_day >= :endDate OR r.start_day <= :endDate AND r.end_day >= :endDate OR r.start_day <= :startDate AND r.end_day >= :startDate))", nativeQuery = true)
}
