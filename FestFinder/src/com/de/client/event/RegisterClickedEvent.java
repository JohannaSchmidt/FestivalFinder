package com.de.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RegisterClickedEvent extends GwtEvent<RegisterClickedEventHandler> {
	  public static Type<RegisterClickedEventHandler> TYPE = new Type<RegisterClickedEventHandler>();
	  
	  @Override
	  public Type<RegisterClickedEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(RegisterClickedEventHandler handler) {
	    handler.onRegisterClicked(this);
	  }
	}
