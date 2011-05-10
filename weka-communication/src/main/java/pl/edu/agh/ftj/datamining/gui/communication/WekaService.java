package pl.edu.agh.ftj.datamining.gui.communication;

import java.util.List;

import pl.edu.agh.ftj.datamining.gui.communication.impl.WekaCommunicationFactory.Type;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("wekaservice")
public interface WekaService extends RemoteService {

	public void createCommunication(Type type);
	
	public List<String> getAlgorithms();
	
	public WekaAnswer runAlgorithm(Integer algorithmType, String location, String id, String table);
}
