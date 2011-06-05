package pl.edu.agh.ftj.datamining.dbapi.webservice;


import pl.edu.agh.ftj.datamining.dbapi.core.BasicConfigurationDataSourceModel;

/**
 * Interfejs okresla metody dostepu do danych z poziomu webservice'u.
 * @author janek
 * @version 1.0.1
 */
public interface IDataAccess {

    /**
     * Pobranie danych z wybranego zrodla w formie konsumowalnej przez Weke
     * @param id unikalny identyffikator zrodla danych
     * @param table nazwa tabeli lub pliku z danymi
     * @return dane do analizy
     */
    public String getData(String id, String table);


    /**
     * Zwraca informacje o dostepnych zrodlach danych
     * @return Informacje o źródłach danych
     */
    public BasicConfigurationDataSourceModel[] getDataSources();
}
