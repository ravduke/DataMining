package pl.edu.agh.ftj.datamining.dbapi.core;

/**
 * Klasa przeznaczona dla typow wyliczeniowych. Tutaj umieszczamy wszystkie rzeczy z nimi zwiazane.
 * @author janek
 * @version 1.0.0
 */
public class DSApiEnums {

    /**
     * Definiuje obslugiwane typy danych.
     * Obecnie dostepne: SQLite, PostgreSQL oraz PlainText (czyli pliki tekstowe)
     */
    public enum DataSourceType {SQLite,PostgreSQL,PlainText}
}
