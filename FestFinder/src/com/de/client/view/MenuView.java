package com.de.client.view;

import com.de.client.presenter.MenuPresenter;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MenuView extends Composite implements MenuPresenter.Display{
		
	private Label lblSuche;
	private TextBox bandSuche;
	private Button suchen;
	private PushButton festivals;
	private PushButton bands;
	private ListBox suche;

		
	public MenuView(){
		FlowPanel vPanel = new FlowPanel();
		initWidget(vPanel);
		vPanel.setStyleName("suche");
		
		lblSuche = new Label("Suchen nach:");
		lblSuche.setStyleName("buttons");		
		suche = new ListBox();
		suche.addItem("Bandname");
		suche.addItem("Festival");
		suche.addItem("Genre");
		suche.setStyleName("buttons");
		bandSuche = new TextBox();
		bandSuche.setText("Name eingeben");
		bandSuche.setStyleName("buttons");
		suchen = new Button("Suchen");
		suchen.setStyleName("buttons");
		festivals = new PushButton(new Image("images/Festivals.png"));
		festivals.setStyleName("buttons");
		festivals.getElement().getStyle().setCursor(Cursor.POINTER); 
		bands = new PushButton(new Image("images/Bands.png"));
		bands.setStyleName("buttons");
		bands.getElement().getStyle().setCursor(Cursor.POINTER); 

		vPanel.add(lblSuche);
		vPanel.add(bandSuche);
		vPanel.add(suche);
		vPanel.add(suchen);
		vPanel.add(festivals);
		vPanel.add(bands);

	}
	public String getDropDown(){
		return suche.getItemText(suche.getSelectedIndex());
	}
	
	public HasClickHandlers getSuchenButton() {
		  return suchen;
	}
	 
	public HasClickHandlers getFestivalsButton() {
		    return festivals;
	}
		  
	public HasClickHandlers getBandButton() {
		    return bands;
	}
	public String getName() {
		  return  bandSuche.getText();
	}

	
	public Widget asWidget(){
		return this;		
	}





		
}