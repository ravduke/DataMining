package pl.edu.agh.ftj.datamining.server.impl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import pl.edu.agh.ftj.datamining.client.ResultService;
import pl.edu.agh.ftj.datamining.server.WekaHolder;
import pl.edu.agh.ftj.datamining.weka.algorithm.WekaAnswer;
import weka.core.Instance;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultServiceImpl extends RemoteServiceServlet implements ResultService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResultServiceImpl.class);
	
	public List<List<Number[]>> getResults(String attrX, String attrY) {
		LOGGER.info("ResultService::getResultForCluser() [attrX=" + attrX + ", attrY=" + attrY + "]");
		WekaAnswer wa = WekaHolder.getInstance().get();
		List<List<Number[]>> results = new ArrayList<List<Number[]>>();

                for(int i = 0 ; i < wa.getNumberOfClusters(); i++) {
                    List<Number[]> valuesForClaster = new ArrayList<Number[]>();
                    for(Instance inst: wa.getClusterInstances(i)) {
                            valuesForClaster.add(wa.getValueForInstance(inst, attrX, attrY).toArray(new Number[2]));
                    }
                    results.add(valuesForClaster);
                }

                for(List<Number[]> objects : results)
                    for(Number[] obj: objects) LOGGER.info("ResultService::getResults() [x=" + obj[0] + ",y=" + obj[1] + "]");
                    
		return results;
	}

	public List<String> getAttributes() {
		LOGGER.info("ResultService::getAttributes() [...]");
		return WekaHolder.getInstance().get().getAttributeNames();
	}

	
}
