package ac.at.fhcampuswien.carrental.rest.mapper;

import ac.at.fhcampuswien.carrental.entity.models.Customer;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public class UserMapper {

    public Customer requestMapping(RegistrationRequestDto requestDto) {
        Customer customer = new Customer(requestDto.getEMail(), requestDto.getFirstName(), requestDto.getLastName(), requestDto.getPassword(), requestDto.getPhoneNumber(), requestDto.getDateOfBirth());
        return customer;
    }

    public RegistrationResponseDto responseMapping(Customer customer) {
        return RegistrationResponseDto.builder()
                .id(customer.getId())
                .eMail(customer.getEMail())
                .build();
    }
}
