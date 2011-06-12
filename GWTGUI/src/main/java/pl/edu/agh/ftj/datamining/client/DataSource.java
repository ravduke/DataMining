package pl.edu.agh.ftj.datamining.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Udaje BasicConfigurationDataSourceModel po stronie klienta
 * 
 * @author Epi
 */
public class DataSource implements Serializable {

	private String database;
	private String displayedName;
	private String id;
	private String location;
	private List<String> tables;

        /**
         *
         * @param database
         */
        public void setDatabase(String database) {
		this.database = database;
	}

        /**
         *
         * @return
         */
        public String getDatabase() {
		return this.database;
	}

        /**
         *
         * @param displayedName
         */
        public void setDisplayedName(String displayedName) {
		this.displayedName = displayedName;
	}

        /**
         *
         * @return
         */
        public String getDisplayedName() {
		return this.displayedName;
	}

        /**
         *
         * @param id
         */
        public void setId(String id) {
		this.id = id;
	}

        /**
         *
         * @return
         */
        public String getId() {
		return this.id;
	}

        /**
         *
         * @param location
         */
        public void setLocation(String location) {
		this.location = location;
	}

        /**
         *
         * @return
         */
        public String getLocation() {
		return this.location;
	}

        /**
         *
         * @return
         */
        public List<String> getTables() {
        if (tables == null) {
            tables = new ArrayList<String>();
        }
        return this.tables;
    }
}
