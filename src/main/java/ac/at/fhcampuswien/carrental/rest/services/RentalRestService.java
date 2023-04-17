package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.entity.models.Rental;
import ac.at.fhcampuswien.carrental.entity.service.CarEntityService;
import ac.at.fhcampuswien.carrental.entity.service.CustomerEntityService;
import ac.at.fhcampuswien.carrental.entity.service.RentalEntityService;
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

    public List<Rental> getAllBookings(HttpServletRequest request, String currentCurrency) throws Exception {
        String accessToken = request.getHeader("Auth");
        String eMail = jwtService.extractUserEmail(accessToken);
        List<Rental>  allRentals = new ArrayList<>();
        List<Rental> allRentalsFromDB = rentalEntityService.getAllRentals(eMail);

        GetConvertedValue getConvertedValue = new GetConvertedValue();
        getConvertedValue.setCurrentValue(1f);
        getConvertedValue.setCurrentCurrencyCode("USD");
        getConvertedValue.setExpectedCurrencyCode(currentCurrency);
        float exchangeRate = currencySOAPService.getConvertedValue(getConvertedValue).floatValue();

        for (Rental r: allRentalsFromDB) {
            float totalCostConverted = r.getTotalCost() * exchangeRate;
            r.setTotalCost(totalCostConverted);
            allRentals.add(r);
        }

        return allRentals;
    }

    public RentalResponseDto createBooking(RentalRequestDto rentalBooking, HttpServletRequest request) throws Exception {
        String accessToken = request.getHeader("Auth");
        String eMail = jwtService.extractUserEmail(accessToken);

        String currentCurrency = rentalBooking.getCurrentCurrency();
        if(!currentCurrency.equals("USD")){
            GetConvertedValue getConvertedValue = new GetConvertedValue();
            getConvertedValue.setCurrentValue(rentalBooking.getTotalCost());
            getConvertedValue.setCurrentCurrencyCode(currentCurrency);
            getConvertedValue.setExpectedCurrencyCode("USD");
            float totatlCostInUSD = currencySOAPService.getConvertedValue(getConvertedValue).floatValue();
            rentalBooking.setTotalCost(totatlCostInUSD);
        }

        return rentalEntityService.createBooking(rentalBooking, eMail);
    }

    public RentalUpdateResponseDto updateBooking(RentalUpdateRequestDto rentalUpdateRequestDto) throws RentalEntityService, Exception {

        String currentCurrency = rentalUpdateRequestDto.getCurrentCurrency();
        if(!currentCurrency.equals("USD")){
            GetConvertedValue getConvertedValue = new GetConvertedValue();
            getConvertedValue.setCurrentValue(rentalUpdateRequestDto.getTotalCost());
            getConvertedValue.setCurrentCurrencyCode(currentCurrency);
            getConvertedValue.setExpectedCurrencyCode("USD");
            float totatlCostInUSD = currencySOAPService.getConvertedValue(getConvertedValue).floatValue();
            rentalUpdateRequestDto.setTotalCost(totatlCostInUSD);
        }

        return rentalEntityService.updateBooking(rentalUpdateRequestDto);

    }

    public Optional<Rental> getBooking (long id){
        return rentalEntityService.getBookingById(id);

    }

    public void removeBooking(Long id){
        rentalEntityService.deleteBooking(id);
    }
}

