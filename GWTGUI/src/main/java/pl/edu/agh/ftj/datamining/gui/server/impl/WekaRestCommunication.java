package pl.edu.agh.ftj.datamining.gui.server.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import pl.edu.agh.ftj.datamining.gui.server.WekaCommunication;
import pl.edu.agh.ftj.datamining.weka.algorithm.WekaAnswer;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;
	
/*
 * Klasa implementujaca interfejs {@link WekaCommunication}.
 * Klasa odpowiedzialna za polaczenie z REST.
 * @author Tomasz Pyszka
 */
public class WekaRestCommunication implements WekaCommunication {

	private static final String URI = "http://prgzsp.ftj.agh.edu.pl:8080/WekaRESTService/rest/";
	private static final Logger LOGGER = Logger.getLogger(WekaRestCommunication.class.getName());
        
	private WebResource webResource;
	private Client client;
	
	// singleton
	private WekaRestCommunication() {
		LOGGER.info("WekaRestCommunication::constructor() [initializing...]");
		client = Client.create();
		webResource = client.resource(URI);
	}
	
	private static final class WekaRestCommunicationHolder {
		private static final WekaCommunication INSTANCE = new WekaRestCommunication();
	}
	
	public static WekaCommunication getInstance() {
		return WekaRestCommunicationHolder.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.WekaCommunication#getAlgorithms()
	 */
	@Override
	public List<String> getAlgorithms() {
		LOGGER.info("WekaRestCommunication::getAlgorithms() [...]");
		String result = null;
		try {
			result = webResource.path("getAlgorithms").accept(MediaType.APPLICATION_XML).get(String.class);
		} catch(UniformInterfaceException uie) {
			LOGGER.warning("ERROR: " + uie);
		}
		return Utils.getAlgorithms(result);
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.WekaCommunication#runAlgorithm()
	 */
	@Override
	public WekaAnswer runAlgorithm(Integer algorithmType, /*String location,*/ String id, String table, String options) {
		LOGGER.info("WekaRestCommunication::runAlgorithm() [algorithmType="
                        + algorithmType + ", id=" + id + ", table=" + table + ", options=" + options + "]");
		webResource = client.resource(URI);
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	 	queryParams.add("algorithmType", String.valueOf(algorithmType));
	 	queryParams.add("id", id);
	 	queryParams.add("table", table);
	 	queryParams.add("options", options);
                
	 	InputStream result = webResource.path("runAlgorithm").queryParams(queryParams).accept(MediaType.APPLICATION_OCTET_STREAM_TYPE).get(InputStream.class);
	 	WekaAnswer weka = null;
        try {
           
        	byte[] responseBytes = readFromStream(result);
            
            ByteArrayInputStream bis = new ByteArrayInputStream(responseBytes);
            ObjectInput in = new ObjectInputStream(bis);
            weka = (WekaAnswer) in.readObject();
            LOGGER.info("WekaRestCommunication::runAlgorithm() [weka=" + weka + "]");
        } catch (IOException e) {
        	LOGGER.warning("WekaRestCommunication::runAlgorithm() [error=" + e + "]");
        }
        catch(ClassNotFoundException e) {
        	LOGGER.warning("WekaRestCommunication::runAlgorithm() [error=" + e + "]");
        }
        catch(NullPointerException e) {
        	LOGGER.warning("WekaRestCommunication::runAlgorithm() [error=" + e + "]");
        }
		return weka;
	}
        
    private byte[] readFromStream(InputStream stream) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1000];
	    int  read = 0;
	    do {
	        read = stream.read(buffer);
	        if(read > 0)
	            baos.write(buffer, 0, read);
	    } while(read > -1);
	    return baos.toByteArray();
    }
}
