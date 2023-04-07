package ac.at.fhcampuswien.carrental.rest.controller;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.models.Currency;
import ac.at.fhcampuswien.carrental.expections.InvalidSessionException;
import ac.at.fhcampuswien.carrental.rest.models.CurrencyRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.CurrencyResponseDto;
import ac.at.fhcampuswien.carrental.rest.services.CurrencySOAPService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1/currency")
@Tag(name = "Currency", description = "Endpoints for managing currency.")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CurrencyController {


    @Autowired
    @NotNull
    CurrencySOAPService currencySOAPService;

    @GetMapping("/allCurrency")
    @Operation(summary = "Lists all currencys.", tags = {"Currency"}, responses = {@ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Currency.class)))})
    public ResponseEntity<CurrencyResponseDto> getAllCurrencies(@Valid @RequestParam CurrencyRequestDto currencyRequestDto) {
        //return ResponseEntity.ok(currencySOAPService.getAllCurrencies(currencyRequestDto));

        return null;
    }
}


/*    @GetMapping("/allCars")
    @Operation(summary = "Lists all cars.", tags = {"Cars"}, responses = {@ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class))), @ApiResponse(description = "Cars not found", responseCode = "404", content = @Content)})
    public ResponseEntity<List<Car>> getAllCars(@Valid @RequestHeader(value = "Auth") String token, @RequestParam("currency") String currency) throws InvalidSessionException {

        List<Car> allAvailableCars = carRestService.getAllCars(token, currency);
        return ResponseEntity.ok(allAvailableCars);
    }
}*/
