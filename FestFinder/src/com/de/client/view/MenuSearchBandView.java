package com.de.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.de.client.presenter.MenuSearchBandPresenter;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MenuSearchBandView extends Composite implements MenuSearchBandPresenter.Display{
		
	private Label lblSuche;
	private TextBox bandSuche;
	private Button suchen;
	private PushButton festivals;
	private PushButton bands;

		
	public MenuSearchBandView(){
		VerticalPanel vPanel = new VerticalPanel();
		initWidget(vPanel);
		vPanel.setStyleName("suche");

			
		lblSuche = new Label("Bandnamen eingeben:");
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