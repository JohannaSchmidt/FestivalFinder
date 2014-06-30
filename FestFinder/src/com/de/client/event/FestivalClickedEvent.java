package com.de.client.event;


import com.de.shared.Festival;
import com.google.gwt.event.shared.GwtEvent;

public class FestivalClickedEvent extends GwtEvent<FestivalClickedEventHandler> {
	  public static Type<FestivalClickedEventHandler> TYPE = new Type<FestivalClickedEventHandler>();
	  private final Festival currentFestival;
	  
    public FestivalClickedEvent(Festival festival) {
			    this.currentFestival = festival;
		}
			  
	public Festival getCurrentFestival() { 
	    	return currentFestival; 
	 }
	  
	  
	  @Override
	  public Type<FestivalClickedEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(FestivalClickedEventHandler handler) {
	    handler.onFestivalClicked(this);
	  }
	}
