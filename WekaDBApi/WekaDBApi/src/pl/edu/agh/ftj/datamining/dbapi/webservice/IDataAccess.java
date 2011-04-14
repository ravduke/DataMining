
package pl.edu.agh.ftj.datamining.dbapi.webservice;

import pl.edu.agh.ftj.datamining.dbapi.core.DSApiEnums;
import weka.core.Instances;

/**
 * Interfejs okresla metody dostepu do danych z poziomu webservice'u.
 * @author janek
 * @version 1.0.0
 */
public interface IDataAccess {

    /**
     * Pobranie danych z wybranego zrodla w formie konsumowalnej przez Weke
     * @return dane do analizy
     */
    public Instances getData();


    /**
     * Zwraca informacje o dostepnych zrodlach danych
     * @return Informacje o źródłach danych
     */
    public String[][] getDataSources();
}
