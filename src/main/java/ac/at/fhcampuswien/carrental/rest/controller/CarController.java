package ac.at.fhcampuswien.carrental.rest.controller;


import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.service.CustomerEntityService;
import ac.at.fhcampuswien.carrental.expections.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.carrental.expections.InvalidSessionException;
import ac.at.fhcampuswien.carrental.rest.models.RefreshTokenDTO;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationResponseDto;
import ac.at.fhcampuswien.carrental.rest.services.CarRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    @Operation(summary = "Lists all available cars.", tags = {"Cars"}, responses = {@ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class))), @ApiResponse(description = "Cars not found", responseCode = "404", content = @Content)})
    public ResponseEntity<List<Car>> getAllCars(@Valid @RequestHeader("Authorization") String token,
                                                @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                                @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) throws InvalidSessionException {

        List<Car> allAvailableCars = carRestService.getAllCars(token);
        return ResponseEntity.ok(allAvailableCars);
    }

    @GetMapping("/availableCars")
    @Operation(
            summary = "Lists all available cars.",
            tags = {"Cars"},
            responses = {@ApiResponse(
                    description = "OK",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class))),
                    @ApiResponse(description = "Cars not found", responseCode = "404", content = @Content)})
    public ResponseEntity<List<Car>> listAvailableCars(@Valid @RequestHeader("Authorization") String token,
                                                       @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                                       @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {

        carRestService.getAvailableCars(token, from, to);
        return new ResponseEntity<>(HttpStatus.OK);
    }





/*    @GetMapping("/availableCars")
    @Operation(summary = "Lists all available cars.", tags = {"Cars"}, responses = {@ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class))), @ApiResponse(description = "Cars not found", responseCode = "404", content = @Content)})
    public ResponseEntity<List<Car>> listAvailableCars(@Valid @RequestHeader("Authorization") String token,
                                                       @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                                       @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) throws InvalidSessionException {

        List<Car> allAvailableCars = carRestService.getAvailableCars(token, from, to);

        return ResponseEntity.ok(allAvailableCars);
    }*/


}
