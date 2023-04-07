
package ac.at.fhcampuswien.carrental.config;


import ac.at.fhcampuswien.carrental.wsdl.GetCurrencyCodes;
import ac.at.fhcampuswien.carrental.wsdl.GetCurrencyCodesResponse;
import ac.at.fhcampuswien.carrental.wsdl.ObjectFactory;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


@Service
    public class CurrencyClient extends WebServiceGatewaySupport {




        @Autowired
        private Jaxb2Marshaller marshaller;

        private WebServiceTemplate template;
        public GetCurrencyCodesResponse getCurrencyResponse(GetCurrencyCodes ex) throws Exception {
            SoapClientConfig config = new SoapClientConfig();
            template = new WebServiceTemplate(marshaller);
            //ConvertCurrency getCurrencyRequest = new ConvertCurrency();
            // JAXBElement<String> sourceCurrencyElement = factory.createConvertCurrencySourceCurrency(sourceCurrency);
            //  JAXBElement<String> destinationCurrencyElement = factory.createConvertCurrencyDestinationCurrency(destinationCurrency);
//            getCurrencyRequest.setSourceCurrency((JAXBElement<String>)(factory.createConvertCurrencySourceCurrency(sourceCurrency)));
//            getCurrencyRequest.setDestinationCurrency((JAXBElement<String>)factory.createConvertCurrencyDestinationCurrency(destinationCurrency));
//            getCurrencyRequest.setValue(50.0);

            return (GetCurrencyCodesResponse) template.marshalSendAndReceive("http://localhost:8000/", ex);

        }
        /*public ConvertCurrencyListResponse convertCurrencyListResponse(ArrayOfdouble values, String sourceCurrency, String destinationCurrency)
        {
            ConvertCurrencyList getCurrencyRequestList = new ConvertCurrencyList();

            getCurrencyRequestList.setValues((JAXBElement<ArrayOfdouble>) factory.createConvertCurrencyListValues(values));
            getCurrencyRequestList.setSourceCurrency((JAXBElement<String>) factory.createConvertCurrencySourceCurrency(sourceCurrency));
            getCurrencyRequestList.setDestinationCurrency((JAXBElement<String>) factory.createConvertCurrencyDestinationCurrency(destinationCurrency));
            return (ConvertCurrencyListResponse) getWebServiceTemplate().marshalSendAndReceive(getCurrencyRequestList,new SoapActionCallback(SoapClientConfig.baseNameSpace+"convertCurrencyList")) ;
        }*/


    }
