//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.04.07 um 05:03:12 PM CEST 
//


package ac.at.fhcampuswien.carrental.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>Java-Klasse für getCurrencyCodesResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="getCurrencyCodesResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getCurrencyCodesResult" type="{currencyconverter.ac.at.fhcampuswien}stringArray" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCurrencyCodesResponse", propOrder = {
    "getCurrencyCodesResult"
})
@XmlSeeAlso({GetCurrencyCodes.class})
public class GetCurrencyCodesResponse {

    @XmlElementRef(name = "getCurrencyCodesResult", namespace = "currencyconverter.ac.at.fhcampuswien", type = JAXBElement.class, required = false)
    protected JAXBElement<StringArray> getCurrencyCodesResult;

    /**
     * Ruft den Wert der getCurrencyCodesResult-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StringArray }{@code >}
     *     
     */
    public JAXBElement<StringArray> getGetCurrencyCodesResult() {
        return getCurrencyCodesResult;
    }

    /**
     * Legt den Wert der getCurrencyCodesResult-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StringArray }{@code >}
     *     
     */
    public void setGetCurrencyCodesResult(JAXBElement<StringArray> value) {
        this.getCurrencyCodesResult = value;
    }

}
