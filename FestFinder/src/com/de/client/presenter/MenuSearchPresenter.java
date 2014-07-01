package com.de.client.presenter;


import java.util.ArrayList;

import com.de.client.BandServiceAsync;
import com.de.client.FestivalServiceAsync;
import com.de.client.event.SearchEvent;
import com.de.client.event.SearchedBandClickedEvent;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MenuSearchPresenter implements Presenter {  

	ArrayList<Festival> festivalList;

  public interface Display {
    HasClickHandlers getSearchButton();
    HasClickHandlers getFestivalsButton();
    HasClickHandlers getBandButton();
    TextBox getName();
    Widget asWidget();
  }
  
  private final FestivalServiceAsync rpcService;
  private final BandServiceAsync bandService;
  private final HandlerManager eventBus;
  private final Display display;
  private ArrayList<Band> bandList;
  
  
  public MenuSearchPresenter(FestivalServiceAsync rpcService, BandServiceAsync bandService, HandlerManager eventBus, Display view) {
    this.rpcService = rpcService;
    this.bandService = bandService;
    this.eventBus = eventBus;
    this.display = view;
  }
  
  
  
  
  public void bind() {
    display.getSearchButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {   
    	final String name = display.getName().getText();
    	System.out.println("Klick auf Search");
    	bandService.getBands(name, new AsyncCallback<ArrayList<Band>>(){

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(ArrayList<Band> result) {
				bandList = result;
				
			}
    		
    	}); 
    	
    	if(!bandList.isEmpty()){
    		eventBus.fireEvent(new SearchedBandClickedEvent(bandList));
    	}
      }
    });
    
    display.getFestivalsButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
          eventBus.fireEvent(new SearchEvent("Festival"));
        }
    });
    
    display.getBandButton().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
          eventBus.fireEvent(new SearchEvent("Band"));
      }
    });
  
  }
  
  
  
  protected void getAllFestivals() {
	rpcService.getAllFestivals(new AsyncCallback<ArrayList<Festival>>() {

		public void onFailure(Throwable caught) {
	        Window.alert("Error fetching festival details");
			
		}

		public void onSuccess(ArrayList<Festival> result) {
			festivalList = result;
			
		}

		
	});
	
}

	public void go(final HasWidgets container) {
	    bind();
	    container.clear();
	    container.add(display.asWidget());
	  }

}
