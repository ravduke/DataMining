package pl.edu.agh.ftj.datamining.client;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ResultServiceAsync {

	public void getResults(String attrX, String attrY, AsyncCallback<List<List<Number[]>>> callback);
	public void getAttributes(AsyncCallback<List<String>> callback);
}
