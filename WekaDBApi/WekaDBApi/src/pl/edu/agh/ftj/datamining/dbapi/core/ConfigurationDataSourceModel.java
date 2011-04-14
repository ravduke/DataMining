package pl.edu.agh.ftj.datamining.dbapi.core;

import java.io.Serializable;

/**
 * Model danych dla konfiguracji zrodel danych
 * @author janek
 * @version 1.0.0
 */
public class ConfigurationDataSourceModel implements Serializable{
    
    private String id, username, password, displayedName, database, location;
    private int port;

    /**
     * Metoda zwraca unikalny identyfikator zrodla danych
     * @return unikalny identyfikator
     */
    public String getId() {
        return id;
    }

    /**
     * Metoda ustawia unikalny identyfikator zrodla danych
     * @param unikalny identyfikator
     */
    public void setId(String id) {
        this.id = id;
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
     * Zwraca nazwe zrodla danych dla
     * @return wyswietlana nazwa zrodla danych
     */
    public String getDisplayedName() {
        return displayedName;
    }

    /**
     * Ustawia wyswietlana nazwe zrodla danych
     * @param displayedName wuswietalana nazwa zrodla danych
     */
    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }

    /**
     * Nazwa bazy danych przechowywanej na serwerze (lub nazwa pliku wraz z rozszerzeniem)
     * @return nazwa bazy danych lub nazwa pliku (z rozszerzeniem)
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Ustawia nazwę bazy danych lub nazwe pliku z danymi. Plik musi zawierac rozszerzenie
     * @param nazwa bazy danych lub nazwa pliku (z rozszerzeniem)
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * Zwraca informacje o lokalizacji bazy danych (adres, nie zawierajacy informacji o sterowniku, np. myserver.com, mojebazy.pl/uzytkownik). W przypadku plikow zwraca sciezke dostepu na serwerze.
     * @return lokalizacja bazy danych na serwerze lub pliku
     */
    public String getLocation() {
        return location;
    }

    /**
     * 
     * Sciezka do bazy nie zawiera portu, ani sterownika, wylacznie adres np. myserver.com, mojebazy.pl/uzytkownik.
     * Sciezka do pliku nie zawiera nazwy pliku.
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
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
}
