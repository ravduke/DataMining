package pl.edu.agh.ftj.datamining.client;

import java.util.List;

import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Example class using the DbService service.
 *
 * @author Epi
 */
public class DbServiceUsageExample extends VerticalPanel {
    private Label lblServerReply = new Label();
    private Button btnGet = new Button("Get available data sources");
    
    public DbServiceUsageExample() {
		add(btnGet);
        add(lblServerReply);

        // Create an asynchronous callback to handle the result.
        final AsyncCallback<List<DataSource> > callback = new AsyncCallback<List<DataSource> >() {
            public void onSuccess(List<DataSource> result) {
				String text = "Available Data Sources:\n";
				for (DataSource ds : result) {
					text += ds.getDisplayedName() + "\n";
				}
                lblServerReply.setText(text);
            }

            public void onFailure(Throwable caught) {
                lblServerReply.setText("Communication failed: " + caught.getMessage());
            }
        };

        // Listen for the button clicks
        btnGet.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                // Make remote call. Control flow will continue immediately and later
                // 'callback' will be invoked when the RPC completes.
                getService().getDataSources(callback);
            }
        });
    }
    
    public static DbServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.

        return GWT.create(DbService.class);
    }
}
