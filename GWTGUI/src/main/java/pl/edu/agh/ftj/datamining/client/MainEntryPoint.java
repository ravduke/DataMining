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
   /*     final Label label = new Label("Hello, GWT!!!");
        final Button button = new Button("Click me!");

        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                label.setVisible(!label.isVisible());
            }
        });

        RootPanel.get().add(button);
        RootPanel.get().add(label); */


      /* RootPanel.get().add(new GWTServiceUsage());*/
       /*  MessageBox.info("Message", "Hello World!!", null);

         ContentPanel cp = new ContentPanel();
         cp.setHeading("Folder Contents");
         cp.setSize(250,140);
         cp.setPosition(10, 10);
         cp.setCollapsible(true);
         cp.setFrame(true);
         cp.setBodyStyle("backgroundColor: white;");
         cp.getHeader().addTool(new ToolButton("x-tool-gear"));
         cp.getHeader().addTool(new ToolButton("x-tool-close"));
         cp.addText("BogusText");
         cp.addButton(new com.extjs.gxt.ui.client.widget.button.Button("Ok"));
         cp.setIconStyle("tree-folder-open");
         RootPanel.get().add(cp);
         cp.layout();*/

         RootPanel.get().add(new GWTServiceGUI());

    }
}
