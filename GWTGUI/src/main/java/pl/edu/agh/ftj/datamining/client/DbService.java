/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.ftj.datamining.client;

import pl.edu.agh.ftj.datamining.client.shared.CommunicationType;
import pl.edu.agh.ftj.datamining.dbapi.core.xsd.BasicConfigurationDataSourceModel;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author Adrian Matoga
 */
@RemoteServiceRelativePath("dbservice")
public interface DbService extends RemoteService {

	public void createCommunication(CommunicationType type);

	/*
	 * Metoda odpowiedzialna za popbranie z serwisu WekaDBApi listy dostepnych
	 * zrodel danych.
	 * @return tablica dostepnych zrodel danych
	 */
	public BasicConfigurationDataSourceModel[] getDataSources();
}
