package com.de.client.event;

import com.de.shared.Band;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class AddBandEvent extends GwtEvent<AddBandEventHandler> {
	  public static Type<AddBandEventHandler> TYPE = new Type<AddBandEventHandler>();
	  Object current;
	  
	  public AddBandEvent(Object current){
		  this.current = current;
	  }
	  
	  public Object getCurrent(){
		  return current;
	  }
	  
	  @Override
	  public Type<AddBandEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(AddBandEventHandler handler) {
	    handler.onBandAdded(this);
	  }
}