package ac.at.fhcampuswien.carrental.rest.controller;


import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.exception.exceptions.InvalidTokenException;
import ac.at.fhcampuswien.carrental.rest.services.CarRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1/cars")
@Tag(name = "Cars", description = "Endpoints for managing car inventory")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CarController {

    @Autowired
    private CarRestService carRestService;

    @GetMapping("/allCars")
    @Operation(
            summary = "Lists all cars.",
            tags = {"Cars"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class))),
                    @ApiResponse(description = "Cars not found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<List<Car>> getAllCars(@Valid @RequestHeader(value = "Auth") String token,
                                                @RequestParam("currency") String currency) {
        List<Car> allAvailableCars = carRestService.getAllCars(currency);
        return ResponseEntity.ok(allAvailableCars);
    }

    @GetMapping("/availableCars")
    @Operation(
            summary = "Lists all available cars.",
            tags = { "Cars" },
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class))),
                    @ApiResponse(description = "Cars not found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<List<Car>> listAvailableCars(@Valid @RequestHeader(value = "Auth") String token,
                                                       @RequestParam("currency") String currency,
                                                       @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                                       @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        List<Car> allAvailableCars = carRestService.getAvailableCars(currency, from, to);
        return new ResponseEntity<>(allAvailableCars, HttpStatus.OK);
    }
}
