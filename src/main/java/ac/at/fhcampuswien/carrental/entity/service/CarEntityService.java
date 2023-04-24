package ac.at.fhcampuswien.carrental.entity.service;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.repository.CarRepository;
import ac.at.fhcampuswien.carrental.entity.repository.RentalRepository;
import ac.at.fhcampuswien.carrental.exception.exceptions.CarNotAvailableException;
import ac.at.fhcampuswien.carrental.exception.exceptions.CarNotFoundException;
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

    public List<Car> getFreeCarsBetweenDates(LocalDate from, LocalDate to) throws CarNotAvailableException {

        List<Long> availableCarIDs = rentalRepository.findAllAvailableCarsBetweenDates(from, to);
        if(availableCarIDs.isEmpty()) availableCarIDs.add(0l);
        List<Car> allAvailableCars = carRepository.findCarsNotInList(availableCarIDs);
        if (allAvailableCars.isEmpty()) {
            throw new CarNotAvailableException("No cars available in this time period");
        }
        return allAvailableCars;
    }

    public Car getCarById(long id) throws CarNotFoundException {
        Car car = carRepository.getCarById(id);
        if(car == null){
            throw new CarNotFoundException("The Car with this ID could not be found!");
        }
        return car;
    }

    public List<Car> getCarsByIds(List<Long> ids) {
        return carRepository.findCarsByIds(ids);
    }
}
