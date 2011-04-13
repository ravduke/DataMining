package pl.edu.agh.ftj.datamining.dbapi.webservice;

import javax.jws.WebService;
import pl.edu.agh.ftj.datamining.dbapi.core.DSApiEnums;
import pl.edu.agh.ftj.datamining.dbapi.core.IDataSource;
import weka.core.Instances;

/**
 * Glowna klasa WebService'u udostepniajaca dostep do danych poprzez API
 * @author janek
 * @version 1.0.0
 */
@WebService
public class DataAccess implements IDataAccess{

    private IDataSource dataSource;

    /**
     * Ustawia zrodlo danych
     * @param dataSourceType wybrane zrodlo danych
     * @param location lokalizacja bazy danych lub pliku
     * @param source plik zrodlowy danych lub nazwa bazy zawierajacej dane
     * @param user nazwa uzytkownika do polaczen z baza danych
     * @param password haslo uzytkownika do polaczen z baza danych
     */
    public void setDataSource(DSApiEnums dataSourceType, String location, String source, String user, String password)
    {
        /*try{

        }catch(DBApiExceptions e){
            
        }*/
    }

    /**
     * Zwraca dane pobrane ze źródła danych
     * @return Dane w postaci konsumowalnej przez Weke
     */
    public Instances getData() {
        return dataSource.getData();
    }

}
