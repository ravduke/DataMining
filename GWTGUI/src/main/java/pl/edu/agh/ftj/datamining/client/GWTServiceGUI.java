 package pl.edu.agh.ftj.datamining.client;

 import com.extjs.gxt.samples.resources.client.Resources;
import com.extjs.gxt.samples.resources.client.model.DBTable;
 import com.extjs.gxt.samples.resources.client.model.Folder;
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
import com.extjs.gxt.ui.client.Style.SelectionMode;
 import com.extjs.gxt.ui.client.data.ModelData;
 import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.TreeStore;
 import com.extjs.gxt.ui.client.util.Margins;
 import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
 import com.extjs.gxt.ui.client.widget.TabItem;
 import com.extjs.gxt.ui.client.widget.TabPanel;


import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;

import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
 import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;
import pl.edu.agh.ftj.datamining.client.shared.CommunicationType;



/**
 * Klasa odpowiedzialna za wyswietlanie GUI.
 * @author Rafal Jablonowski
 */
public class GWTServiceGUI extends LayoutContainer {

  /**
  * Panel glowny
  */
 ContentPanel panel;
 /**
  * Pasek menu
  */
 MenuBar bar;
 /**
  * Pasek narzedzi
  */
 ToolBar toolBar;
  /**
  * Panel zakladek
  */
 TabPanel tabPanel;
 /**
  * Zakladka wyboru atrybutow
  */
 TabItem selectAttributes;
  /**
  * Drzewo danych
  */
  TreePanel<ModelData> tree;
  /**
  * Polaczenie z serwerem bazy danych
  */
 boolean DBconnected = false;
 /**
  * Polaczenie z serwerem Weki
  */
 boolean Wekaconnected = false;
  /**
  * Wybranie bazy danych
  */
 boolean selectDataTab = false;
 public  GWTServiceGUI() {
   

 }
     /**
     * Tworzy interfejs uzytkownika
     * @param parent Element docelowy
     * @param index Miejsce wstawienia
     */
  @Override
   protected void onRender(Element parent, int index) {
     super.onRender(parent, index);

     //
     // Panel glowny
     //
     final ContentPanel panel = new ContentPanel();
     panel.setHeading("DataMining Application");
     panel.setWidth("99%");
     panel.setHeight(580);

     //
     // Pasek menu
     //
     MenuBar bar = new MenuBar();
     
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
     //////////////////////////////////////////////////////////////
     // Pasek narzedzi
     //////////////////////////////////////////////////////////////
     toolBar = new ToolBar();

    final TextField<String> info_name = new TextField<String>();
    info_name.setEnabled(false);
    info_name.setValue("Connect to the server");
    info_name.setReadOnly(true);

    final TextField<String> database_type = new TextField<String>();
    database_type.setEnabled(false);
    database_type.setValue("Database type:");
    database_type.setReadOnly(true);
    database_type.setVisible(false);

    final TextField<String> database_name = new TextField<String>();
    database_name.setEnabled(false);
    database_name.setValue("Database name:");
    database_name.setReadOnly(true);
    database_name.setVisible(false);

    final TextField<String> table_name = new TextField<String>();
    table_name.setEnabled(false);
    table_name.setValue("Table name:");
    table_name.setReadOnly(true);
    table_name.setVisible(false);

     toolBar.add(new SeparatorToolItem());
     final Button connectButton = new Button("Connect");
     connectButton.setIcon(Resources.ICONS.menu_show());

     connectButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if(!DBconnected)
                getService().getDatabases(new AsyncCallback<Folder>() {
                    public void onFailure(Throwable caught) {
                        MessageBox.alert("Failure", "Connecting to server failed.", null);
                        DBconnected = false;
                    }

                    public void onSuccess(Folder result) {
                            tree.getStore().removeAll();
                            tree.collapseAll();
                            tree.getStore().add(result.getChildren(), true);
                            DBconnected = true;
                            info_name.setValue("    Data selection:");
                            MessageBox.info("Connected", "Connected to the DB server.", null);
                            database_type.setVisible(true);
                            database_name.setVisible(true);
                            table_name.setVisible(true);
                    }
                });
                else
                    MessageBox.alert("Connection", "Already connected to DB server.", null);

                if(!Wekaconnected)
                getWekaService().createCommunication(CommunicationType.REST, new AsyncCallback<Void>() {

                    public void onFailure(Throwable caught) {
                        MessageBox.alert("Connection", "Connecting to REST Weka server failed.", null);
                        Wekaconnected = false;
                    }

                    public void onSuccess(Void result) {
                        Wekaconnected = true;
                        MessageBox.info("Connected", "Connected to the Weka server.", null);
/*                       getWekaService().getAlgorithms(new AsyncCallback<List<String>>() {

                            public void onFailure(Throwable caught) {
                                MessageBox.alert("Connection", "getAlgo ERROR", null);
                            }

                            public void onSuccess(List<String> result) {
                                info_name.setValue(result.get(0).toString());
                            }
                        });*/
                    }
                });
                else
                    MessageBox.alert("Connection", "Already connected to Weka server.", null);
            }
      });
     toolBar.add(connectButton);

     toolBar.add(new SeparatorToolItem());
     Button refreshButton = new Button("Refresh");
     refreshButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if(DBconnected){
                    getService().getDatabases(new AsyncCallback<Folder>() {
                        public void onFailure(Throwable caught) {
                            tree.getStore().removeAll();
                            tree.collapseAll();
                            DBconnected = false;
                            selectDataTab = false;
                            selectAttributes.disable();
                            database_type.setValue("Database type:");
                            database_name.setValue("Database name:");
                            table_name.setValue("Table name:");
                            info_name.setValue("Connect to the server");
                            database_type.setVisible(false);
                            database_name.setVisible(false);
                            table_name.setVisible(false);
                            MessageBox.alert("Failure", "Connecting to DB server failed.", null);
                        }

                        public void onSuccess(Folder result) {
                            tree.getStore().removeAll();
                            tree.collapseAll();
                            tree.getStore().add(result.getChildren(), true);
                            MessageBox.info("Connection refreshed.", "Connection to the DB server refreshed.", null);
                        }
                    });
             } else
                MessageBox.alert("Cannot refresh.", "Not connected the DB server.", null);

              if(Wekaconnected)
                getWekaService().createCommunication(CommunicationType.REST, new AsyncCallback<Void>() {

                    public void onFailure(Throwable caught) {
                        MessageBox.alert("Connection", "Connecting to REST Weka server failed.", null);
                        Wekaconnected = false;
                    }

                    public void onSuccess(Void result) {
                        Wekaconnected = true;
                        MessageBox.info("Connection refreshed.", "Connection to the Weka server refreshed.", null);
                    }
                }); else
                MessageBox.alert("Cannot refresh.", "Not connected the Weka server.", null);
                
            }
        });
     toolBar.add(refreshButton);

     toolBar.add(new SeparatorToolItem());
     Button disconnectButton = new Button("Disconnect");
     disconnectButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if(DBconnected){
                    tree.getStore().removeAll();
                    tree.collapseAll();
                    DBconnected = false;
                    selectDataTab = false;
                    selectAttributes.disable();
                    database_type.setValue("Database type:");
                    database_name.setValue("Database name:");
                    table_name.setValue("Table name:");
                    info_name.setValue("Connect to the server");
                    database_type.setVisible(false);
                    database_name.setVisible(false);
                    table_name.setVisible(false);
                    MessageBox.info("Disconnected", "Disconnected from the DB server.", null);
                } else
                    MessageBox.alert("Not connected", "Not connected the DB server.", null);

              if(Wekaconnected){
                    Wekaconnected = false;
                    MessageBox.info("Disconnected", "Disconnected from the Weka server.", null);
              }
              else
                MessageBox.alert("Cannot refresh.", "Not connected the Weka server.", null);
            }
        });
        
     toolBar.add(disconnectButton);

     toolBar.add(new SeparatorToolItem());
     Button clearButton = new Button("Clear Results");
     toolBar.add(clearButton);

     toolBar.add(new SeparatorToolItem());
     Button exportGraphsButton = new Button("Export Graphs");
     toolBar.add(exportGraphsButton);

     toolBar.add(new SeparatorToolItem());
     /////////////////////////////////////////////////////////////////////////
     // Panel zakladek
     /////////////////////////////////////////////////////////////////////////
    tabPanel = new TabPanel();
    tabPanel.setPlain(true);
    tabPanel.setBorders(false);
    tabPanel.setHeight(579);

    TabItem connect = new TabItem("Select Data");
    ContentPanel horizontalPane = new ContentPanel();
    horizontalPane.setHeaderVisible(false);
    horizontalPane.setFrame(true);
    horizontalPane.setWidth("100%");
    horizontalPane.setHeight(479);
    horizontalPane.setLayout(new RowLayout(Orientation.HORIZONTAL));
             //
             // Drzewo danych
             //
    tree = new TreePanel<ModelData>(new TreeStore<ModelData>());
    tree.setWidth(400);
    tree.setDisplayProperty("name");
    tree.getStyle().setLeafIcon(Resources.ICONS.album());
    //tree.getStore().add(getTestDatabases().getChildren(), true);

    tree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    tree.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<ModelData>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<ModelData> se) {

                for(ModelData iteam : tree.getStore().getModels())
                {
                    if(se.getSelectedItem().equals(iteam)){
                        database_type.setValue(se.getSelectedItem().get("genre").toString());
                        database_name.setValue(se.getSelectedItem().get("author").toString());
                        table_name.setValue(se.getSelectedItem().toString() ) ;
                        selectDataTab = true;
                        selectAttributes.enable();
                    }
                }
            }
    });

    FormPanel verticalPane1 = new FormPanel();
    verticalPane1.setHeaderVisible(false);
    verticalPane1.setWidth("100%");
    verticalPane1.setHeight(470);
    verticalPane1.setLayout(new RowLayout(Orientation.VERTICAL));

    FormPanel verticalPane2 = new FormPanel();
    verticalPane2.setHeaderVisible(false);
    verticalPane2.setWidth("100%");
    verticalPane2.setHeight(470);
    verticalPane2.setLayout(new RowLayout(Orientation.VERTICAL));

    FormPanel verticalPane3 = new FormPanel();
    verticalPane3.setHeaderVisible(false);
    verticalPane3.setWidth("100%");
    verticalPane3.setHeight(470);
    verticalPane3.setLayout(new RowLayout(Orientation.VERTICAL));

    verticalPane1.add(info_name , new RowData(1, -1, new Margins(4)));
    verticalPane2.add(tree , new RowData(1, -1, new Margins(4)));
    verticalPane3.add(database_type , new RowData(1, -1, new Margins(4)));
    verticalPane3.add(database_name , new RowData(1, -1, new Margins(4)));
    verticalPane3.add(table_name , new RowData(1, -1, new Margins(4)));
    
    horizontalPane.add(verticalPane1 , new RowData(0.13, -1, new Margins(4,4,4,14)));
    horizontalPane.add(verticalPane2 , new RowData(0.3, -1, new Margins(4,4,4,14)));
    horizontalPane.add(verticalPane3 , new RowData(0.13   , -1, new Margins(4,4,4,4)));
    
    connect.add(horizontalPane); // Add panel to TabIteam Select Data

    //////////////////////////////////////////////////////////////////////////
    //  Panel Select Attributes
    //////////////////////////////////////////////////////////////////////////
    selectAttributes = new TabItem("Select Attributes");
    // iconTab.setIcon(Resources.ICONS.table());
    selectAttributes.addStyleName("pad-text");
    //selectAttributes.disable();
    ContentPanel horizontalTab2Pane = new ContentPanel();
    horizontalTab2Pane.setHeaderVisible(false);
    horizontalTab2Pane.setFrame(true);
    horizontalTab2Pane.setWidth("100%");
    horizontalTab2Pane.setHeight(479);
    horizontalTab2Pane.setLayout(new RowLayout(Orientation.HORIZONTAL));

    selectAttributes.add(horizontalTab2Pane);
     
     //////////////////////////////////////////////////////////////
     //////////////////////////////////////////////////////////////
    tabPanel.add(connect);
    tabPanel.add(selectAttributes);

    panel.setTopComponent(bar);
    panel.add(toolBar);
    panel.add(tabPanel);

     //panel.setSize(1200, 300);

    add(panel, new FlowData(10));

    
   }

  /*  private TabPanel createTabPanel() {
     
     //FormData formData = new FormData("-500");
 */

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
     
  /*   FormPanel p1 = new FormPanel();
     p1.setHeaderVisible(false);
     p1.setWidth("100%");
     p1.setHeight(470);
     p1.setLayout(new RowLayout(Orientation.VERTICAL));

    */
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

     TextField<String> table_name = new TextField<String>();
     table_name.setEnabled(false);
     table_name.setValue("Table name:");
     table_name.setReadOnly(true);  */


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
 //   p1.add(tree , new RowData(0.2, 1, new Margins(4)));
     /*
     p1.add(database_type , new RowData(0.09, 1, new Margins(4)));
     p1.add(combo , new RowData(0.15, 1, new Margins(4)));
     p1.add(buttonConnect , new RowData(0.1, 1, new Margins(4)));
     p1.add(db_name , new RowData(0.25, 1, new Margins(4,4,4,44)));

     p2.add(database_name , new RowData(0.09, 1, new Margins(4)));
     p2.add(combo2 , new RowData(0.15, 1, new Margins(4)));

     p3.add(table_name , new RowData(0.09, 1, new Margins(4)));
     p3.add(combo3 , new RowData(0.15, 1, new Margins(4)));
*/
   //  simple.add(p1 , new RowData(1, -1, new Margins(4,4,4,14)));
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

  /*   connect.add(simple);  // tab panel adds Frame
     connect.addStyleName("pad-text");
     //connect.addText("Just a plain old tab");    
     panel.add(connect);
/* ******************* SELECT ATTRIBUTES ********************************* */
  /*   TabItem selectAttributes = new TabItem("Select Attributes");
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
   //  return panel;
   // }

    public static GWTServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.

        return GWT.create(GWTService.class);
    }
    public static WekaServiceAsync getWekaService() {
       return GWT.create(WekaService.class);
    }
    
public Folder getTestDatabases() {
    Folder[] folders = new Folder[] {
        new Folder("PostgreSQL",  new DBTable[] {
                                    new DBTable("Six String Quartets"),
                                    new DBTable("Three String Quartets"),
                                    new DBTable("Grosse Fugue for String Quartets")
                                }
                  ),
        new Folder("SQLite",  new DBTable[] {
                                    new DBTable("Six String Quartets"),
                                    new DBTable("Three String Quartets"),
                                    new DBTable("Grosse Fugue for String Quartets")
                                }
                  ),
        new Folder("Plain Text")
        };
    folders[0].setChildren(
                                 new Folder ("Database1", new DBTable[] {
                                    new DBTable("aaats"),
                                    new DBTable("bbbring Quartets"),
                                    new DBTable("cccugue for String Quartets")
                                    }
                                )
                          );
    folders[2].setChildren(
                                new DBTable[] {
                                    new DBTable("arrraats"),
                                    new DBTable("bbbring Quartets"),
                                    new DBTable("cccugue for String Quartets")
                                }
                          );


    Folder root = new Folder("root");
    for (int i = 0; i < folders.length; i++) {
      root.add((Folder) folders[i]);
    }
    return root;
    }

}


/*


 
 */