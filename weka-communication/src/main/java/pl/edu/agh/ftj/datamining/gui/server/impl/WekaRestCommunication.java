package pl.edu.agh.ftj.datamining.gui.server.impl;

import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import pl.edu.agh.ftj.datamining.gui.server.WekaCommunication;
import pl.edu.agh.ftj.datamining.gui.server.WekaAnswer;
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
	public WekaAnswer runAlgorithm(Integer algorithmType, String location, String id, String table) {
		LOGGER.info("WekaRestCommunication::runAlgorithm() [...]");
		webResource = client.resource(URI);
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	 	queryParams.add("algorithmType", String.valueOf(algorithmType));
	 	queryParams.add("location", location);
	 	queryParams.add("id", id);
	 	queryParams.add("table", table);
	 	
		WekaAnswer weka = null;
		try {
			weka = (WekaAnswer) webResource.path("runAlgorithm").queryParams(queryParams).accept(MediaType.APPLICATION_XML_TYPE).get(WekaAnswer.class);
		} catch(UniformInterfaceException uie) {
			LOGGER.warning("ERROR: " + uie);
		}
		return weka;
	}
}
