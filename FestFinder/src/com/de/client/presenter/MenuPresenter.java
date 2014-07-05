package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.BandServiceAsync;
import com.de.client.FestivalServiceAsync;
import com.de.client.event.AddBandButtonEvent;
import com.de.client.event.GetHomeEvent;
import com.de.client.event.SearchClickedEvent;
import com.de.client.event.SearchEvent;
import com.de.client.event.SearchedBandClickedEvent;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MenuPresenter implements Presenter {  

	ArrayList<Festival> festivalList;

  public interface Display {
	    HasClickHandlers getSuchenButton();
	    HasClickHandlers getAddFestivalButton();
	    HasClickHandlers getAddBandButton();
	    HasClickHandlers getHomeButton();
	    TextBox getSucheBox();
	    String getDropDown();
	    HasClickHandlers getFestivalsButton();
	    String getName();
	    HasClickHandlers getBandButton();  
	    Widget asWidget();
  }
  
  private final FestivalServiceAsync rpcService;
  private final BandServiceAsync bandService;
  private final HandlerManager eventBus;
  private final Display display;
  private ArrayList<Band> bandList;
  
  
  public MenuPresenter(FestivalServiceAsync rpcService, BandServiceAsync bandService, HandlerManager eventBus, Display view) {
    this.rpcService = rpcService;
    this.bandService = bandService;
    this.eventBus = eventBus;
    this.display = view;
  }
  
  
  
  
  public void bind() {
	  
	  display.getSuchenButton().addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				//display.getSucheBox().setText("Suchbegriff eingeben");
				bandList = null;
				festivalList = null;
				String name = display.getName();
				if(display.getDropDown().equals("Band")){
					bandService.getBands(name, new AsyncCallback<ArrayList<Band>>(){

					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						
					}
		
					public void onSuccess(ArrayList<Band> result) {
						bandList = result;
			    		eventBus.fireEvent(new SearchedBandClickedEvent(bandList, "Band"));
					}
		    		
					}); 
				
				} else if (display.getDropDown().equals("Festival")){
		      	rpcService.getFestivals(name, new AsyncCallback<ArrayList<Festival>>(){

					public void onSuccess(ArrayList<Festival> result) {
		  				festivalList = result;
		  	      		eventBus.fireEvent(new SearchedBandClickedEvent("Festival",festivalList));
					}

					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						
					}
		      		
		      	}); 

		      } else if (display.getDropDown().equals("Genre")){
		      	bandService.getGenreBands(name, new AsyncCallback<ArrayList<Band>>(){

		  			public void onFailure(Throwable caught) {
		  				caught.printStackTrace();
		  				
		  			}

		  			public void onSuccess(ArrayList<Band> result) {
		  				bandList = result;
		  	      		eventBus.fireEvent(new SearchedBandClickedEvent(bandList, "Band"));
		  				
		  			}
		      		
		      	}); 

		      }
		      }
		    });
		  
		  display.getAddFestivalButton().addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddBandButtonEvent("Festival"));
				
			}
			  
		  });

		  display.getAddBandButton().addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddBandButtonEvent("Band"));
				
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
	    
	    display.getHomeButton().addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new GetHomeEvent());			
			}
	    	
	    });
	    display.getSucheBox().addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				display.getSucheBox().setText("");
				
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
