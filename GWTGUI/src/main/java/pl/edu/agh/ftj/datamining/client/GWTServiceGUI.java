 package pl.edu.agh.ftj.datamining.client;

 import com.extjs.gxt.samples.resources.client.Resources;
 import com.extjs.gxt.samples.resources.client.TestData;
import com.extjs.gxt.samples.resources.client.model.DBTable;
 import com.extjs.gxt.samples.resources.client.model.Folder;
import com.extjs.gxt.samples.resources.client.model.Music;
 import com.extjs.gxt.ui.client.Style.Orientation;
 import com.extjs.gxt.ui.client.widget.layout.FlowData;
 import com.extjs.gxt.ui.client.widget.menu.MenuBar;
 import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
 import com.extjs.gxt.ui.client.widget.ContentPanel;
 import com.extjs.gxt.ui.client.widget.button.Button;

 import com.extjs.gxt.ui.client.widget.menu.Menu;
 import com.extjs.gxt.ui.client.widget.menu.MenuItem;
 import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
 import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
 import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
 import com.extjs.gxt.ui.client.Style.Scroll;
 import com.extjs.gxt.ui.client.data.BaseModel;
 import com.extjs.gxt.ui.client.data.ModelData;
 import com.extjs.gxt.ui.client.event.ButtonEvent;
 import com.extjs.gxt.ui.client.event.ComponentEvent;
 import com.extjs.gxt.ui.client.event.Events;
 import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStore;
 import com.extjs.gxt.ui.client.util.Margins;
 import com.extjs.gxt.ui.client.widget.LayoutContainer;
 import com.extjs.gxt.ui.client.widget.TabItem;
 import com.extjs.gxt.ui.client.widget.TabPanel;

import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;

import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
 import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;



/**
 *
 * @author Rafal Jablonowski
 */
public class GWTServiceGUI extends LayoutContainer {

 Folder root;
 public  GWTServiceGUI() {
    root = new Folder();
 }
     /**
     * Tworzy interfejs uzytkownika
     * @param parent Element docelowy
     * @param index Miejsce wstawienia
     */
  @Override
   protected void onRender(Element parent, int index) {
     super.onRender(parent, index);

     MenuBar bar = createBar();
     ToolBar toolBar = createToolBar();
     TabPanel tabPanel = createTabPanel();

     ContentPanel panel = new ContentPanel();
     panel.setHeading("DataMining Application");
     panel.setWidth("99%");
     panel.setHeight(580);

     tabPanel.setHeight(579);

     panel.setTopComponent(bar);
     panel.add(toolBar);
     panel.add(tabPanel);
     
     //panel.setSize(1200, 300);

     add(panel, new FlowData(10));
   }

    /**
     * Tworzy pasek menu interfejsu uzytkownika
     */
  private MenuBar createBar() {

     Menu menu = new Menu();

     MenuItem itemResearch = new MenuItem("New Research");
     menu.add(itemResearch);
     menu.add(new SeparatorMenuItem());
     MenuItem itemConnect = new MenuItem("Connect");
     menu.add(itemConnect);
     MenuItem itemRefresh = new MenuItem("Refresh");
     menu.add(itemRefresh);
     MenuItem itemDisconnect = new MenuItem("Disconnect");
     menu.add(itemDisconnect);
     menu.add(new SeparatorMenuItem());
     MenuItem itemClear = new MenuItem("Clear Results");
     menu.add(itemClear);
     MenuItem itemGraphs = new MenuItem("Export Graphs");
     menu.add(itemGraphs);
     menu.add(new SeparatorMenuItem());
     MenuItem  itemExit = new MenuItem("Exit");
     menu.add( itemExit);
     
     MenuBar bar = new MenuBar();
     bar.setBorders(true);
     bar.setStyleAttribute("borderTop", "none");
     bar.add(new MenuBarItem("File", menu));

     menu = new Menu();
     menu.add(new MenuItem("User Interface"));
     menu.add(new MenuItem("Documentation"));
     MenuBarItem item3 = new MenuBarItem("Help", menu);
     bar.add(item3);

     menu = new Menu();
     menu.add(new MenuItem("Information"));
     menu.add(new MenuItem("Contact"));

     MenuBarItem item4 = new MenuBarItem("About", menu);
     bar.add(item4);
     return bar;
  }
  
