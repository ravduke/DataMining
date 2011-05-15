/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.agh.ftj.datamining.client;

import com.extjs.gxt.samples.resources.client.model.Folder;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author Rafal Jablonowski
 */
@RemoteServiceRelativePath("gwtservice")
public interface GWTService extends RemoteService {
    public String myMethod(String s);
    public Folder getDatabases();
        
}
