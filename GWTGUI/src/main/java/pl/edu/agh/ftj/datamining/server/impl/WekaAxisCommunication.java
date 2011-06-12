package pl.edu.agh.ftj.datamining.server.impl;

import java.util.List;

import pl.edu.agh.ftj.datamining.server.WekaCommunication;
import pl.edu.agh.ftj.datamining.weka.algorithm.WekaAnswer;

/*
 * Klasa implementujaca interfejs {@link WekaCommunication}.
 * Klasa odpowiedzialna za polaczenie z Axis-em.
 * @author Tomasz Pyszka
 */
/**
 *
 * @author ravduke
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
	
        /**
         *
         * @return
         */
        public static WekaCommunication getInstance() {
		return WekaAxisCommunicationHolder.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.WekaCommunication#getAlgorithms()
	 */
        /**
         *
         * @return
         */
        @Override
	public List<String> getAlgorithms() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.WekaCommunication#runAlgorithm()
	 */
	@Override
	public WekaAnswer runAlgorithm(Integer algorithmType, /*String location,*/
			String id, String table, String options) {
		// TODO Auto-generated method stub
		return null;
	}
}
