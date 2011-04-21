package pl.edu.agh.ftj.datamining.dbapi.test.plaintext;

import org.junit.Test;
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
    public void testGetData() throws Exception {
        System.out.println("getData");
        String file = "";
        String location = "";
        PlainText instance = new PlainText();
        Instances expResult = null;
        Instances result = instance.getData(location, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Glowna metoda testujaca wyjatki
     */
    @Test
    public void testException(){
        fail("Exceptions were not thrown as expected.");
    }
}