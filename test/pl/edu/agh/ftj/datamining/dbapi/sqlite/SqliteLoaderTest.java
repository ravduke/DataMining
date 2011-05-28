package pl.edu.agh.ftj.datamining.dbapi.sqlite;

import org.junit.Test;
import pl.edu.agh.ftj.datamining.dbapi.sqlite.*;
import static org.junit.Assert.*;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Testy klasy SqliteLoader.
 * @author Sebastian Klek
 * @version 1.0.0
 */
public class SqliteLoaderTest {

    /**
     * Test metody getInstances() - sprawdza, czy zgadza sie ilosc naglowkow
     * i ilosc danych w odpowiadajacej plikowi *.arff tabeli w bazie danych.
     */
    @Test
    public void testGetInstances() throws Exception {
        String url = "jdbc:sqlite:data/sqlite/weka.db";
        String table = "cpu.with.vendor";
        String file = "C:\\Program Files\\Weka-3-7\\data\\" + table + ".arff";

        SqliteLoader sl = new SqliteLoader(url, table);

        Instances file_data = DataSource.read(file);
        Instances db_data = sl.getInstances();
        assertEquals(file_data.numAttributes(), db_data.numAttributes());
        assertEquals(file_data.numInstances(), db_data.numInstances());
    }
}
