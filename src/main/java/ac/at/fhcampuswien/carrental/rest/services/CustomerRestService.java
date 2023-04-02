package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.entity.models.Customer;
import ac.at.fhcampuswien.carrental.entity.service.CustomerEntityService;
import ac.at.fhcampuswien.carrental.expections.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.carrental.rest.models.LoginDTO;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationResponseDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerRestService {


    @NonNull
    CustomerEntityService customerEntityService;


    public RegistrationResponseDto createCustomer(RegistrationRequestDto registrationRequestDto) throws CustomerAlreadyExistsException {
        return customerEntityService.addCustomer(registrationRequestDto);
    }

    public String login(LoginDTO logindata){
        return customerEntityService.customerLogin(logindata);
    }

}
