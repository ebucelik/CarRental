package ac.at.fhcampuswien.carrental.config;

import ac.at.fhcampuswien.carrental.wsdl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {

    public static String baseNameSpace;
    public static String baseURI;
    //private final String path = "src/main/resources/wsdl/currencyConverter.wsdl";
    public SoapClientConfig() throws Exception {
        baseNameSpace = "http://localhost:8000/";
        /*try
        {
            InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("api.wsdl");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceStream));

            String rootNameSpace = null;

            String wsdlLine;
            while((wsdlLine = bufferedReader.readLine()) != null)
            {
                if(wsdlLine.contains("targetNamespace") && rootNameSpace == null)
                {
                    int targetIndex = wsdlLine.indexOf("targetNamespace");
                    String result = wsdlLine.substring(targetIndex,wsdlLine.length());
                    rootNameSpace = getWSDLSubstring(result);
                }
                else if(wsdlLine.contains("portType") && baseNameSpace == null && rootNameSpace != null)
                {
                    baseNameSpace= rootNameSpace+"/"+ getWSDLSubstring(wsdlLine) +"/";
                }
                else if(wsdlLine.contains("location") && baseURI == null)
                {
                    baseURI = getWSDLSubstring(wsdlLine);
                }
            }
            System.out.println("This is baseNameSpace: "+rootNameSpace);
            System.out.println("This is baseURI: "+ baseURI);
        }
        catch (Exception e)
        {
            throw new Exception("Exception while finding out BaseNameSpace");
        }*/


    }

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(GetConvertedValue.class, GetConvertedValueResponse.class, GetCurrencyCodes.class, GetCurrencyCodesResponse.class, StringArray.class);
        return jaxb2Marshaller;
    }

   /* @Bean
    public CurrencyClient1 currencyClient(Jaxb2Marshaller jaxb2Marshaller) {
        CurrencyClient1 service = new CurrencyClient1();
        service.setDefaultUri(baseURI);
        service.setMarshaller(jaxb2Marshaller);
        service.setUnmarshaller(jaxb2Marshaller);
        return service;
    }*/

    public String getWSDLSubstring(String line)
    {
        return line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
    }
}