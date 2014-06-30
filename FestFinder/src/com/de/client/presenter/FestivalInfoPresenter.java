package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.BandServiceAsync;
import com.de.client.FestivalServiceAsync;
import com.de.client.event.ZurueckEvent;
import com.de.client.presenter.MenuPresenter.Display;
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

public class FestivalInfoPresenter implements Presenter {  

	ArrayList<String> bandList;

  public interface Display {
    HasClickHandlers getZurueck();
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
		getAllFestivalsBands();
	    container.clear();
	    container.add(display.asWidget());
	  }

}

