package com.de.client.view;

import java.util.ArrayList;

import com.de.client.presenter.FestivalInfoPresenter;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.de.shared.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FestivalInfoView extends Composite  implements FestivalInfoPresenter.Display{

	FlowPanel vPanel;
	private Label name;
	private Label ort;
	private Label von;
	private Label bis;
	private Anchor web;
	private Band currentBand;
	
	private FlexTable bands;
	
	private SuggestBox bandsToAdd;
	private Button addBandsToFestival = new Button();
	private Button add = new Button();
	private Button zurueck;
	

		
	public FestivalInfoView(Object object){
		vPanel = new FlowPanel();
		initWidget(vPanel);
		vPanel.setStyleName("seite");
			
		if(object instanceof Festival){
			
			
			name = new Label(((Festival) object).getName());
			name.addStyleName("headlines");
			ort = new Label(((Festival) object).getOrt());
			von = new Label(((Festival) object).getsDatum().toString());
			bis = new Label(((Festival) object).geteDatum().toString());
			web = new Anchor(((Festival) object).getWebsite());
			
			addBandsToFestival = new Button("Diese Band dem Festival zuordnen");
		
			bands = new FlexTable();
			bands.setBorderWidth(1);
			
			vPanel.add(name);
			vPanel.add(ort);
			vPanel.add(von);
			vPanel.add(bis);
			vPanel.add(web);			
			vPanel.add(bands);

			
		} else if(object instanceof Band){
			currentBand = (Band)object;
			name = new Label(((Band) object).getName());
			name.addStyleName("headlines");
			ort = new Label(((Band) object).getGenre());
			von = new Label(String.valueOf(((Band) object).getJahr()));
			web = new Anchor(((Band) object).getWebsite());
		
			bands = new FlexTable();
			bands.setBorderWidth(1);
			
			vPanel.add(name);
			vPanel.add(ort);
			vPanel.add(von);
			vPanel.add(web);	
			
			add = new Button("Zur Bandliste hinzufuegen");
			add.setStyleName("clickbuttons");
			vPanel.add(add);
			
		}
		
		zurueck = new Button("Zurück");
		zurueck.setStyleName("clickbuttons");
		vPanel.add(zurueck);


		
			
	}
	public void setOracle(ArrayList<Band> words){
		addBandsToFestival.setStyleName("clickbuttons");
		 MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();		    
		    for (Band word : words) {
		      oracle.add(word.getName());
		    }
		bandsToAdd = new SuggestBox(oracle);
		vPanel.add(bandsToAdd);
		vPanel.add(addBandsToFestival);		
	}
	
	 public void setData(ArrayList<String> data) {
		   bands.removeAllRows();
		    
		    for (int i = 0; i < data.size(); ++i) {
		      bands.setText(i, 0, data.get(i));
		    }
		  }



	 
	 public HasClickHandlers getZurueck() {
		  return zurueck;
	}
	 
	 public HasClickHandlers getABTFButton() {
		  return addBandsToFestival;
	}
	 
	 
	 public HasClickHandlers getTable() {
		  return bands;
	}
	 
	 public HasClickHandlers getAddButton(){
		 return add;
	}
	 
	public Band getcurrentBand(){
		return currentBand;
	}
	 
	public String getBandName(){
		return bandsToAdd.getText();
	}

	
	public Widget asWidget(){
		return this;		
	}

	public Cell getBandsTableCell(ClickEvent event) {
		return bands.getCellForEvent(event);
	}

		
}