package ac.at.fhcampuswien.carrental.rest.mapper;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.models.Customer;
import ac.at.fhcampuswien.carrental.entity.models.Rental;
import ac.at.fhcampuswien.carrental.rest.models.*;
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


    public RentalUpdateResponseDto RentalToUpdateResponse(Rental rentalUpdate) {
        return RentalUpdateResponseDto.builder()
                .id(rentalUpdate.getId())
                .carId(rentalUpdate.getCarId())
                .startDay(rentalUpdate.getStartDay())
                .endDay(rentalUpdate.getEndDay())
                .totalCost(rentalUpdate.getTotalCost())
                .build();
    }

    public CarListDTO carToDisplayList(Car car, float dailyCostConverted, float totalCost){
        return CarListDTO.builder()
                .id(car.getId())
                .dailyCost(dailyCostConverted)
                .brand(car.getBrand())
                .model(car.getModel())
                .hp(car.getHp())
                .buildDate(car.getBuildDate())
                .fuelType(car.getFuelType())
                .imageLink(car.getImageLink())
                .totalCosts(totalCost)
                .build();
    }
}
