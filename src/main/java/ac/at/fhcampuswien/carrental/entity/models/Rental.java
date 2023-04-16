package ac.at.fhcampuswien.carrental.entity.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "rentals")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long customerId;
    private Long carId;
    private LocalDate startDay;
    private LocalDate endDay;
    private float totalCost;
}



