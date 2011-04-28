package pl.edu.agh.ftj.datamining.dbapi.postgresql;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Test obslugi bazy postgresql
 * @author Szymon Stupkiewicz
 */
public class PostgreSqlTest {

    public PostgreSqlTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getData method, of class PostgreSql.
     */
    @Test
    public void testGetData() throws Exception {
        System.out.println("getData");
//      TODO choose a file and corresponding table to test
        String uri = "";
        String table = "";
        String file = "";

        Postgresql instance = new Postgresql();
        Instances db_data = instance.getData(uri, table);
        Instances file_data = DataSource.read( file );

        assertEquals(file_data, db_data);
    }
}
