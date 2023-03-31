package ac.at.fhcampuswien.carrental.entity.service;


import ac.at.fhcampuswien.carrental.entity.models.Rental;
import ac.at.fhcampuswien.carrental.entity.repository.RentalRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RentalEntityService{

    RentalRepository myRepo;

    Date x = new Date(2023,01,01);
    Date x2 = new Date(2023,01,15);

    public void runneri() {

        List<Long> myList = myRepo.findAllAvailableCarsBetweenDates(x, x2);

        for(long x : myList){
            System.out.println(x);
        }
    }
}
