//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.04.07 um 05:03:12 PM CEST 
//


package ac.at.fhcampuswien.carrental.wsdl;


import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * <p>Java-Klasse für getConvertedValue complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="getConvertedValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="current_value" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/&gt;
 *         &lt;element name="current_currency_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="expected_currency_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */

@jakarta.xml.bind.annotation.XmlRootElement(name = "getConvertedValue", namespace = "currencyconverter.ac.at.fhcampuswien")
@jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType.FIELD)
@jakarta.xml.bind.annotation.XmlType(name = "getConvertedValue", propOrder = {
    "currentValue",
    "currentCurrencyCode",
    "expectedCurrencyCode"
})
@AllArgsConstructor
@NoArgsConstructor
public class GetConvertedValue {

    @jakarta.xml.bind.annotation.XmlElement(name = "current_value", namespace = "currencyconverter.ac.at.fhcampuswien")
    protected Float currentValue;
    @jakarta.xml.bind.annotation.XmlElement(name = "current_currency_code", namespace = "currencyconverter.ac.at.fhcampuswien")
    protected String currentCurrencyCode;
    @jakarta.xml.bind.annotation.XmlElement(name = "expected_currency_code", namespace = "currencyconverter.ac.at.fhcampuswien")
    protected String expectedCurrencyCode;

    /**
     * Ruft den Wert der currentValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Float }{@code >}
     *     
     */
    public Float getCurrentValue() {
        return currentValue;
    }

    /**
     * Legt den Wert der currentValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Float }{@code >}
     *     
     */
    public void setCurrentValue(Float value) {
        this.currentValue = value;
    }

    /**
     * Ruft den Wert der currentCurrencyCode-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getCurrentCurrencyCode() {
        return currentCurrencyCode;
    }

    /**
     * Legt den Wert der currentCurrencyCode-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCurrentCurrencyCode(String value) {
        this.currentCurrencyCode = value;
    }

    /**
     * Ruft den Wert der expectedCurrencyCode-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getExpectedCurrencyCode() {
        return expectedCurrencyCode;
    }

    /**
     * Legt den Wert der expectedCurrencyCode-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExpectedCurrencyCode(String value) {
        this.expectedCurrencyCode = value;
    }

}
