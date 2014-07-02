package com.de.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class BandListEvent extends GwtEvent<BandListEventHandler>{
	  public static Type<BandListEventHandler> TYPE = new Type<BandListEventHandler>();
	  String token;
	  
	  public BandListEvent(String token){
		  this.token = token;
	  }
	  
	  public String getToken(){
		  return token;
	  }

		  
		  @Override
		  public Type<BandListEventHandler> getAssociatedType() {
		    return TYPE;
		  }

		  @Override
		  protected void dispatch(BandListEventHandler handler) {
		    handler.onBandListClicked(this);
		  }

	}
