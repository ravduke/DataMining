/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.agh.ftj.datamining.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pl.edu.agh.ftj.datamining.client.shared.CommunicationType;
import pl.edu.agh.ftj.datamining.dbapi.core.xsd.BasicConfigurationDataSourceModel;

/**
 *
 * @author Epi
 */
public interface DbServiceAsync {

	public void createCommunication(CommunicationType type, AsyncCallback<Void> callback);

	public void getDataSources(AsyncCallback<BasicConfigurationDataSourceModel[]> callback);
}
