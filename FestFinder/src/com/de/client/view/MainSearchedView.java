package com.de.client.view;

import java.util.ArrayList;

import com.de.client.presenter.MainSearchedPresenter;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainSearchedView extends Composite implements MainSearchedPresenter.Display {
	private FlexTable bandsTable;
	private Button addBand;
	private VerticalPanel vPanel;
	
	
	public MainSearchedView(){
		vPanel = new VerticalPanel();
		initWidget(vPanel);
		vPanel.setStyleName("seite");
			

	    bandsTable = new FlexTable();
	    bandsTable.setCellSpacing(0);
	    bandsTable.setCellPadding(0);
	    bandsTable.setBorderWidth(1);
	    bandsTable.setWidth("100%");
	    bandsTable.addStyleName("contacts-ListContents");
	    bandsTable.getColumnFormatter().setWidth(0, "15px");

	    vPanel.add(bandsTable);
		
			
	}
	
	 public void setFestivalData(ArrayList<Festival> data) {
		   bandsTable.removeAllRows();
		    
		    for (int i = 0; i < data.size(); ++i) {
		        bandsTable.setWidget(i, 0, new CheckBox());
		        bandsTable.setText(i, 1, data.get(i).getName());
		    }
		  }
	 
	 public void setBandData(ArrayList<Band> data) {
			   bandsTable.removeAllRows();
			    
			    for (int i = 0; i < data.size(); ++i) {
			        bandsTable.setWidget(i, 0, new CheckBox());
			        bandsTable.setText(i, 1, data.get(i).getName());
			    }
			 }

	 
	  public int getSelectedRow(ClickEvent event) {
		    int selectedRow = -1;
		    HTMLTable.Cell cell = bandsTable.getCellForEvent(event);
		    
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
	  
	  public void setButton(){
		//  bandsTable.removeAllRows();
		  vPanel.remove(bandsTable);
		  addBand = new Button("Band Hinzufuegen");
		  vPanel.add(addBand);
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





}
