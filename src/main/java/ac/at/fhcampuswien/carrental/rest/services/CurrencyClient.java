
package ac.at.fhcampuswien.carrental.rest.services;

import ac.at.fhcampuswien.carrental.exception.exceptions.CurrencyServiceNotAvailableException;
import ac.at.fhcampuswien.carrental.wsdl.*;
import jakarta.validation.constraints.NotNull;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import java.io.IOException;

@Service
public class CurrencyClient {
    @Autowired
    @NotNull
    private Jaxb2Marshaller marshaller;

    @Autowired
    @NotNull
    private HttpComponentsMessageSender httpComponentsMessageSender;

    private WebServiceTemplate template;

    private final String URI = "http://54.93.118.155:8501";

    private final String basicAuth = "Basic 1101bf5c602959307750538c2f57b5d359b5eb66ed13623b814aafead8038ebf";

    public GetCurrencyCodesResponse getCurrencyResponse(GetCurrencyCodes getCurrencyCodes) throws CurrencyServiceNotAvailableException {
        configureTemplate();

        try {
            GetCurrencyCodesResponse response = (GetCurrencyCodesResponse) template.marshalSendAndReceive(getCurrencyCodes, message -> {
                SoapMessage soapMessage = (SoapMessage) message;
                SoapHeader soapHeader = soapMessage.getSoapHeader();

                StringSource headerSource = new StringSource("<cur:RequestHeader xmlns:cur=\"currencyconverter.ac.at.fhcampuswien\">\n" +
                        "<cur:authentication>" + basicAuth + "</cur:authentication>\n" +
                        "</cur:RequestHeader>"
                );

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(headerSource, soapHeader.getResult());
            });
            return response;
        } catch(WebServiceIOException e){
            if (e.getCause() instanceof ConnectTimeoutException) {
                throw new CurrencyServiceNotAvailableException("Currency Service not available!");
            }
            return null;
        }
    }

    public GetConvertedValueResponse getCurrencyValue(GetConvertedValue getConvertedValue) throws CurrencyServiceNotAvailableException{
        configureTemplate();

        try {
            GetConvertedValueResponse response = (GetConvertedValueResponse) template.marshalSendAndReceive(getConvertedValue, message -> {
                SoapMessage soapMessage = (SoapMessage) message;
                SoapHeader soapHeader = soapMessage.getSoapHeader();
                StringSource headerSource = new StringSource("<cur:RequestHeader xmlns:cur=\"currencyconverter.ac.at.fhcampuswien\">\n" +
                        "<cur:authentication>" + basicAuth + "</cur:authentication>\n" +
                        "</cur:RequestHeader>"
                );
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(headerSource, soapHeader.getResult());
            });
            return response;
        } catch (WebServiceIOException e) {
            if (e.getCause() instanceof ConnectTimeoutException) {
                throw new CurrencyServiceNotAvailableException("Currency Service not available!");
            }
        }
        return null;
    }

    private void configureTemplate(){
        template = new WebServiceTemplate(marshaller);
        template.setDefaultUri(URI);
        template.setMessageSender(httpComponentsMessageSender);
    }
}
