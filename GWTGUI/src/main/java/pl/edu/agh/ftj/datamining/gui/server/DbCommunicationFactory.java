package pl.edu.agh.ftj.datamining.gui.server;

import pl.edu.agh.ftj.datamining.client.shared.CommunicationType;
import pl.edu.agh.ftj.datamining.gui.server.impl.DbAxisCommunication;

/**
 *
 * @author Adrian Matoga
 */
public class DbCommunicationFactory {

	public static DbCommunication create(CommunicationType type) throws Exception {
		switch(type) {
		case AXIS:
			return DbAxisCommunication.getInstance();
		default:
			// FIXME: mamy jakas wlasna hierarchie wyjatkow?
			throw new Exception("Unsupported Communication Type");
		}
	}
}
