package ac.at.fhcampuswien.carrental.rest.mapper;

import ac.at.fhcampuswien.carrental.entity.models.Customer;
import ac.at.fhcampuswien.carrental.entity.models.Rental;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RegistrationResponseDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public class UserMapper {

    public Customer requestMapping(RegistrationRequestDto requestDto) {
        Customer customer = Customer.builder()
                .eMail(requestDto.getEMail())
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .dateOfBirth(requestDto.getDateOfBirth())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();
        customer.setPassword(requestDto.getPassword());
        return customer;
    }

    public RegistrationResponseDto responseMapping(Customer customer) {
        return RegistrationResponseDto.builder()
                .id(customer.getId())
                .eMail(customer.getEMail())
                .build();
    }

    public Rental BookingRequestToRental(RentalRequestDto rentalBooking, Long id){
        return Rental.builder()
                .customerId(id)
                .carId(rentalBooking.getCarId())
                .startDay(rentalBooking.getStartDay())
                .endDay(rentalBooking.getEndDay())
                .totalCost(rentalBooking.getTotalCost())
                .build();
    }

    public RentalResponseDto RentalToBookingResponse(Rental rental, String eMail){
        return RentalResponseDto.builder()
                .eMail(eMail)
                .carId(rental.getCarId())
                .startDay(rental.getStartDay())
                .endDay(rental.getEndDay())
                .totalCost(rental.getTotalCost())
                .build();
    }
}
