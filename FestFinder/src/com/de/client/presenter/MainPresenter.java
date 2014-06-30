package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.FestivalServiceAsync;
import com.de.client.event.FestivalClickedEvent;
import com.de.client.event.RegisterEvent;
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
	  Festival currentFestival;
	
	  public interface Display {
	    HasClickHandlers getFestivalTable();
	    int getSelectedRow(ClickEvent event);
	    void setData(ArrayList<Festival> festivals);
	    Widget asWidget();
	  }
	  
	  private final FestivalServiceAsync rpcService;
	  private final HandlerManager eventBus;
	  private final Display display;
	  
	  public MainPresenter(FestivalServiceAsync rpcService, HandlerManager eventBus, Display view) {
	    this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	  }
	  
	  public void bind() {
		  getAllFestivals();
		  
		  display.getFestivalTable().addClickHandler(new ClickHandler() {
		        public void onClick(ClickEvent event) {
		          int selectedRow = display.getSelectedRow(event);
		          
		          if (selectedRow >= 0) {
		           setCurrentFestival(festivalList.get(selectedRow));
			        eventBus.fireEvent(new FestivalClickedEvent());
		          }
		        }

		      });

	  
	  }
	  
	  protected void getAllFestivals() {
		rpcService.getAllFestivals(new AsyncCallback<ArrayList<Festival>>() {
	
			public void onSuccess(ArrayList<Festival> result) {
				festivalList = result;
				display.setData(festivalList);
				
			}
	
			public void onFailure(Throwable caught) {
		        Window.alert("Error fetching festival details");
				
			}
			
		});
		
	}
	  

		private void setCurrentFestival(Festival festival) {
			currentFestival = festival;
			
		}
	  
	 public Festival getCurrentFestival(){
		 return currentFestival;
	 }
	
	public void go(final HasWidgets container) {
		   bind();
		    container.clear();
		    container.add(display.asWidget());
	}
	
	}
	
