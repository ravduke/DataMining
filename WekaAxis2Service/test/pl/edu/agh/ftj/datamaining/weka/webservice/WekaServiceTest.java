/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.agh.ftj.datamaining.weka.webservice;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.edu.agh.ftj.datamaining.weka.algorithm.WekaAnswer;

/**
 *
 * @author bat
 */
public class WekaServiceTest {

    public WekaServiceTest() {
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
     * Test of getAlgorithms method, of class WekaService.
     */
    @Test
    public void testGetAlgorithms() {
        System.out.println("getAlgorithms");
        WekaService instance = new WekaService();
        String[] expResult = null;
        String[] result = instance.getAlgorithms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAlgorithm method, of class WekaService.
     */
    @Test
    public void testSetAlgorithm() {
        System.out.println("setAlgorithm");
        int algorithmType = 0;
        String location = "";
        String id = "";
        String table = "";
        String[] options = null;
        WekaService instance = new WekaService();
        String expResult = "";
        String result = instance.setAlgorithm(algorithmType, location, id, table, options);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runAlgorithm method, of class WekaService.
     */
    @Test
    public void testRunAlgorithm() {
        System.out.println("runAlgorithm");
        WekaService instance = new WekaService();
        WekaAnswer expResult = null;
        WekaAnswer result = instance.runAlgorithm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}