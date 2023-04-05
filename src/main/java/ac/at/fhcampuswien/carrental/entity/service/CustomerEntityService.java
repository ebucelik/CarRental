package ac.at.fhcampuswien.carrental.entity.service;


import ac.at.fhcampuswien.carrental.entity.models.Customer;
import ac.at.fhcampuswien.carrental.entity.repository.CustomerRepository;
import ac.at.fhcampuswien.carrental.expections.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.carrental.rest.mapper.UserMapper;
import ac.at.fhcampuswien.carrental.rest.models.LoginDTO;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerEntityService {


    CustomerRepository customerRepository;

    UserMapper userMapper;


    public boolean checkCustomerExistance(String email) {
        return customerRepository.existsByeMail(email);
    }

    public Customer findCustomer(String email){
        return customerRepository.findByeMail(email);
    }

    public RegistrationResponseDto addCustomer(RegistrationRequestDto customerDto) throws CustomerAlreadyExistsException {
        if (checkCustomerExistance(customerDto.getEMail())) {
            throw new CustomerAlreadyExistsException("This Customer exists already.");
        }
        Customer customer = userMapper.requestMapping(customerDto);
        Customer dbResponse = customerRepository.save(customer);
        return userMapper.responseMapping(dbResponse);
    }

}
