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

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getDatabase() {
		return this.database;
	}

	public void setDisplayedName(String displayedName) {
		this.displayedName = displayedName;
	}

	public String getDisplayedName() {
		return this.displayedName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return this.location;
	}

	public List<String> getTables() {
        if (tables == null) {
            tables = new ArrayList<String>();
        }
        return this.tables;
    }
}
