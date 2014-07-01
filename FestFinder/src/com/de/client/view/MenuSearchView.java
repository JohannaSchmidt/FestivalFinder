package com.de.client.view;

import com.de.client.presenter.MenuSearchPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MenuSearchView extends Composite implements MenuSearchPresenter.Display{
		
	private Label lblSuche;
	private TextBox bandSuche;
	private Button suchen;
	private PushButton festivals;
	private PushButton bands;

		
	public MenuSearchView(String token){
		VerticalPanel vPanel = new VerticalPanel();
		initWidget(vPanel);
		vPanel.setStyleName("suche");

		if(token == "Band"){	
			lblSuche = new Label("Bandnamen eingeben:");
		} else if (token == "Festival"){
			lblSuche = new Label("Festivalnamen eingeben:");
		} else if (token == "Genre"){
			lblSuche = new Label("Genre eingeben:");
		}
		suchen = new Button("Suchen");
		festivals = new PushButton(new Image("images/Festivals.png"));
		festivals.setStyleName("buttons");
		bands = new PushButton(new Image("images/Bands.png"));
		bands.setStyleName("buttons");
		bandSuche = new TextBox();
		

		
		vPanel.add(lblSuche);
		vPanel.add(bandSuche);
		vPanel.add(suchen);
		vPanel.add(festivals);
		vPanel.add(bands);
			
	}
	
	public HasClickHandlers getSearchButton() {
		  return suchen;
	}
	
	public HasClickHandlers getFestivalsButton() {
		    return festivals;
	}
		  
	public HasClickHandlers getBandButton() {
		    return bands;
	}
	
	public TextBox getName() {
		  return  bandSuche;
	}
	 
	public Widget asWidget(){
		return this;		
	}




		
}