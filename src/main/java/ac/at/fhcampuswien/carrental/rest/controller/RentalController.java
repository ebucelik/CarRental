package ac.at.fhcampuswien.carrental.rest.controller;


import ac.at.fhcampuswien.carrental.entity.models.Rental;
import ac.at.fhcampuswien.carrental.rest.services.JwtService;
import ac.at.fhcampuswien.carrental.entity.service.RentalEntityService;
import ac.at.fhcampuswien.carrental.exception.exceptions.BookingFailedException;
import ac.at.fhcampuswien.carrental.rest.models.RentalRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalResponseDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalUpdateRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalUpdateResponseDto;
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
@Tag(name = "Bookings", description = "Endpoints for managing bookings.")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RentalController {

    @Autowired
    @NotNull
    RentalRestService rentalRestService;

    @Autowired
    @NotNull
    CarRestService carRestService;

    @Autowired
    JwtService jwtService;

    @GetMapping("/allBookings")
    @Operation(
            summary = "Lists all bookings.",
            tags = {"Bookings"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rental.class)))
            })
    public ResponseEntity<List<Rental>> getBookings(@Valid @RequestHeader(value = "Auth") String token,
                                                    @RequestParam String currentCurrency,
                                                    HttpServletRequest request) throws Exception {
        List<Rental> rentals = rentalRestService.getAllBookings(request, currentCurrency);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @PostMapping("/booking")
    @Operation(
            summary = "Creates a booking in the database.",
            tags = {"Bookings"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponseDto.class)))
            })
    public ResponseEntity<RentalResponseDto> createBooking(@Valid @RequestHeader(value = "Auth") String token,
                                                           @RequestBody RentalRequestDto rentalBooking,
                                                           HttpServletRequest request) {
        try {
            RentalResponseDto rentalResponseDto = rentalRestService.createBooking(rentalBooking, request);
            return new ResponseEntity<>(rentalResponseDto, HttpStatus.CREATED);
        } catch (BookingFailedException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/booking")
    @Operation(
            summary = "Update a booking in the database.",
            tags = {"Bookings"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponseDto.class)))
            })
    public ResponseEntity<RentalUpdateResponseDto> updateBooking(@Valid @RequestHeader(value = "Auth") String token,
                                                                 RentalUpdateRequestDto rentalUpdateRequestDto) throws RentalEntityService, Exception {
        RentalUpdateResponseDto rentalUpdateResponseDto = rentalRestService.updateBooking(rentalUpdateRequestDto);
        return new ResponseEntity<>(rentalUpdateResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/booking/{bookingId}")
    @Operation(summary = "Delete booking entry from database.", tags = {"Bookings"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBooking(@Valid @RequestHeader(value = "Auth") String token, @Valid @PathVariable Long bookingId) {
        rentalRestService.removeBooking(bookingId);
    }
}
