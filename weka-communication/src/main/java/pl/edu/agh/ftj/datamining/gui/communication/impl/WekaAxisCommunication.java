package pl.edu.agh.ftj.datamining.gui.communication.impl;

import pl.edu.agh.ftj.datamining.gui.communication.WekaAnswer;
import pl.edu.agh.ftj.datamining.gui.communication.WekaCommunication;

/*
 * Klasa implementujaca interfejs {@link WekaCommunication}.
 * Klasa odpowiedzialna za polaczenie z Axis-em.
 * @author Tomasz Pyszka
 */
public class WekaAxisCommunication implements WekaCommunication {

	// URI interfejsu Weki
	private static final String URI = "";
	
	//singleton
	private WekaAxisCommunication() {
		
	}
	
	private static final class WekaAxisCommunicationHolder {
		private static final WekaCommunication INSTANCE = new WekaAxisCommunication();
	}
	
	public static WekaCommunication getInstance() {
		return WekaAxisCommunicationHolder.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.communication.WekaCommunication#getAlgorithms()
	 */
	@Override
	public String getAlgorithms() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.communication.WekaCommunication#runAlgorithm()
	 */
	@Override
	public WekaAnswer runAlgorithm(Integer algorithmType, String location,
			String id, String table) {
		// TODO Auto-generated method stub
		return null;
	}
}
