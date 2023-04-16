package ac.at.fhcampuswien.carrental.rest.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CurrencyRequestDto {

    @NotBlank
    @NotEmpty
    List<String> exchangeRates;
}
