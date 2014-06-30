package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.UserServiceAsync;
import com.de.client.event.LogoutEvent;
import com.de.shared.Festival;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class LoggedInPresenter implements Presenter {  

	ArrayList<Festival> festivalList;

  public interface Display {
    HasClickHandlers getLogoutButton();
    Widget asWidget();
  }
  
  private final UserServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;
  
  public LoggedInPresenter(UserServiceAsync rpcService, HandlerManager eventBus, Display view) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = view;
  }
  
  public void bind() {
    display.getLogoutButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new LogoutEvent());
      }
    });   
  
  }

	public void go(final HasWidgets container) {
	    bind();
	    //container.clear();
	    container.add(display.asWidget());
	  }

}
