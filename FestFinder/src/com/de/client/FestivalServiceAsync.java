package com.de.client;

import java.util.ArrayList;

import com.de.shared.Festival;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface FestivalServiceAsync {
	
  void getAllFestivals(AsyncCallback<ArrayList<Festival>> callback)
      throws IllegalArgumentException;
  
  void getFestivals(String name, AsyncCallback<ArrayList<Festival>> callback)
  	  throws IllegalArgumentException;
  
  void onAddFestival(Festival festival, AsyncCallback<Void> callback);
  
  void onDeleteFestival(String name, AsyncCallback<Void> callback);
}