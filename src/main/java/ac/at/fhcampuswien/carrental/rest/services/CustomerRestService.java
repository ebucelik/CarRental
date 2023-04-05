package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.entity.helper.Hashing;
import ac.at.fhcampuswien.carrental.entity.models.Customer;
import ac.at.fhcampuswien.carrental.entity.service.CustomerEntityService;
import ac.at.fhcampuswien.carrental.entity.service.JwtService;
import ac.at.fhcampuswien.carrental.expections.AuthenticationException;
import ac.at.fhcampuswien.carrental.expections.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.carrental.expections.CustomerNotFoundException;
import ac.at.fhcampuswien.carrental.expections.InvalidPasswordException;
import ac.at.fhcampuswien.carrental.rest.models.LoginDTO;
import ac.at.fhcampuswien.carrental.rest.models.RefreshTokenDTO;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationResponseDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerRestService {
    @NonNull
    CustomerEntityService customerEntityService;
    @Autowired
    private JwtService jwtService;

    public RegistrationResponseDto createCustomer(RegistrationRequestDto registrationRequestDto) throws CustomerAlreadyExistsException {
        return customerEntityService.addCustomer(registrationRequestDto);
    }
    public String customerLogin(LoginDTO loginData) throws AuthenticationException {
        Customer customer = null;
        try {
            customer = checkCustomerExistance(loginData.getEMail());
            if (checkPassword(loginData.getPassword(), customer)) {
                return jwtService.generateToken(loginData.getEMail());
            } else throw new InvalidPasswordException("Invalid Password");
        } catch (InvalidPasswordException | CustomerNotFoundException e) {
            throw new AuthenticationException("Username or password invalid!");
        }
    }

    private Customer checkCustomerExistance(String email) throws CustomerNotFoundException {
        Customer customer = customerEntityService.findCustomer(email);
        if (customer == null) {
            throw new CustomerNotFoundException("This email does not exists.");
        }
        return customer;
    }

    private boolean checkPassword(String password, Customer customer) throws InvalidPasswordException {
        if (comparePassword(customer, password)) {
            return true;
        } else {
            throw new InvalidPasswordException("The password is not correct.");
        }
    }

    public boolean comparePassword(Customer customer, String enteredPassword) {
        byte[] hash = Hashing.generateHash(enteredPassword, customer.getSalt());
        return Arrays.equals(hash, customer.getPassword());

    }

    public String refreshAccessToken(RefreshTokenDTO refreshTokenDTO) throws CustomerNotFoundException {
        String token = refreshTokenDTO.getToken();
        String eMail = jwtService.extractUserEmail(token);
        checkCustomerExistance(eMail);
        if (Boolean.FALSE.equals(jwtService.isTokenExpired(token)))
            return token;
        else {
            jwtService.isTokenExpired(token);
            return jwtService.generateToken(eMail);
        }
    }
}


