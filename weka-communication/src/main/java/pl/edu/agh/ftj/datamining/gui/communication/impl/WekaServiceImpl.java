package pl.edu.agh.ftj.datamining.gui.communication.impl;


import pl.edu.agh.ftj.datamining.gui.communication.WekaAnswer;
import pl.edu.agh.ftj.datamining.gui.communication.WekaCommunication;
import pl.edu.agh.ftj.datamining.gui.communication.WekaService;
import pl.edu.agh.ftj.datamining.gui.communication.impl.WekaCommunicationFactory.Type;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class WekaServiceImpl extends RemoteServiceServlet implements WekaService {

	private static final long serialVersionUID = 1L;
	private WekaCommunication wekaCommunication;
	
	@Override
	public void createCommunication(Type type) {
		wekaCommunication = WekaCommunicationFactory.create(type);
	}

	@Override
	public String getAlgorithms() {
		return wekaCommunication.getAlgorithms();
	}

	@Override
	public WekaAnswer runAlgorithm(Integer algorithmType, String location,
			String id, String table) {
		return wekaCommunication.runAlgorithm(algorithmType, location, id, table);
	}
}
