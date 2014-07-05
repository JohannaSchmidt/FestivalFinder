package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.BandServiceAsync;
import com.de.client.event.AddBandToBandListEvent;
import com.de.client.event.FestivalClickedEvent;
import com.de.client.event.ZurueckEvent;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

public class FestivalInfoPresenter implements Presenter {  

	ArrayList<String> bandList;

  public interface Display {
    HasClickHandlers getZurueck();
    HasClickHandlers getAddButton();
    HasClickHandlers getTable();
    Cell getBandsTableCell(ClickEvent event);
    Band getcurrentBand();
    void setData(ArrayList<String> bands);
    Widget asWidget();
  }
  
  private final BandServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;
  private Festival current;
  
  public FestivalInfoPresenter(BandServiceAsync rpcService, HandlerManager eventBus, Display view) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = view;
  }
  
  public FestivalInfoPresenter(BandServiceAsync rpcService, HandlerManager eventBus, Display view, Festival current) {
	    this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	    this.current = current;
	  }
  
  public void bind() {
	  

    display.getZurueck().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new ZurueckEvent());
      }
    });
    
    display.getAddButton().addClickHandler(new ClickHandler() {   
        public void onClick(ClickEvent event) {
          eventBus.fireEvent(new AddBandToBandListEvent(display.getcurrentBand()));
        }
      });

    display.getTable().addClickHandler(new ClickHandler() {   
        public void onClick(ClickEvent event) {
	          Cell selected = display.getBandsTableCell(event);
	          System.out.println("Click auf BAnd");
	          
	         if (selected != null) {
	        	 rpcService.getBands(bandList.get(selected.getRowIndex()), new AsyncCallback<ArrayList<Band>>(){

					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						
					}

					public void onSuccess(ArrayList<Band> result) {
		        		 eventBus.fireEvent(new FestivalClickedEvent(result.get(0)));
						
					}
	        		 
	        	 });

	        	 
	         }
        }
    });

    
    if(current != null){
		getAllFestivalsBands();
    }
  
  }
  
  protected void getAllFestivalsBands() {
	rpcService.getAllFestivalBands(current , new AsyncCallback<ArrayList<String>>() {

		public void onSuccess(ArrayList<String> result) {
			bandList = result;
			display.setData(bandList);			
		}

		public void onFailure(Throwable caught) {
			Window.alert("Error loading Bands for Festival");		
		}
	});
  }
  

	public void go(final HasWidgets container) {
	    bind();
	    container.clear();
	    container.add(display.asWidget());
	  }

}

