package pl.edu.agh.ftj.datamining.dbapi.webservice;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import pl.edu.agh.ftj.datamining.dbapi.core.ConfigurationDataSourceModel;
import pl.edu.agh.ftj.datamining.dbapi.core.ConfigurationHelper;
import pl.edu.agh.ftj.datamining.dbapi.core.IDataSource;
import weka.core.Instances;

/**
 * Glowna klasa WebService'u udostepniajaca dostep do danych poprzez API
 *
 * Klasa ma wbudowany logger, zapisujacy wyjatki do pliku ws.log
 * @author janek
 * @version 1.0.0
 */
@WebService
public class DataAccess implements IDataAccess {

    private IDataSource dataSource;
    private ConfigurationHelper helper;
    private static Logger log;
    private FileHandler fh;

    /**
     * Inicjalizacja loggera
     */
    public DataAccess() {
        log = Logger.getLogger("wsLog");
        try {
            fh = new FileHandler("ws.log", true);
            log.addHandler(fh);
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Zwraca dane pobrane ze źródła danych
     * @return Dane w postaci konsumowalnej przez Weke
     */
    public Instances getData() {
        readConfigurationFile();
        return dataSource.getData();
    }

    /**
     * Zwraca zrodla dostepne danych 
     * @return ID zrodla danych, wraz z nazwa
     */
    public String[][] getDataSources() {
        try {
            readConfigurationFile();
            List<ConfigurationDataSourceModel> conf = helper.getConfiguration();
            String[][] confArray = new String[conf.size()][];
            for (int i = 0; i < conf.size(); i++) {
                confArray[i] = new String[2];
                confArray[i][0] = conf.get(i).getId();
                confArray[i][0] = conf.get(i).getDisplayedName();
            }
            return confArray;
        } catch (Exception ex) {
            log.log(Level.CONFIG, "Initialization error while reading configuration file.");
            log.log(Level.CONFIG, ex.getMessage());
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
            log.log(Level.CONFIG, "Initialization error while reading configuration file.");
            log.log(Level.CONFIG, ex.getMessage());
        }
    }
}
