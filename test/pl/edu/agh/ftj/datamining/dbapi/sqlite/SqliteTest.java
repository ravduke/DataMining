package pl.edu.agh.ftj.datamining.dbapi.sqlite;

import org.junit.Test;
import static org.junit.Assert.*;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Test obslugi bazy sqlite
 * @author Sebastian Klek
 * @version 1.0.0
 */
public class SqliteTest {

    /**
     * Test metody getData z klasy Sqlite
     */
    @Test
    public void testGetData() throws Exception {
        String uri = "jdbc:sqlite:data/sqlite/weka.db";
        String table = "soybean";
        String location = "C:\\Program Files\\Weka-3-7\\data\\";
        String file =  location+ table + ".arff";
        
        Sqlite s = new Sqlite();
        Instances db_data = s.getData(uri, table);
        Instances file_data = DataSource.read( file );
        assertEquals(file_data.numAttributes(), db_data.numAttributes());
        assertEquals(file_data.numInstances(), db_data.numInstances());

        table = "segment-challenge";
        file = location + table + ".arff";
        s = new Sqlite();
        db_data = s.getData(uri, table);
        file_data = DataSource.read( file );
        assertEquals(file_data.numAttributes(), db_data.numAttributes());
        assertEquals(file_data.numInstances(), db_data.numInstances());
    }
}
