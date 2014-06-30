package com.de.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RegisterEvent  extends GwtEvent<RegisterEventHandler> {
	  public static Type<RegisterEventHandler> TYPE = new Type<RegisterEventHandler>();
	  
	  @Override
	  public Type<RegisterEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(RegisterEventHandler handler) {
	    handler.onRegister(this);
	  }
	}
