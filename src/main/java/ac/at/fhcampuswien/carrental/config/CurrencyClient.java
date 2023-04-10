
package ac.at.fhcampuswien.carrental.config;


import ac.at.fhcampuswien.carrental.wsdl.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


@Service
public class CurrencyClient{
    @Autowired
    @NotNull
    private Jaxb2Marshaller marshaller;

    private WebServiceTemplate template;

    private final String URI = "http://localhost:8000/";

    public GetCurrencyCodesResponse getCurrencyResponse(GetCurrencyCodes ex) throws Exception {
        SoapClientConfig config = new SoapClientConfig();
        //Convert Object in XML
        template = new WebServiceTemplate(marshaller);
        GetCurrencyCodesResponse z = (GetCurrencyCodesResponse) template.marshalSendAndReceive(URI, ex);
        StringArray as = z.getGetCurrencyCodesResult();
        System.out.println("The List= " + z.getGetCurrencyCodesResult().getString().get(1));
        return z;
    }

    public GetConvertedValueResponse getCurrencyValue(GetConvertedValue ex) throws Exception {

        ex.setCurrentValue(5f);
        ex.setCurrentCurrencyCode("EUR");
        ex.setExpectedCurrencyCode("GBP");
        template = new WebServiceTemplate(marshaller);
        GetConvertedValueResponse z = (GetConvertedValueResponse) template.marshalSendAndReceive(URI, ex);
        System.out.println("The result= " + z.getGetConvertedValueResult());
        return z;
    }



}
