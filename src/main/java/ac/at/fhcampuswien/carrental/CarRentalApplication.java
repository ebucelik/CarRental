package ac.at.fhcampuswien.carrental;

import ac.at.fhcampuswien.carrental.entity.models.Rental;
import ac.at.fhcampuswien.carrental.entity.repository.RentalRepository;
import ac.at.fhcampuswien.carrental.entity.service.RentalEntityService;
import ac.at.fhcampuswien.carrental.rest.services.RentalRestService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User Management API", version = "0.1"))
public class CarRentalApplication {


    public static void main(String[] args) {

        SpringApplication.run(CarRentalApplication.class, args);
    }

}




