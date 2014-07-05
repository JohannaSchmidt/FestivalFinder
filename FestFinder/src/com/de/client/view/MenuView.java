package com.de.client.view;

import com.de.client.presenter.MenuPresenter;
import com.de.shared.User;
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
	private PushButton home;
	private PushButton festivals;
	private PushButton bands;
	private ListBox suche;
	private Button addFestival;
	private Button addBand;

		
	public MenuView(User user){
		FlowPanel vPanel = new FlowPanel();
		FlowPanel suchPanel = new FlowPanel();
		initWidget(vPanel);
		vPanel.setStyleName("suche");
		suchPanel.setStyleName("suchpanel");
		
		lblSuche = new Label("Suchen nach:");	
		suche = new ListBox();
		suche.addItem("Bandname");
		suche.addItem("Festival");
		suche.addItem("Genre");
		suche.setStyleName("buttons");
		bandSuche = new TextBox();
		bandSuche.setText("Name eingeben");
		suchen = new Button("Suchen");
		suchen.setStyleName("clickbuttons");
		
		home = new PushButton(new Image("images/Home.png"));
		home.setStyleName("buttons");
		home.getElement().getStyle().setCursor(Cursor.POINTER);
		festivals = new PushButton(new Image("images/Festivals.png"));
		festivals.setStyleName("buttons");
		festivals.getElement().getStyle().setCursor(Cursor.POINTER); 
		bands = new PushButton(new Image("images/Bands.png"));
		bands.setStyleName("buttons");
		bands.getElement().getStyle().setCursor(Cursor.POINTER); 
		addFestival = new Button("Festival anlegen");
		addBand = new Button("Band anlegen");

		suchPanel.add(lblSuche);
		suchPanel.add(bandSuche);
		suchPanel.add(suche);
		suchPanel.add(suchen);
		vPanel.add(suchPanel);
		vPanel.add(home);
		vPanel.add(festivals);
		vPanel.add(bands);
		
		if(user != null){
			if(user.getRolle().equals("Admin")){
				addBand.setStyleName("clickbuttons");
				addFestival.setStyleName("clickbuttons");
				vPanel.add(addBand);
				vPanel.add(addFestival);
			}
		}
	}

	
	public String getDropDown(){
		return suche.getItemText(suche.getSelectedIndex());
	}
	
	public HasClickHandlers getSuchenButton() {
		  return suchen;
	}
	public HasClickHandlers getAddFestivalButton() {
		  return addFestival;
	}
	public HasClickHandlers getAddBandButton() {
		  return addBand;
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