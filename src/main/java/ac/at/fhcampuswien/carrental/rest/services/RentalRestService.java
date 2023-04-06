package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.entity.models.Rental;
import ac.at.fhcampuswien.carrental.entity.service.CarEntityService;
import ac.at.fhcampuswien.carrental.entity.service.CustomerEntityService;
import ac.at.fhcampuswien.carrental.entity.service.JwtService;
import ac.at.fhcampuswien.carrental.entity.service.RentalEntityService;
import ac.at.fhcampuswien.carrental.expections.BookingFailedException;
import ac.at.fhcampuswien.carrental.rest.models.RentalRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalResponseDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalUpdateRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalUpdateResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    CurrencyRestService currencyRestService;
    @Autowired
    RentalEntityService rentalEntityService;


    public List<Rental> getAllBookings(HttpServletRequest request) {
        String accessToken = request.getHeader("Auth");
        String eMail = jwtService.extractUserEmail(accessToken);
        return rentalEntityService.getAllRentals(eMail);
    }


    public RentalResponseDto createBooking(RentalRequestDto rentalBooking, HttpServletRequest request) throws BookingFailedException {
        String accessToken = request.getHeader("Auth");
        String eMail = jwtService.extractUserEmail(accessToken);
        return rentalEntityService.createBooking(rentalBooking, eMail);
    }



/*    public RentalUpdateResponseDto updateBooking(String token, RentalUpdateRequestDto rentalUpdateRequestDto) {
        long customerId = rentalUpdateRequestDto.getCustomerId();
        Optional<Rental> choosenBooking = getBooking(customerId);
        RentalUpdateResponseDto = choosenBooking;

    }

    public Optional<Rental> getBooking (long id){
        return rentalEntityService.getBookingById(id);

    }*/
}

