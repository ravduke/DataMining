package pl.edu.agh.ftj.datamining.dbapi.core;

import weka.core.Instances;
/**
 * Definicja zrodla danych. Interfejs musi byc zaimplementowany przez kazde zrodlo danych
 * (czyli SQLite, PostgreSQL, pliki tekstowe, itd.)
 *
 * @author janek
 * @version 1.0.0 alfa
 */
public interface IDataSource {

    /** 
     * Ustawianie parametrow zrodla danych do polaczen
     * @param location URI do polaczen z baza lub lokalizacja pliku w systemie
     * @param username Nazwa uzytkownika do polaczen
     * @param password Haslo
     */
    public void setDataSourceParameters(String location,String username, String password);

    /**
     * Pobranie danych w formie konsumowalnej przez Weke
     * @return dane do analizy
     */
    public Instances getData();
}
