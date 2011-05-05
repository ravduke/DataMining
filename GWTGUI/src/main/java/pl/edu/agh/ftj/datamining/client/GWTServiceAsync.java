/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.agh.ftj.datamining.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author ravduke
 */
public interface GWTServiceAsync {
    public void myMethod(String s, AsyncCallback<String> callback);
}
