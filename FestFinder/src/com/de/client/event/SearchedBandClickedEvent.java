package com.de.client.event;

import java.util.ArrayList;

import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.event.shared.GwtEvent;

public class SearchedBandClickedEvent extends GwtEvent<SearchedBandClickedEventHandler>{
	  public static Type<SearchedBandClickedEventHandler> TYPE = new Type<SearchedBandClickedEventHandler>();
	  private ArrayList <Band> bands = null;
	  private ArrayList <Festival> festivals = null;
	  
	 public SearchedBandClickedEvent(ArrayList<Band> bandlist, String token) {
		    this.bands = bandlist;
	}
	 
	 public SearchedBandClickedEvent(ArrayList<Festival> festivallist) {
		    this.festivals = festivallist;
	}
		  
    public ArrayList<Band> getSearchedBands() { 
    	return bands; 
    }
    
    public ArrayList<Festival> getSearchedFestivals() { 
    	return festivals; 
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
