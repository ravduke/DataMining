package pl.edu.agh.ftj.datamining.client;

import java.util.List;
import pl.edu.agh.ftj.datamining.client.shared.CommunicationType;
import pl.edu.agh.ftj.datamining.client.shared.WekaAnswerDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/*
 * Serwis odpowiedzialny za komunikacje z klientem Weki.
 * @author Tomasz Pyszka
 */
@RemoteServiceRelativePath("wekaservice")
public interface WekaService extends RemoteService {

	/*
	 * Metoda odpowiedzialna za utworzenie odpowiedniego klienta.
	 * @param typ komunikacji REST lub AXIS {@link Type}
	 */
	public void createCommunication(CommunicationType type);
	
	/*
	 * Metoda odpowiedzialna za popbranie z klienta Weki dostepnych algorytmow.
	 * @return zwraca dostepne algorytmy
	 */
	public List<String> getAlgorithms();
	
	/*
	 * Metoda odpowiedzialna za uruchomienie za pomoca klienta algorytmu Weki.
	 * @return Zwraca dane z algorytmu {@link WekaAnswer}.
	 */
	public WekaAnswerDTO runAlgorithm(Integer algorithmType, String location, String id, String table);
}
