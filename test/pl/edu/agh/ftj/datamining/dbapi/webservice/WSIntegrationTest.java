/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.agh.ftj.datamining.dbapi.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.xml.ws.WebServiceRef;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.edu.agh.ftj.datamining.dbapi.webservice.*;

/**
 * Testy integracyjne dla WebService'u
 * @author janek
 * @version 1.0.0
 */
public class WSIntegrationTest {

    //@WebServiceRef(wsdlLocation = "C:\\Users\\janek\\Documents\\NetBeansProjects\\WekaDBApi\\WekaDBApi\\src\\META-INF\\wsdl\\localhost_8080\\axis2\\services\\DataAccessService.wsdl")
    private DataAccessService client;


    public WSIntegrationTest() {

    }

    @Test
    /**
     * Metoda sprawdzajaca zwracana liste zrodel danych
     */
    public void testDataSourceList(){
        

        try{
        client = new DataAccessService();//new URL("http://localhost:8080/axis2/services/DataAccessService?wsdl"));
        DataAccessServicePortType dataClient = client.getDataAccessServiceHttpSoap11Endpoint();
        List<pl.edu.agh.ftj.datamining.dbapi.core.xsd.BasicConfigurationDataSourceModel> response = dataClient.getDataSources().getReturn();
        assertEquals(response.size(),3);
        }catch(Exception ex){
            System.out.print(ex.getMessage());
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * Spradza czy przekazywane dane sa poprawne
     */
    @Test
    public void testDataSource(){
        try{
        //client = new DataAccessService(new URL("http://localhost:8080/axis2/services/DataAccessService?wsdl"));
        client = new DataAccessService();
        DataAccessServicePortType dataClient = client.getDataAccessServiceHttpSoap11Endpoint();
        String response = dataClient.getData("1", "iris.arff.csv");
        assertEquals(response.length(), 1);
        }catch(Exception ex){
            System.out.print(ex.getMessage());
            ex.printStackTrace();
            fail();
        }
    }

}