package com.de.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.de.client.presenter.MainCreateBandPresenter;

public class MainCreateBandView extends Composite implements MainCreateBandPresenter.Display{


	private TextBox bandName;
	private TextBox genre;
	private TextBox gJahr;
	private TextBox webSite;
	private Button addBand;
	private Label lblBandName;
	private Label lblGnr;
	private Label lblGJahr;
	private Label lblWebsite;

		
	public MainCreateBandView(){
		FlowPanel vPanel = new FlowPanel();
		initWidget(vPanel);
		vPanel.setStyleName("seite");
		
		lblBandName = new Label("Bandname:");		
		bandName = new TextBox();
		lblGnr = new Label("Genre:");
		genre = new TextBox();
		lblGJahr = new Label("Gruendungsjahr:");
		gJahr = new TextBox();
		lblWebsite = new Label("Website:");
		webSite = new TextBox();


		addBand = new Button("Band Hinzufuegen");
			
		vPanel.add(lblBandName);
	    vPanel.add(bandName);
		vPanel.add(lblGnr);
	    vPanel.add(genre);
		vPanel.add(lblGJahr);
	    vPanel.add(gJahr);
		vPanel.add(lblWebsite);
	    vPanel.add(webSite);
	    vPanel.add(addBand);
		
			
	}
	
	 public HasClickHandlers getAddButton() {
		  return addBand;
	}
		
	 public String getName() {
		 if(bandName.getText() !=null){
			 return bandName.getText();
		 }
		 return null;
	}
	 
	 public String getGenre() {
		 if(genre.getText() !=null){
			 return genre.getText();
		 }
		 return null;
	}
	 
	 public String getGJahr() {
		 if(gJahr.getText() !=null){
			 return gJahr.getText();
		 }
		 return null;
	}
	 
	 public String getSite() {
		 if(webSite.getText() !=null){
			 return webSite.getText();
		 }
		 return null;
	}
	
	public Widget asWidget(){
		return this;		
	}

	
		
}