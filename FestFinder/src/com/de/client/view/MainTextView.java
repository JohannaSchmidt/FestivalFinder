package com.de.client.view;

import java.util.ArrayList;
import java.util.List;

import com.de.client.presenter.MainPresenter;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainTextView extends Composite  implements MainPresenter.Display{

	private FlexTable festivalsTable;
	FlowPanel vPanel;
	private Button delete;
		
	public MainTextView(){
		vPanel = new FlowPanel();
		initWidget(vPanel);
		vPanel.setStyleName("seite");
			
	    festivalsTable = new FlexTable();
	    festivalsTable.setCellSpacing(0);
	    festivalsTable.setCellPadding(0);
	    festivalsTable.setBorderWidth(1);
	    festivalsTable.setWidth("100%");
	    festivalsTable.addStyleName("contacts-ListContents");
	    festivalsTable.getColumnFormatter().setWidth(0, "15px");
	    delete = new Button("Bands aus Bandliste loeschen");
	    
	    vPanel.add(festivalsTable);
		
			
	}
	
	 public void setFestivalData(ArrayList<Festival> data) {
		   festivalsTable.removeAllRows();
		    if(data.isEmpty()){

		    } else {
			    for (int i = 0; i < data.size(); ++i) {
			        festivalsTable.setWidget(i, 0, new CheckBox());
			        festivalsTable.setText(i, 1, data.get(i).getName());
			    }
		    }
		  }
	 
		public void setBandData(ArrayList<Band> data) {
			   festivalsTable.removeAllRows();

				    for (int i = 0; i < data.size(); ++i) {
				        festivalsTable.setWidget(i, 0, new CheckBox());
				        festivalsTable.setText(i, 1, data.get(i).getName());
				        vPanel.add(delete);
				    }
			    
			 }
	 
		  public int getSelectedRow(ClickEvent event) {
			    int selectedRow = -1;
			    HTMLTable.Cell cell = festivalsTable.getCellForEvent(event);
			    
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
		  
		public List<Integer> getSelectedRows() {
			    List<Integer> selectedRows = new ArrayList<Integer>();
			    
			    for (int i = 0; i < festivalsTable.getRowCount(); ++i) {
			      CheckBox checkBox = (CheckBox)festivalsTable.getWidget(i, 0);
				      if (checkBox.getValue()) {
				          selectedRows.add(i);
				      }
			    }
			    
			 return selectedRows;
		 }
	  


	
	public Widget asWidget(){
		return this;		
	}

	public HasClickHandlers getFestivalTable() {
		return festivalsTable;
	}

	public HasClickHandlers getDeleteButton() {
		return delete;
	}
		
}