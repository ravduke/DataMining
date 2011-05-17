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

import pl.edu.agh.ftj.datamining.client.GWTService;

/**
 *
* @author Rafal Jablonowski
 */
public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {



    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return  "  " ;
    }

    public Folder getDatabases() {
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
    for (int i = 0; i < folders.length; i++) {
      root.add((Folder) folders[i]);
    }
    return root;
    }
}
