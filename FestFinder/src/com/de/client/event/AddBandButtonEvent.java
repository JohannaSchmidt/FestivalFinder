package com.de.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.de.client.event.AddBandButtonEventHandler;

public class AddBandButtonEvent extends GwtEvent<AddBandButtonEventHandler> {
	public static Type<AddBandButtonEventHandler> TYPE = new Type<AddBandButtonEventHandler>();
	public String token;

	public AddBandButtonEvent(String token){
		this.token = token;
	}
	
	public String getToken(){
		return token;
	}
	@Override
	public Type<AddBandButtonEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(AddBandButtonEventHandler handler) {
		handler.onAddBandClicked(this);
		
	}
	

}