package pl.edu.agh.ftj.datamining.client;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import pl.edu.agh.ftj.datamining.client.shared.CommunicationType;
import pl.edu.agh.ftj.datamining.client.shared.WekaAnswerDTO;

/**
 *
 * @author ravduke
 */
public interface WekaServiceAsync {
	
    /**
     *
     * @param type
     * @param callback
     */
    public void createCommunication(CommunicationType type, AsyncCallback<Void> callback);
	
    /**
     *
     * @param callback
     */
    public void getAlgorithms(AsyncCallback<List<String>> callback);
	
        /**
         *
         * @param algorithmType
         * @param id
         * @param table
         * @param options
         * @param callback
         */
        public void runAlgorithm(Integer algorithmType, /*String location,*/ String id, String table, String options, AsyncCallback<WekaAnswerDTO> callback);
}
