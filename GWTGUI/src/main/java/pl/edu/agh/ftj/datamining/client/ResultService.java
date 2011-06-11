package pl.edu.agh.ftj.datamining.client;

import java.util.List;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("resultservice")
public interface ResultService extends RemoteService {

	public List<List<Number[]>> getResults(String attrX, String attrY);
	
	public List<String> getAttributes();
}