    /**
     * Tworzy pasek narzedzi interfejsu uzytkownika
     */
  private ToolBar  createToolBar() {

     ToolBar toolBar = new ToolBar();

     toolBar.add(new SeparatorToolItem());
     Button newResearchButton = new Button("New Research");
     toolBar.add(newResearchButton);

     toolBar.add(new SeparatorToolItem());
     Button connectButton = new Button("Connect");
     connectButton.setIcon(Resources.ICONS.menu_show());
     final AsyncCallback<Folder> connectCallback = new AsyncCallback<Folder>() {
        public void onSuccess(Folder result) {
            root = result;
        }
        public void onFailure(Throwable caught) {
            // lblServerReply.setText("Communication failed");
        }
     };
        connectButton.addListener(Events.Select,
            new Listener<ButtonEvent>(){
            public void handleEvent(ButtonEvent e) {
                getService().getDatabases(connectCallback);
            }
        });

     toolBar.add(connectButton);

     toolBar.add(new SeparatorToolItem());
     Button refreshButton = new Button("Refresh");
     toolBar.add(refreshButton);

     toolBar.add(new SeparatorToolItem());
     Button disconnectButton = new Button("Disconnect");
     toolBar.add(disconnectButton);    

     toolBar.add(new SeparatorToolItem());
     Button clearButton = new Button("Clear Results");
     toolBar.add(clearButton);

     toolBar.add(new SeparatorToolItem());
     Button exportGraphsButton = new Button("Export Graphs");
     toolBar.add(exportGraphsButton);

     toolBar.add(new SeparatorToolItem());
      return toolBar;
  }

