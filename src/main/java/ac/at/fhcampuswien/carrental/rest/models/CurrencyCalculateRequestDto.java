package ac.at.fhcampuswien.carrental.rest.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
public class CurrencyCalculateRequestDto {

    float totalCost;
    String currentCurrency;
    String excpectedCurrency;
}
