
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
     * Okreslenie, z ktorego zrodla danych beda pobierane.
     * @param dataSourceType zrodlo danych, ktore bedzie odpytywane
     * @param location okresla lokalizacje zrodla danych (dla baz danych bedzie to adres bazy, dla plikow, lokalizacja pliku)
     * @param source nazwa bazy danych lub nazwa pliku (z rozszerzeniem)
     * @param user nazwa uzytkownika do polaczen z baza danych
     * @param password haslo uzytkownika do polaczen z baza danych
     */
    public void setDataSource(DSApiEnums dataSourceType, String location, String source, String user, String password);

    /**
     * Pobranie danych z wybranego zrodla w formie konsumowalnej przez Weke
     * @return dane do analizy
     */
    public Instances getData();
}
