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
     * Pobieranie danych dla danego zrodla danych
     * @param table tabela z danymi lub nazwa pliku z danymi
     */
    public Instances getData(String table) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
