package com.de.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class FestivalClickedEvent extends GwtEvent<FestivalClickedEventHandler> {
	  public static Type<FestivalClickedEventHandler> TYPE = new Type<FestivalClickedEventHandler>();
	  
	  @Override
	  public Type<FestivalClickedEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(FestivalClickedEventHandler handler) {
	    handler.onFestivalClicked(this);
	  }
	}
