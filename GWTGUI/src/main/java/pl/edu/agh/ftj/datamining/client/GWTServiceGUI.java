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
import com.extjs.gxt.ui.client.event.BaseEvent;
 import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
 import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
 import com.extjs.gxt.ui.client.event.SelectionChangedListener;
 import com.extjs.gxt.ui.client.event.SelectionListener;
 import com.extjs.gxt.ui.client.store.TreeStore;
 import com.extjs.gxt.ui.client.util.Margins;
 import com.extjs.gxt.ui.client.widget.LayoutContainer;
 import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Slider;
 import com.extjs.gxt.ui.client.widget.TabItem;
 import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.custom.Portal;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
 import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SliderField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;
import org.adapters.highcharts.codegen.sections.options.OptionPath;
import org.adapters.highcharts.codegen.types.SeriesType;
import org.adapters.highcharts.codegen.utils.ClientConsole;
import org.adapters.highcharts.gxt.widgets.HighChart;
import pl.edu.agh.ftj.datamining.client.shared.CommunicationType;
import pl.edu.agh.ftj.datamining.client.shared.WekaAnswerDTO;


/**
 * Klasa odpowiedzialna za wyswietlanie GUI.
 * @author Rafal Jablonowski
 */
public class GWTServiceGUI extends LayoutContainer {

