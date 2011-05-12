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
        new Folder("Quartets",  new DBTable[] {
                                    new DBTable("Six String Quartets"),
                                    new DBTable("Three String Quartets"),
                                    new DBTable("Grosse Fugue for String Quartets")
                                }
                  )
        };
    folders[0].setChildren(
                                new Folder ("Database1", new DBTable[] {
                                    new DBTable("aaats"),
                                    new DBTable("bbbring Quartets"),
                                    new DBTable("cccugue for String Quartets")
                                    }
                                )
                          );
    folders[0].setChildren(
                                new DBTable[] {
                                    new DBTable("aaats"),
                                    new DBTable("bbbring Quartets"),
                                    new DBTable("cccugue for String Quartets")
                                }
                          );

    Folder root = new Folder("root");
    for (int i = 0; i < folders.length; i++) {
      root.add((Folder) folders[i]);
    }
    return root;
    }
}
