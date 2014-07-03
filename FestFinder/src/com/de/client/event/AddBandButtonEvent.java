package com.de.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.de.client.event.AddBandButtonEventHandler;

public class AddBandButtonEvent extends GwtEvent<AddBandButtonEventHandler> {
	public static Type<AddBandButtonEventHandler> TYPE = new Type<AddBandButtonEventHandler>();

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
