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
	private PushButton addFestival;
	private PushButton addBand;
	private PushButton deleteFestival;
	private PushButton deleteBand;

		
	public MenuView(User user){
		FlowPanel vPanel = new FlowPanel();
		FlowPanel suchPanel = new FlowPanel();
		initWidget(vPanel);
		vPanel.setStyleName("suche");
		suchPanel.setStyleName("suchpanel");
		
		lblSuche = new Label("Suchen nach:");	
		lblSuche.setStyleName("suchentext");
		suche = new ListBox();
		suche.addItem("Band");
		suche.addItem("Festival");
		suche.addItem("Genre");
		suche.setStyleName("suchenfeld");
		suche.getElement().getStyle().setCursor(Cursor.POINTER);
		bandSuche = new TextBox();
		bandSuche.setText("Suchbegriff eingeben");
		bandSuche.setStyleName("eingabeFeld");
		suchen = new Button("Suchen");
		suchen.setStyleName("suchenbutton");
		suchen.getElement().getStyle().setCursor(Cursor.POINTER);

		addFestival = new PushButton(new Image("images/Festivalanlegen.png"));
		addBand = new PushButton(new Image("images/Bandanlegen.png"));
		deleteFestival = new PushButton(new Image("images/Festivalloeschen.png"));
		deleteBand = new PushButton(new Image("images/Bandloeschen.png"));



		home = new PushButton(new Image("images/Home.png"));
		home.setStyleName("buttons");
		home.getElement().getStyle().setCursor(Cursor.POINTER);
		
		festivals = new PushButton(new Image("images/Festivals.png"));
		festivals.setStyleName("buttons");
		festivals.getElement().getStyle().setCursor(Cursor.POINTER); 
		
		bands = new PushButton(new Image("images/Bands.png"));
		bands.setStyleName("buttons");
		bands.getElement().getStyle().setCursor(Cursor.POINTER); 
		
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
				addFestival.setStyleName("buttons");
				addFestival.getElement().getStyle().setCursor(Cursor.POINTER);
				
				addBand.setStyleName("buttons");
				addBand.getElement().getStyle().setCursor(Cursor.POINTER); 
				
				deleteFestival.setStyleName("buttons");
				deleteFestival.getElement().getStyle().setCursor(Cursor.POINTER); 
				
				deleteBand.setStyleName("buttons");
				deleteBand.getElement().getStyle().setCursor(Cursor.POINTER); 
				
				vPanel.add(addBand);
				vPanel.add(deleteBand);
				vPanel.add(addFestival);
				vPanel.add(deleteFestival);
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
	public HasClickHandlers getDeleteFestivalButton() {
		  return deleteFestival;
	}
	public HasClickHandlers getDeleteBandButton() {
		  return deleteBand;
	}	
	 
	public HasClickHandlers getFestivalsButton() {
		return festivals;
	}
		
	public HasClickHandlers getHomeButton(){
		return home;
	}
	
	public TextBox getSucheBox(){
		return bandSuche;
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