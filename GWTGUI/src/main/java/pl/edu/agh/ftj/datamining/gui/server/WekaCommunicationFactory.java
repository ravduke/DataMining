package pl.edu.agh.ftj.datamining.gui.server;

import pl.edu.agh.ftj.datamining.gui.server.impl.WekaAxisCommunication;
import pl.edu.agh.ftj.datamining.gui.server.impl.WekaRestCommunication;
import pl.edu.agh.ftj.datamining.client.shared.CommunicationType;

/*
 * Klasa odpowiedzialna za utworzenie obiektu do komunikacji z Weka.
 * @author Tomasz Pyszka
 */
public class WekaCommunicationFactory {
	

	
	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.WekaCommunicationFactory#create(pl.edu.agh.ftj.datamining.gui.WekaCommunicationFactory.Type)
	 */
	public static WekaCommunication create(CommunicationType type) {
		
		switch(type) {
			case AXIS: return WekaAxisCommunication.getInstance();
			case REST: return WekaRestCommunication.getInstance();
		}
		return null;
	}
}
