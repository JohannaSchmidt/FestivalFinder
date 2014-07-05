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
	
	private TextBox festId;
	private TextBox fName;
	private TextBox sDatum;
	private TextBox eDatum;
	private TextBox ort;
	private TextBox fWeb;
	private Button addFestival;
	
	
	private Label lblBandName;
	private Label lblGnr;
	private Label lblGJahr;
	private Label lblWebsite;
	
	private Label lfestId;
	private Label lfName;
	private Label lsDatum;
	private Label leDatum;
	private Label lort;
	private Label lfWeb;

		
	public MainCreateBandView(String token){
		FlowPanel vPanel = new FlowPanel();
		initWidget(vPanel);
		vPanel.setStyleName("seite");
		
		addBand = new Button("Band Hinzufuegen");
		addBand.setStyleName("clickbuttons");
		addFestival = new Button("Festival Hinzufuegen");
		addFestival.setStyleName("clickbuttons");
		
		if(token == "Band"){
			
		lblBandName = new Label("Bandname:");		
		bandName = new TextBox();
		lblGnr = new Label("Genre:");
		genre = new TextBox();
		lblGJahr = new Label("Gruendungsjahr:");
		gJahr = new TextBox();
		lblWebsite = new Label("Website:");
		webSite = new TextBox();

			
		vPanel.add(lblBandName);
	    vPanel.add(bandName);
		vPanel.add(lblGnr);
	    vPanel.add(genre);
		vPanel.add(lblGJahr);
	    vPanel.add(gJahr);
		vPanel.add(lblWebsite);
	    vPanel.add(webSite);
	    vPanel.add(addBand);
	    
		} else if(token == "Festival"){
			lfestId = new Label("FestivalID:");		
			festId = new TextBox();
			lfName = new Label("Festivalname:");
			fName = new TextBox();
			lsDatum = new Label("Startdatum: yyyy-mm-dd:");
			sDatum = new TextBox();
			leDatum = new Label("Enddatum: yyyy-mm-dd:");
			eDatum = new TextBox();
			lort = new Label("Ort:");
			ort = new TextBox();
			lfWeb = new Label("Website:");
			fWeb = new TextBox();


			addFestival = new Button("Festival Hinzufuegen");
			addBand.setStyleName("clickbuttons");
				
			vPanel.add(lfestId);
		    vPanel.add(festId);
			vPanel.add(lfName);
		    vPanel.add(fName);
			vPanel.add(lsDatum);
		    vPanel.add(sDatum);
		    vPanel.add(leDatum);
		    vPanel.add(eDatum);
		    vPanel.add(lort);
		    vPanel.add(ort);
			vPanel.add(lfWeb);
		    vPanel.add(fWeb);
		    vPanel.add(addFestival);
		}
		
		
			
	}
	
	 public HasClickHandlers getAddBandButton() {
		  return addBand;
	}
	 
	 public HasClickHandlers getAddFestivalButton() {
		  return addFestival;
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
	 
	 public String getfId() {
		 if(festId.getText() !=null){
			 return festId.getText();
		 }
		 return null;
	}
	 public String getfName() {
		 if(fName.getText() !=null){
			 return fName.getText();
		 }
		 return null;
	}
	 
	 public String getfsDatum() {
		 if(sDatum.getText() !=null){
			 return sDatum.getText();
		 }
		 return null;
	}
	 public String getfeDatum() {
		 if(eDatum.getText() !=null){
			 return eDatum.getText();
		 }
		 return null;
	}
	 
	 public String getOrt() {
		 if(ort.getText() !=null){
			 return ort.getText();
		 }
		 return null;
	}
	 
	 public String getfWeb() {
		 if(fWeb.getText() !=null){
			 return fWeb.getText();
		 }
		 return null;
	}
	
	public Widget asWidget(){
		return this;		
	}

	
		
}