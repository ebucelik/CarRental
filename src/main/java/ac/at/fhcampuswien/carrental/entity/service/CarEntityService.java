package ac.at.fhcampuswien.carrental.entity.service;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.repository.CarRepository;
import ac.at.fhcampuswien.carrental.entity.repository.RentalRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CarEntityService {

    CarRepository carRepository;
    RentalRepository rentalRepository;
    public List<Car> getFreeCarsBetweenDates(LocalDate from, LocalDate to){

        List<Long> availableCarIDs = rentalRepository.findAllAvailableCarsBetweenDates(from, to);
        List<Car> allAvailableCars = carRepository.findAllById(availableCarIDs);
        return allAvailableCars;
    }
}
