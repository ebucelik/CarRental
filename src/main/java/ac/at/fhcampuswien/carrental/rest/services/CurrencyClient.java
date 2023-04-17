
package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.wsdl.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import java.io.IOException;

@Service
public class CurrencyClient{
    @Autowired
    @NotNull
    private Jaxb2Marshaller marshaller;

    private WebServiceTemplate template;

    private final String URI = "http://54.93.118.155:8501";

    private final String basicAuth = "Basic 1101bf5c602959307750538c2f57b5d359b5eb66ed13623b814aafead8038ebf";

    public GetCurrencyCodesResponse getCurrencyResponse(GetCurrencyCodes getCurrencyCodes) throws Exception {
        template = new WebServiceTemplate(marshaller);
        template.setDefaultUri(URI);

        return (GetCurrencyCodesResponse) template.marshalSendAndReceive(getCurrencyCodes, new WebServiceMessageCallback() {
            @Override
            public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
                SoapMessage soapMessage = (SoapMessage) message;
                SoapHeader soapHeader = soapMessage.getSoapHeader();

                StringSource headerSource = new StringSource("<cur:RequestHeader xmlns:cur=\"currencyconverter.ac.at.fhcampuswien\">\n" +
                        "<cur:authentication>" + basicAuth + "</cur:authentication>\n" +
                        "</cur:RequestHeader>"
                );

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(headerSource, soapHeader.getResult());
            }
        });
    }

    public GetConvertedValueResponse getCurrencyValue(GetConvertedValue getConvertedValue) throws Exception {
        template = new WebServiceTemplate(marshaller);
        template.setDefaultUri(URI);

        return (GetConvertedValueResponse) template.marshalSendAndReceive(getConvertedValue, message -> {
            SoapMessage soapMessage = (SoapMessage) message;
            SoapHeader soapHeader = soapMessage.getSoapHeader();
            StringSource headerSource = new StringSource("<cur:RequestHeader xmlns:cur=\"currencyconverter.ac.at.fhcampuswien\">\n" +
                    "<cur:authentication>" + basicAuth + "</cur:authentication>\n" +
                    "</cur:RequestHeader>"
            );

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(headerSource, soapHeader.getResult());
        });
    }
}
