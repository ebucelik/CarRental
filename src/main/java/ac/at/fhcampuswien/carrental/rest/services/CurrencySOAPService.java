package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.config.CurrencyClient;
import ac.at.fhcampuswien.carrental.rest.models.CurrencyResponseDto;
import ac.at.fhcampuswien.carrental.wsdl.GetConvertedValue;
import ac.at.fhcampuswien.carrental.wsdl.GetConvertedValueResponse;
import ac.at.fhcampuswien.carrental.wsdl.GetCurrencyCodes;
import ac.at.fhcampuswien.carrental.wsdl.GetCurrencyCodesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencySOAPService {

    @Autowired
    CurrencyClient currencyClient;

    public Double getConvertedValue(GetConvertedValue getConvertedValue) throws Exception {
        return currencyClient
                .getCurrencyValue(getConvertedValue)
                .getGetConvertedValueResult();
    }

    public List<String> getCurrencyCodes(GetCurrencyCodes getCurrencyCodes) throws Exception {
        return currencyClient
                .getCurrencyResponse(getCurrencyCodes)
                .getGetCurrencyCodesResult()
                .getString();
    }
}
