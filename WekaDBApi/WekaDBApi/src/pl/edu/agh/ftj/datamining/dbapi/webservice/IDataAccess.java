
package pl.edu.agh.ftj.datamining.dbapi.webservice;

import java.util.List;
import pl.edu.agh.ftj.datamining.dbapi.core.BasicConfigurationDataSourceModel;
import weka.core.Instances;

/**
 * Interfejs okresla metody dostepu do danych z poziomu webservice'u.
 * @author janek
 * @version 1.0.0
 */
public interface IDataAccess {

    /**
     * Pobranie danych z wybranego zrodla w formie konsumowalnej przez Weke
     * @param id unikalny identyffikator zrodla danych
     * @param table nazwa tabeli lub pliku z danymi
     * @return dane do analizy
     */
    public Instances getData(String id, String table);


    /**
     * Zwraca informacje o dostepnych zrodlach danych
     * @return Informacje o źródłach danych
     */
    public BasicConfigurationDataSourceModel[] getDataSources();
}
