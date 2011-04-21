package pl.edu.agh.ftj.datamining.dbapi.exceptions;

/**
 * Wyjatki dla zrodel danych
 * @author janek
 * @version 1.0.0
 */
public class DataSourceException extends Exception{
    /**
     * Wykonuje konstruktor klasy nadrzednej
     */
    public DataSourceException(){
       super();
    }

    /**
     * Tworzy nowy obiekt wyjatku 
     * @param message tresc komunikatu wyjatku
     */
    public DataSourceException(String message){
        super(message);
    }
}
