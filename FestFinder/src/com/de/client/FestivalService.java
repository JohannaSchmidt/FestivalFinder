package com.de.client;

import java.util.ArrayList;

import com.de.shared.Festival;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("festivals")
public interface FestivalService extends RemoteService {
	
  ArrayList<Festival> getAllFestivals() throws Exception;
  
  ArrayList<Festival> getFestivals(String name) throws Exception;
  
  void onAddFestival(Festival festival) throws Exception;
  
  void onDeleteFestival (String name) throws Exception;
  
}
