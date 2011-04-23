package pl.edu.agh.ftj.datamining.dbapi.test.plaintext;

import org.junit.Test;
import pl.edu.agh.ftj.datamining.dbapi.exceptions.DataSourceException;
import pl.edu.agh.ftj.datamining.dbapi.plaintext.PlainText;
import static org.junit.Assert.*;
import weka.core.Instances;

/**
 * Podstawowy zestaw testow jednostkowych dla plikow tekstowych
 * @author janek
 * @version 1.0.0
 */
public class PlainTextTest {

    /**
     * Domyslny, pusty konstruktor.
     */
    public PlainTextTest() {
    }

    /**
     * Glowna metoda testujaca poprawnosc zwracanych danych
     * @throws Exception
     */
    @Test
    public void testGetData() {
        //dokonczyc test
        try {
            String location = "data";
            String file = "contact-lenses.arff.csv";
            PlainText instance = new PlainText();
            //Instances expResult = null;
            Instances result = instance.getData(location, file);
            //sprawdzenie poprawnosci naglowkow
            assertEquals(3,result.attribute("age").numValues());
            assertEquals(2,result.attribute("spectacle-prescrip").numValues());
            assertEquals(2, result.attribute("astigmatism").numValues());
            assertEquals(2, result.attribute("tear-prod-rate").numValues());
            assertEquals(3, result.attribute("contact-lenses").numValues());
            //sprawdzenie czy ilosc wynikow sie zgadza
            assertEquals(24, result.numInstances());
        } catch (DataSourceException ex) {
            fail("Test failed.");
        }
    }

    /**
     * Glowna metoda testujaca wyjatki
     */
    @Test
    public void testException() {
        String location = null;
        String file = null;
        PlainText instance = new PlainText();

        try {
            location = "data";
            file = "contact-lenses.arff.csv";
            instance.getData(location, file);
        } catch (DataSourceException ex) {
            fail("Exceptions was thrown.");
            fail(ex.getMessage());
        }
        try {
            file = "cos.csv";
            instance.getData(location, file);
        } catch (DataSourceException ex) {
            //zgodnie z oczekiwaniami polecial wyjatek
        }
        try {
            file = null;
            instance.getData(location, file);
        } catch (DataSourceException ex) {
            //zgodnie z oczekiwaniami polecial wyjatek
        }

        try {
            location = null;
            file = "contact-lenses.arff.csv";
            instance.getData(location, file);
        } catch (DataSourceException ex) {
            //zgodnie z oczekiwaniami polecial wyjatek
        }
    }

    /**
     * Testuje czy poleci wyjatek, jesli plik zawiera niepoprawnie sformatowane dane (bledny naglowek)
     */
    @Test
    public void testException2() {
        try {
            PlainText instance = new PlainText();
            String location = "data";
            String file = "bledny.csv";
            instance.getData(location, file);
        } catch (DataSourceException ex) {
            //zgodnie z oczekiwaniami polecial wyjatek
        }
    }
}
