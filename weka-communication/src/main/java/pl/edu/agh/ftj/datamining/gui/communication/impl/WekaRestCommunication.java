package pl.edu.agh.ftj.datamining.gui.communication.impl;

import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBElement;
import pl.edu.agh.ftj.datamining.gui.communication.WekaAnswer;
import pl.edu.agh.ftj.datamining.gui.communication.WekaCommunication;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/*
 * Klasa implementujaca interfejs {@link WekaCommunication}.
 * Klasa odpowiedzialna za polaczenie z REST.
 * @author Tomasz Pyszka
 */
public class WekaRestCommunication implements WekaCommunication {

	private static final String URI = "http://localhost:8080/WekaRESTService/rest/";
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
	 * @see pl.edu.agh.ftj.datamining.gui.communication.WekaCommunication#getAlgorithms()
	 */
	@Override
	public String getAlgorithms() {
		LOGGER.info("WekaRestCommunication::getAlgorithms() [...]");
		String result = null;
		try {
			result = webResource.path("getAlgorithms").accept(MediaType.APPLICATION_XML_TYPE).get(String.class);
		} catch(UniformInterfaceException uie) {
			LOGGER.warning("ERROR: " + uie);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.communication.WekaCommunication#setAlgorithm(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean setAlgorithm(Integer algorithmType, String location, String id, String table) {
		LOGGER.info("WekaRestCommunication::setAlgorithm() [algorithmType=" + algorithmType
				+ ", location=" + location + ", id=" + id + ", table=" + table + "]");
		
		MultivaluedMap params = new MultivaluedMapImpl();
		params.put("algorithmType", algorithmType);
		params.put("location", location);
		params.put("id", id);
		params.put("table", table);
		String result = "NieOK";
		
		try {
			result = webResource.path("setAlgorithm").queryParams(params).accept(MediaType.TEXT_PLAIN).get(String.class);
		} catch(UniformInterfaceException uie) {
			LOGGER.warning("ERROR: " + uie);
		}
		LOGGER.info("WekaRestCommunication::setAlgorithm() [result=" + result + "]");
		if(result.equals("NieOK")) return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.communication.WekaCommunication#runAlgorithm()
	 */
	@Override
	public WekaAnswer runAlgorithm() {
		LOGGER.info("WekaRestCommunication::runAlgorithm() [...]");
		webResource = client.resource(URI);
		GenericType<JAXBElement<WekaAnswer>> wekaType = new GenericType<JAXBElement<WekaAnswer>>() {};
		WekaAnswer weka = null;
		try {
			weka = (WekaAnswer) webResource.path("runAlgorithm").accept(MediaType.APPLICATION_XML_TYPE).get(wekaType).getValue();
		} catch(UniformInterfaceException uie) {
			LOGGER.warning("ERROR: " + uie);
		}
		return weka;
	}
}
