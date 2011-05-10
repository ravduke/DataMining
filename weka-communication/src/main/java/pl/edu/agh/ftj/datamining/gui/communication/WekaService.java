package pl.edu.agh.ftj.datamining.gui.communication;

import pl.edu.agh.ftj.datamining.gui.communication.impl.WekaCommunicationFactory.Type;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("wekaservice")
public interface WekaService extends RemoteService {

	public void createCommunication(Type type);
	
	public String getAlgorithms();
	
	public WekaAnswer runAlgorithm(Integer algorithmType, String location, String id, String table);
}