	private final Portal portal = new Portal(2);
	
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
  * Zakladka wynikow
  */
 TabItem showResults;
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
  * Wybranie tabeli danych do przetwarzania
  */
 boolean selectDataTab = false;
 /**
  * Wybranie atrybutow danych do przetwarzania
  */
  boolean selectAttributesTab = false;
 /**
  * Opcje wybranego algorytmu
  */
  String algorithmOptions = new String();
  /**
  * Wybrana tablica z bazy danych
  */
  DBTable selectedTabel;
  /**
  * Odpowiedz WekaAnswer
  */
 WekaAnswerDTO weka = null;

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
     menu.add(new MenuItem("Algorithm Discription"));
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
                    getDbService().getDataSources(new AsyncCallback<List<DataSource>>() {

                    public void onFailure(Throwable caught) {
                        MessageBox.alert("Failure", "Connecting to server failed.", null);
                        DBconnected = false;
                        tree.getStore().removeAll();
                        tree.collapseAll();
                        DBconnected = false;
                        selectDataTab = false;
                        selectAttributesTab = false;
                        //selectAttributes.disable();
                        database_type.setValue("Database type:");
                        database_name.setValue("Database name:");
                        table_name.setValue("Table name:");
                        info_name.setValue("Connect to the server");
                        database_type.setVisible(false);
                        database_name.setVisible(false);
                        table_name.setVisible(false);
                        MessageBox.alert("Failure", "Connecting to DB server failed.", null);
                    }

                    public void onSuccess(List<DataSource> result) {
                        MessageBox.info("Connected", "Connected to the DB server.", null);
                           // narazie po stronie klienta
                        Folder[] folders = new Folder[] {
                            new Folder("PostgreSQL"),
                            new Folder("SQLite"),
                            new Folder("Plain Text")
                            }; 
                        int i = 0;

                        for (DataSource ds : result) {

                            DBTable[] dbTabel = new DBTable[ds.getTables().size()];
                            for (int j = 0; j < ds.getTables().size(); j++) {
                                dbTabel[j] = new DBTable(ds.getTables().get(j),ds.getDisplayedName(),ds.getDatabase(),ds.getId(),ds.getLocation());
                            }

                            folders[i].setChildren( new Folder (ds.getDatabase(), dbTabel));

                            i++;  
                        }
                        Folder root = new Folder("root");
                        for (int j = 0; j < folders.length; j++) {
                          root.add((Folder) folders[j]);
                        }
                        DBconnected = true;
                        selectAttributesTab = false;
                        info_name.setValue("    Data selection:");                        
                        database_type.setVisible(true);
                        database_name.setVisible(true);
                        table_name.setVisible(true);
                        tree.getStore().removeAll();
                        tree.collapseAll();
                        tree.getStore().add(root.getChildren(), true);
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
                   getDbService().getDataSources(new AsyncCallback<List<DataSource>>() {
                        public void onFailure(Throwable caught) {
                            tree.getStore().removeAll();
                            tree.collapseAll();
                            DBconnected = false;
                            selectDataTab = false;
                            selectAttributesTab = false;
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

                        public void onSuccess(List<DataSource> result) {
                        Folder[] folders = new Folder[] {
                            new Folder("PostgreSQL"),
                            new Folder("SQLite"),
                            new Folder("Plain Text")
                            };
                        int i = 0;

                        for (DataSource ds : result) {

                            DBTable[] dbTabel = new DBTable[ds.getTables().size()];
                            for (int j = 0; j < ds.getTables().size(); j++) {
                                dbTabel[j] = new DBTable(ds.getTables().get(j),ds.getDisplayedName(),ds.getDatabase(),ds.getId(),ds.getLocation());
                            }

                            folders[i].setChildren( new Folder (ds.getDatabase(), dbTabel));

                            i++;
                        }
                        Folder root = new Folder("root");
                        for (int j = 0; j < folders.length; j++) {
                          root.add((Folder) folders[j]);
                        }
                        DBconnected = true;
                        selectAttributesTab = false;
                        selectAttributes.disable();
                        info_name.setValue("    Data selection:");
                        database_type.setVisible(true);
                        database_name.setVisible(true);
                        table_name.setVisible(true);
                        tree.getStore().removeAll();
                        tree.collapseAll();
                        tree.getStore().add(root.getChildren(), true);
                        database_type.setValue("Database type:");
                        database_name.setValue("Database name:");
                        table_name.setValue("Table name:");
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
                    selectAttributesTab = false;
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
    tabPanel.setHeight(679);
    tabPanel.setWidth("99%");
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
                        database_type.setValue(se.getSelectedItem().get("displayedName").toString());
                        database_name.setValue(se.getSelectedItem().get("database").toString());
                        table_name.setValue(se.getSelectedItem().get("name").toString() ) ;
                        selectDataTab = true;
                        selectAttributes.enable();
                        selectedTabel = new DBTable(se.getSelectedItem().get("name").toString(),
                                se.getSelectedItem().get("displayedName").toString(),
                                se.getSelectedItem().get("database").toString(),
                                se.getSelectedItem().get("id").toString(),
                                se.getSelectedItem().get("location").toString());
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
    ContentPanel verticalTab2Pane = new ContentPanel();
    verticalTab2Pane.setHeaderVisible(false);
    verticalTab2Pane.setFrame(true);
    verticalTab2Pane.setWidth("100%");
    verticalTab2Pane.setHeight(479);
    verticalTab2Pane.setLayout(new RowLayout(Orientation.VERTICAL));

    FormPanel horizontalTab2Pane1 = new FormPanel();
    horizontalTab2Pane1.setHeaderVisible(false);
    horizontalTab2Pane1.setWidth("100%");
    horizontalTab2Pane1.setLayout(new RowLayout(Orientation.HORIZONTAL));

    final SimpleComboBox combo = new SimpleComboBox<String>();
    combo.setEmptyText("SimpleKMeans ");
    
    //combo.setLabelSeparator("Select algorithm type:");
    combo.setFieldLabel("Algorithm type");

    final LabelField dataTypeLabel = new LabelField();
    final LabelField dataNameLabel = new LabelField();
    final LabelField tableNameLabel = new LabelField();
    final LabelField algOptionsLabel = new LabelField();

    selectAttributes.addListener(Events.Select,new Listener<ComponentEvent>() {

            public void handleEvent(ComponentEvent be) {
                if(!selectAttributesTab)
                    getWekaService().getAlgorithms(new AsyncCallback<List<String>>() {
                    public void onFailure(Throwable caught) {
                        MessageBox.alert("Error", "Cannot get algotrithm list.", null);
                    }
                    public void onSuccess(List<String> result) {
                        selectAttributesTab = true;
                        combo.removeAll();
                        for(String s : result)
                            combo.add(s);

                    }
                    });
                    
                    dataTypeLabel.setValue(database_type.getValue());
                    dataNameLabel.setValue(database_name.getValue());
                    tableNameLabel.setValue(table_name.getValue());
            }
    });
        
    combo.setTypeAhead(true);
    combo.setTriggerAction(TriggerAction.ALL);
    combo.setEditable(false);

    //
    //  Attributes pane
    //
    FormPanel horizontalTab2Pane2 = new FormPanel();
    horizontalTab2Pane2.setHeaderVisible(false);
    horizontalTab2Pane2.setWidth("100%");
    horizontalTab2Pane2.setLayout(new RowLayout(Orientation.HORIZONTAL));

    final FormPanel attributePane = new FormPanel();
    attributePane.setHeaderVisible(false);
    attributePane.setWidth("100%");
    attributePane.setLayout(new RowLayout(Orientation.VERTICAL));
    attributePane.setBorders(true);

    //
    //  Attribute SimpleKMeans
    //

    final FormPanel a1 = new FormPanel();
    a1.setHeaderVisible(false);
    a1.setWidth("100%");
    a1.setLayout(new RowLayout(Orientation.HORIZONTAL));


    final LabelField numCluster = new LabelField();
    numCluster.setValue("Liczba klastrow: 1");

    final Slider slider = new Slider();
    slider.setMinValue(1);
    slider.setMaxValue(12);
    slider.setValue(1);
    slider.setMessage("{0} klaster");
    slider.setIncrement(1);
    slider.addListener(Events.Change, new Listener<ComponentEvent>() {

            public void handleEvent(ComponentEvent be) {
                if(slider.getValue() == 1)
                    slider.setMessage("{0} klaster");
                else if(slider.getValue() > 4)
                    slider.setMessage("{0} klastrow");
                else
                    slider.setMessage("{0} klastry");
                numCluster.setValue("Liczba klastrow: " + slider.getValue() );
            }
    });        
    
    final SliderField sf = new SliderField(slider);
    sf.setFieldLabel("Size");
    
    a1.add(numCluster, new RowData(0.2, 1, new Margins(4,4,4,4)));
    a1.add(sf, new RowData(0.5, 1, new Margins(4,4,4,4)));

    final CheckBox variationCheckBox = new CheckBox();
    variationCheckBox.setBoxLabel("     Wyswietl odchylenia standardowe dla srodkow klastrow");
    variationCheckBox.setValue(false);

    final FormPanel a2 = new FormPanel();
    a2.setHeaderVisible(false);
    a2.setWidth("100%");
    a2.setLayout(new RowLayout(Orientation.HORIZONTAL));

    final LabelField distanceFunction = new LabelField();
    distanceFunction.setValue("Distance Function:");

    Radio radio = new Radio();
    radio.setBoxLabel("Euclidean Distance");
    radio.setValue(true);

    Radio radio2 = new Radio();
    radio2.setBoxLabel("Manhattan Distance");

    RadioGroup radioGroup = new RadioGroup();
    radioGroup.setFieldLabel("Distance Function: ");
    radioGroup.add(radio);
    radioGroup.add(radio2);

    
    a2.add(distanceFunction, new RowData(0.2, 1, new Margins(2)));
    a2.add(radioGroup, new RowData(0.5, 1, new Margins(2)));

    final FormPanel a3 = new FormPanel();
    a3.setHeaderVisible(false);
    a3.setWidth("100%");
    a3.setLayout(new RowLayout(Orientation.HORIZONTAL));

    final LabelField maxIteration = new LabelField();
    maxIteration.setValue("Liczba iteracji: 20");

    final Slider slider2 = new Slider();
    slider2.setMinValue(1);
    slider2.setMaxValue(100);
    slider2.setValue(20);
    slider2.setMessage("{0} iteracji");
    slider2.setIncrement(1);
    slider2.addListener(Events.Change, new Listener<ComponentEvent>() {

            public void handleEvent(ComponentEvent be) {
                if(slider2.getValue() == 1)
                    slider2.setMessage("{0} iteracja");
                else if(slider2.getValue() > 4)
                    slider2.setMessage("{0} iteracje");
                else
                    slider2.setMessage("{0} iteracji");
                maxIteration.setValue("Liczba iteracji: " + slider2.getValue() );
            }
    });

    final SliderField sf2 = new SliderField(slider2);

    a3.add(maxIteration, new RowData(0.2, 1, new Margins(4,4,4,4)));
    a3.add(sf2, new RowData(0.5, 1, new Margins(4,4,4,4)));

    final CheckBox instanceOrderCheckBox = new CheckBox();
    instanceOrderCheckBox.setBoxLabel("     Zachowaj kolejnosc instancji");
    instanceOrderCheckBox.setValue(false);

    final CheckBox fastCheckBox = new CheckBox();
    fastCheckBox.setBoxLabel("     Szybsze obliczanie dystansow poprzez okrojenie wartosci."
            + "\n Wylacza obliczanie kwadratowych bledow");
    fastCheckBox.setValue(false);

    final FormPanel a4 = new FormPanel();
    a4.setHeaderVisible(false);
    a4.setWidth("100%");
    a4.setLayout(new RowLayout(Orientation.HORIZONTAL));

    final LabelField randomSeed = new LabelField();
    randomSeed.setValue("Losowa liczba ziarne: 20");

    final Slider slider3 = new Slider();
    slider3.setMinValue(2);
    slider3.setMaxValue(50);
    slider3.setValue(10);
    slider3.setMessage("{0} ziaren");
    slider3.setIncrement(1);
    slider3.addListener(Events.Change, new Listener<ComponentEvent>() {

            public void handleEvent(ComponentEvent be) {
                if(slider3.getValue() == 1)
                    slider3.setMessage("{0} ziarno");
                else if(slider3.getValue() > 4)
                    slider3.setMessage("{0} ziaren");
                else
                    slider3.setMessage("{0} ziarna");
                randomSeed.setValue("Losowa liczba ziarne: " + slider3.getValue() );
            }
    });

    final SliderField sf3 = new SliderField(slider3);

    a4.add(randomSeed, new RowData(0.3, 1, new Margins(4,4,4,4)));
    a4.add(sf3, new RowData(0.5, 1, new Margins(4,4,4,4)));
    
    attributePane.add(a1, new RowData(1, 0.15, new Margins(2)));
    attributePane.add(variationCheckBox, new RowData(1, 0.15, new Margins(2,2,2,14)));
    attributePane.add(a2, new RowData(1, 0.15, new Margins(2,2,2,4)));
    attributePane.add(a3, new RowData(1, 0.15, new Margins(2,2,2,4)));
    attributePane.add(instanceOrderCheckBox, new RowData(1, 0.15, new Margins(2,2,2,14)));
    attributePane.add(fastCheckBox, new RowData(1, 0.15, new Margins(2,2,2,14)));
    attributePane.add(a4, new RowData(1, 0.15, new Margins(2,2,2,4)));
    //
    //  Attribute EM
    //
    final CheckBox EM_printOldFormat = new CheckBox();
    EM_printOldFormat.setBoxLabel("Wyswietla model w starym formacie (przydatne jesli istnieje wiele klastrow)");
    EM_printOldFormat.setValue(false);
    attributePane.add(EM_printOldFormat, new RowData(1, 0.15, new Margins(2,2,2,4)));

    //
    //  HierarchicalClusterer
    //

   final SimpleComboBox sposobLaczeniaComboBox = new SimpleComboBox<String>();
    sposobLaczeniaComboBox.setEmptyText("Sposob laczenia.. ");

    final FormPanel a5 = new FormPanel();
    a5.setHeaderVisible(false);
    a5.setWidth("100%");
    a5.setLayout(new RowLayout(Orientation.HORIZONTAL));

    sposobLaczeniaComboBox.setFieldLabel("Sposob laczenia");
    sposobLaczeniaComboBox.setTypeAhead(true);
    sposobLaczeniaComboBox.setTriggerAction(TriggerAction.ALL);
    sposobLaczeniaComboBox.setEditable(false);

    sposobLaczeniaComboBox.add("SINGLE");
    sposobLaczeniaComboBox.add("COMPLETE");
    sposobLaczeniaComboBox.add("AVERAGE");
    sposobLaczeniaComboBox.add("MEAN");
    sposobLaczeniaComboBox.add("CENTROID");
    sposobLaczeniaComboBox.add("WARD");
    sposobLaczeniaComboBox.add("ADJCOMLPETE");
    sposobLaczeniaComboBox.add("NEIGHBOR_JOINING");

    sposobLaczeniaComboBox.select(1);

    final LabelField linkType = new LabelField();
    linkType.setValue("Sposob laczenia: ");

    a5.add(linkType, new RowData(0.2, 0.15, new Margins(4,4,4,4)));
    a5.add(sposobLaczeniaComboBox, new RowData(0.25, 0.3, new Margins(4,4,4,4)));

    attributePane.add(a5, new RowData(1,0.16, new Margins(2,2,2,4)));
    
    final CheckBox HC_formNewick = new CheckBox();
    HC_formNewick.setBoxLabel("Wyswietlanie hierarchi w  formacie Newick");
    HC_formNewick.setValue(false);
    attributePane.add(HC_formNewick, new RowData(1, 0.15, new Margins(2,2,2,4)));

    final CheckBox HC_Debug = new CheckBox();
    HC_Debug.setBoxLabel("Debug mode. Wyswietla dodatkowe informacje na wyjsciu");
    HC_Debug.setValue(false);
    attributePane.add(HC_Debug, new RowData(1, 0.15, new Margins(2,2,2,4)));

    final CheckBox HC_Distance = new CheckBox();
    HC_Distance.setBoxLabel("Interpretacja odleglosci jako dugosci galezi, lub jako wysokosci wezla (niezaznaczone)");
    HC_Distance.setValue(false);
    attributePane.add(HC_Distance, new RowData(1, 0.15, new Margins(2,2,2,4)));

    //
    //  Cobweb
    //

 final FormPanel a6 = new FormPanel();
    a6.setHeaderVisible(false);
    a6.setWidth("100%");
    a6.setLayout(new RowLayout(Orientation.HORIZONTAL));

    final LabelField minDeviation = new LabelField();
    minDeviation.setValue("Min odchylenie standardowe: ");

    final Slider slider4 = new Slider();
    slider4.setMinValue(0);
    slider4.setMaxValue(5);
    slider4.setValue(0);
    slider4.setMessage("{0} Ostrosc");
    slider4.setIncrement(1);
    slider4.addListener(Events.Change, new Listener<ComponentEvent>() {

            public void handleEvent(ComponentEvent be) {
                minDeviation.setValue("Min odchylenie standardowe: " + slider4.getValue() );
            }
    });

    final SliderField sf4 = new SliderField(slider4);

    a6.add(minDeviation, new RowData(0.35, 1, new Margins(4,4,4,4)));
    a6.add(sf4, new RowData(0.5, 1, new Margins(4,4,4,4)));

   attributePane.add(a6, new RowData(1,0.16, new Margins(2,2,2,4)));


 final FormPanel a7 = new FormPanel();
    a7.setHeaderVisible(false);
    a7.setWidth("100%");
    a7.setLayout(new RowLayout(Orientation.HORIZONTAL));

    final LabelField cutOff = new LabelField();
    cutOff.setValue("Wartosc graniczna: ");

    final Slider slider5 = new Slider();
    slider5.setMinValue(0);
    slider5.setMaxValue(10);
    slider5.setValue(2);
    slider5.setIncrement(1);
    slider5.addListener(Events.Change, new Listener<ComponentEvent>() {

            public void handleEvent(ComponentEvent be) {
                cutOff.setValue("Wartosc graniczna: " + (float)slider5.getValue()/1000f );
            }
    });

    final SliderField sf5 = new SliderField(slider5);

    a7.add(cutOff, new RowData(0.35, 1, new Margins(4,4,4,4)));
    a7.add(sf5, new RowData(0.5, 1, new Margins(4,4,4,4)));

   attributePane.add(a7, new RowData(1,0.16, new Margins(2,2,2,4)));
    //
    //  FarthestFirst
    //

   /////////////////////
    combo.addListener(Events.Select, new Listener<BaseEvent>() {

            public void handleEvent(BaseEvent be) {
                switch(combo.getSelectedIndex()) {
                    case 0:
                        for(int i =0; i < attributePane.getItemCount(); i++)
                            if(i<7)
                                attributePane.getWidget(i).setVisible(true);
                            else
                                attributePane.getWidget(i).setVisible(false);

                        break;
                    case 1:
                        for(int i =0; i < attributePane.getItemCount(); i++)
                            attributePane.getWidget(i).setVisible(false);

                        attributePane.getWidget(7).setVisible(true);
                        attributePane.getWidget(0).setVisible(true);
                        attributePane.getWidget(3).setVisible(true);
                        attributePane.getWidget(6).setVisible(true);
                        break;
                    case 2:
                        for(int i =1; i < attributePane.getItemCount(); i++)
                                attributePane.getWidget(i).setVisible(false);

                        attributePane.getWidget(0).setVisible(true);
                        attributePane.getWidget(2).setVisible(true);
                        attributePane.getWidget(8).setVisible(true);
                        attributePane.getWidget(9).setVisible(true);
                        attributePane.getWidget(10).setVisible(true);
                        attributePane.getWidget(11).setVisible(true);
                        
                        break;
                    case 3:
                        for(int i =0; i < attributePane.getItemCount(); i++)
                                attributePane.getWidget(i).setVisible(false);

                        attributePane.getWidget(6).setVisible(true);
                        attributePane.getWidget(12).setVisible(true);
                        attributePane.getWidget(13).setVisible(true);

                        break;
                    case 4:
                        for(int i =0; i < attributePane.getItemCount(); i++)
                                attributePane.getWidget(i).setVisible(false);

                        attributePane.getWidget(0).setVisible(true);
                        attributePane.getWidget(6).setVisible(true);
                        break;
                    default:
                        for(int i =0; i < attributePane.getItemCount(); i++)
                            if(i<7)
                                attributePane.getWidget(i).setVisible(true);
                            else
                                attributePane.getWidget(i).setVisible(false);
                        break;
                }
                
            }
    });
    //
    //  Panel wyniku
    //
    FormPanel resultTab2Pane = new FormPanel();
    resultTab2Pane.setHeaderVisible(false);
    resultTab2Pane.setWidth("100%");
    resultTab2Pane.setLayout(new RowLayout(Orientation.VERTICAL));

    resultTab2Pane.add(dataTypeLabel,new RowData(0.3, 0.15, new Margins(4,4,4,104)));
    resultTab2Pane.add(dataNameLabel,new RowData(0.3, 0.15, new Margins(4,4,4,104)));
    resultTab2Pane.add(tableNameLabel,new RowData(0.3, 0.15, new Margins(4,4,4,104)));
    resultTab2Pane.add(algOptionsLabel,new RowData(1, 0.15, new Margins(4,4,4,104)));
    //
    // client close do weki
    // testy jednostkowe
    //
    //
    
    Button buttonRun = new Button("RUN");
    buttonRun.addListener(Events.OnClick, new Listener<ButtonEvent>() {
       public void handleEvent(ButtonEvent be) {

                int algorithmType = 1;
                switch(combo.getSelectedIndex()) {
                    case 0:
                        algorithmType = 1;
                        
                        algorithmOptions = "N " + slider.getValue() + ";I " + slider2.getValue() + ";S " + slider3.getValue();
                        if(variationCheckBox.getValue())
                           algorithmOptions += ";V";
                        if(instanceOrderCheckBox.getValue())
                           algorithmOptions += ";O";
                        if(fastCheckBox.getValue())
                           algorithmOptions += ";fast";
                        
                        break;
                    case 1:
                        algorithmType = 2;
                        
                        algorithmOptions = "N " + slider.getValue() + ";I " + slider2.getValue() + ";S " + slider3.getValue();
                        if(EM_printOldFormat.getValue())
                           algorithmOptions += ";O";
                      
                        break;
                    case 2:
                        algorithmType = 3;

                        algorithmOptions = "N " + slider.getValue() ;
                        if(HC_formNewick.getValue())
                           algorithmOptions += ";P";
                        if(HC_Debug.getValue())
                           algorithmOptions += ";D";
                        if(HC_Distance.getValue())
                           algorithmOptions += ";B";

                        if(sposobLaczeniaComboBox.getSelectedIndex() == -1)
                            algorithmOptions += ";L SINGLE";
                        else
                            algorithmOptions += ";L " + sposobLaczeniaComboBox.getRawValue().toString();
                        break;
                    case 3:
                        algorithmType = 4;
                        algorithmOptions = "A " + slider4.getValue() + ";C " + slider5.getValue() + ";S " + slider3.getValue();
                        break;
                    case 4:
                        algorithmType = 5;
                        algorithmOptions = "N " + slider.getValue() + ";S " + slider3.getValue();
                        break;
                    default:
                        algorithmType = 1;
                        algorithmOptions = "N " + slider.getValue() + ";I " + slider2.getValue() + ";S " + slider3.getValue();
                        if(variationCheckBox.getValue())
                           algorithmOptions += ";V";
                        if(instanceOrderCheckBox.getValue())
                           algorithmOptions += ";O";
                        if(fastCheckBox.getValue())
                           algorithmOptions += ";fast";
                        break;
                }

        
           algOptionsLabel.setValue(algorithmOptions + selectedTabel.getLocation() + " " + selectedTabel.getId() + " " + selectedTabel.getName());
           algOptionsLabel.setValue(algorithmOptions);
           
           getWekaService().runAlgorithm(algorithmType, selectedTabel.getId(), selectedTabel.getName(), algorithmOptions, new AsyncCallback<WekaAnswerDTO>() {

                    public void onFailure(Throwable caught) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void onSuccess(WekaAnswerDTO result) {
                        weka = result;
                        portal.removeAll();
                        portal.repaint();
                        getResultService().getAttributes(new AsyncCallback<List<String>>() {
							
							public void onSuccess(List<String> arg0) {
								int j = 0;
								for (int chartNum = 0; chartNum < arg0.size(); chartNum +=2) {
									if(chartNum + 1 < arg0.size()) {
										addChart(200, j, portal, arg0.get(chartNum), arg0.get(chartNum+1));
										j++;
									}
		                        }
                                                                portal.repaint();
		                        portal.show();
							}
							
							public void onFailure(Throwable arg0) {
								throw new UnsupportedOperationException("Not supported yet.");
							}
						});
                    }
                });     
       }
     });


    resultTab2Pane.add(buttonRun,new RowData(0.3, 0.35, new Margins(64,4,4,204)));

    horizontalTab2Pane1.add(combo, new RowData(0.15, 1, new Margins(4,4,4,14)));
    horizontalTab2Pane2.add(attributePane,new RowData(0.5, 1, new Margins(4,4,4,14)));
    horizontalTab2Pane2.add(resultTab2Pane,new RowData(0.5, 1, new Margins(4,4,4,14)));
    
    verticalTab2Pane.add(horizontalTab2Pane1, new RowData(1, 0.125, new Margins(4,4,4,14)));
    verticalTab2Pane.add(horizontalTab2Pane2, new RowData(1, 0.7, new Margins(4,4,4,14)));
    selectAttributes.add(verticalTab2Pane);

   //////////////////////////////////////////////////////////////////////////
    //  Panel Results
    //////////////////////////////////////////////////////////////////////////
    
    createResultPane();


     //////////////////////////////////////////////////////////////
     //////////////////////////////////////////////////////////////
    tabPanel.add(connect);
    tabPanel.add(selectAttributes);
    tabPanel.add(showResults);

    panel.setTopComponent(bar);
    panel.add(toolBar);
    panel.add(tabPanel);

     //panel.setSize(1200, 300);

    add(panel, new FlowData(10));

    
   }

    public static WekaServiceAsync getWekaService() {
       return GWT.create(WekaService.class);
    }
    
    public static DbServiceAsync getDbService() {
       return GWT.create(DbService.class);
    }

    public static ResultServiceAsync getResultService() {
    	return GWT.create(ResultService.class);
    }
    private void createResultPane() {
        showResults = new TabItem("Show Results");
        showResults.setId("resultTab");
        // iconTab.setIcon(Resources.ICONS.table());
        showResults.addStyleName("pad-text");
        ContentPanel verticalTab3Pane = new ContentPanel();
        verticalTab3Pane.setHeaderVisible(false);
        verticalTab3Pane.setFrame(true);
        verticalTab3Pane.setWidth("100%");
        verticalTab3Pane.setHeight(479);
        verticalTab3Pane.setLayout(new FitLayout());
        
        portal.setBorders(true);
        portal.setStyleAttribute("backgroundColor", "white");
        portal.setColumnWidth(0, .50);
        portal.setColumnWidth(1, .50);

        // insert the whole portal container
        verticalTab3Pane.add(portal);
        showResults.add(verticalTab3Pane);
    }

    private void addChart(final int delay, final int position,
        final Portal portal, final String attrX, final String attrY) {

        Timer timer = new Timer() {
            public void run() {
                Portlet portlet = new Portlet();
                portlet.setHeight(400);
                portlet.setHeading("Weka Chart");
                portlet.setLayout(new FitLayout());
                initHighChart(portlet, position, attrX, attrY);
            }
        };
        timer.schedule(delay);
    }

    private void initHighChart(final Portlet portlet, final int chartNum, String attrX, String attrY) {
        final HighChart hc = new HighChart(null, "scatter");
        try {
            hc.setOption(new OptionPath("/title/text"), "Cluster #" + chartNum);
            hc.setOption(new OptionPath("/credits/enabled"), false);
            hc.setOption(new OptionPath("/xAxis/allowDecimals"), true);
            hc.setOption(new OptionPath("/xAxis/title/text"), attrX);
            hc.setOption(new OptionPath("/yAxis/title/text"), attrY);
            //hc.setOption(new OptionPath("/yAxis/min"), 0);
            //hc.setOption(new OptionPath("/subtitle/text"), "the subtitle");
            hc.setOption(new OptionPath("/chart/zoomType"), "xy");
            hc.setOption(new OptionPath("/chart/renderTo"), "container");
            hc.setOption(new OptionPath("/chart/type"), "scatter");
            hc.setOption(new OptionPath("/plotOptions/scatter/marker/enabled"), true);
            hc.setOption(new OptionPath("/plotOptions/scatter/marker/radius"), 4);
            hc.setOption(new OptionPath("/plotOptions/scatter/marker/states/hover/enabled"), true);
            hc.setOption(new OptionPath("/plotOptions/scatter/marker/states/hover/lineColor"), "rgb(100,100,100)");
            hc.setOption(new OptionPath("/plotOptions/scatter/states/hover/marker/enabled"), false);
            
        } catch (Exception e) {
            ClientConsole.err("Building options", e);
        }
        
        getResultService().getResults(attrX, attrY, new AsyncCallback<List<List<Number[]>>>() {
            public void onSuccess(List<List<Number[]>> arg0) {
            	//Log.info("onSuccess...");
                if(arg0 != null) {
                	//Log.info("ARG0 is not null");
                    for(int i = 0 ; i < arg0.size(); i++) {
                    	//Log.info("ARG0 has size > 0");
                        SeriesType series = new SeriesType("cluster #" + i);
                        series.setType("scatter");
                        List<Number[]> numbers = arg0.get(i);
                       // Log.info("numbers=" + numbers);
                        if(numbers != null) {
                            for (int j = 0 ; j < numbers.size() ; j++) {
                                if(numbers.get(j) != null && numbers.get(j)[0] != null && numbers.get(j)[1] != null) {
                                	//Log.info("x= " + numbers.get(j)[0] + " y=" + numbers.get(j)[1]);
                                    series.addEntry(new SeriesType.SeriesDataEntry(
                                        numbers.get(j)[0],
                                        numbers.get(j)[1]));
                                }
                            }
                        }
                        hc.addSeries(series);
                    }
                }
                        // no offset in the resize
                hc.setHeightOffset(0);
                // reduces the refresh delay from 1000 to 100 it seems to work
                hc.setResizeDelay(100);

                hc.followWindowResize(false);
        
                portlet.add(hc);
                portal.add(portlet, chartNum % 2);
	    }
	
            public void onFailure(Throwable arg0) {

            }
        });
    }
}
