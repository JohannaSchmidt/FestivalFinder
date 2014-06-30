package com.de.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SearchBandClickedEvent extends GwtEvent<SearchBandClickedEventHandler>{
  public static Type<SearchBandClickedEventHandler> TYPE = new Type<SearchBandClickedEventHandler>();
	  
	  @Override
	  public Type<SearchBandClickedEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(SearchBandClickedEventHandler handler) {
	    handler.onSearchBandClicked(this);
	  }
}
