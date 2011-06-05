package pl.edu.agh.ftj.datamining.dbapi.postgresql;

import org.junit.Test;
import static org.junit.Assert.*;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Test obslugi bazy postgresql
 * @author Szymon Stupkiewicz
 * @version 1.0.2
 */
public class PostgreSqlTest {

    /**
     * Standardowy konstruktor - nic nie robi.
     */
    public PostgreSqlTest() {
    }

    /**
     * Test metody getData z klasy Postgresql
     */
    @Test
    public void testGetData() throws Exception {
        System.out.println("getData");
//      TODO choose a file and corresponding table to test
        String uri = "jdbc:postgresql://localhost/datamine?user=datamine&password=me@me.com";
        String table = "iris";
        String file = "C:\\Program Files\\Weka-3-7\\data\\" + table + ".arff";
        
        Postgresql instance = new Postgresql();
        Instances db_data = instance.getData(uri, table);
        Instances file_data = DataSource.read( file );

        assertEquals(file_data.numAttributes(), db_data.numAttributes());
        assertEquals(file_data.numInstances(), db_data.numInstances());
    }
}
