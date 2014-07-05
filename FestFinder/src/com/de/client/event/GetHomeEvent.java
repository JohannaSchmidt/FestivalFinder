package com.de.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class GetHomeEvent extends GwtEvent<GetHomeEventHandler> {
	  public static Type<GetHomeEventHandler> TYPE = new Type<GetHomeEventHandler>();
	  
	  @Override
	  public Type<GetHomeEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(GetHomeEventHandler handler) {
	    handler.onHomeClicked(this);
	  }
	}

