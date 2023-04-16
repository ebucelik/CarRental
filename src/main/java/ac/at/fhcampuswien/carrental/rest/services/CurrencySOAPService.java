package ac.at.fhcampuswien.carrental.rest.services;


//import ac.at.fhcampuswien.carrental.config.CurrencyClient;
import ac.at.fhcampuswien.carrental.config.CurrencyClient;
import ac.at.fhcampuswien.carrental.wsdl.GetConvertedValue;
import ac.at.fhcampuswien.carrental.wsdl.GetConvertedValueResponse;
import ac.at.fhcampuswien.carrental.wsdl.GetCurrencyCodes;
import ac.at.fhcampuswien.carrental.wsdl.GetCurrencyCodesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CurrencySOAPService {


    @Autowired
    CurrencyClient currencyClient;
    public boolean checkIfValidCurrency(String currency) {
        return customerCurrencies(currency);
    }


    public void getAllCurrencies(GetConvertedValue ex) throws Exception {

        GetConvertedValueResponse n = currencyClient.getCurrencyValue(ex);
       System.out.print(n.getGetConvertedValueResult().toString());
    }
    public void getAllCurrencies1(GetCurrencyCodes ex) throws Exception {

        GetCurrencyCodesResponse n = currencyClient.getCurrencyResponse(ex);
        for (String x: n.getGetCurrencyCodesResult().getString()) {
            System.out.println("Value: " + x);
        }
    }


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
