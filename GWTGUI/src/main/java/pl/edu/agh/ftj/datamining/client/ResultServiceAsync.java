package pl.edu.agh.ftj.datamining.client;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author ravduke
 */
public interface ResultServiceAsync {

    /**
     *
     * @param attrX
     * @param attrY
     * @param callback
     */
    public void getResults(String attrX, String attrY, AsyncCallback<List<List<Number[]>>> callback);
    /**
     *
     * @param callback
     */
    public void getAttributes(AsyncCallback<List<String>> callback);
}
