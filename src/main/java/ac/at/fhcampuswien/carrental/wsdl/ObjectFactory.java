//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2023.04.07 um 05:03:12 PM CEST 
//


package ac.at.fhcampuswien.carrental.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InvalidInputError_QNAME = new QName("currencyconverter.ac.at.fhcampuswien", "InvalidInputError");
    private final static QName _StringArray_QNAME = new QName("currencyconverter.ac.at.fhcampuswien", "stringArray");
    private final static QName _GetConvertedValue_QNAME = new QName("currencyconverter.ac.at.fhcampuswien", "getConvertedValue");
    private final static QName _GetConvertedValueResponse_QNAME = new QName("currencyconverter.ac.at.fhcampuswien", "getConvertedValueResponse");
    private final static QName _GetCurrencyCodes_QNAME = new QName("currencyconverter.ac.at.fhcampuswien", "getCurrencyCodes");
    private final static QName _GetCurrencyCodesResponse_QNAME = new QName("currencyconverter.ac.at.fhcampuswien", "getCurrencyCodesResponse");
    private final static QName _GetCurrencyCodesResponseGetCurrencyCodesResult_QNAME = new QName("currencyconverter.ac.at.fhcampuswien", "getCurrencyCodesResult");
    private final static QName _GetCurrencyCodesInput_QNAME = new QName("currencyconverter.ac.at.fhcampuswien", "input");
    private final static QName _GetConvertedValueResponseGetConvertedValueResult_QNAME = new QName("currencyconverter.ac.at.fhcampuswien", "getConvertedValueResult");
    private final static QName _GetConvertedValueCurrentValue_QNAME = new QName("currencyconverter.ac.at.fhcampuswien", "current_value");
    private final static QName _GetConvertedValueCurrentCurrencyCode_QNAME = new QName("currencyconverter.ac.at.fhcampuswien", "current_currency_code");
    private final static QName _GetConvertedValueExpectedCurrencyCode_QNAME = new QName("currencyconverter.ac.at.fhcampuswien", "expected_currency_code");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvalidInputError }
     * 
     */
    public InvalidInputError createInvalidInputError() {
        return new InvalidInputError();
    }

    /**
     * Create an instance of {@link StringArray }
     * 
     */
    public StringArray createStringArray() {
        return new StringArray();
    }

    /**
     * Create an instance of {@link GetConvertedValue }
     * 
     */
    public GetConvertedValue createGetConvertedValue() {
        return new GetConvertedValue();
    }

    /**
     * Create an instance of {@link GetConvertedValueResponse }
     * 
     */
    public GetConvertedValueResponse createGetConvertedValueResponse() {
        return new GetConvertedValueResponse();
    }

    /**
     * Create an instance of {@link GetCurrencyCodes }
     * 
     */
    public GetCurrencyCodes createGetCurrencyCodes() {
        return new GetCurrencyCodes();
    }

    /**
     * Create an instance of {@link GetCurrencyCodesResponse }
     * 
     */
    public GetCurrencyCodesResponse createGetCurrencyCodesResponse() {
        return new GetCurrencyCodesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidInputError }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InvalidInputError }{@code >}
     */
    @XmlElementDecl(namespace = "currencyconverter.ac.at.fhcampuswien", name = "InvalidInputError")
    public JAXBElement<InvalidInputError> createInvalidInputError(InvalidInputError value) {
        return new JAXBElement<InvalidInputError>(_InvalidInputError_QNAME, InvalidInputError.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringArray }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StringArray }{@code >}
     */
    @XmlElementDecl(namespace = "currencyconverter.ac.at.fhcampuswien", name = "stringArray")
    public JAXBElement<StringArray> createStringArray(StringArray value) {
        return new JAXBElement<StringArray>(_StringArray_QNAME, StringArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConvertedValue }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetConvertedValue }{@code >}
     */
    @XmlElementDecl(namespace = "currencyconverter.ac.at.fhcampuswien", name = "getConvertedValue")
    public JAXBElement<GetConvertedValue> createGetConvertedValue(GetConvertedValue value) {
        return new JAXBElement<GetConvertedValue>(_GetConvertedValue_QNAME, GetConvertedValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConvertedValueResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetConvertedValueResponse }{@code >}
     */
    @XmlElementDecl(namespace = "currencyconverter.ac.at.fhcampuswien", name = "getConvertedValueResponse")
    public JAXBElement<GetConvertedValueResponse> createGetConvertedValueResponse(GetConvertedValueResponse value) {
        return new JAXBElement<GetConvertedValueResponse>(_GetConvertedValueResponse_QNAME, GetConvertedValueResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrencyCodes }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrencyCodes }{@code >}
     */
    @XmlElementDecl(namespace = "currencyconverter.ac.at.fhcampuswien", name = "getCurrencyCodes")
    public JAXBElement<GetCurrencyCodes> createGetCurrencyCodes(GetCurrencyCodes value) {
        return new JAXBElement<GetCurrencyCodes>(_GetCurrencyCodes_QNAME, GetCurrencyCodes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrencyCodesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCurrencyCodesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "currencyconverter.ac.at.fhcampuswien", name = "getCurrencyCodesResponse")
    public JAXBElement<GetCurrencyCodesResponse> createGetCurrencyCodesResponse(GetCurrencyCodesResponse value) {
        return new JAXBElement<GetCurrencyCodesResponse>(_GetCurrencyCodesResponse_QNAME, GetCurrencyCodesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringArray }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StringArray }{@code >}
     */
    @XmlElementDecl(namespace = "currencyconverter.ac.at.fhcampuswien", name = "getCurrencyCodesResult", scope = GetCurrencyCodesResponse.class)
    public JAXBElement<StringArray> createGetCurrencyCodesResponseGetCurrencyCodesResult(StringArray value) {
        return new JAXBElement<StringArray>(_GetCurrencyCodesResponseGetCurrencyCodesResult_QNAME, StringArray.class, GetCurrencyCodesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "currencyconverter.ac.at.fhcampuswien", name = "input", scope = GetCurrencyCodes.class, defaultValue = "default value")
    public JAXBElement<String> createGetCurrencyCodesInput(String value) {
        return new JAXBElement<String>(_GetCurrencyCodesInput_QNAME, String.class, GetCurrencyCodes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "currencyconverter.ac.at.fhcampuswien", name = "getConvertedValueResult", scope = GetConvertedValueResponse.class)
    public JAXBElement<Double> createGetConvertedValueResponseGetConvertedValueResult(Double value) {
        return new JAXBElement<Double>(_GetConvertedValueResponseGetConvertedValueResult_QNAME, Double.class, GetConvertedValueResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Float }{@code >}
     */
    @XmlElementDecl(namespace = "currencyconverter.ac.at.fhcampuswien", name = "current_value", scope = GetConvertedValue.class, defaultValue = "1")
    public JAXBElement<Float> createGetConvertedValueCurrentValue(Float value) {
        return new JAXBElement<Float>(_GetConvertedValueCurrentValue_QNAME, Float.class, GetConvertedValue.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "currencyconverter.ac.at.fhcampuswien", name = "current_currency_code", scope = GetConvertedValue.class, defaultValue = "EUR")
    public JAXBElement<String> createGetConvertedValueCurrentCurrencyCode(String value) {
        return new JAXBElement<String>(_GetConvertedValueCurrentCurrencyCode_QNAME, String.class, GetConvertedValue.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "currencyconverter.ac.at.fhcampuswien", name = "expected_currency_code", scope = GetConvertedValue.class, defaultValue = "USD")
    public JAXBElement<String> createGetConvertedValueExpectedCurrencyCode(String value) {
        return new JAXBElement<String>(_GetConvertedValueExpectedCurrencyCode_QNAME, String.class, GetConvertedValue.class, value);
    }

}
