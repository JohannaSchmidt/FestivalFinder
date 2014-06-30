package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.BandServiceAsync;
import com.de.shared.Band;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;




public class MainSearchedBandPresenter implements Presenter{

	
	  ArrayList<Band> bandList;
	  Band currentBand;
	
	  public interface Display {
	    HasClickHandlers getBandsTable();
	    int getSelectedRow(ClickEvent event);
	    void setData(ArrayList<Band> bands);
	    Widget asWidget();
	  }
	  
	  private final BandServiceAsync rpcService;
	  private final HandlerManager eventBus;
	  private final Display display;
	  
	  public MainSearchedBandPresenter(BandServiceAsync rpcService, HandlerManager eventBus, Display view) {
	    this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	  }
	  
	  public MainSearchedBandPresenter(BandServiceAsync rpcService, HandlerManager eventBus, Display view, ArrayList<Band> bands) {
		    this.rpcService = rpcService;
		    this.eventBus = eventBus;
		    this.display = view;
		    this.bandList = bands;
	  }
	  
	  public void bind() {
		  
		/*  display.getBandsTable().addClickHandler(new ClickHandler() {
		        public void onClick(ClickEvent event) {
		          int selectedRow = display.getSelectedRow(event);
		          
		          if (selectedRow >= 0) {
		           setCurrentBand(bandList.get(selectedRow));
			        eventBus.fireEvent(new FestivalClickedEvent());
		          }
		        }

		      });*/
		  
		  if(!bandList.isEmpty()){
			  display.setData(bandList);
		  }

	  
	  }
	  
	  protected void getBands(String name) {
		rpcService.getBands(name, new AsyncCallback<ArrayList<Band>>() {
	
			public void onSuccess(ArrayList<Band> result) {
				bandList = result;
				display.setData(bandList);
				
			}
	
			public void onFailure(Throwable caught) {
		        Window.alert("Error fetching band details");
				
			}
			
		});
		
	}
	  

		private void setCurrentBand(Band band) {
			currentBand = band;
			
		}
	  
	 public Band getCurrentFestival(){
		 return currentBand;
	 }
	
	public void go(final HasWidgets container) {
		    bind();
		    container.clear();
		    container.add(display.asWidget());
	}
	
}
