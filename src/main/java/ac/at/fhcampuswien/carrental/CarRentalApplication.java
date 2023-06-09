package ac.at.fhcampuswien.carrental;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.models.Rental;
import ac.at.fhcampuswien.carrental.entity.repository.CarRepository;
import ac.at.fhcampuswien.carrental.entity.repository.RentalRepository;
import ac.at.fhcampuswien.carrental.rest.services.CurrencyClient;
import ac.at.fhcampuswien.carrental.rest.services.CurrencySOAPService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Carrental API", version = "1.0"))
public class CarRentalApplication {

    @Autowired
    @NotNull
    private RentalRepository rentalRepository;
    @Autowired
    @NotNull
    private CarRepository carRepository;

    @Autowired
    CurrencySOAPService currencySOAPService;

    @Autowired
    CurrencyClient currencyClient;

    @PostConstruct
    public void initializeData() throws Exception {
        List<Car> cars = Stream.of(
                new Car(1L, 70, "Volkswagen", "Sharan", "140", "2015-01-01", "Diesel", "https://www.autoscout24.at/cms-content-assets/7um8LehWjr8ivfvWpoKWS9-4554fa427501bdcf82d97a5b586f15b1-3jcYw3F2oycED9VRLNLvJ4-e2de141217a08ff758af9afc697554b8-Volkswagen-Golf-2020-1600-04-1100-1100.jpg"),
                new Car(2L, 80, "Audi", "Q5", "190", "2020-01-01", "Diesel", "https://m.faz.net/media0/ppmedia/aktuell/295225037/1.7484902/mmobject-still_full/rein-elektrischer-fuenfmeter.jpg"),
                new Car(3L, 700, "Ferrari", "Enzo", "860", "2023-01-01", "Petrol", "https://cdn.motor1.com/images/mgl/Znnm7r/s1/ferrari-sp48-unica.jpg"),
                new Car(4L, 800, "Lamborghini", "Urus", "650", "2023-01-01", "Petrol", "https://www.lamborghini.com/sites/it-en/files/DAM/lamborghini/facelift_2019/model_gw/urus/2023/model_chooser/urus_performante_m.jpg"),
                new Car(5L, 90, "Mercedes Benz", "CLA 180", "122", "2016-05-01", "Petrol", "https://cdn.guterate.net/prod/2022/07/451536_01.jpg")
        ).collect(Collectors.toList());
        carRepository.saveAll(cars);

        List<Rental> rentals = Stream.of(
                new Rental(1L, 1L, 1L, LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 10), 100),
                new Rental(2L, 2L, 2L, LocalDate.of(2023, 01, 05), LocalDate.of(2023, 01, 15), 100),
                new Rental(3L, 3L, 3L, LocalDate.of(2023, 02, 01), LocalDate.of(2023, 02, 10), 100)
        ).collect(Collectors.toList());
        rentalRepository.saveAll(rentals);

    }

    public static void main(String[] args) {
        SpringApplication.run(CarRentalApplication.class, args);
    }

}



