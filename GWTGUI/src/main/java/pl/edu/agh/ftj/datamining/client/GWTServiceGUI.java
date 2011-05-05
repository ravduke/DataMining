 package pl.edu.agh.ftj.datamining.client;


import com.extjs.gxt.ui.client.Style.Orientation;
 import com.extjs.gxt.ui.client.widget.layout.FlowData;
 import com.extjs.gxt.ui.client.widget.menu.MenuBar;
 import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
 import com.extjs.gxt.ui.client.widget.ContentPanel;
 import com.extjs.gxt.ui.client.widget.button.Button;
 import com.extjs.gxt.ui.client.widget.button.SplitButton;
 import com.extjs.gxt.ui.client.widget.button.ToggleButton;
 import com.extjs.gxt.ui.client.widget.menu.CheckMenuItem;
 import com.extjs.gxt.ui.client.widget.menu.DateMenu;
 import com.extjs.gxt.ui.client.widget.menu.Menu;
 import com.extjs.gxt.ui.client.widget.menu.MenuItem;
 import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
 import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
 import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
 import com.extjs.gxt.ui.client.Style.Scroll;
 import com.extjs.gxt.ui.client.data.BaseModel;
 import com.extjs.gxt.ui.client.event.ButtonEvent;
 import com.extjs.gxt.ui.client.event.ComponentEvent;
 import com.extjs.gxt.ui.client.event.Events;
 import com.extjs.gxt.ui.client.event.Listener;
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
 import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;



/**
 *
 * @author Rafal Jablonowski
 */
public class GWTServiceGUI extends LayoutContainer {

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

     MenuBar bar = createBar();
     ToolBar toolBar = createToolBar();
     TabPanel tabPanel = createTabPanel();

     ContentPanel panel = new ContentPanel();
     panel.setHeading("DataMining Application");
     panel.setWidth("99%");
     panel.setHeight(530);

     tabPanel.setHeight(529);

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

     MenuItem item1 = new MenuItem("New");
     menu.add(item1);

     MenuItem item2 = new MenuItem("Open File");
     menu.add(item2);  

     Menu sub = new Menu();
     sub.add(new MenuItem("readme.txt"));
     sub.add(new MenuItem("helloworld.txt"));
     item2.setSubMenu(sub);

     MenuItem item0 = new MenuItem("Exit");
     menu.add(item0);

     MenuBar bar = new MenuBar();
     bar.setBorders(true);
     bar.setStyleAttribute("borderTop", "none");
     bar.add(new MenuBarItem("File", menu));

     Menu sub2 = new Menu();
     sub2.add(new MenuItem("Cut"));
     sub2.add(new MenuItem("Copy"));

     MenuBarItem edit = new MenuBarItem("Options", sub2);
     bar.add(edit);

     sub = new Menu();
     sub.add(new MenuItem("Search"));
     sub.add(new MenuItem("File"));
     sub.add(new MenuItem("Java"));

     MenuBarItem item3 = new MenuBarItem("Help", sub);
     bar.add(item3);

     menu = new Menu();

     menu.add(new MenuItem("Information"));
     menu.add(new MenuItem("Contact"));
     CheckMenuItem menuItem = new CheckMenuItem("Check Box");
     menuItem.setChecked(true);
     menu.add(menuItem);

     menuItem = new CheckMenuItem("Check Box 2");
     menu.add(menuItem);


     menu.add(new SeparatorMenuItem());

     MenuItem radios = new MenuItem("Radio Options");
     menu.add(radios);

     Menu radioMenu = new Menu();
     CheckMenuItem r = new CheckMenuItem("Blue Theme");
     r.setGroup("radios");
     r.setChecked(true);
     radioMenu.add(r);
     r = new CheckMenuItem("Gray Theme");
     r.setGroup("radios");
     radioMenu.add(r);
     radios.setSubMenu(radioMenu);

     MenuItem date = new MenuItem("Choose a Date");
     //date.setIcon(Resources.ICONS.calendar());
     menu.add(date);

     date.setSubMenu(new DateMenu());

