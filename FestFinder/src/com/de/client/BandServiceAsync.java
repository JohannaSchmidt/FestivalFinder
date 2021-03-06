package com.de.client;

import java.util.ArrayList;

import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface BandServiceAsync {
	
  void getAllBands(AsyncCallback<ArrayList<Band>> callback)
      throws IllegalArgumentException;
  
  void getBands(String name,AsyncCallback<ArrayList<Band>> callback)
  	  throws IllegalArgumentException;
  
  
  void getAllFestivalBands(Festival festival, AsyncCallback<ArrayList<String>> callback)
	      throws IllegalArgumentException;
  
  void onAddBand(Band band, AsyncCallback<Void> callback);
  
  void onDeleteBand(String name, AsyncCallback<Void> callback);
  
  void onAddBandToFestival(String band, String festival, AsyncCallback<Void> callback);

  void getGenreBands(String name, AsyncCallback<ArrayList<Band>> asyncCallback);
}