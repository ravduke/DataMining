package pl.edu.agh.ftj.datamining.dbapi.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pl.edu.agh.ftj.datamining.dbapi.core.DSApiEnums.DataSourceType;

/**
 * Klasa odpowiedzialna za odczyt pliku konfiguracyjnego
 * @author janek
 * @version 1.0.1
 */
public class ConfigurationHelper {

    private List<ConfigurationDataSourceModel> configuration;

    /**
     * Odczyt pliku konfiguracyjnego i jego przechowywanie
     */
    public ConfigurationHelper() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
        readConfigurationFile();
    }

    /**
     * Konfiguracja zrodel danych
     * @return lista zrodel danych
     */
    public List<ConfigurationDataSourceModel> getConfiguration() {
        return configuration;
    }

    /**
     * Odczyt pliku konfiguracyjnego z pliku conf.xml
     */
    private void readConfigurationFile() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
        configuration = new ArrayList<ConfigurationDataSourceModel>();
        //read and parse file
        File file = new File("conf.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("server");
        //read data from file
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            ConfigurationDataSourceModel dsm = new ConfigurationDataSourceModel();
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                NodeList elementList = element.getElementsByTagName("id");
                String id = ((Node)((Element)elementList.item(0)).getChildNodes().item(0)).getNodeValue();
                dsm.setId(id);

                elementList = element.getElementsByTagName("type");
                String type = ((Node)((Element)elementList.item(0)).getChildNodes().item(0)).getNodeValue();
                dsm.setDataSourceType(DataSourceType.valueOf(type));

                elementList = element.getElementsByTagName("database");
                Node n = ((Node)((Element)elementList.item(0)).getChildNodes().item(0));
                String database = (n!=null)? n.getNodeValue():"";
                dsm.setDatabase(database);

                elementList = element.getElementsByTagName("name");
                String name = ((Node)((Element)elementList.item(0)).getChildNodes().item(0)).getNodeValue();
                dsm.setDisplayedName(name);

                elementList = element.getElementsByTagName("location");
                String location = ((Node)((Element)elementList.item(0)).getChildNodes().item(0)).getNodeValue();
                dsm.setLocation(location);

                elementList = element.getElementsByTagName("port");
                String portString = ((Node)((Element)elementList.item(0)).getChildNodes().item(0)).getNodeValue();
                int port = Integer.parseInt(portString);
                dsm.setPort(port);

                elementList = element.getElementsByTagName("username");
                n = ((Node)((Element)elementList.item(0)).getChildNodes().item(0));
                String username = (n!=null)? n.getNodeValue():"";
                dsm.setUsername(username);

                elementList = element.getElementsByTagName("password");
                n = ((Node)((Element)elementList.item(0)).getChildNodes().item(0));
                String password = (n!=null)? n.getNodeValue():"";
                dsm.setPassword(password);

                elementList = element.getElementsByTagName("className");
                String className = ((Node)((Element)elementList.item(0)).getChildNodes().item(0)).getNodeValue();
                dsm.setClassName(className);

                elementList = element.getElementsByTagName("table");
                String[] tables = new String[elementList.getLength()];
                for(int j=0;j<elementList.getLength();j++){
                    tables[j] = elementList.item(j).getChildNodes().item(0).getNodeValue();
                }
                dsm.setTables(tables);
            }
            
            configuration.add(dsm);
        }
    }
}
