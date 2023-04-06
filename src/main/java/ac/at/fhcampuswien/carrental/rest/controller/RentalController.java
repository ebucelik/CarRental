package ac.at.fhcampuswien.carrental.rest.controller;


import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.models.Rental;
import ac.at.fhcampuswien.carrental.expections.BookingFailedException;
import ac.at.fhcampuswien.carrental.rest.models.RentalRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalResponseDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalUpdateRequestDto;
import ac.at.fhcampuswien.carrental.rest.services.CarRestService;
import ac.at.fhcampuswien.carrental.rest.services.RentalRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "Booking", description = "Endpoints for managing bookings.")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RentalController {

    @Autowired
    @NotNull
    RentalRestService rentalRestService;

    @Autowired
    @NotNull
    CarRestService carRestService;

    @GetMapping("/allBookings")
    @Operation(summary = "Lists all cars.", tags = {"Bookings"}, responses = {@ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rental.class)))})
    public ResponseEntity<List<Rental>> getBookings(@Valid @RequestHeader(value = "Auth") String token, HttpServletRequest request) {
            List<Rental> rentals = rentalRestService.getAllBookings(request);
            return new ResponseEntity<>(rentals, HttpStatus.OK);
        }

    @PostMapping("/booking")
    @Operation(summary = "Creates a rental booking in the database.", tags = {"Rentals"}, responses = {@ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponseDto.class)))})
    public ResponseEntity<RentalResponseDto> createBooking(@Valid @RequestHeader(value = "Auth") String token, @RequestBody RentalRequestDto rentalBooking, HttpServletRequest request) {

        try {
            RentalResponseDto rentalResponseDto = rentalRestService.createBooking(rentalBooking, request);
            return new ResponseEntity<>(rentalResponseDto, HttpStatus.CREATED);
        } catch (BookingFailedException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*    @PutMapping("/{bookingid}")
    public ResponseEntity<Object> updateBooking(@Valid @RequestHeader(value = "Auth") String token, RentalUpdateRequestDto rentalUpdateRequestDto) {
        rentalRestService.updateBooking(token, rentalUpdateRequestDto);
        return new ResponseEntity<>(rentalResponseDto, HttpStatus.OK);
    }


    @PutMapping("/{bookingid}")
    public ResponseEntity<Void> removeBooking() {
        return null;
    }*/


}
