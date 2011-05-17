package pl.edu.agh.ftj.datamining.client;

import com.extjs.gxt.samples.resources.client.model.Folder;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Interfejs metod asynchronicznych komunikacji GWT klient-serwer
 * @author Rafal Jablonowski
 */
public interface GWTServiceAsync {
    public void myMethod(String s, AsyncCallback<String> callback);
    public void getDatabases(AsyncCallback<Folder> asyncCallback);

}
