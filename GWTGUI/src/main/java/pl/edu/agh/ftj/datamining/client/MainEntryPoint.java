
package pl.edu.agh.ftj.datamining.client;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;



/**
 * Main entry point.
 *
 * @author Rafal Jablonowski
 */
public class MainEntryPoint implements EntryPoint {
    /** 
     * Tworzy instancje MainEntryPoint
     */
    public MainEntryPoint() {


    }

    /** 
     * Metoda entry point, wywolywana automatycznie poprzez zaladowanie
     * modulu, ktory deklaruje klase wykonawcza jako punkt wejscia (entry-point)
     */
    public void onModuleLoad() {
           RootPanel.get().add(new GWTServiceGUI());

    }
}
