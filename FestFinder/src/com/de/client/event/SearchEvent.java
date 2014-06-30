package com.de.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SearchEvent extends GwtEvent<SearchEventHandler> {

	public static Type<SearchEventHandler> TYPE = new Type<SearchEventHandler>();
	  
	  @Override
	  public Type<SearchEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(SearchEventHandler handler) {
	    handler.onSearchFestivals(this);
	  }
	}

