package com.de.client.event;

import java.util.ArrayList;

import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.event.shared.GwtEvent;

public class SearchedBandClickedEvent extends GwtEvent<SearchedBandClickedEventHandler>{
	  public static Type<SearchedBandClickedEventHandler> TYPE = new Type<SearchedBandClickedEventHandler>();
	  private ArrayList <Band> bands = null;
	  private ArrayList <Festival> festivals = null;
	  String token;
	  
	 public SearchedBandClickedEvent(ArrayList<Band> bandlist, String token) {
		 bands = null;
		 festivals = null;
		 this.token = token;
		 this.bands = bandlist;
	}
	 
	 public SearchedBandClickedEvent(String token, ArrayList<Festival> festivallist) {
		 bands = null;
		 festivals = null;
		 this.token = token;
		 this.festivals = festivallist;
	}
		  
  public ArrayList<Band> getSearchedBands() { 
  	return bands; 
  }
  
  public ArrayList<Festival> getSearchedFestivals() { 
  	return festivals; 
  }
  
  public String getToken(){
  	return token;
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
