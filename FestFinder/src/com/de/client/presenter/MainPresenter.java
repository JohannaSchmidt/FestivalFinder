package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.BandServiceAsync;
import com.de.client.FestivalServiceAsync;
import com.de.client.event.FestivalClickedEvent;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;


public class MainPresenter  implements Presenter {  

	  ArrayList<Festival> festivalList;
	  ArrayList<Band> bandList;
	
	  public interface Display {
	    HasClickHandlers getFestivalTable();
	    int getSelectedRow(ClickEvent event);
	    void setFestivalData(ArrayList<Festival> festivals);
	    void setBandData(ArrayList<Band> bands);
	    Widget asWidget();
	  }
	  
	  private final FestivalServiceAsync rpcService;
	  private final HandlerManager eventBus;
	  private final Display display;
	  String token;
	  BandServiceAsync bandService;
	  
	  public MainPresenter(FestivalServiceAsync rpcService, HandlerManager eventBus, Display view) {
	    this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	  }
	  
	  public MainPresenter(FestivalServiceAsync rpcService, BandServiceAsync bandService, HandlerManager eventBus, Display view, String token) {
		    this.rpcService = rpcService;
		    this.bandService = bandService;
		    this.eventBus = eventBus;
		    this.display = view;
		    this.token = token;
	  }
	  
	  public void bind() {
		  getAllFestivals();
		  
		  display.getFestivalTable().addClickHandler(new ClickHandler() {
		        public void onClick(ClickEvent event) {
		          int selectedRow = display.getSelectedRow(event);
		          
		          if (selectedRow >= 0) {
		        	 if(token == "Festival"){
		        		 eventBus.fireEvent(new FestivalClickedEvent(festivalList.get(selectedRow)));
		        	 } else if(token == "Band"){
		        		 eventBus.fireEvent(new FestivalClickedEvent(bandList.get(selectedRow)));
		        	 }
		        }

		      }
		  

	  
		  });
	  }
	  
	  protected void getAllFestivals() {
		  
		  if(token == "Festival"){
			rpcService.getAllFestivals(new AsyncCallback<ArrayList<Festival>>() {
		
				public void onSuccess(ArrayList<Festival> result) {
					festivalList = result;
					display.setFestivalData(festivalList);
					
				}
		
				public void onFailure(Throwable caught) {
			        Window.alert("Error fetching festival details");
					
				}
			
			});
		  } else if (token == "Band"){
				bandService.getAllBands(new AsyncCallback<ArrayList<Band>>() {
					
					public void onSuccess(ArrayList<Band> result) {
						bandList = result;
						display.setBandData(bandList);
						
					}
			
					public void onFailure(Throwable caught) {
				        Window.alert("Error fetching festival details");
						
					}
				
				});
		  }
		
	}

	
	public void go(final HasWidgets container) {
		   bind();
		   container.clear();
		   container.add(display.asWidget());
	}
	
	}
	
