package pl.edu.agh.ftj.datamining.dbapi.core;

import java.io.Serializable;

/**
 * Model danych dla konfiguracji zrodel danych zawierajacy podstawowe informacje
 * @author janek
 * @version 1.0.1
 */
public class BasicConfigurationDataSourceModel implements Serializable{
    private String id, displayedName, database, location;
    private String[] tables;
    

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
     * Pobiera tablice z nazwami tabel lub liste plikow zrodlowych
     * @return Tablica nazwa tabel z danymi dla danego zrodla lub w przypadku plikow ich nazwy
     */
    public String[] getTables() {
        return tables;
    }

    /**
     * Ustawia liste plikow lub tabel zawierajacych dane
     * @param tables lista plikow lub tabel z bazy danych zawierająca dane
     */
    public void setTables(String[] tables) {
        this.tables = tables;
    }
    
}
