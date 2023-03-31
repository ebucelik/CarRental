package ac.at.fhcampuswien.carrental;

import ac.at.fhcampuswien.carrental.entity.models.Rental;
import ac.at.fhcampuswien.carrental.entity.repository.RentalRepository;
import ac.at.fhcampuswien.carrental.entity.service.RentalEntityService;
import ac.at.fhcampuswien.carrental.rest.services.RentalRestService;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CarRentalApplication {

    public static void main(String[] args) {


        SpringApplication app = new SpringApplication(CarRentalApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext context = app.run(args);

        CarRentalApplication carRentalApplication = context.getBean(CarRentalApplication.class);
        carRentalApplication.run();
    }
    }

/*

        run();
        SpringApplication.run(CarRentalApplication.class, args);



    }


