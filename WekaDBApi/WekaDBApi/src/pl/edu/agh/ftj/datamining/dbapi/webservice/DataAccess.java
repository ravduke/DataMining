package pl.edu.agh.ftj.datamining.dbapi.webservice;

import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import pl.edu.agh.ftj.datamining.dbapi.core.BasicConfigurationDataSourceModel;
import pl.edu.agh.ftj.datamining.dbapi.core.ConfigurationDataSourceModel;
import pl.edu.agh.ftj.datamining.dbapi.core.ConfigurationHelper;
import pl.edu.agh.ftj.datamining.dbapi.core.IDataSource;
import pl.edu.agh.ftj.datamining.dbapi.exceptions.DataSourceException;
import weka.core.Instances;

/**
 * Glowna klasa WebService'u udostepniajaca dostep do danych poprzez API
 *
 * Klasa ma wbudowany logger, zapisujacy wyjatki do pliku ws.log
 * @author janek
 * @version 1.1.1
 */
@WebService
public class DataAccess implements IDataAccess {

    private IDataSource dataSource;
    private ConfigurationHelper helper;
    //private HashMap<String, ConfigurationDataSourceModel> config;
    private static final Logger log  = Logger.getLogger("wsLog");
    private FileHandler fh;
    BasicConfigurationDataSourceModel[] simpleConfArray;
    //ConfigurationDataSourceModel[] fullConfArray;
    
    /**
     * Inicjalizacja loggera
     */
    public DataAccess() {
        try {
            fh = new FileHandler("ws.log", true);
            log.addHandler(fh);
            readConfigurationFile();
            getDataSources();
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
            if(simpleConfArray == null)
                readConfigurationFile();
            int uid = Integer.parseInt(id) - 1;
            ConfigurationDataSourceModel conf = helper.getConfiguration().get(uid);
            dataSource = (IDataSource)(Class.forName(conf.getClassName()).newInstance());
            //System.out.println(conf.getLocation());
            //System.out.println(table);
            return dataSource.getData(conf.getLocation(), table);
            //return dataSource.getData(conf.getLocation()+"//"+conf.getDatabase()+"?user="+conf.getUsername()+"&datamine="+conf.getPassword(), table);
        } catch (InstantiationException ex) {
            log.log(Level.ALL,"InstatnioException");
            log.log(Level.WARNING,ex.getMessage());
            log.log(Level.ALL,ex.getStackTrace().toString());
            return null;
        } catch (IllegalAccessException ex) {
            log.log(Level.ALL,"IllegalAccessException");
            log.log(Level.WARNING,ex.getMessage());
            log.log(Level.ALL,ex.getStackTrace().toString());
            return null;
        } catch (ClassNotFoundException ex) {
            log.log(Level.ALL,"ClassNotFoundException");
            log.log(Level.WARNING,ex.getMessage());
            log.log(Level.ALL,ex.getStackTrace().toString());
            return null;
        }catch(NumberFormatException ex){
            log.log(Level.ALL,"NumberFormatException");
            log.log(Level.WARNING,ex.getMessage());
            log.log(Level.ALL,ex.getStackTrace().toString());
            return null;
        }catch(DataSourceException ex){
            log.log(Level.ALL,"DataSourceException");
            log.log(Level.WARNING,ex.getMessage());
            log.log(Level.ALL,ex.getStackTrace().toString());
            return null;
        }
    }

    /**
     * Zwraca zrodla dostepne danych 
     * @return podstawowe informacje o zrodle danych
     */
    public BasicConfigurationDataSourceModel[] getDataSources() {
        try {
            readConfigurationFile();
            simpleConfArray = new BasicConfigurationDataSourceModel[helper.getConfiguration().size()];
//            fullConfArray = new ConfigurationDataSourceModel[helper.getConfiguration().size()];
            //config = new HashMap<String, ConfigurationDataSourceModel>();
            for(int i = 0;i<helper.getConfiguration().size();i++){
                //config.put(helper.getConfiguration().get(i).getId(), helper.getConfiguration().get(i));

                simpleConfArray[i] = (BasicConfigurationDataSourceModel) helper.getConfiguration().get(i);
            }
            return simpleConfArray;
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
            log.log(Level.ALL,"Reading configuration file ...");
            helper = new ConfigurationHelper();
        } catch (Exception ex) {
            log.log(Level.ALL, "Initialization error while reading configuration file.");
            log.log(Level.ALL, ex.getMessage());
        }
    }
}
