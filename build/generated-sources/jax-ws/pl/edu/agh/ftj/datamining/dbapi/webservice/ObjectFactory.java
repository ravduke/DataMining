
package pl.edu.agh.ftj.datamining.dbapi.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.edu.agh.ftj.datamining.dbapi.webservice package. 
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

    private final static QName _GetDataResponseReturn_QNAME = new QName("http://webservice.dbapi.datamining.ftj.agh.edu.pl", "return");
    private final static QName _GetDataId_QNAME = new QName("http://webservice.dbapi.datamining.ftj.agh.edu.pl", "id");
    private final static QName _GetDataTable_QNAME = new QName("http://webservice.dbapi.datamining.ftj.agh.edu.pl", "table");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.edu.agh.ftj.datamining.dbapi.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDataResponse }
     * 
     */
    public GetDataResponse createGetDataResponse() {
        return new GetDataResponse();
    }

    /**
     * Create an instance of {@link GetData }
     * 
     */
    public GetData createGetData() {
        return new GetData();
    }

    /**
     * Create an instance of {@link GetDataSourcesResponse }
     * 
     */
    public GetDataSourcesResponse createGetDataSourcesResponse() {
        return new GetDataSourcesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.dbapi.datamining.ftj.agh.edu.pl", name = "return", scope = GetDataResponse.class)
    public JAXBElement<String> createGetDataResponseReturn(String value) {
        return new JAXBElement<String>(_GetDataResponseReturn_QNAME, String.class, GetDataResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.dbapi.datamining.ftj.agh.edu.pl", name = "id", scope = GetData.class)
    public JAXBElement<String> createGetDataId(String value) {
        return new JAXBElement<String>(_GetDataId_QNAME, String.class, GetData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.dbapi.datamining.ftj.agh.edu.pl", name = "table", scope = GetData.class)
    public JAXBElement<String> createGetDataTable(String value) {
        return new JAXBElement<String>(_GetDataTable_QNAME, String.class, GetData.class, value);
    }

}
