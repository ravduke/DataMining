package pl.edu.agh.ftj.datamining.dbapi.core;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Klasa testujaca konfiguracje.
 *
 * @author janek
 * @version 1.0.2
 */
public class ConfigurationHelperTest {

  /**
     * Test poprawnosci odczytu konfiguracji
     */
    @Test
    public void testGetConfiguration() {
        try{
            System.out.println("getConfiguration");
            ConfigurationHelper instance = new ConfigurationHelper();
            List<ConfigurationDataSourceModel> result = instance.getConfiguration();
            assertEquals(3, result.size());
            assertEquals("1", result.get(0).getId());
            assertEquals(DSApiEnums.DataSourceType.PlainText, result.get(0).getDataSourceType());
            assertEquals("Tekst",result.get(0).getDisplayedName());
            assertEquals("C:\\Program Files\\Weka-3-7\\data",result.get(0).getLocation());
            assertEquals("",result.get(0).getDatabase());
            assertEquals(-1,result.get(0).getPort());
            assertEquals("",result.get(0).getUsername());
            assertEquals("",result.get(0).getPassword());
            assertEquals("pl.edu.agh.ftj.datamining.dbapi.plaintext.PlainText",result.get(0).getClassName());
            //assertEquals(1,result.get(0).getTables().length);
            //for(int i = 0;i<1;i++)
//            assertEquals("iris.arff.csv",result.get(0).getTables()[0]);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            fail("Read configuration exception was thrown.");
        }
    }

}