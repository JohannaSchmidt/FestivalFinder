package com.de.client.event;

import java.util.ArrayList;

import com.de.shared.Band;
import com.google.gwt.event.shared.GwtEvent;

public class SearchedBandClickedEvent extends GwtEvent<SearchedBandClickedEventHandler>{
	  public static Type<SearchedBandClickedEventHandler> TYPE = new Type<SearchedBandClickedEventHandler>();
	  private final ArrayList <Band> bands;
	  
	 public SearchedBandClickedEvent(ArrayList<Band> bandlist) {
		    this.bands = bandlist;
	}
		  
    public ArrayList<Band> getSearchedBands() { 
    	return bands; 
    }
	  
	  @Override
	  public Type<SearchedBandClickedEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(SearchedBandClickedEventHandler handler) {
	    handler.onSearchBandClicked(this);
	  }
	

}
