
package ac.at.fhcampuswien.carrental.config;


import ac.at.fhcampuswien.carrental.wsdl.GetCurrencyCodes;
import ac.at.fhcampuswien.carrental.wsdl.GetCurrencyCodesResponse;
import ac.at.fhcampuswien.carrental.wsdl.ObjectFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import javax.xml.bind.JAXBElement;


    public class CurrencyClient extends WebServiceGatewaySupport {

        private ObjectFactory factory;

        public CurrencyClient()
        {
            factory = new ObjectFactory();
        }
        public GetCurrencyCodesResponse getCurrencyResponse()
        {
            //ConvertCurrency getCurrencyRequest = new ConvertCurrency();
            // JAXBElement<String> sourceCurrencyElement = factory.createConvertCurrencySourceCurrency(sourceCurrency);
            //  JAXBElement<String> destinationCurrencyElement = factory.createConvertCurrencyDestinationCurrency(destinationCurrency);
//            getCurrencyRequest.setSourceCurrency((JAXBElement<String>)(factory.createConvertCurrencySourceCurrency(sourceCurrency)));
//            getCurrencyRequest.setDestinationCurrency((JAXBElement<String>)factory.createConvertCurrencyDestinationCurrency(destinationCurrency));
//            getCurrencyRequest.setValue(50.0);
            return (GetCurrencyCodesResponse) getWebServiceTemplate().marshalSendAndReceive(new SoapActionCallback(SoapClientConfig.baseNameSpace+"getCurrencyCodes"));
        }
        public ConvertCurrencyListResponse convertCurrencyListResponse(ArrayOfdouble values, String sourceCurrency, String destinationCurrency)
        {
            ConvertCurrencyList getCurrencyRequestList = new ConvertCurrencyList();

            getCurrencyRequestList.setValues((JAXBElement<ArrayOfdouble>) factory.createConvertCurrencyListValues(values));
            getCurrencyRequestList.setSourceCurrency((JAXBElement<String>) factory.createConvertCurrencySourceCurrency(sourceCurrency));
            getCurrencyRequestList.setDestinationCurrency((JAXBElement<String>) factory.createConvertCurrencyDestinationCurrency(destinationCurrency));
            return (ConvertCurrencyListResponse) getWebServiceTemplate().marshalSendAndReceive(getCurrencyRequestList,new SoapActionCallback(SoapClientConfig.baseNameSpace+"convertCurrencyList")) ;
        }


    }
