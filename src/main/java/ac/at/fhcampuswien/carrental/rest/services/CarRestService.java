package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.service.CarEntityService;
import ac.at.fhcampuswien.carrental.entity.service.JwtService;
import ac.at.fhcampuswien.carrental.exception.exceptions.InvalidTokenException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarRestService {
    @NonNull
    CarEntityService carEntityService;
    @NonNull
    JwtService jwtService;

    @NonNull
    CurrencySOAPService currencySOAPService;

    public List<Car> getAvailableCars(String currency, LocalDate from, LocalDate to) {
        return carEntityService.getFreeCarsBetweenDates(from, to);
    }

    public List<Car> getAllCars(String currency) {
        return carEntityService.getAllCars();
    }
}


