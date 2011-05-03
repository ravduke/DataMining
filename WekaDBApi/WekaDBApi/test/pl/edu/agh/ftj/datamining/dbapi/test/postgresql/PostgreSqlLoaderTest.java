package pl.edu.agh.ftj.datamining.dbapi.test.postgresql;

import org.junit.Test;
import pl.edu.agh.ftj.datamining.dbapi.postgresql.PostgresqlLoader;
import static org.junit.Assert.*;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Testy klasy PostgreSqlLoader.
 * @author Szymon Stupkiewicz
 * @version 1.0.0
 */
public class PostgreSqlLoaderTest {

    /**
     * Domyslny konstruktor
     */
    public PostgreSqlLoaderTest() {
    }

    /**
     * Test metody getInstances() - sprawdza, czy zgadza sie ilosc naglowkow
     * i ilosc danych w odpowiadajacej plikowi *.arff tabeli w bazie danych.
     */
    @Test
    public void testGetInstances() throws Exception {
        System.out.println("getInstances");
        String uri = "jdbc:postgresql://localhost/datamine?user=datamine&password=me@me.com";
        String table = "iris";
        String file = "/home/timon/uci/nominal/" + table + ".arff";

        PostgresqlLoader instance = new PostgresqlLoader(uri, table);

        Instances file_data = DataSource.read(file);
        Instances db_data = instance.getInstances();

        assertEquals(file_data.numAttributes(), db_data.numAttributes());
        assertEquals(file_data.numInstances(), db_data.numInstances());
    }
}
