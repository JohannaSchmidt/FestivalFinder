package com.de.client;

import java.util.ArrayList;

import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("bands")
public interface BandService extends RemoteService {
	
  ArrayList<Band> getAllBands() throws Exception;
  
  ArrayList<Band> getBands(String name) throws Exception;
  
  ArrayList<String> getAllFestivalBands(Festival festival) throws Exception;
  
  void onAddBand(Band band) throws Exception;
  
  void onDeleteBand (String name) throws Exception;
  
  ArrayList<Band> getGenreBands(String genre) throws Exception;
 
}
