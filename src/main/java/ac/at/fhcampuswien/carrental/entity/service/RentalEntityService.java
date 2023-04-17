package ac.at.fhcampuswien.carrental.entity.service;


import ac.at.fhcampuswien.carrental.entity.models.Rental;
import ac.at.fhcampuswien.carrental.entity.repository.CustomerRepository;
import ac.at.fhcampuswien.carrental.entity.repository.RentalRepository;
import ac.at.fhcampuswien.carrental.rest.models.RentalRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalResponseDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalUpdateRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.RentalUpdateResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ac.at.fhcampuswien.carrental.rest.mapper.UserMapper;

import java.sql.DataTruncation;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RentalEntityService extends Throwable {

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CustomerRepository customerRepository;

    public RentalResponseDto createBooking(RentalRequestDto rentalRequestDto, String eMail) {
        Long id = customerRepository.findIdByeMailIgnoreCase(eMail);
        Rental rentalBooking = userMapper.BookingRequestToRental(rentalRequestDto, id);
        Rental savedRental = rentalRepository.save(rentalBooking);
        return userMapper.RentalToBookingResponse(savedRental, eMail);
    }


    public List<Rental> getAllRentals(String eMail) {
        Long id = customerRepository.findIdByeMailIgnoreCase(eMail);
        List<Rental> rentals;
        rentals = rentalRepository.findByCustomerId(id);
        return rentals;
    }

    public Optional<Rental> getBookingById(long id) {
        return rentalRepository.findById(id);

    }

    public void deleteBooking(Long id) {
        rentalRepository.deleteById(id);
    }

    public RentalUpdateResponseDto updateBooking(RentalUpdateRequestDto rentalUpdateRequestDto) throws RentalEntityService {
       try{
           Rental rentalUpdate = rentalRepository.updateRental(rentalUpdateRequestDto.getCarId(), rentalUpdateRequestDto.getStartDay(),
                   rentalUpdateRequestDto.getEndDay(), rentalUpdateRequestDto.getTotalCost(), rentalUpdateRequestDto.getId());
           return userMapper.RentalToUpdateResponse(rentalUpdate);
       } catch(DataAccessException ex){

       }

        return null;
    }

}
