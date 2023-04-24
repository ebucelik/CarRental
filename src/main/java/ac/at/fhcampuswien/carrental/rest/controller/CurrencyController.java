package ac.at.fhcampuswien.carrental.rest.controller;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.models.Currency;
import ac.at.fhcampuswien.carrental.exception.exceptions.CurrencyServiceNotAvailableException;
import ac.at.fhcampuswien.carrental.rest.models.CurrencyResponseDto;
import ac.at.fhcampuswien.carrental.rest.services.CurrencySOAPService;
import ac.at.fhcampuswien.carrental.wsdl.GetCurrencyCodes;
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

    @GetMapping("/currencyCodes")
    @Operation(
            summary = "Lists all currency codes.",
            tags = {"Currency"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Currency.class))),
                    @ApiResponse(description = "Currency Service is not available!", responseCode = "500", content = @Content)
            })
    public ResponseEntity<CurrencyResponseDto> getAllCurrencies(@Valid @RequestHeader(value = "Auth") String token) throws CurrencyServiceNotAvailableException {
        GetCurrencyCodes getCurrencyCodes = new GetCurrencyCodes();
        List<String> currencyCodes = currencySOAPService.getCurrencyCodes(getCurrencyCodes);
        CurrencyResponseDto currencyResponseDto = new CurrencyResponseDto();
        currencyResponseDto.setCurrencyCodes(currencyCodes);
        return ResponseEntity.ok(currencyResponseDto);
    }
}
