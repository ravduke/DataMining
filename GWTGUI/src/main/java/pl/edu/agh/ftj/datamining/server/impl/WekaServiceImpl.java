package pl.edu.agh.ftj.datamining.server.impl;


import java.util.List;
import pl.edu.agh.ftj.datamining.client.WekaService;
import pl.edu.agh.ftj.datamining.server.WekaCommunication;
import pl.edu.agh.ftj.datamining.server.WekaCommunicationFactory;
import pl.edu.agh.ftj.datamining.server.WekaWrapper;
import pl.edu.agh.ftj.datamining.weka.algorithm.WekaAnswer;
import pl.edu.agh.ftj.datamining.client.shared.CommunicationType;
import pl.edu.agh.ftj.datamining.client.shared.WekaAnswerDTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/*
 * Klasa implementujaca interfejs {@link WekaService}.
 * @author Tomasz Pyszka
 */
/**
 *
 * @author ravduke
 */
public class WekaServiceImpl extends RemoteServiceServlet implements WekaService {

	private static final long serialVersionUID = 1L;
	private WekaCommunication wekaCommunication;
	
	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.WekaService#createCommunication(pl.edu.agh.ftj.datamining.gui.impl.WekaCommunicationFactory.Type)
	 */
        /**
         *
         * @param type
         */
        @Override
	public void createCommunication(CommunicationType type) {
		wekaCommunication = WekaCommunicationFactory.create(type);
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.WekaService#getAlgorithms()
	 */
        /**
         *
         * @return
         */
        @Override
	public List<String> getAlgorithms() {
		return wekaCommunication.getAlgorithms();
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.WekaService#runAlgorithm(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
        /**
         *
         * @param algorithmType
         * @param id
         * @param table
         * @param options
         * @return
         */
        @Override
	public WekaAnswerDTO runAlgorithm(Integer algorithmType, /*String location,*/
			String id, String table, String options) {
		
		WekaAnswer wekaAnswer = wekaCommunication.runAlgorithm(algorithmType, id, table, options);
		WekaWrapper ww = new WekaWrapper(wekaAnswer);

		return ww.getWekaAnswer();
	}
}
