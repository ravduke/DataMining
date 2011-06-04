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
    //@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/axis2/services/DataAccessService.wsdl")
    //private DataAccessService client;

    public WSIntegrationTest() {

    }

    @Test
    /**
     * Metoda sprawdzajaca zwracana liste zrodel danych
     */
    public void testDataSourceList(){
        

        try{
            //client = new DataAccessService(new URL("http://localhost:8080/axis2/services/DataAccessService"));
          //  DataAccessServicePortType type = client.getDataAccessServiceHttpSoap12Endpoint();
            //assertEquals(3,type.getDataSources().getReturn().size());
        //client = new pl.edu.agh.ftj.datamining.dbapi.jaxrpc.DataAccessService_Impl();//new URL("http://localhost:8080/axis2/services/DataAccessService?wsdl"));
        //pl.edu.agh.ftj.datamining.dbapi.jaxrpc.DataAccessServicePortType dataClient = client.getDataAccessServiceHttpSoap11Endpoint();
         //client = new pl.edu.agh.ftj.datamining.dbapi.jaxrpc.DataAccessService_Impl();//new URL("http://localhost:8080/axis2/services/DataAccessService?wsdl"));
        //client = new DataAccessService();
        //pl.edu.agh.ftj.datamining.dbapi.jaxrpc.BasicConfigurationDataSourceModel[] response = dataClient.getDataSources().get_return();
//        assertEquals(client.getDataAccessServiceHttpSoap11Endpoint().getDataSources().getReturn().size(),3);
        }catch(Exception ex){
            System.out.print(ex.getMessage());
            ex.printStackTrace();
        //    fail();
        }
    }

    /**
     * Spradza czy przekazywane dane sa poprawne
     */
    @Test
    public void testDataSource(){
        try{
        //client = new pl.edu.agh.ftj.datamining.dbapi.jaxrpc.DataAccessService_Impl();
        //pl.edu.agh.ftj.datamining.dbapi.jaxrpc.DataAccessServicePortType dataClient = client.getDataAccessServiceHttpSoap11Endpoint();
        //client = new DataAccessService();
        //DataAccessServicePortType type = client.getDataAccessServiceHttpSoap12Endpoint();
        //String response = type.getData("1", "iris.arff.csv");
        //assertTrue(response.length()>100?true:false);
        }catch(Exception ex){
            System.out.print(ex.getMessage());
            ex.printStackTrace();
          //  fail();
        }
    }

}