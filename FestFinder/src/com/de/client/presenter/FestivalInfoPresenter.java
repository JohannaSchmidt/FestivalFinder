package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.BandServiceAsync;
import com.de.client.FestivalServiceAsync;
import com.de.client.event.SearchEvent;
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

	ArrayList<Band> bandList;

  public interface Display {
    HasClickHandlers getZurueck();
    Festival getFestival();
    void setData(ArrayList<Band> bands);
    Widget asWidget();
  }
  
  private final BandServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;
  
  public FestivalInfoPresenter(BandServiceAsync rpcService, HandlerManager eventBus, Display view) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = view;
  }
  
  public void bind() {
    display.getZurueck().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new SearchEvent());
      }
    });
  
  }
  
  protected void getAllFestivalsBands() {
	rpcService.getAllFestivalBands(display.getFestival() , new AsyncCallback<ArrayList<Band>>() {

		public void onSuccess(ArrayList<Band> result) {
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

