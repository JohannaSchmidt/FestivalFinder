package com.de.client.view;


import java.util.ArrayList;

import com.de.client.presenter.MainSearchedPresenter;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

public class MainSearchedView extends Composite implements MainSearchedPresenter.Display {
	private FlexTable bandsTable;
	private Button addBand;
	private FlowPanel vPanel;

	
	
	public MainSearchedView(){
		vPanel = new FlowPanel();
		initWidget(vPanel);
		vPanel.setStyleName("seite");
			

	    bandsTable = new FlexTable();
	    bandsTable.setCellSpacing(0);
	    bandsTable.setCellPadding(0);
	    bandsTable.setWidth("100%");
	    bandsTable.addStyleName("contacts-ListContents");
	    bandsTable.getColumnFormatter().setWidth(0, "15px");
	    bandsTable.getElement().getStyle().setCursor(Cursor.POINTER);

	    
		addBand = new Button("Band Hinzufuegen");
		addBand.setStyleName("clickbuttonseinloggen");
		vPanel.add(addBand);
		addBand.setVisible(false);

	    vPanel.add(bandsTable);
		
			
	}
	
	 public void setFestivalData(ArrayList<Festival> data) {
		   bandsTable.removeAllRows();
		    
		    for (int i = 0; i < data.size(); ++i) {
		        bandsTable.setText(i, 0, data.get(i).getName());
		    }
		  }
	 
	 public void setBandData(ArrayList<Band> data) {
			   bandsTable.removeAllRows();
			    
			    for (int i = 0; i < data.size(); ++i) {
			        bandsTable.setText(i, 0, data.get(i).getName());
			    }
			 }
	  
	  public void setButton(){
		  addBand.setVisible(true);
		  
	  }
	  
	  public HasClickHandlers getAddBandButton(){
		  return addBand;
	  }
	  
	  
	public Widget asWidget(){
		return this;		
	}

	public HasClickHandlers getBandsTable() {
		return bandsTable;
	}

	public Cell getBandsTableCell(ClickEvent event) {
		return bandsTable.getCellForEvent(event);
	}

}