package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.BandServiceAsync;
import com.de.client.event.AddBandButtonEvent;
import com.de.client.event.FestivalClickedEvent;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

public class MainSearchedPresenter implements Presenter{

	
	  ArrayList<Band> bandList;
	  ArrayList<Festival> festivalList;
	  Band currentBand;
	
	  public interface Display {
		Cell getBandsTableCell(ClickEvent event);
	    HasClickHandlers getBandsTable();
	    void setBandData(ArrayList<Band> bands);
	    void setFestivalData(ArrayList<Festival> festivals);
	    Widget asWidget();		
	    public void setButton();
		HasClickHandlers getAddBandButton();
	  }
	  
	  private final BandServiceAsync rpcService;
	  private final HandlerManager eventBus;
	  private final Display display;
	  private String token;
	  
	  public MainSearchedPresenter(BandServiceAsync rpcService, HandlerManager eventBus, Display view) {
	    this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	  }
	  
	  public MainSearchedPresenter(BandServiceAsync rpcService, HandlerManager eventBus, Display view, ArrayList<Band> bands) {
		    this.rpcService = rpcService;
		    this.eventBus = eventBus;
		    this.display = view;
		    this.bandList = bands;
		    this.token = "Band";
	  }
	  
	  public MainSearchedPresenter(BandServiceAsync rpcService, HandlerManager eventBus, ArrayList<Festival> festivals, Display view) {
		  	this.rpcService = rpcService;
		    this.eventBus = eventBus;
		    this.display = view;
		    this.festivalList = festivals;
		    this.token = "Festival";
	  }

	  
	  
	  public void bind() {
		  
		  if(token == "Festival"){
			  display.setFestivalData(festivalList);
		  }
		  else if(token == "Band"){
			  if(bandList.isEmpty()){
				  display.setButton();
			  } else {
			  display.setBandData(bandList);
			  }
			  
		  }
		  
		  display.getAddBandButton().addClickHandler(new ClickHandler(){
			  public void onClick(ClickEvent event) {
				  eventBus.fireEvent(new AddBandButtonEvent());
			  }
		  });

		  display.getBandsTable().addClickHandler(new ClickHandler() {
		        public void onClick(ClickEvent event) {
		          Cell selected = display.getBandsTableCell(event);
		          
		          if (selected != null) {
		        	 if(token == "Festival"){
		        		 eventBus.fireEvent(new FestivalClickedEvent(festivalList.get(selected.getRowIndex())));
		        	 } else if(token == "Band"){
		        		 eventBus.fireEvent(new FestivalClickedEvent(bandList.get(selected.getRowIndex())));
		        	 }
		        }

		      }
		  

	  
		  });
	  
	  }
	
	public void go(final HasWidgets container) {
		    bind();
		    container.clear();
		    container.add(display.asWidget());
	}
	
}
