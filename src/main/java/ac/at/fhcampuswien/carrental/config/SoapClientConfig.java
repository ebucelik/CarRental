package ac.at.fhcampuswien.carrental.config;

import ac.at.fhcampuswien.carrental.wsdl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(
                GetConvertedValue.class,
                GetConvertedValueResponse.class,
                GetCurrencyCodes.class,
                GetCurrencyCodesResponse.class,
                StringArray.class
        );
        return jaxb2Marshaller;
    }

}