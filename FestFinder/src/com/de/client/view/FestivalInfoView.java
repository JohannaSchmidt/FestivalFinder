package com.de.client.view;

import java.util.ArrayList;

import com.de.client.presenter.FestivalInfoPresenter;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FestivalInfoView extends Composite  implements FestivalInfoPresenter.Display{

	private Label name;
	private Label ort;
	private Label von;
	private Label bis;
	private Anchor web;
	private Band currentBand;
	
	private FlexTable bands;
	
	private Button add = new Button();
	private Button zurueck;
	

		
	public FestivalInfoView(Object object){
		FlowPanel vPanel = new FlowPanel();
		initWidget(vPanel);
		vPanel.setStyleName("seite");
			
		if(object instanceof Festival){
			
			
			name = new Label(((Festival) object).getName());
			ort = new Label(((Festival) object).getOrt());
			von = new Label(((Festival) object).getsDatum().toString());
			bis = new Label(((Festival) object).geteDatum().toString());
			web = new Anchor(((Festival) object).getWebsite());
		
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
			vPanel.add(add);
			
		}
		
		zurueck = new Button("Zur�ck");
		vPanel.add(zurueck);


		
			
	}
	
	 public void setData(ArrayList<String> data) {
		   bands.removeAllRows();
		    
		    for (int i = 0; i < data.size(); ++i) {
		      bands.setText(i, 0, data.get(i));
		    }
		  }
	  public int getSelectedRow(ClickEvent event) {
		    int selectedRow = -1;
		    HTMLTable.Cell cell = bands.getCellForEvent(event);
		    
		    if (cell != null) {
		      // Suppress clicks if the user is actually selecting the 
		      //  check box
		      //
		      if (cell.getCellIndex() > 0) {
		        selectedRow = cell.getRowIndex();
		      }
		    }
		    
		    return selectedRow;
		  }


	 
	 public HasClickHandlers getZurueck() {
		  return zurueck;
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
	 

	
	public Widget asWidget(){
		return this;		
	}

		
}