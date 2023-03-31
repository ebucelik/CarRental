package ac.at.fhcampuswien.carrental.entity.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float dailyCost;
    private String brand;
    private String model;

    private String hp;
    private String buildDate;

    private String fuelType;
    private String imageLink;
}
