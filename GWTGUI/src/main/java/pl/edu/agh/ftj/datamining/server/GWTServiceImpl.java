/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.agh.ftj.datamining.server;

import com.extjs.gxt.samples.resources.client.model.DBTable;
import com.extjs.gxt.samples.resources.client.model.Folder;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.xml.ws.WebServiceRef;

import pl.edu.agh.ftj.datamining.client.DbService;
import pl.edu.agh.ftj.datamining.client.DataSource;
import pl.edu.agh.ftj.datamining.dbapi.webservice.DataAccessService;
import pl.edu.agh.ftj.datamining.dbapi.webservice.DataAccessServicePortType;
import pl.edu.agh.ftj.datamining.dbapi.webservice.GetDataSourcesResponse;
import pl.edu.agh.ftj.datamining.dbapi.core.xsd.BasicConfigurationDataSourceModel;


import pl.edu.agh.ftj.datamining.client.GWTService;

/**
 *
* @author Rafal Jablonowski
 */
public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {


	@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/axis2/services/DataAccessService.wsdl")
	private DataAccessService service;

	public List<DataSource> getDataSources() {
                // service = Null Pointer !! :/
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
        
    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return  "  " ;
    }

    public Folder getDatabases() {
  /*      DataAccessServicePortType port = service.getDataAccessServiceHttpSoap12Endpoint();
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

        Folder[] folders = new Folder[3];
        int i = 0;
	for (DataSource ds : dsList) {

            folders[i].setName( ds.getDisplayedName() );

            DBTable[] dbTabel = new DBTable[ds.getTables().size()];
            for (String name : ds.getTables()) {
                dbTabel[0] = new DBTable(name,ds.getDisplayedName(),ds.getDatabase(),ds.getId(),ds.getLocation());
            }

            folders[i].setChildren( new Folder (ds.getDatabase(), dbTabel));

            i++;
	}*/
    Folder[] folders = new Folder[] {
        new Folder("PostgreSQL"),
        new Folder("SQLite"),
        new Folder("Plain Text")
        };
    folders[0].setChildren(
                                 new Folder ("Database1", new DBTable[] {
                                    new DBTable("Table0","Database1","PostgreSQL"),
                                    new DBTable("Table-1","Database1","PostgreSQL"),
                                    new DBTable("Table-2","Database1","PostgreSQL")
                                    }
                                )
                          );
    folders[1].setChildren(
                                new Folder ("Database2", new DBTable[] {
                                    new DBTable("Table1","Database2","SQLite"),
                                    new DBTable("Table2","Database2","SQLite"),
                                    new DBTable("Table3","Database2","SQLite")
                                    }
                                )
                          );
    folders[2].setChildren(
                                new Folder ("Database3", new DBTable[] {
                                    new DBTable("Table44","Database3","Plain Text"),
                                    new DBTable("Table23","Database3","Plain Text"),
                                    new DBTable("Table4","Database3","Plain Text")
                                    }
                                )
                          );

    Folder root = new Folder("root");
    for (int j = 0; j < folders.length; j++) {
      root.add((Folder) folders[j]);
    }
    return root;
    }

      /*     DataAccessServicePortType port = service.getDataAccessServiceHttpSoap12Endpoint();
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

        Folder[] folders = new Folder[3];
        int i = 0;
	for (DataSource ds : dsList) {

            folders[i].setName( ds.getDisplayedName() );

            DBTable[] dbTabel = new DBTable[ds.getTables().size()];
            for (String name : ds.getTables()) {
                dbTabel[0] = new DBTable(name,ds.getDisplayedName(),ds.getDatabase(),ds.getId(),ds.getLocation());
            }

            folders[i].setChildren( new Folder (ds.getDatabase(), dbTabel));

            i++;
	}
       
       */
}
