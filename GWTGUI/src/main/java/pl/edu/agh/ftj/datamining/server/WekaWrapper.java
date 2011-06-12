package pl.edu.agh.ftj.datamining.server;

import pl.edu.agh.ftj.datamining.weka.algorithm.WekaAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.ftj.datamining.client.shared.WekaAnswerDTO;


/**
 *
 * @author ravduke
 */
public class WekaWrapper {

	private WekaAnswer wa;
	private WekaAnswerDTO waDTO;
	private static final Logger LOGGER = LoggerFactory.getLogger(WekaWrapper.class);
	
        /**
         *
         * @param wa
         */
        public WekaWrapper(WekaAnswer wa) {
		LOGGER.info("WekaWrapper::constructor() [alg. name=" + wa.getAlgorithmName() + "]");
		this.wa = wa;
		this.waDTO = new WekaAnswerDTO();
		wrap();
        WekaHolder.getInstance().put(wa);
	}
	
	private void wrap() {
		waDTO.setAcuity(this.wa.getAcuity());
		waDTO.setAlgorithmName(this.wa.getAlgorithmName());
		waDTO.setAlgorithmType(this.wa.getAlgorithmType());
		waDTO.setAssignments(this.wa.getAssignments());
		waDTO.setClusterModelsNumericAtts(this.wa.getClusterModelsNumericAtts());
		waDTO.setClusterNominalCounts(this.wa.getClusterNominalCounts());
		waDTO.setClusterPriors(this.wa.getClusterPriors());
		waDTO.setClusterSizes(this.wa.getClusterSizes());
		waDTO.setCorrect(this.wa.isCorrect());
		waDTO.setCutoff(this.wa.getCutoff());
		waDTO.setGraph(this.wa.getGraph());
		waDTO.setGraphType(this.wa.getGraphType());
		waDTO.setInfo(this.wa.getInfo());
		waDTO.setMaxIterations(this.wa.getMaxIterations());
		waDTO.setMinStdDev(this.wa.getMinStdDev());
		waDTO.setNumberOfClusters(this.wa.getNumberOfClusters());
		waDTO.setNumClusters(this.waDTO.getNumClusters());
		waDTO.setOptions(this.wa.getOptions());
		waDTO.setPrintNewick(this.wa.isPrintNewick());
		waDTO.setRevision(this.wa.getRevision());
		waDTO.setSquaredError(this.wa.getSquaredError());
	}
	
        /**
         *
         * @return
         */
        public WekaAnswerDTO getWekaAnswer() {
		return waDTO;
	}
}
