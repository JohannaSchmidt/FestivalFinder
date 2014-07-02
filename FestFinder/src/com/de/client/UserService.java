package com.de.client;

import java.util.ArrayList;

import com.de.shared.Band;
import com.de.shared.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("users")
public interface UserService extends RemoteService {

  ArrayList<User> getAllUsers() throws Exception;
  
  ArrayList<String> getBandList(User user) throws Exception;
  
  void onAddUser(User user) throws Exception;
  
  void addToBandList(User user, Band band) throws Exception;
  
  User getCurrentUser() throws Exception;
  
  void setCurrentUser(User user) throws Exception;

}
