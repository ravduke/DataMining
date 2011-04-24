package pl.edu.agh.ftj.datamining.dbapi.core;

import java.io.Serializable;
import pl.edu.agh.ftj.datamining.dbapi.core.DSApiEnums.DataSourceType;

/**
 * Model danych dla konfiguracji zrodel danych
 * @author janek
 * @version 1.0.2
 */
public class ConfigurationDataSourceModel extends BasicConfigurationDataSourceModel implements Serializable{
    
    private String username, password;
    private int port;
    private DataSourceType dataSourceType;
    private String className;

    /**
     * Nazwa klasy odpowiedzialnej za obsluge danego zrodla danych
     * @return Nazwa klasy zawierajaca przestrzen nazw
     */
    public String getClassName() {
        return className;
    }

    /**
     * Nazwa klasy odpowiedzialnej za obsluge danego zrodla danych
     * @param Nazwa klasy zawierajaca przestrzen nazw
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Pobiera nazwa uzytkownika do polaczen z baza danych
     * @return nazwa uzytkownika do polaczen z baza danych
     */
    public String getUsername() {
        return username;
    }

    /**
     * ustawia nazwe uzytkownika
     * @param username nazwa uzytkownika
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metoda zwraca haslo dla danego uzytkownika
     * @return haslo do logowania dla danego uzytkownika
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metoda ustawia haslo dla danego uzytkownika
     * @param password haslo dla uzytkownika
     */
    public void setPassword(String password) {
        this.password = password;
    }

    
    /**
     * Port do polaczen z baza. Dla plikow -1.
     * @return numer portu do polaczen z baza danych. Dla plikow jako zrodlo danych -1.
     */
    public int getPort() {
        return port;
    }

    /**
     * Port do polaczen z baza. Dla plikow -1.
     * @param Numer portu do polaczen z baza danych. Dla plikow -1.
     */
    public void setPort(int port) {
        this.port = port;
    }


    /**
     * Zwraca typ zrodla danych
     * @return typ zrodla danych
     */
    public DataSourceType getDataSourceType() {
        return dataSourceType;
    }

    /**
     * Ustawia typ zrodla danych
     * @param dataSourceType typ zrodla danych
     */
    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

}
