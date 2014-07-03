package com.de.client.presenter;



import java.util.ArrayList;

import com.de.client.UserServiceAsync;
import com.de.client.event.AddBandEvent;
import com.de.client.event.RegisterEvent;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.de.shared.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MainCreateBandPresenter implements Presenter{

	  public interface Display {
	    HasClickHandlers getAddButton();
	    String getName();
	    String getGenre();
	    String getGJahr();
	    String getSite();
	    Widget asWidget();
	  }
	  
	  private final UserServiceAsync rpcService;
	  private final HandlerManager eventBus;
	  private final Display display;
	  
	  public MainCreateBandPresenter(UserServiceAsync rpcService, HandlerManager eventBus, Display view) {
	    this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	  }
	  
	  public void bind() {
	    display.getAddButton().addClickHandler(new ClickHandler() {   
	      public void onClick(ClickEvent event) {
	    	  String name = display.getName();
	    	  String genre = display.getGenre();
	    	  String gJahr = display.getGJahr();
	    	  int jahr = Integer.parseInt(gJahr);  
	    	  String site = display.getSite();
	    	  final Band band = new Band(name, genre, jahr, site, "user");
	    	  
	    	  rpcService.onAddBand(band, new AsyncCallback<Void>() {

				public void onFailure(Throwable caught) {
					caught.printStackTrace();
					
				}

				public void onSuccess(Void result) {
					
					eventBus.fireEvent(new AddBandEvent(band));
				}
	    		     		 	    		  
	    	  });
	    	
	      }
	    });

	  }
	
	
		public void go(final HasWidgets container) {
		    bind();
		    container.clear();
		    container.add(display.asWidget());
		  }
	
	}
	
