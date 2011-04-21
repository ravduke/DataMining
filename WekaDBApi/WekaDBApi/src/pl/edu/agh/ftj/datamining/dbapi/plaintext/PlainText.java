package pl.edu.agh.ftj.datamining.dbapi.plaintext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import pl.edu.agh.ftj.datamining.dbapi.core.IDataSource;
import pl.edu.agh.ftj.datamining.dbapi.exceptions.DataSourceException;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

/**
 * Klasa do obslugi plikow tekstowych
 * @author janek
 * @version 1.0.1
 */
public class PlainText implements IDataSource{

    /**
     * Pobieranie danych dla danego zrodla danych. W przypadku wyjatku odpowiedni komunikat jest ustawiony, a wyjatek wyrzucany.
     * @param file tabela z danymi lub nazwa pliku z danymi
     * @param location lokalizacja pliku w systemie plikow
     * @throws DataSourceException wyrzucany w przypadku, gdy odczyt plikow sie nie powiedzie
     * @return obiekt z danymi
     */
    public Instances getData(String location, String file) throws DataSourceException{
        CSVLoader cvs = new CSVLoader();
        try{
            cvs.setSource(new File(location+"/"+file));
            return cvs.getDataSet();
        }catch(FileNotFoundException e){
            throw new DataSourceException("File not found. Please check configuration file.");
        }catch(IOException e){
            throw new DataSourceException("File not found or couldn't be opened. Please check configuration file.");
        }catch(Exception e){
            throw new DataSourceException(e.getMessage());
        }
    }
}