    /**
     * Tworzy panel kart interfejsu uzytkownika
     */
    private TabPanel createTabPanel() {

     TabPanel panel = new TabPanel();
     panel.setPlain(true);
     panel.setBorders(false);
    
     TabItem connect = new TabItem("Select Data");

     ContentPanel simple = new ContentPanel();
     simple.setHeaderVisible(false);
     simple.setFrame(true);
     simple.setWidth("100%");
     simple.setHeight(479);
     simple.setLayout(new RowLayout(Orientation.HORIZONTAL));
     //simple.setLayout(new BorderLayout());
     
     //FormData formData = new FormData("-500");
    // TextField<String> firstName = new TextField<String>();
    // firstName.setFieldLabel("Name");
     //simple.add(firstName);//, formData);

/*      COMBOBOX DATABASE TYPE       
     SimpleComboBox combo = new SimpleComboBox<String>();
     combo.setEmptyText("Select database...");  
     //combo.setLabelSeparator("Database type:");
     combo.setFieldLabel("Database type");
     combo.add("PostgreSQL");
     combo.add("SQLite");
     combo.add("Plain Text");
     combo.setTypeAhead(true);
     combo.setTriggerAction(TriggerAction.ALL);
     combo.setEditable(false);
 */
/*      COMBOBOX DATABASE NAME        
     final SimpleComboBox combo2 = new SimpleComboBox<String>();
     combo2.setEmptyText("Select database name...");
     //combo.setLabelSeparator("Database type:");
     combo2.setFieldLabel("Database name");
     combo2.add("database0");
     combo2.add("database1");
     combo2.add("database3");
     combo2.add("database3");
     combo2.setTypeAhead(true);
     combo2.setTriggerAction(TriggerAction.ALL);
     combo2.setEditable(false);
     combo2.setEnabled(false);
*/
   /*      COMBOBOX TABLE NAME        

    final SimpleComboBox<String> combo3 = new SimpleComboBox<String>();
     combo3.setEmptyText("Select table...");
     combo3.setLabelSeparator("Database type:");
     combo3.setFieldLabel("Table name");
     combo3.add("table0");
     combo3.add("table1");
     combo3.add("table3");
     combo3.add("table3");
     combo3.setTypeAhead(true);
     combo3.setTriggerAction(TriggerAction.ALL);
     combo3.setEditable(false);
     combo3.setEnabled(false);
        */

   /*  ComboBox combo3 = new ComboBox();
     combo3.setEmptyText("Select table...");
     combo3.setLabelSeparator("Database type:");
     combo3.setFieldLabel("Table name");
     ListStore<Stock> store3 = new ListStore<Stock>();
     List<Stock> stocks3 = new ArrayList<Stock>();
     stocks3.add(new Stock("table0"));
     stocks3.add(new Stock("table1"));
     stocks3.add(new Stock("table3"));
     stocks3.add(new Stock("table3"));
     store3.add(stocks3);
     combo3.setStore(store3);
     combo3.setDisplayField("name");
     combo3.setTypeAhead(true);
     combo3.setTriggerAction(TriggerAction.ALL);
     combo3.setEditable(false); */
     
     FormPanel p1 = new FormPanel();
     p1.setHeaderVisible(false);
     p1.setWidth("100%");
     p1.setHeight(470);
     p1.setLayout(new RowLayout(Orientation.VERTICAL));

    Folder model = root;

    TreeStore<ModelData> store = new TreeStore<ModelData>();
    store.add(model.getChildren(), true); 

    final TreePanel<ModelData> tree = new TreePanel<ModelData>(store);

    tree.setWidth(400);
    tree.setDisplayProperty("name");
    tree.getStyle().setLeafIcon(Resources.ICONS.music());
    
 /*    FormPanel p2 = new FormPanel();
     p2.setHeaderVisible(false);
     p2.setWidth("100%");
     p2.setHeight(50);
     p2.setLayout(new RowLayout(Orientation.HORIZONTAL));
     
     FormPanel p3 = new FormPanel();
     
     p3.setHeaderVisible(false);
     p3.setWidth("100%");
     p3.setHeight(50);
     p3.setLayout(new RowLayout(Orientation.HORIZONTAL));
     */
/*
     TextField<String> database_type = new TextField<String>();
     database_type.setEnabled(false);
     database_type.setValue("Database type:");
     database_type.setReadOnly(true);

     final TextField<String> db_name = new TextField<String>();
     db_name.setEnabled(false);
     db_name.setEmptyText("name...");
     db_name.setReadOnly(true);

     TextField<String> database_name = new TextField<String>();
     database_name.setEnabled(false);     
     database_name.setValue("Database name:");
     database_name.setReadOnly(true);

     TextField<String> Table_name = new TextField<String>();
     Table_name.setEnabled(false);
     Table_name.setValue("Table name:");
     Table_name.setReadOnly(true);  */


     /*      BUTTON CONNECT TO DATABASE        
     Button buttonConnect = new Button("Connect");
     buttonConnect.addListener(Events.OnClick, new Listener<ButtonEvent>() {
       public void handleEvent(ButtonEvent be) {
         Window.alert("Connected!");
         combo2.setEnabled(true);
         combo3.setEnabled(true);
         db_name.setValue("name @ localhost");
       }
     });
    */
    p1.add(tree , new RowData(0.2, 1, new Margins(4)));
     /*
     p1.add(database_type , new RowData(0.09, 1, new Margins(4)));
     p1.add(combo , new RowData(0.15, 1, new Margins(4)));
     p1.add(buttonConnect , new RowData(0.1, 1, new Margins(4)));
     p1.add(db_name , new RowData(0.25, 1, new Margins(4,4,4,44)));

     p2.add(database_name , new RowData(0.09, 1, new Margins(4)));
     p2.add(combo2 , new RowData(0.15, 1, new Margins(4)));

     p3.add(Table_name , new RowData(0.09, 1, new Margins(4)));
     p3.add(combo3 , new RowData(0.15, 1, new Margins(4)));
*/
     simple.add(p1 , new RowData(1, -1, new Margins(4,4,4,14)));
/*     simple.add(p2 , new RowData(1, -1, new Margins(4,4,4,14)));
     simple.add(p3 , new RowData(1, -1, new Margins(4,4,4,14)));
*/
     //Label label1 = new Label("Database type:");
     
     //simple.add(connect , new RowData(0.1, -1, new Margins(4)));
     //simple.add(label1, new RowData(0.3, -1, new Margins(4)));
    // simple.add(combo, new RowData(0.1, -1, new Margins(4)));     // frame adds ComboBox
     //simple.add(buttonConnect, new RowData(0.1, -1, new Margins(4)));
    // simple.add(combo2, new RowData(0.1, -1, new Margins(0, 4, 0, 4)));     // frame adds ComboBox
     //simple.add(combo3, new RowData(0.1, -1, new Margins(4)));     // frame adds ComboBox

     connect.add(simple);  // tab panel adds Frame
     connect.addStyleName("pad-text");
     //connect.addText("Just a plain old tab");    
     panel.add(connect);
/* ******************* SELECT ATTRIBUTES ********************************* */
     TabItem selectAttributes = new TabItem("Select Attributes");
    // iconTab.setIcon(Resources.ICONS.table());
     selectAttributes.addStyleName("pad-text");
     selectAttributes.addText("Just a plain old tab with an icon");
     panel.add(selectAttributes);

     TabItem results = new TabItem("Results");
     results.setScrollMode(Scroll.AUTO);
     results.addStyleName("pad-text");
     results.addText("Text Ajax tab");
    // results.setAutoLoad(new RequestBuilder(RequestBuilder.GET, GWT.getHostPageBaseURL() + "data/ajax1.html"));
     panel.add(results);

     TabItem visualization = new TabItem("Visualization");
     visualization.addListener(Events.Select, new Listener<ComponentEvent>() {
       public void handleEvent(ComponentEvent be) {
        // Window.alert("Event Tab Was Selected");
       }
     });
     visualization.addStyleName("pad-text");
     visualization.addText("I am tab 4's content. I also have an event listener attached.");
     panel.add(visualization);

     /*TabItem disabled = new TabItem("Disabled");
     disabled.setEnabled(false);
     panel.add(disabled);*/
     return panel;
    }

    public static GWTServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.

        return GWT.create(GWTService.class);
    }
}

