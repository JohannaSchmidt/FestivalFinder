package com.de.client.event;



import com.google.gwt.event.shared.GwtEvent;

public class FestivalClickedEvent extends GwtEvent<FestivalClickedEventHandler> {
	  public static Type<FestivalClickedEventHandler> TYPE = new Type<FestivalClickedEventHandler>();
	  private final Object current;
	  
    public FestivalClickedEvent(Object current) {
			    this.current = current;
		}

			  
	public Object getCurrent() { 
	    	return current; 
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
