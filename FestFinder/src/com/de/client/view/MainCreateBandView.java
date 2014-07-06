package com.de.client.view;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.de.client.presenter.MainCreateBandPresenter;
import com.de.shared.Band;
import com.de.shared.Festival;

public class MainCreateBandView extends Composite implements MainCreateBandPresenter.Display{



	private TextBox bandName;
	private TextBox genre;
	private TextBox gJahr;
	private TextBox webSite;
	private Button addBand;
	private SuggestBox thingsToDelete;
	private Button deleteBand;
	private Button deleteFestival;
	
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
	FlowPanel vPanel;
	String token;

		
	public MainCreateBandView(String token){
		this.token = token;
		vPanel = new FlowPanel();
		initWidget(vPanel);
		vPanel.setStyleName("seite");
		
		addBand = new Button("Band Hinzufügen");
		addBand.setStyleName("clickbuttonseinloggen");
		addBand.getElement().getStyle().setCursor(Cursor.POINTER);
		deleteBand = new Button("Löschen");
		deleteBand.setStyleName("clickbuttonseinloggen");
		deleteBand.getElement().getStyle().setCursor(Cursor.POINTER);
		deleteFestival = new Button("Löschen");
		deleteFestival.setStyleName("clickbuttonseinloggen");
		deleteFestival.getElement().getStyle().setCursor(Cursor.POINTER);
		addFestival = new Button("Festival Hinzufügen");
		addFestival.setStyleName("clickbuttonseinloggen");
		addFestival.getElement().getStyle().setCursor(Cursor.POINTER);
		
		if(token == "Band"){
			
		lblBandName = new Label("Bandname:");	
		lblBandName.setStyleName("text");
		bandName = new TextBox();
		lblGnr = new Label("Genre:");
		lblGnr.setStyleName("text");
		genre = new TextBox();
		lblGJahr = new Label("Gruendungsjahr:");
		lblGJahr.setStyleName("text");
		gJahr = new TextBox();
		lblWebsite = new Label("Website:");
		lblWebsite.setStyleName("text");
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
			lfestId.setStyleName("text");
			festId = new TextBox();
			lfName = new Label("Festivalname:");
			lfName.setStyleName("text");
			fName = new TextBox();
			lsDatum = new Label("Startdatum: yyyy-mm-dd:");
			lsDatum.setStyleName("text");
			sDatum = new TextBox();
			leDatum = new Label("Enddatum: yyyy-mm-dd:");
			leDatum.setStyleName("text");
			eDatum = new TextBox();
			lort = new Label("Ort:");
			lort.setStyleName("text");
			ort = new TextBox();
			lfWeb = new Label("Website:");
			lfWeb.setStyleName("text");
			fWeb = new TextBox();


			addFestival = new Button("Festival Hinzufuegen");
			addFestival.setStyleName("clickbuttonseinloggen");
			addBand.setStyleName("clickbuttonseinloggen");
				
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
	
	public void setOracleBand(ArrayList<Band> words){
		deleteBand.setStyleName("clickbuttons");
		 MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();		    
		    for (Band word : words) {
		      oracle.add(word.getName());
		    }
		thingsToDelete = new SuggestBox(oracle);
		vPanel.add(thingsToDelete);
		vPanel.add(deleteBand);		
}

public void setOracleFestival(ArrayList<Festival> words){
	deleteFestival.setStyleName("clickbuttons");
	 MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();		    
	    for (Festival word : words) {
	      oracle.add(word.getName());
	    }
	thingsToDelete = new SuggestBox(oracle);
	vPanel.add(thingsToDelete);
	vPanel.add(deleteFestival);		
}
 public HasClickHandlers getAddBandButton() {
	  return addBand;
}
 
 public HasClickHandlers getDeleteBandButton() {
	  return deleteBand;
}
 public HasClickHandlers getDeleteFestivalButton() {
	  return deleteFestival;
}
	public String getDeleteName(){
		return thingsToDelete.getText();
	}
 
 public HasClickHandlers getAddFestivalButton() {
	  return addFestival;
}
 public String getToken(){
	 return token;
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