package pl.edu.agh.ftj.datamining.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Epi
 */
public interface DbServiceAsync {
	public void getDataSources(AsyncCallback<List<DataSource> > asyncCallback);
}
