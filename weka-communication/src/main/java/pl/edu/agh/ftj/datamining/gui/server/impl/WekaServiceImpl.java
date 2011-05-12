package pl.edu.agh.ftj.datamining.gui.server.impl;


import java.util.List;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import pl.edu.agh.ftj.datamining.gui.client.WekaService;
import pl.edu.agh.ftj.datamining.gui.server.WekaAnswer;
import pl.edu.agh.ftj.datamining.gui.server.WekaCommunication;
import pl.edu.agh.ftj.datamining.gui.server.WekaCommunicationFactory;
import pl.edu.agh.ftj.datamining.gui.shared.CommunicationType;
import pl.edu.agh.ftj.datamining.gui.shared.WekaAnswerDTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/*
 * Klasa implementujaca interfejs {@link WekaService}.
 * @author Tomasz Pyszka
 */
public class WekaServiceImpl extends RemoteServiceServlet implements WekaService {

	private static final long serialVersionUID = 1L;
	private WekaCommunication wekaCommunication;
	private Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
	
	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.WekaService#createCommunication(pl.edu.agh.ftj.datamining.gui.impl.WekaCommunicationFactory.Type)
	 */
	@Override
	public void createCommunication(CommunicationType type) {
		wekaCommunication = WekaCommunicationFactory.create(type);
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.WekaService#getAlgorithms()
	 */
	@Override
	public List<String> getAlgorithms() {
		return wekaCommunication.getAlgorithms();
	}

	/*
	 * (non-Javadoc)
	 * @see pl.edu.agh.ftj.datamining.gui.WekaService#runAlgorithm(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public WekaAnswerDTO runAlgorithm(Integer algorithmType, String location,
			String id, String table) {
		
		WekaAnswerDTO wekaAnswerDTO = new WekaAnswerDTO();
		WekaAnswer wekaAnswer = wekaCommunication.runAlgorithm(algorithmType, location, id, table);
		mapper.map(wekaAnswer, wekaAnswerDTO);
		return wekaAnswerDTO;
	}
}
