package pl.edu.agh.ftj.datamining.gui.client;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import pl.edu.agh.ftj.datamining.gui.shared.CommunicationType;
import pl.edu.agh.ftj.datamining.gui.shared.WekaAnswerDTO;

public interface WekaServiceAsync {
	
	public void createCommunication(CommunicationType type, AsyncCallback<Void> callback);
	
	public void getAlgorithms(AsyncCallback<List<String>> callback);
	
	public void runAlgorithm(Integer algorithmType, String location, String id, String table, AsyncCallback<WekaAnswerDTO> callback);
}
