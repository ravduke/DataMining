package pl.edu.agh.ftj.datamining.dbapi.postgresql;

import pl.edu.agh.ftj.datamining.dbapi.core.IDataSource;
import pl.edu.agh.ftj.datamining.dbapi.exceptions.PgsqlSourceException;
import weka.core.Instances;

/**
 * Klasa obslugujaca baze danych PostgreSQL
 *
 * @author Szymon Stupkiewicz
 * @version 1.0.0
 */
public class Postgresql implements IDataSource {

    /**
     * Pobiera dane dla zrodla danych PostgreSQL.
     * 
     * @param location URL do bazy danych zawierajace typ sterownika, nazwe hosta, baze danych a takze uzytkownika i haslo.
     * @param table Nazwa tabeli, z ktorej beda pobierane dane.
     * @return Obiekt Instances z danymi dla algorytmow WEKI
     * @throws PgsqlSourceException 
     */
    public Instances getData(String location, String table) throws PgsqlSourceException {

        PostgresqlLoader loader = new PostgresqlLoader(location, table);
        return loader.getInstances();
    }
}
