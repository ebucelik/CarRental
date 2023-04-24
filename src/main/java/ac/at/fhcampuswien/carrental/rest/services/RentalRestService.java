package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.models.Rental;
import ac.at.fhcampuswien.carrental.entity.service.CarEntityService;
import ac.at.fhcampuswien.carrental.entity.service.CustomerEntityService;
import ac.at.fhcampuswien.carrental.entity.service.RentalEntityService;
import ac.at.fhcampuswien.carrental.exception.exceptions.BookingNotFoundException;
import ac.at.fhcampuswien.carrental.exception.exceptions.CarNotAvailableException;
import ac.at.fhcampuswien.carrental.exception.exceptions.CurrencyServiceNotAvailableException;
import ac.at.fhcampuswien.carrental.rest.models.RentalRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalResponseDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalUpdateRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalUpdateResponseDto;
import ac.at.fhcampuswien.carrental.wsdl.GetConvertedValue;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalRestService {

    @NonNull
    CustomerEntityService customerEntityService;
    @NonNull
    CarEntityService carEntityService;
    @NonNull
    JwtService jwtService;
    @NonNull
    CurrencySOAPService currencySOAPService;
    @Autowired
    RentalEntityService rentalEntityService;

    private static final DecimalFormat df = new DecimalFormat("#.##");

    public List<Rental> getAllBookings(HttpServletRequest request, String currentCurrency) throws CurrencyServiceNotAvailableException {
        String accessToken = request.getHeader("Auth");
        String eMail = jwtService.extractUserEmail(accessToken);
        List<Rental> allRentals = new ArrayList<>();
        List<Rental> allRentalsFromDB = rentalEntityService.getAllRentals(eMail);

        GetConvertedValue getConvertedValue = new GetConvertedValue(1f, "USD", currentCurrency);
        float exchangeRate = currencySOAPService.getConvertedValue(getConvertedValue).floatValue();

        for (Rental r : allRentalsFromDB) {
            float totalCostConverted = r.getTotalCost() * exchangeRate;
            float totalCostFormatted = formatCosts(totalCostConverted);
            r.setTotalCost(totalCostFormatted);
            allRentals.add(r);
        }
        return allRentals;
    }

    public RentalResponseDto createBooking(RentalRequestDto rentalBooking, HttpServletRequest request) throws CarNotAvailableException, CurrencyServiceNotAvailableException {
        String accessToken = request.getHeader("Auth");
        String eMail = jwtService.extractUserEmail(accessToken);

        if (isCarAvailable(rentalBooking)) {
            String currentCurrency = rentalBooking.getCurrentCurrency();
            if (!currentCurrency.equals("USD")) {
                GetConvertedValue getConvertedValue = new GetConvertedValue(rentalBooking.getTotalCost(), currentCurrency, "USD");
                float totalCostInUSD = currencySOAPService.getConvertedValue(getConvertedValue).floatValue();
                float totalCostFormatted = formatCosts(totalCostInUSD);
                rentalBooking.setTotalCost(totalCostFormatted);
            }
            RentalResponseDto responseDto = rentalEntityService.createBooking(rentalBooking, eMail);
            GetConvertedValue getConvertedValue = new GetConvertedValue(responseDto.getTotalCost(), "USD", currentCurrency);
            float totalCostConverted = currencySOAPService.getConvertedValue(getConvertedValue).floatValue();
            float totalCostFormatted = formatCosts(totalCostConverted);
            responseDto.setTotalCost(totalCostFormatted);
            return responseDto;
        } else {
            throw new CarNotAvailableException("This Car is not available in this time period!");
        }
    }

    public RentalUpdateResponseDto updateBooking(RentalUpdateRequestDto rentalUpdateRequestDto) throws CurrencyServiceNotAvailableException, BookingNotFoundException {

            String currentCurrency = rentalUpdateRequestDto.getCurrentCurrency();
            if (!currentCurrency.equals("USD")) {
                GetConvertedValue getConvertedValue = new GetConvertedValue(rentalUpdateRequestDto.getTotalCost(), currentCurrency, "USD");
                float totalCostInUSD = currencySOAPService.getConvertedValue(getConvertedValue).floatValue();
                float totalCostFormatted = formatCosts(totalCostInUSD);
                rentalUpdateRequestDto.setTotalCost(totalCostFormatted);
            }
            RentalUpdateResponseDto responseDto = rentalEntityService.updateBooking(rentalUpdateRequestDto);
            GetConvertedValue getConvertedValue = new GetConvertedValue(responseDto.getTotalCost(), "USD", currentCurrency);
            float totalCostConverted = currencySOAPService.getConvertedValue(getConvertedValue).floatValue();
            float totalCostFormatted = formatCosts(totalCostConverted);
            responseDto.setTotalCost(totalCostFormatted);
            return responseDto;

    }

    public void removeBooking(Long id) {
        rentalEntityService.deleteBooking(id);
    }

    private float formatCosts(float costs) {
        String formatTotal = df.format(costs);
        String replacedTotal = formatTotal.replace(",", ".");
        return Float.parseFloat(replacedTotal);
    }

    public boolean isCarAvailable(RentalRequestDto rentalBooking) throws CarNotAvailableException {
        List<Car> bookedCars = carEntityService.getFreeCarsBetweenDates(rentalBooking.getStartDay(), rentalBooking.getEndDay());
        for (Car c : bookedCars) {
            if (c.getId().equals(rentalBooking.getCarId())) {
                return true;
            }
        }
        return false;
    }

    public boolean isCarAlreadyBookedForUpdate(RentalUpdateRequestDto rentalUpdateRequestDto) throws CarNotAvailableException {
        List<Car> bookedCars = carEntityService.getFreeCarsBetweenDates(rentalUpdateRequestDto.getStartDay(), rentalUpdateRequestDto.getEndDay());
        for (Car c : bookedCars) {
            if (c.getId().equals(rentalUpdateRequestDto.getCarId())) {
                return true;
            }
        }
        return false;
    }
}

