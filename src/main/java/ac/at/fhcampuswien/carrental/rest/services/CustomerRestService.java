package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.entity.helper.Hashing;
import ac.at.fhcampuswien.carrental.entity.models.Customer;
import ac.at.fhcampuswien.carrental.entity.service.CustomerEntityService;
import ac.at.fhcampuswien.carrental.exception.exceptions.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.carrental.exception.exceptions.CustomerNotFoundException;
import ac.at.fhcampuswien.carrental.exception.exceptions.InvalidPasswordException;
import ac.at.fhcampuswien.carrental.exception.exceptions.InvalidTokenException;
import ac.at.fhcampuswien.carrental.rest.models.*;
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

    public LoginResponseDTO customerLogin(LoginDTO loginData) throws InvalidPasswordException, CustomerNotFoundException {
        Customer customer = checkCustomerExistence(loginData.getEMail());
        checkPassword(loginData.getPassword(), customer);

        String accessToken = jwtService.generateToken(loginData.getEMail(), JwtService.Token.AccessToken);
        String refreshToken = jwtService.generateToken(loginData.getEMail(), JwtService.Token.RefreshToken);

        return new LoginResponseDTO(accessToken, refreshToken);
    }

    private Customer checkCustomerExistence(String email) throws CustomerNotFoundException {
        Customer customer = customerEntityService.findCustomer(email);
        if (customer == null) {
            throw new CustomerNotFoundException("Email or password is incorrect.");
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

    public String refreshAccessToken(RefreshTokenDTO refreshTokenDTO) throws InvalidTokenException, CustomerNotFoundException {
        String token = refreshTokenDTO.getRefreshToken();
        String eMail = jwtService.extractUserEmail(token);

        jwtService.isTokenExpiredOrInvalid(token);

        return jwtService.generateToken(eMail, JwtService.Token.AccessToken);
    }

/*    public Boolean checkJWT(String refreshTokenDTO) throws CustomerNotFoundException {
        if (Boolean.TRUE.equals(jwtService.validateToken(String.valueOf(refreshTokenDTO))))
            return true;
        return false;
    }*/
}


