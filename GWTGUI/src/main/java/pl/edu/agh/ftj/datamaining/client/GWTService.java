/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.agh.ftj.datamaining.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author ravduke
 */
@RemoteServiceRelativePath("gwtservice")
public interface GWTService extends RemoteService {
    public String myMethod(String s);
}
