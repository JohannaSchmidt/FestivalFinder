package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.BandServiceAsync;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;




public class MainSearchedBandPresenter implements Presenter{

	
	  ArrayList<Band> bandList;
	  ArrayList<Festival> festivalList;
	  Band currentBand;
	
	  public interface Display {
	    HasClickHandlers getBandsTable();
	    int getSelectedRow(ClickEvent event);
	    void setBandData(ArrayList<Band> bands);
	    void setFestivalData(ArrayList<Festival> festivals);
	    Widget asWidget();
	  }
	  
	  private final BandServiceAsync rpcService;
	  private final HandlerManager eventBus;
	  private final Display display;
	  private String token;
	  
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
		    this.token = "Band";
	  }
	  
	  public MainSearchedBandPresenter(BandServiceAsync rpcService, HandlerManager eventBus, ArrayList<Festival> festivals, Display view) {
		  	this.rpcService = rpcService;
		    this.eventBus = eventBus;
		    this.display = view;
		    this.festivalList = festivals;
		    this.token = "Festival";
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
		  
		  if(token == "Festival"){
			  display.setFestivalData(festivalList);
		  }
		  else if(token == "Band"){
			  display.setBandData(bandList);
		  }

	  
	  }
	  
	/*  protected void getBands(String name) {
		rpcService.getBands(name, new AsyncCallback<ArrayList<Band>>() {
	
			public void onSuccess(ArrayList<Band> result) {
				bandList = result;
				display.setBandData(bandList);
				
			}
	
			public void onFailure(Throwable caught) {
		        Window.alert("Error fetching band details");
				
			}
			
		});
		
	}
	  */
	
	public void go(final HasWidgets container) {
		    bind();
		    container.clear();
		    container.add(display.asWidget());
	}
	
}
