package com.de.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.de.client.presenter.MainPresenter;
import com.de.shared.Festival;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainTextView extends Composite  implements MainPresenter.Display{

	private FlexTable festivalsTable;
		
	public MainTextView(){
		VerticalPanel vPanel = new VerticalPanel();
		initWidget(vPanel);
		vPanel.setStyleName("seite");
			
	    festivalsTable = new FlexTable();
	    festivalsTable.setCellSpacing(0);
	    festivalsTable.setCellPadding(0);
	    festivalsTable.setWidth("100%");
	    festivalsTable.addStyleName("contacts-ListContents");
	    festivalsTable.getColumnFormatter().setWidth(0, "15px");

	    vPanel.add(festivalsTable);
		
			
	}
	
	 public void setData(ArrayList<Festival> data) {
		   festivalsTable.removeAllRows();
		    
		    for (int i = 0; i < data.size(); ++i) {
		        festivalsTable.setWidget(i, 0, new CheckBox());
		        festivalsTable.setText(i, 1, data.get(i).getfName());
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

	
	public Widget asWidget(){
		return this;		
	}

	public HasClickHandlers getFestivalTable() {
		return festivalsTable;
	}

		
}