/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.agh.ftj.datamining.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.WebServiceRef;
import pl.edu.agh.ftj.datamining.client.DbService;
import pl.edu.agh.ftj.datamining.client.DataSource;
import pl.edu.agh.ftj.datamining.dbapi.webservice.DataAccessService;
import pl.edu.agh.ftj.datamining.dbapi.webservice.DataAccessServicePortType;
import pl.edu.agh.ftj.datamining.dbapi.webservice.GetDataSourcesResponse;
import pl.edu.agh.ftj.datamining.dbapi.core.xsd.BasicConfigurationDataSourceModel;

/**
 * Zasysa zrodla danych z WekaDBApi i oddaje je w formie strawnej
 * dla klienta GWT.
 *
 * @author Epi
 */
public class DbServiceImpl extends RemoteServiceServlet implements DbService {

	@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/axis2/services/DataAccessService.wsdl")
	private DataAccessService service;

	public List<DataSource> getDataSources() {
		DataAccessServicePortType port = service.getDataAccessServiceHttpSoap12Endpoint();
		GetDataSourcesResponse result = port.getDataSources();
		ArrayList<DataSource> dsList = new ArrayList<DataSource>();
		for (BasicConfigurationDataSourceModel bdsm : result.getReturn()) {
			DataSource ds = new DataSource();
			ds.setDatabase(bdsm.getDatabase().getValue());
			ds.setDisplayedName(bdsm.getDisplayedName().getValue());
			ds.setId(bdsm.getId().getValue());
			ds.setLocation(bdsm.getLocation().getValue());
			List<String> tables = ds.getTables();
			for (String table : bdsm.getTables()) {
				tables.add(table);
			}
			dsList.add(ds);
		}
		return dsList;
	}
}
