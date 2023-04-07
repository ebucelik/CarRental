package ac.at.fhcampuswien.carrental;

import ac.at.fhcampuswien.carrental.entity.models.Car;
import ac.at.fhcampuswien.carrental.entity.models.Customer;
import ac.at.fhcampuswien.carrental.entity.models.Rental;
import ac.at.fhcampuswien.carrental.entity.repository.CarRepository;
import ac.at.fhcampuswien.carrental.entity.repository.CustomerRepository;
import ac.at.fhcampuswien.carrental.entity.repository.RentalRepository;
import ac.at.fhcampuswien.carrental.entity.service.RentalEntityService;
import ac.at.fhcampuswien.carrental.rest.services.CurrencySOAPService;
import ac.at.fhcampuswien.carrental.rest.services.RentalRestService;
import ac.at.fhcampuswien.carrental.wsdl.GetCurrencyCodes;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User Management API", version = "0.1"))
public class CarRentalApplication {

    @Autowired
    @NotNull
    private RentalRepository rentalRepository;
    @Autowired
    @NotNull
    private CarRepository carRepository;

    @Autowired
    @NotNull
    private CustomerRepository customerRepository;

    @Autowired
    CurrencySOAPService c;

    @PostConstruct
    public void initializeData() throws Exception {
        List<Car> cars = Stream.of(
                new Car(1L, 10, "Volkswagen", "Sharan", "140", "2023-01-01", "Diesel", "example.com"),
                new Car(2L, 20, "Audi", "Q5", "190", "2023-01-01", "Diesel", "example.com"),
                new Car(3L, 30, "Ferrari", "Enzo", "860", "2023-01-01", "Petrol", "example.com"),
                new Car(4L, 40, "Lamborghini", "Urus", "650", "2023-01-01", "Petrol", "example.com")
        ).collect(Collectors.toList());
        carRepository.saveAll(cars);

        List<Rental> rentals = Stream.of(
                new Rental(1001L,5001L,1L, LocalDate.of(2023,01,01), LocalDate.of(2023,01,10),100),
                new Rental(1002L,5001L,2L, LocalDate.of(2023,01,05), LocalDate.of(2023,01,15),100),
                new Rental(1003L,5001L,3L, LocalDate.of(2023,02,01), LocalDate.of(2023,01,10),100)
        ).collect(Collectors.toList());
        rentalRepository.saveAll(rentals);

        List<Customer> customers = Stream.of(
                new Customer("jt@.com", "Jonny", "Test", "Apfel", "123456789", "2000-01-01"),
                new Customer("jt2@.com", "Jenny", "Test", "Apfel2", "123456789", "2000-01-01")
        ).collect(Collectors.toList());
        customerRepository.saveAll(customers);

        GetCurrencyCodes ex = new GetCurrencyCodes();
        System.out.print(ex.getInput()+"apppppppppppppppppppppppppppp");
        c.getAllCurrencies(ex);

    }
    public static void main(String[] args) {
        SpringApplication.run(CarRentalApplication.class, args);
    }

}



