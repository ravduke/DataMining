package pl.edu.agh.ftj.datamining.gui.server.impl;

import pl.edu.agh.ftj.datamining.gui.server.DbCommunication;
import pl.edu.agh.ftj.datamining.dbapi.core.xsd.BasicConfigurationDataSourceModel;

/**
 *
 * @author Adrian Matoga
 */
public class DbAxisCommunication implements DbCommunication {

	private DbAxisCommunication() {

	}

	private static final class DbAxisCommunicationHolder {
		private static final DbCommunication INSTANCE = new DbAxisCommunication();
	}

	public static DbCommunication getInstance() {
		return DbAxisCommunicationHolder.INSTANCE;
	}

	@Override
	public BasicConfigurationDataSourceModel[] getDataSources() {
		// TODO Auto-generated method stub
		return null;
	}

    /*
	/ *
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.WekaCommunication#runAlgorithm()
	 * /
	@Override
	public WekaAnswer runAlgorithm(Integer algorithmType, String location,
			String id, String table) {
		// TODO Auto-generated method stub
		return null;
	}*/
}
