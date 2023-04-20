package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.service.CarEntityService;
import ac.at.fhcampuswien.carrental.exception.exceptions.CarNotAvailableException;
import ac.at.fhcampuswien.carrental.exception.exceptions.CurrencyServiceNotAvailableException;
import ac.at.fhcampuswien.carrental.rest.mapper.UserMapper;
import ac.at.fhcampuswien.carrental.rest.models.CarListDTO;
import ac.at.fhcampuswien.carrental.wsdl.GetConvertedValue;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class CarRestService {
    @NonNull
    CarEntityService carEntityService;
    @NonNull
    JwtService jwtService;

    @NonNull
    CurrencySOAPService currencySOAPService;

    @Autowired
    UserMapper userMapper;

    private static final DecimalFormat df = new DecimalFormat("#.##");

    public List<CarListDTO> getAvailableCars(String currentCurrency, String chosenCurrency, LocalDate from, LocalDate to) throws CarNotAvailableException, CurrencyServiceNotAvailableException {
        List<CarListDTO> carsToDisplay = new ArrayList<>();
        int bookingDays = (int) DAYS.between(from, to) + 1;
        List<Car> availableCars = carEntityService.getFreeCarsBetweenDates(from, to);
        double exchangeRate = getExchangeRate(currentCurrency, chosenCurrency);

        for (Car c: availableCars) {
            float dailyCoastConverted = (float) (c.getDailyCost() * exchangeRate);
            float totalCost = (float) (dailyCoastConverted * bookingDays);
            float dailyCostFormatted = formatCosts(dailyCoastConverted);
            float totalCostFormatted = formatCosts(totalCost);

            CarListDTO carWithTotalCost = userMapper.carToDisplayList(c, dailyCostFormatted, totalCostFormatted);
            carsToDisplay.add(carWithTotalCost);
        }
        return carsToDisplay;
    }

    private float formatCosts(float costs) {
        String formatDaily = df.format(costs);
        String replacedDaily = formatDaily.replace(",", ".");
        return Float.parseFloat(replacedDaily);
    }

    public Double getExchangeRate(String currentCurrency, String chosenCurrency) throws CurrencyServiceNotAvailableException {
        GetConvertedValue getConvertedValue = new GetConvertedValue();
        getConvertedValue.setCurrentValue(1f);
        getConvertedValue.setCurrentCurrencyCode(currentCurrency);
        getConvertedValue.setExpectedCurrencyCode(chosenCurrency);
        return currencySOAPService.getConvertedValue(getConvertedValue);
    }
}


