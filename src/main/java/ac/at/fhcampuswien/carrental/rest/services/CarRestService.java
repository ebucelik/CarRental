package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.service.CarEntityService;
import ac.at.fhcampuswien.carrental.entity.service.JwtService;
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


    public List<Car> getAvailableCars(String token, String currency, LocalDate from, LocalDate to) {
        if (!jwtService.isTokenExpired(token) /*&& jwtService.validateToken2(token) && currencyRestService.checkIfValidCurrency(currency)*/) {
            return carEntityService.getFreeCarsBetweenDates(from, to);
        } else {
            return null;
        }
    }

    public List<Car> getAllCars(String token, String currency) {
        if (!jwtService.isTokenExpired(token) /*&& currencyRestService.checkIfValidCurrency(currency)*/)
            return carEntityService.getAllCars();
        return null;
    }
}