     MenuBarItem item4 = new MenuBarItem("About", menu);
     bar.add(item4);
     return bar;
  }
  
    /**
     * Tworzy pasek narzedzi interfejsu uzytkownika
     */
  private ToolBar  createToolBar() {

       ToolBar toolBar = new ToolBar();

     Button item1 = new Button("Button w/ Menu");
    // item1.setIcon(Resources.ICONS.menu_show());

   /*  List<String> list = new ArrayList<String>();
     //final ListStore<Stock> store = new ListStore<Stock>();

     store.add(list);

     final ComboBox<Stock> combo = new ComboBox<Stock>();
     combo.setFieldLabel("Company");
     combo.setDisplayField("name");
     combo.setName("name");
     combo.setValueField("symbol");
     combo.setForceSelection(true);
     combo.setStore(store);
     combo.setTriggerAction(TriggerAction.ALL);

     AdapterMenuItem adapter = new AdapterMenuItem(combo);
     adapter.setManageFocus(true);
     adapter.setCanActivate(true);*/

     Menu menu = new Menu();
    // menu.add(adapter);

     CheckMenuItem menuItem = new CheckMenuItem("Check Box");
     menuItem.setChecked(true);
     menu.add(menuItem);

     menuItem = new CheckMenuItem("Check Box 2");
     menu.add(menuItem);
     item1.setMenu(menu);

     menu.add(new SeparatorMenuItem());

     MenuItem radios = new MenuItem("Radio Options");
     menu.add(radios);

     Menu radioMenu = new Menu();
     CheckMenuItem r = new CheckMenuItem("Blue Theme");
     r.setGroup("radios");
     r.setChecked(true);
     radioMenu.add(r);
     r = new CheckMenuItem("Gray Theme");
     r.setGroup("radios");
     radioMenu.add(r);
     radios.setSubMenu(radioMenu);

     MenuItem date = new MenuItem("Choose a Date");
     //date.setIcon(Resources.ICONS.calendar());
     menu.add(date);

     date.setSubMenu(new DateMenu());

     toolBar.add(item1);

     toolBar.add(new SeparatorToolItem());

     SplitButton splitItem = new SplitButton("Split Button");
    // splitItem.setIcon(Resources.ICONS.list_items());

     menu = new Menu();
     menu.add(new MenuItem("<b>Bold</b>"));
     menu.add(new MenuItem("<i>Italic</i>"));
     menu.add(new MenuItem("<u>Underline</u>"));
     splitItem.setMenu(menu);

     toolBar.add(splitItem);

     toolBar.add(new SeparatorToolItem());

     ToggleButton toggle = new ToggleButton("Toggle");
     toggle.toggle(true);
     toolBar.add(toggle);

     toolBar.add(new SeparatorToolItem());

     Button scrollerButton = new Button("Scrolling Menu");

     Menu scrollMenu = new Menu();
     scrollMenu.setMaxHeight(200);
     for (int i = 0; i < 40; i++) {
       scrollMenu.add(new MenuItem("Item " + i));
     }

     scrollerButton.setMenu(scrollMenu);

     toolBar.add(scrollerButton);

     toolBar.add(new SeparatorToolItem());

    // toolBar.add(new FillToolItem());
    // ThemeSelector selector = new ThemeSelector();
    // toolBar.add(selector);

      return toolBar;
  }

    /**
     * Tworzy panel kart interfejsu uzytkownika
     */
    private TabPanel createTabPanel() {

     TabPanel panel = new TabPanel();
     panel.setPlain(true);
     panel.setBorders(false);
    
     TabItem connect = new TabItem("Connect");

     ContentPanel simple = new ContentPanel();
     simple.setHeaderVisible(false);
     simple.setFrame(true);
     simple.setWidth("100%");
     simple.setHeight(479);
     simple.setLayout(new RowLayout(Orientation.VERTICAL));
     //simple.setLayout(new BorderLayout());
     
     //FormData formData = new FormData("-500");
    // TextField<String> firstName = new TextField<String>();
    // firstName.setFieldLabel("Name");
     //simple.add(firstName);//, formData);

/*      COMBOBOX DATABASE TYPE        */
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

/*      COMBOBOX DATABASE NAME        */
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

   /*      COMBOBOX TABLE NAME        */

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
     p1.setHeight(50);
     p1.setLayout(new RowLayout(Orientation.HORIZONTAL));
     
     FormPanel p2 = new FormPanel();
     p2.setHeaderVisible(false);
     p2.setWidth("100%");
     p2.setHeight(50);
     p2.setLayout(new RowLayout(Orientation.HORIZONTAL));
     
     FormPanel p3 = new FormPanel();
     
     p3.setHeaderVisible(false);
     p3.setWidth("100%");
     p3.setHeight(50);
     p3.setLayout(new RowLayout(Orientation.HORIZONTAL));

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
     Table_name.setReadOnly(true);


     /*      BUTTON CONNECT TO DATABASE        */
     Button buttonConnect = new Button("Connect");
     buttonConnect.addListener(Events.OnClick, new Listener<ButtonEvent>() {
       public void handleEvent(ButtonEvent be) {
         Window.alert("Connected!");
         combo2.setEnabled(true);
         combo3.setEnabled(true);
         db_name.setValue("name @ localhost");
       }
     });
    
     p1.add(database_type , new RowData(0.09, 1, new Margins(4)));
     p1.add(combo , new RowData(0.15, 1, new Margins(4)));
     p1.add(buttonConnect , new RowData(0.1, 1, new Margins(4)));
     p1.add(db_name , new RowData(0.25, 1, new Margins(4,4,4,44)));

     p2.add(database_name , new RowData(0.09, 1, new Margins(4)));
     p2.add(combo2 , new RowData(0.15, 1, new Margins(4)));

     p3.add(Table_name , new RowData(0.09, 1, new Margins(4)));
     p3.add(combo3 , new RowData(0.15, 1, new Margins(4)));

     simple.add(p1 , new RowData(1, -1, new Margins(4,4,4,14)));
     simple.add(p2 , new RowData(1, -1, new Margins(4,4,4,14)));
     simple.add(p3 , new RowData(1, -1, new Margins(4,4,4,14)));

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
/* *********************************************************************** */
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

    class Stock extends BaseModel {

      public Stock() {
      }

      public Stock(String name) {
        set("name", name);
      }

    }
}

