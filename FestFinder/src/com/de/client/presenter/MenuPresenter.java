package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.FestivalServiceAsync;
import com.de.client.event.SearchClickedEvent;
import com.de.client.event.SearchEvent;
import com.de.shared.Festival;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MenuPresenter implements Presenter {  

	ArrayList<Festival> festivalList;

  public interface Display {
    HasClickHandlers getNameButton();
    HasClickHandlers getFestivalButton();
    HasClickHandlers getGenreButton();
    HasClickHandlers getFestivalsButton();
    HasClickHandlers getBandButton();
    Widget asWidget();
  }
  
  private final FestivalServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;
  
  
  public MenuPresenter(FestivalServiceAsync rpcService, HandlerManager eventBus, Display view) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = view;
  }
  
  
  
  
  public void bind() {
    display.getNameButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) { 
        eventBus.fireEvent(new SearchClickedEvent("Band"));
      }
    });
    
    display.getFestivalButton().addClickHandler(new ClickHandler() {   
        public void onClick(ClickEvent event) {    
            eventBus.fireEvent(new SearchClickedEvent("Festival"));
        }
      });
    
    display.getGenreButton().addClickHandler(new ClickHandler() {   
        public void onClick(ClickEvent event) {    
            eventBus.fireEvent(new SearchClickedEvent("Genre"));
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
