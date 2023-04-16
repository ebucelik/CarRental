package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.entity.helper.Hashing;
import ac.at.fhcampuswien.carrental.entity.models.Customer;
import ac.at.fhcampuswien.carrental.entity.service.CustomerEntityService;
import ac.at.fhcampuswien.carrental.entity.service.JwtService;
import ac.at.fhcampuswien.carrental.exception.exceptions.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.carrental.exception.exceptions.CustomerNotFoundException;
import ac.at.fhcampuswien.carrental.exception.exceptions.InvalidPasswordException;
import ac.at.fhcampuswien.carrental.exception.exceptions.InvalidTokenException;
import ac.at.fhcampuswien.carrental.rest.models.LoginDTO;
import ac.at.fhcampuswien.carrental.rest.models.RefreshTokenDTO;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationResponseDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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

    public String customerLogin(LoginDTO loginData) throws InvalidPasswordException, CustomerNotFoundException {
        Customer customer = checkCustomerExistence(loginData.getEMail());

        checkPassword(loginData.getPassword(), customer);

        return jwtService.generateToken(loginData.getEMail());
    }

    private Customer checkCustomerExistence(String email) throws CustomerNotFoundException {
        Customer customer = customerEntityService.findCustomer(email);

        if (customer == null) {
            throw new CustomerNotFoundException("This email does not exist.");
        }

        return customer;
    }

    private void checkPassword(String password, Customer customer) throws InvalidPasswordException {
        if (comparePassword(customer, password)) {
            return;
        }

        throw new InvalidPasswordException("Email or password is incorrect.");
    }

    public boolean comparePassword(Customer customer, String enteredPassword) {
        byte[] hash = Hashing.generateHash(enteredPassword, customer.getSalt());
        return Arrays.equals(hash, customer.getPassword());
    }

    public String refreshAccessToken(RefreshTokenDTO refreshTokenDTO) throws CustomerNotFoundException {
        String token = refreshTokenDTO.getToken();
        String eMail = jwtService.extractUserEmail(token);
        //Todo what if the customer is not found when we extract the token
        checkCustomerExistence(eMail);

        try {
            jwtService.isTokenExpiredOrInvalid(token);

            return token;
        } catch(InvalidTokenException | CustomerNotFoundException exception) {
            return jwtService.generateToken(eMail);
        }
    }

/*    public Boolean checkJWT(String refreshTokenDTO) throws CustomerNotFoundException {
        if (Boolean.TRUE.equals(jwtService.validateToken(String.valueOf(refreshTokenDTO))))
            return true;
        return false;
    }*/
}


