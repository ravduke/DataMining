package pl.edu.agh.ftj.datamining.gui.communication.impl;


import java.util.List;
import pl.edu.agh.ftj.datamining.gui.communication.WekaAnswer;
import pl.edu.agh.ftj.datamining.gui.communication.WekaCommunication;
import pl.edu.agh.ftj.datamining.gui.communication.WekaService;
import pl.edu.agh.ftj.datamining.gui.communication.impl.WekaCommunicationFactory.Type;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/*
 * Klasa implementujaca interfejs {@link WekaService}.
 * @author Tomasz Pyszka
 */
public class WekaServiceImpl extends RemoteServiceServlet implements WekaService {

	private static final long serialVersionUID = 1L;
	private WekaCommunication wekaCommunication;
	
	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.communication.WekaService#createCommunication(pl.edu.agh.ftj.datamining.gui.communication.impl.WekaCommunicationFactory.Type)
	 */
	@Override
	public void createCommunication(Type type) {
		wekaCommunication = WekaCommunicationFactory.create(type);
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.communication.WekaService#getAlgorithms()
	 */
	@Override
	public List<String> getAlgorithms() {
		return wekaCommunication.getAlgorithms();
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.communication.WekaService#runAlgorithm(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public WekaAnswer runAlgorithm(Integer algorithmType, String location,
			String id, String table) {
		return wekaCommunication.runAlgorithm(algorithmType, location, id, table);
	}
}
