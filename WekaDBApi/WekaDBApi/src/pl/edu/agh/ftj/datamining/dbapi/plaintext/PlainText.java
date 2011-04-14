package pl.edu.agh.ftj.datamining.dbapi.plaintext;

import pl.edu.agh.ftj.datamining.dbapi.core.IDataSource;
import weka.core.Instances;

/**
 * Klasa do obslugi plikow tekstowych
 * @author janek
 * @version 1.0.0 beta
 */
public class PlainText implements IDataSource{

    /**
     * Ustawianie zrodla danych
     * @param location
     * @param username
     * @param password
     */
    public Instances getData(String location, String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
