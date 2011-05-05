
package pl.edu.agh.ftj.datamaining.weka.webservice;

import pl.edu.agh.ftj.datamaining.weka.algorithm.WekaAlgorithm;
import pl.edu.agh.ftj.datamaining.weka.algorithm.WekaAnswer;

/**
 * Klasa implementująca inferfejs Webservisu dla Weki
 * Służy jako "most" łączący Webservisy z biblioteką Weka.
 * Webservis stworzony dla Axis2
 * adres: http://localhost:8080/axis2/services/WekaService/{nazwaMetody}?{parametr1=aaa&parametr2=bbb}
 * wsdl: http://localhost:8080/axis2/services/WekaService?wsdl
 * @author Szymon Skupien
 */
public class WekaService implements IWekaService {

    /**
     * w założeniu obiekt, w którym będzie obsługiwał biblioteke Weki.
     * Na razie nie dostałem jeszcze informacji o tym jak klasa będzie wyglądała.
     */
    private WekaAlgorithm wekaAlgorithm = new WekaAlgorithm();


    /**
     * @return zwraca tablicę z nazwami dostępnych algorytmów
     */
    public String[] getAlgorithms(){
        return wekaAlgorithm.getAlgorithms();
    }

    /**
     * Funkcja przygotowujaca algorytm Wei
     *
     * @param algorithmType wybiera typ algorytmu
     * @param location      adres URL do webservisu dbapi
     * @param id            id do danych (do webservisu dbapi)
     * @param table         table do danych (do webservisu dbapi)
     * @param options       opcje algorytmu 
     * @return              String z odpowiedzia czy operacja wykonala sie poprawnie
     */
    public String setAlgorithm(int algorithmType, String location, String id, String table, String[] options){
        wekaAlgorithm.setAlgorithmType(algorithmType);
        wekaAlgorithm.setOptions(options);
        //TODO: wyciaganie danych z webserwisu dbapi; ustawienie parametrow algorytmu; wyslanie odpowiedzi
        return "OKSet";
    }


    /**
     * Funkcja uruchamia dzialanie Weki
     * @return Zwraca przetworzone dane z Weki
     */
    public WekaAnswer runAlgorithm(){
       wekaAlgorithm.run();
       return wekaAlgorithm.getData();
    }



}
