package com.de.client.event;

import com.de.shared.Band;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class AddBandToBandListEvent extends GwtEvent<AddBandToBandListEventHandler>{
	  public static Type<AddBandToBandListEventHandler> TYPE = new Type<AddBandToBandListEventHandler>();
	  Band current;
	  
	  public AddBandToBandListEvent(Band current){
		  this.current = current;
	  }
	  
	  public Band getBand(){
		  return current;
	  }

		  
		  @Override
		  public Type<AddBandToBandListEventHandler> getAssociatedType() {
		    return TYPE;
		  }

		  @Override
		  protected void dispatch(AddBandToBandListEventHandler handler) {
		    handler.onBandToBandListAdded(this);
		  }

	}
