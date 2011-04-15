package pl.edu.agh.ftj.datamining.dbapi.core;

import weka.core.Instances;
/**
 * Definicja zrodla danych. Interfejs musi byc zaimplementowany przez kazde zrodlo danych
 * (czyli SQLite, PostgreSQL, pliki tekstowe, itd.)
 *
 * @author janek
 * @version 1.0.0 beta
 */
public interface IDataSource {

    /**
     * Pobranie danych w formie konsumowalnej przez Weke
     * @param location URI do polaczen z baza lub lokalizacja pliku w systemie
     * @param table z danymi lub nazwa pliku zawierającego dane
     */
    public Instances getData(String table);
}
