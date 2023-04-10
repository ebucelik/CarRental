//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.04.07 um 05:03:12 PM CEST 
//


package ac.at.fhcampuswien.carrental.wsdl;



import jakarta.xml.bind.annotation.*;


/**
 * <p>Java-Klasse für getConvertedValueResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="getConvertedValueResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getConvertedValueResult" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@jakarta.xml.bind.annotation.XmlRootElement(name = "getConvertedValueResponse", namespace = "currencyconverter.ac.at.fhcampuswien")
@jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType.FIELD)
@jakarta.xml.bind.annotation.XmlType(name = "getConvertedValueResponse", propOrder = {
    "getConvertedValueResult"
})
public class GetConvertedValueResponse {

    @jakarta.xml.bind.annotation.XmlElement(name = "getConvertedValueResult", namespace = "currencyconverter.ac.at.fhcampuswien")
    protected Double getConvertedValueResult;

    /**
     * Ruft den Wert der getConvertedValueResult-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public Double getGetConvertedValueResult() {
        return getConvertedValueResult;
    }

    /**
     * Legt den Wert der getConvertedValueResult-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setGetConvertedValueResult(Double value) {
        this.getConvertedValueResult = value;
    }

}
