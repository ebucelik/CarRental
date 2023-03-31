package ac.at.fhcampuswien.carrental.rest.controller;

import ac.at.fhcampuswien.carrental.expections.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.carrental.expections.CustomerNotFoundException;
import ac.at.fhcampuswien.carrental.expections.InvalidSessionException;
import ac.at.fhcampuswien.carrental.rest.models.InboundCustomerLoginDto;
import ac.at.fhcampuswien.carrental.rest.models.InboundCustomerRegistrationDto;
import ac.at.fhcampuswien.carrental.rest.models.OutboundCustomerRegistrationDto;
import ac.at.fhcampuswien.carrental.rest.services.CustomerRestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "Users", description = "Endpoints for managing users")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerController {
/*    @Autowired
    private CustomerRestService customerRestService;

    @PostMapping("/register")
    @Operation(summary = "Creates a user in the database.", tags = {"Users"}, responses = {@ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OutboundUserRegistrationDto.class))), @ApiResponse(description = "User already Exists", responseCode = "409", content = @Content)})
    public ResponseEntity<OutboundCustomerRegistrationDto> register(@Valid @RequestBody Customer customer) throws CustomerAlreadyExistsException, MethodArgumentNotValidException {

        OutboundCustomerRegistrationDto outboundUserRegistrationDto = customerRestService.createCustomer(customer);
        return new ResponseEntity<>(outboundUserRegistrationDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user.", tags = {"Users"}, responses = {@ApiResponse(description = "OK", responseCode = "200"), @ApiResponse(description = "User locked", responseCode = "401", content = @Content), @ApiResponse(description = "User or password not correct", responseCode = "401", content = @Content)})
    public ResponseEntity<Object> login(@RequestBody @Valid InboundCustomerLoginDto inboundCustomerdto, HttpSession session) {

        customerRestService.login(inboundUserRegistrationDto.getCutomername(), inboundUserRegistrationDto.getPassword(), sesssionID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{username}/logout")
    @Operation(summary = "Logs out a user.", tags = {"Users"}, responses = {@ApiResponse(description = "OK", responseCode = "200"), @ApiResponse(description = "User not found", responseCode = "404", content = @Content), @ApiResponse(description = "Invalid session", responseCode = "401", content = @Content)})
    public ResponseEntity<Object> logout(@PathVariable String username, HttpSession session) throws InvalidSessionException, CustomerNotFoundException {

        userService.logout(username, (UUID) session.getAttribute(sessionIdName));
        session.removeAttribute(sessionIdName);
        return new ResponseEntity<>(HttpStatus.OK);*/

    }







/*
    @PostMapping("register")
    @PostMapping("login")
    @PostMapping("logout/{id}")
    @DeleteMapping("users/{id}")

*/


/*
    @PostMapping("/login")
    @ApiOperation(value = "Authenticate user and create session", response = UserSession.class)
    public ResponseEntity<UserSession> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.authenticate(username, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserSession session = userService.createSession(user);
        return ResponseEntity.ok(session);
    }*/
