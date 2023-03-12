package ac.at.fhcampuswien.carrental.services;

import ac.at.fhcampuswien.carrental.models.Car;
import ac.at.fhcampuswien.carrental.models.Rental;
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


