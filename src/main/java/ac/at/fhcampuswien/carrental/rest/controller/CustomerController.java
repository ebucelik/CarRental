package ac.at.fhcampuswien.carrental.rest.controller;


import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.service.JwtService;
import ac.at.fhcampuswien.carrental.expections.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.carrental.rest.models.*;
import ac.at.fhcampuswien.carrental.rest.services.CustomerRestService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@Tag(name = "Customers", description = "Endpoints for managing customers")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerController {
    @Autowired
    private CustomerRestService customerRestService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/registration")
    @Operation(summary = "Creates a customers in the database.", tags = {"Customers"}, responses = {@ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationResponseDto.class))), @ApiResponse(description ="Customer already Exists", responseCode = "409", content = @Content)})
    public ResponseEntity<RegistrationResponseDto> register(@Valid @RequestBody RegistrationRequestDto registrationRequestDto) throws CustomerAlreadyExistsException, MethodArgumentNotValidException {

        RegistrationResponseDto outboundUserRegistrationDto = customerRestService.createCustomer(registrationRequestDto);
        return new ResponseEntity<>(outboundUserRegistrationDto, HttpStatus.CREATED);
    }


    @PostMapping("/auth")
    @Operation(summary = "Token Generator Test.", tags = {"Token"}, responses = {@ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json")), @ApiResponse(description ="Customer already Exists", responseCode = "409", content = @Content)})
    public String authenticateWithToken(@RequestBody LoginDTO loginDTO){
        return jwtService.generateToken(loginDTO.getEMail());
    }


    @PostMapping("/login")
    @Operation(summary = "Customer Login.", tags = {"Customers"}, responses = {@ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RefreshTokenDTO.class))), @ApiResponse(description = "User or password not correct", responseCode = "401", content = @Content)})
    public ResponseEntity<RefreshTokenDTO> login(@RequestBody @Valid LoginDTO loginData) {

        String token = customerRestService.login(loginData);
        RefreshTokenDTO tokenResponse = new RefreshTokenDTO(token);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
/*
    @PostMapping("/{username}/logout")
    @Operation(summary = "Logs out a user.", tags = {"Users"}, responses = {@ApiResponse(description = "OK", responseCode = "200"), @ApiResponse(description = "User not found", responseCode = "404", content = @Content), @ApiResponse(description = "Invalid session", responseCode = "401", content = @Content)})
    public ResponseEntity<Object> logout(@PathVariable String username, HttpSession session) throws InvalidSessionException, CustomerNotFoundException {

        userService.logout(username, (UUID) session.getAttribute(sessionIdName));
        session.removeAttribute(sessionIdName);
        return new ResponseEntity<>(HttpStatus.OK);*/


}




