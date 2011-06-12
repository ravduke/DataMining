package pl.edu.agh.ftj.datamining.client;

import java.util.List;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author ravduke
 */
@RemoteServiceRelativePath("resultservice")
public interface ResultService extends RemoteService {

    /**
     *
     * @param attrX
     * @param attrY
     * @return
     */
    public List<List<Number[]>> getResults(String attrX, String attrY);
	
        /**
         *
         * @return
         */
        public List<String> getAttributes();
}
