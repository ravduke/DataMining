package pl.edu.agh.ftj.datamining.gui.communication.impl;

import pl.edu.agh.ftj.datamining.gui.communication.WekaCommunication;
import pl.edu.agh.ftj.datamining.gui.communication.WekaCommunicationFactory;

/*
 * Klasa implementujaca interfejs {@link WekaCommunicationFactory}.
 * Klasa odpowiedzialna za utworzenie obiektu do komunikacji z Weka.
 * @author Tomasz Pyszka
 */
public class WekaCommunicationFactoryImpl implements WekaCommunicationFactory {

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.communication.WekaCommunicationFactory#create(pl.edu.agh.ftj.datamining.gui.communication.WekaCommunicationFactory.Type)
	 */
	public WekaCommunication create(Type type) {
		
		switch(type) {
			case AXIS: return WekaAxisCommunication.getInstance();
			case REST: return WekaRestCommunication.getInstance();
		}
		return null;
	}
}
