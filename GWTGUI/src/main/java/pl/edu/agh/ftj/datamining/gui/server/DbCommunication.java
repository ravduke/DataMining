package pl.edu.agh.ftj.datamining.gui.server;

import pl.edu.agh.ftj.datamining.dbapi.core.xsd.BasicConfigurationDataSourceModel;

/**
 * Interfejs do komunikacji z WekaDBApi.
 * @author Adrian Matoga
 */
public interface DbCommunication {
	public BasicConfigurationDataSourceModel[] getDataSources();
}
