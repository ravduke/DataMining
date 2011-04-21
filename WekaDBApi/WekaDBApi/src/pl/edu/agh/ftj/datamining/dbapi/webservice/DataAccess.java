package pl.edu.agh.ftj.datamining.dbapi.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import pl.edu.agh.ftj.datamining.dbapi.core.BasicConfigurationDataSourceModel;
import pl.edu.agh.ftj.datamining.dbapi.core.ConfigurationHelper;
import pl.edu.agh.ftj.datamining.dbapi.core.IDataSource;
import pl.edu.agh.ftj.datamining.dbapi.exceptions.DataSourceException;
import weka.core.Instances;

/**
 * Glowna klasa WebService'u udostepniajaca dostep do danych poprzez API
 *
 * Klasa ma wbudowany logger, zapisujacy wyjatki do pliku ws.log
 * @author janek
 * @version 1.0.1
 */
@WebService
public class DataAccess implements IDataAccess {

    private IDataSource dataSource;
    private ConfigurationHelper helper;
    private static final Logger log  = Logger.getLogger("wsLog");
    private FileHandler fh;

    /**
     * Inicjalizacja loggera
     */
    public DataAccess() {
        try {
            fh = new FileHandler("ws.log", true);
            log.addHandler(fh);
            readConfigurationFile();
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Zwraca dane pobrane ze źródła danych
     * @param id unikalny identyfikator zrodla danych
     * @param table nazwa tabeli z danymi lub nazwa pliku z danymi
     * @return Dane w postaci konsumowalnej przez Weke
     */
    public Instances getData(String id, String table) {
        //instancjonowanie z wykorzystaniem mechanizmu refleksji
        try{
            return dataSource.getData(table, table);
        }catch(DataSourceException e){
            log.log(Level.WARNING,e.getMessage());
            return null;
        }
    }

    /**
     * Zwraca zrodla dostepne danych 
     * @return podstawowe informacje o zrodle danych
     */
    public List<BasicConfigurationDataSourceModel> getDataSources() {
        try {
            readConfigurationFile();
            List<BasicConfigurationDataSourceModel> confList = new ArrayList<BasicConfigurationDataSourceModel>();
            confList.add((BasicConfigurationDataSourceModel) helper.getConfiguration());
            return confList;
        } catch (Exception ex) {
            log.log(Level.ALL, "Initialization error while reading configuration file.");
            log.log(Level.ALL, ex.getMessage());
            return null;
        }
    }

    /**
     * Odczyt pliku konfiguracyjnego z uzyciem klasy ConfigurationHelper
     */
    private void readConfigurationFile() {
        try {
            helper = new ConfigurationHelper();
        } catch (Exception ex) {
            log.log(Level.ALL, "Initialization error while reading configuration file.");
            log.log(Level.ALL, ex.getMessage());
        }
    }
}
