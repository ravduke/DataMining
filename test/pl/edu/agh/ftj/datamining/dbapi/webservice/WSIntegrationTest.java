/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.agh.ftj.datamining.dbapi.webservice;

import java.net.URL;
import javax.xml.ws.WebServiceRef;
import org.junit.Test;
import static org.junit.Assert.*;
//import pl.edu.agh.ftj.datamining.dbapi.webservice.*;

/**
 * Testy integracyjne dla WebService'u
 * @author janek
 * @version 1.0.0
 */
public class WSIntegrationTest {

    //@WebServiceRef(wsdlLocation = "C:\\Users\\janek\\Documents\\NetBeansProjects\\WekaDBApi\\WekaDBApi\\src\\META-INF\\wsdl\\localhost_8080\\axis2\\services\\DataAccessService.wsdl")
    //private pl.edu.agh.ftj.datamining.dbapi.jaxrpc.DataAccessService client;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/axis2/services/DataAccessService.wsdl")
    private DataAccessService client;

    public WSIntegrationTest() {

    }

    @Test
    /**
     * Metoda sprawdzajaca zwracana liste zrodel danych
     */
    public void testDataSourceList(){
        

        try{

            DataAccessServicePortType type = client.getDataAccessServiceHttpSoap11Endpoint();
            GetDataSourcesResponse result = type.getDataSources();
            assertEquals(3,result.getReturn().size());
       
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
            DataAccessServicePortType type = client.getDataAccessServiceHttpSoap11Endpoint();
            String result = type.getData("1","iris.arff.csv");

            assertTrue(result.length()>100?true:false);
        }catch(Exception ex){
            System.out.print(ex.getMessage());
            ex.printStackTrace();
            fail();
        }
    }

}