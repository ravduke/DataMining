package pl.edu.agh.ftj.datamining.dbapi.sqlite;

import pl.edu.agh.ftj.datamining.dbapi.core.IDataSource;
import pl.edu.agh.ftj.datamining.dbapi.exceptions.DataSourceException;
import weka.core.Instances;

/**
 * Klasa obslugujaca baze danych SQLite
 *
 * @author Sebastian Klek
 * @version 1.0.0
 */
public class Sqlite implements IDataSource {

    /**
     * Pobiera dane dla zrodla danych PostgreSQL.
     * 
     * @param location URL do bazy danych zawierajace typ sterownika, nazwe hosta, baze danych a takze uzytkownika i haslo.
     * @param table Nazwa tabeli, z ktorej beda pobierane dane.
     * @return Obiekt Instances z danymi dla algorytmow WEKI
     * @throws DataSourceException
     */
    public Instances getData(String location, String table) throws DataSourceException {

        SqliteLoader loader = new SqliteLoader(location, table);
        return loader.getInstances();
    }
}
