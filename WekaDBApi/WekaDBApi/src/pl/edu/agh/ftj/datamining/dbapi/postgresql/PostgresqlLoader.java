package pl.edu.agh.ftj.datamining.dbapi.postgresql;

import java.sql.SQLException;
import pl.edu.agh.ftj.datamining.dbapi.exceptions.PgsqlSourceException;
import weka.core.Instances;
import weka.core.converters.DatabaseLoader;

/**
 * Klasa obslugujaca import danych z bazy danych PostgreSQL do obiektów Instance
 *
 * @author Szymon Stupkiewicz
 * @version 1.0.0
 */
public final class PostgresqlLoader {

    private String dbUrl, relation;
    private Instances instances;

    public PostgresqlLoader(String url, String table) throws PgsqlSourceException {

        this.setDbUrl(url);
        this.setRelation(table);

        try {
            this.fetchInstances();
        } catch (ClassNotFoundException ex) {
            String message = "The required PostgreSQL JDBC driver was not found. Additional info:\n" + ex.getMessage();
            throw new PgsqlSourceException(message);
        } catch (SQLException ex) {
            String message = "There was an error in your SQL statement. Additional info:\n" + ex.getMessage();
            throw new PgsqlSourceException(message);
        } catch (Exception ex) {
            String message = "There was an exception. Additional info:\n" + ex.getMessage();
            throw new PgsqlSourceException(message);
        }
    }

    /**
     * Pobiera dane z bazy danych.
     *
     * @throws ClassNotFoundException Wyrzucany, gdy nie zostal zaladowany sterownik JDBC dla PostgreSQL
     * @throws Exception Wyrzucany, gdy API weki sobie tego zazyczy.
     */
    private void fetchInstances() throws ClassNotFoundException,Exception {
        Class.forName("org.postgresql.Driver");
        DatabaseLoader loader = new DatabaseLoader();
        loader.setSource(this.getDbUrl());
        loader.setQuery("SELECT * FROM " + this.getRelation());
        this.setInstances(loader.getDataSet());
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public Instances getInstances() {
        return instances;
    }

    public String getRelation() {
        return relation;
    }

    public void setDbUrl(String db_url) {
        this.dbUrl = db_url;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    private void setInstances(Instances instances) {
        this.instances = instances;
    }
}
