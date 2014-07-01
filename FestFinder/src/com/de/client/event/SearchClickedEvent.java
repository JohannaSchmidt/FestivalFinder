package com.de.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SearchClickedEvent extends GwtEvent<SearchClickedEventHandler>{
  public static Type<SearchClickedEventHandler> TYPE = new Type<SearchClickedEventHandler>();
  private final String token;
  
  public SearchClickedEvent(String token){
	  this.token = token;
  }
  
  public String getToken(){
	  return token;
  }
	  
	  @Override
	  public Type<SearchClickedEventHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(SearchClickedEventHandler handler) {
	    handler.onSearchClicked(this);
	  }
}
