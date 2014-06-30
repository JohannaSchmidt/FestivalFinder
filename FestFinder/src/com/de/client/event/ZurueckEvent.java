package com.de.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class ZurueckEvent extends GwtEvent<ZurueckEventHandler> {

	public static Type<ZurueckEventHandler> TYPE = new Type<ZurueckEventHandler>();
	  
	  @Override
	  public Type<ZurueckEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(ZurueckEventHandler handler) {
	    handler.onZurueckClicked(this);
	  }
	}
