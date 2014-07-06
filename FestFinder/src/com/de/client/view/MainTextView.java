package com.de.client.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.de.client.presenter.MainPresenter;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.de.shared.User;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainTextView extends Composite  implements MainPresenter.Display{

	private FlexTable festivalsTable;
	FlowPanel vPanel;
	private Button delete;
	private User current;
		
	public MainTextView(User current){
		this.current = current;
		vPanel = new FlowPanel();
		initWidget(vPanel);
		vPanel.setStyleName("seite");
			
	    festivalsTable = new FlexTable();
	    festivalsTable.setCellSpacing(0);
	    festivalsTable.setCellPadding(0);
	    festivalsTable.setWidth("100%");
	    festivalsTable.addStyleName("contacts-ListContents");
	    festivalsTable.getColumnFormatter().setWidth(0, "15px");
	    festivalsTable.setStyleName("list");
	    delete = new Button("Bands aus Bandliste löschen");
	    delete.setStyleName("clickbuttons");
	    
	    festivalsTable.getElement().getStyle().setCursor(Cursor.POINTER);

	    vPanel.add(festivalsTable);

			
	}
	
	 public void setFestivalData(ArrayList<Festival> data) {
		   festivalsTable.removeAllRows();
		    HashSet<Festival> hashSet = new HashSet<Festival>(data);
		    data.clear();
		    data.addAll(hashSet);
		    if(data.isEmpty()){

		    } else {
			    for (int i = 0; i < data.size(); ++i) {
			        festivalsTable.setText(i, 0, data.get(i).getName());
			    }
		    }
		  }
	 
		public void setBandData(ArrayList<Band> data) {
			   festivalsTable.removeAllRows();
			    HashSet<Band> hashSet = new HashSet<Band>(data);
			    data.clear();
			    data.addAll(hashSet);
				    for (int i = 0; i < data.size(); i++) {
				        if(current != null){
					        festivalsTable.setText(i, 1, data.get(i).getName());
					        festivalsTable.setWidget(i, 0, new CheckBox());
					        vPanel.add(delete);
				        } else {
					        festivalsTable.setText(i, 0, data.get(i).getName());

				        }
				    }			    
				        
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
	public Cell getFestivalTableCell(ClickEvent event) {
		return festivalsTable.getCellForEvent(event);
	}

	public HasClickHandlers getDeleteButton() {
		return delete;
	}
		
}