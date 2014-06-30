package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.FestivalServiceAsync;
import com.de.client.event.SearchEvent;
import com.de.shared.Festival;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LogoPresenter implements Presenter {  

	ArrayList<Festival> festivalList;

  public interface Display {
    HasClickHandlers getPanel();
    Widget asWidget();
  }
  
  private final FestivalServiceAsync rpcService;
  private final HandlerManager eventBus;
  final Display display;
  
  public LogoPresenter(FestivalServiceAsync rpcService, HandlerManager eventBus, Display view) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = view;
  }
  
  public void bind() {
    display.getPanel().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new SearchEvent());
      }
    });
  
  }

	public void go(final HasWidgets container) {
	    bind();
	    container.clear();
	    container.add(display.asWidget());
	  }



}
