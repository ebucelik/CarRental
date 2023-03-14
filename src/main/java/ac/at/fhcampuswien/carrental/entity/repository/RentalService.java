package ac.at.fhcampuswien.carrental.entity.repository;

import ac.at.fhcampuswien.carrental.rest.models.Car;
import ac.at.fhcampuswien.carrental.rest.models.Rental;
import java.util.List;

public interface RentalService {
    List<Car> getAvailableCars(String currencyCode,
                               long startTimestamp,
                               long endTimestamp);

    Rental rentCar(int customerId,
                   int carId,
                   String currencyCode,
                   long startTimestamp,
                   long endTimestamp);

    List<Rental> getRentals(int customerId,
                            String currencyCode);
}


