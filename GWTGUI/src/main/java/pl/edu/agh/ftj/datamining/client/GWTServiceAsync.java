package pl.edu.agh.ftj.datamining.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Rafal Jablonowski
 */
public interface GWTServiceAsync {
    public void myMethod(String s, AsyncCallback<String> callback);
}

