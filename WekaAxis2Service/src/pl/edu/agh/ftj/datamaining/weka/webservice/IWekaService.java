package pl.edu.agh.ftj.datamaining.weka.webservice;

import pl.edu.agh.ftj.datamaining.weka.algorithm.WekaAnswer;

/**
 * Interfejs do Webservisu udostępniającego funkcjonalność Weki
 * @author Szymon Skupien
 */
public interface IWekaService {

    /**
     * @return zwraca tablicę z nazwami dostępnych algorytmów
     */
    public String[] getAlgorithms();

    /**
     * Funkcja przygotowujaca algorytm Weki
     *
     * @param algorithmType wybiera typ algorytmu (od 0 do 3) (indeks tablicy z metody getAlgorithms)
     * @param location      adres URL do webservisu dbapi
     * @param id            id do danych (do webservisu dbapi)
     * @param table         table do danych (do webservisu dbapi)
     * @param options       opcje algorytmu
     * @return              String z odpowiedzia czy operacja wykonala sie poprawnie
     */
    public String setAlgorithm(int algorithmType, String location, String id, String table, String[] options);

    /**
     * Funkcja uruchamia dzialanie algorytmu
     * @return Zwraca przetworzone dane z Weki
     */
    public WekaAnswer runAlgorithm();


}
