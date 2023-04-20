package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.exception.exceptions.CurrencyServiceNotAvailableException;
import ac.at.fhcampuswien.carrental.wsdl.GetConvertedValue;
import ac.at.fhcampuswien.carrental.wsdl.GetCurrencyCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencySOAPService {

    @Autowired
    CurrencyClient currencyClient;

    public Double getConvertedValue(GetConvertedValue getConvertedValue) throws CurrencyServiceNotAvailableException {
        return currencyClient
                .getCurrencyValue(getConvertedValue)
                .getGetConvertedValueResult();
    }

    public List<String> getCurrencyCodes(GetCurrencyCodes getCurrencyCodes) throws CurrencyServiceNotAvailableException {
        return currencyClient
                .getCurrencyResponse(getCurrencyCodes)
                .getGetCurrencyCodesResult()
                .getString();
    }





}
