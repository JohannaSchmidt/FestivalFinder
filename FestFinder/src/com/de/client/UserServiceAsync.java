package com.de.client;

import java.util.ArrayList;

import com.de.shared.Band;
import com.de.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface UserServiceAsync {
	
  void getAllUsers(AsyncCallback<ArrayList<User>> callback)
      throws IllegalArgumentException;
  
  void getBandList(User user, AsyncCallback<ArrayList<String>> callback) throws IllegalArgumentException;
  
  	void onAddUser(User user, AsyncCallback<Void> callback);
  	
  	void addToBandList(User user, Band band, AsyncCallback<Void> callback);
  	
  	void getCurrentUser(AsyncCallback<User> callback);
  	
  	void setCurrentUser(User user, AsyncCallback<Void> callback);

	void onAddBand(Band band, AsyncCallback<Void> asyncCallback);

}