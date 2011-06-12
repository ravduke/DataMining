package pl.edu.agh.ftj.datamining.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author Epi
 */
@RemoteServiceRelativePath("dbservice")
public interface DbService extends RemoteService {
    /**
     *
     * @return
     */
    public abstract List<DataSource> getDataSources();
}
