package pl.edu.agh.ftj.datamining.server;

import pl.edu.agh.ftj.datamining.weka.algorithm.WekaAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ravduke
 */
public class WekaHolder {

	private WekaAnswer wekaAnswer;
	private static final Logger LOGGER = LoggerFactory.getLogger(WekaHolder.class);
	
	private WekaHolder() {
		LOGGER.info("WekaHolder::constructor() [...]");
	}
	
	private static final class WekaHolderHandler {
		private static final WekaHolder INSTANCE = new WekaHolder();
	}
	
        /**
         *
         * @return
         */
        public static WekaHolder getInstance() {
		return WekaHolderHandler.INSTANCE;
	}
	
        /**
         *
         * @return
         */
        public WekaAnswer get() {
		LOGGER.info("WekaHolder::get() [...]");
		return wekaAnswer;
	}
	
        /**
         *
         * @param wekaAnswer
         */
        public void put(WekaAnswer wekaAnswer) {
		LOGGER.info("WekaHolder::put() [wa=" + wekaAnswer.getAlgorithmName() + "]");
		this.wekaAnswer = wekaAnswer;
	}
}
