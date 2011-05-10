package pl.edu.agh.ftj.datamining.gui.communication.impl;

import pl.edu.agh.ftj.datamining.gui.communication.WekaCommunication;

/*
 * Klasa odpowiedzialna za utworzenie obiektu do komunikacji z Weka.
 * @author Tomasz Pyszka
 */
public class WekaCommunicationFactory {
	
	// Typy komunikacji (AXIS, REST)
	public enum Type { AXIS, REST }
	
	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.communication.WekaCommunicationFactory#create(pl.edu.agh.ftj.datamining.gui.communication.WekaCommunicationFactory.Type)
	 */
	public static WekaCommunication create(Type type) {
		
		switch(type) {
			case AXIS: return WekaAxisCommunication.getInstance();
			case REST: return WekaRestCommunication.getInstance();
		}
		return null;
	}
}
