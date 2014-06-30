package com.de.client.view;

import com.de.client.presenter.MenuPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MenuView extends Composite implements MenuPresenter.Display{
		
	private Label lblSuche;
//	private TextBox bandSuche;
	private Button suchenName;
	private Button suchenFestival;
	private Button suchenGenre;
	private PushButton festivals;
	private PushButton bands;
//	private RadioButton radioBtnBand;
//	private RadioButton radioBtnFestival;
//	private RadioButton radioBtnGenre;
		
	public MenuView(){
		VerticalPanel vPanel = new VerticalPanel();
		initWidget(vPanel);
		vPanel.setStyleName("suche");
//		vPanel.setBorderWidth(1);
//		vPanel.setSpacing(2);
//		vPanel.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
//			
		lblSuche = new Label("Suchen nach:");
//		radioBtnBand = new RadioButton("SuchenNach", "Band");
//		radioBtnFestival = new RadioButton("SuchenNach", "Festival");
//		radioBtnGenre = new RadioButton("SuchenNach", "Genre");
//		bandSuche = new TextBox();
		suchenName = new Button("Name");
		suchenFestival = new Button("Festival");
		suchenGenre = new Button("Genre");
		festivals = new PushButton(new Image("images/Festivals.png"));
		festivals.setStyleName("buttons");
		bands = new PushButton(new Image("images/Bands.png"));
		bands.setStyleName("buttons");
		
		vPanel.add(lblSuche);
//		vPanel.add(radioBtnBand);
//		vPanel.add(radioBtnFestival);	
//		vPanel.add(radioBtnGenre);	
//		vPanel.add(bandSuche);
		vPanel.add(suchenName);
		vPanel.add(suchenFestival);
		vPanel.add(suchenGenre);
		vPanel.add(festivals);
		vPanel.add(bands);
			
	}
	
	public HasClickHandlers getNameButton() {
		  return suchenName;
	}
	 
	public HasClickHandlers getFestivalButton() {
		  return suchenFestival;
	}
	
	public HasClickHandlers getGenreButton() {
		  return suchenGenre;
	}
	
	public HasClickHandlers getFestivalsButton() {
		    return festivals;
	}
		  
	public HasClickHandlers getBandButton() {
		    return bands;
	}
	
//	public TextBox getSuche() {
//		  return  bandSuche;
//	}
	
//	public void check(RadioButton) {
//		  SuchenNach.getValue();
//	}
	
	public Widget asWidget(){
		return this;		
	}




		
}