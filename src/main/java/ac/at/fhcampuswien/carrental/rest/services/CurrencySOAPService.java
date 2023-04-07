package ac.at.fhcampuswien.carrental.rest.services;


import ac.at.fhcampuswien.carrental.rest.models.CurrencyRequestDto;
import ac.at.fhcampuswien.carrental.rest.models.CurrencyResponseDto;
import ac.at.fhcampuswien.carrental.wsdl.GetCurrencyCodes;
import ac.at.fhcampuswien.carrental.wsdl.GetCurrencyCodesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CurrencySOAPService {
    public boolean checkIfValidCurrency(String currency) {
        return customerCurrencies(currency);
    }


/*    public CurrencyResponseDto getAllCurrencies(){

       return new CurrencyResponseDto(currencyConversationService.listCurrencies().getString());
    }*/


    //ToDO this have to be refacored in one file
    private boolean customerCurrencies(String currency) {
        ArrayList<String> currencies = new ArrayList<>();
        currencies.add("USD");
        currencies.add("EUR");
        currencies.add("JPY");
        currencies.add("BGN");
        currencies.add("CZK");
        currencies.add("DKK");
        currencies.add("GBP");
        currencies.add("HUF");
        currencies.add("PLN");
        currencies.add("RON");
        currencies.add("SEK");
        currencies.add("CHF");
        currencies.add("ISK");
        currencies.add("NOK");
        currencies.add("HRK");
        currencies.add("TRY");
        currencies.add("AUD");
        currencies.add("BRL");
        currencies.add("CAD");
        currencies.add("CNY");
        currencies.add("HKD");
        currencies.add("IDR");
        currencies.add("ILS");
        currencies.add("INR");
        currencies.add("KRW");
        currencies.add("MXN");
        currencies.add("MYR");
        currencies.add("NZD");
        currencies.add("PHP");
        currencies.add("SGD");
        currencies.add("THB");
        currencies.add("ZAR");

        for (String element : currencies) {
            if (element.equals(currency))
                return true;
        }
        return false;
    }

}
