package ac.at.fhcampuswien.carrental.entity.service;


import ac.at.fhcampuswien.carrental.entity.models.Customer;
import ac.at.fhcampuswien.carrental.entity.repository.CustomerRepository;
import ac.at.fhcampuswien.carrental.exception.exceptions.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.carrental.rest.mapper.UserMapper;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerEntityService {

    CustomerRepository customerRepository;
    UserMapper userMapper;

    public void checkCustomerExistence(String email) throws CustomerAlreadyExistsException {
        if (customerRepository.existsByeMail(email)) {
            throw new CustomerAlreadyExistsException("A user for this email is already existing. Please try to log in.");
        }
    }

    public Customer findCustomer(String email) {
        return customerRepository.findByeMail(email);
    }

    public RegistrationResponseDto addCustomer(RegistrationRequestDto registrationRequestDto) throws CustomerAlreadyExistsException {
        checkCustomerExistence(registrationRequestDto.getEMail());

        Customer customer = userMapper.requestMapping(registrationRequestDto);
        Customer dbResponse = customerRepository.save(customer);

        return userMapper.responseMapping(dbResponse);
    }
}
