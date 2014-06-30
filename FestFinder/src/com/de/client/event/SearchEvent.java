package com.de.client.event;

import java.util.ArrayList;

import com.de.shared.Band;
import com.google.gwt.event.shared.GwtEvent;

public class SearchEvent extends GwtEvent<SearchEventHandler> {

	public static Type<SearchEventHandler> TYPE = new Type<SearchEventHandler>();	
    private final String token;
	  
	  public SearchEvent(String token) {
			    this.token = token;
	  }
			  
	  public String getToken() { 
	  	return token; 
	  }
	  
	  @Override
	  public Type<SearchEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(SearchEventHandler handler) {
	     handler.onSearchClicked(this);
	  }
	}

