
package pl.edu.agh.ftj.datamining.dbapi.core.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.edu.agh.ftj.datamining.dbapi.core.xsd package. 
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

    private final static QName _BasicConfigurationDataSourceModelLocation_QNAME = new QName("http://core.dbapi.datamining.ftj.agh.edu.pl/xsd", "location");
    private final static QName _BasicConfigurationDataSourceModelId_QNAME = new QName("http://core.dbapi.datamining.ftj.agh.edu.pl/xsd", "id");
    private final static QName _BasicConfigurationDataSourceModelDisplayedName_QNAME = new QName("http://core.dbapi.datamining.ftj.agh.edu.pl/xsd", "displayedName");
    private final static QName _BasicConfigurationDataSourceModelDatabase_QNAME = new QName("http://core.dbapi.datamining.ftj.agh.edu.pl/xsd", "database");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.edu.agh.ftj.datamining.dbapi.core.xsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BasicConfigurationDataSourceModel }
     * 
     */
    public BasicConfigurationDataSourceModel createBasicConfigurationDataSourceModel() {
        return new BasicConfigurationDataSourceModel();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://core.dbapi.datamining.ftj.agh.edu.pl/xsd", name = "location", scope = BasicConfigurationDataSourceModel.class)
    public JAXBElement<String> createBasicConfigurationDataSourceModelLocation(String value) {
        return new JAXBElement<String>(_BasicConfigurationDataSourceModelLocation_QNAME, String.class, BasicConfigurationDataSourceModel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://core.dbapi.datamining.ftj.agh.edu.pl/xsd", name = "id", scope = BasicConfigurationDataSourceModel.class)
    public JAXBElement<String> createBasicConfigurationDataSourceModelId(String value) {
        return new JAXBElement<String>(_BasicConfigurationDataSourceModelId_QNAME, String.class, BasicConfigurationDataSourceModel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://core.dbapi.datamining.ftj.agh.edu.pl/xsd", name = "displayedName", scope = BasicConfigurationDataSourceModel.class)
    public JAXBElement<String> createBasicConfigurationDataSourceModelDisplayedName(String value) {
        return new JAXBElement<String>(_BasicConfigurationDataSourceModelDisplayedName_QNAME, String.class, BasicConfigurationDataSourceModel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://core.dbapi.datamining.ftj.agh.edu.pl/xsd", name = "database", scope = BasicConfigurationDataSourceModel.class)
    public JAXBElement<String> createBasicConfigurationDataSourceModelDatabase(String value) {
        return new JAXBElement<String>(_BasicConfigurationDataSourceModelDatabase_QNAME, String.class, BasicConfigurationDataSourceModel.class, value);
    }

}
