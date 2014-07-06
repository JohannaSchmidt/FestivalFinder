package com.de.client.presenter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.de.client.BandServiceAsync;
import com.de.client.FestivalServiceAsync;
import com.de.client.UserServiceAsync;
import com.de.client.event.FestivalClickedEvent;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.de.shared.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;


public class MainPresenter  implements Presenter {  

	  ArrayList<Festival> festivalList;
	  ArrayList<Band> bandList;
	  ArrayList<String> userbands;
	  User current;
	
	  public interface Display {
	    Cell getFestivalTableCell(ClickEvent event);
	    HasClickHandlers getFestivalTable();
	    HasClickHandlers getDeleteButton();
	    void setFestivalData(ArrayList<Festival> festivals);
	    void setBandData(ArrayList<Band> bands);
	    List<Integer> getSelectedRows();
	    Widget asWidget();
	  }
	  
	  private final FestivalServiceAsync rpcService;
	  private final HandlerManager eventBus;
	  private final Display display;
	  String token;
	  BandServiceAsync bandService;
	  UserServiceAsync userService;
	  
	  public MainPresenter(FestivalServiceAsync rpcService, HandlerManager eventBus, Display view) {
	    this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	  }
	  
	  public MainPresenter(FestivalServiceAsync rpcService, BandServiceAsync bandService, HandlerManager eventBus, Display view, String token) {
		    this.rpcService = rpcService;
		    this.bandService = bandService;
		    this.eventBus = eventBus;
		    this.display = view;
		    this.token = token;
	  }
	  
	  public MainPresenter(FestivalServiceAsync rpcService, BandServiceAsync bandService, UserServiceAsync userService, HandlerManager eventBus, Display view, String token, User current) {
		    this.rpcService = rpcService;
		    this.bandService = bandService;
		    this.eventBus = eventBus;
		    this.display = view;
		    this.token = token;
		    this.userService = userService;
		    this.current = current;
	  }
	  
	  public void bind() {
		  getAllFestivals();
		  
		  display.getFestivalTable().addClickHandler(new ClickHandler() {
		        public void onClick(ClickEvent event) {
		          Cell selected = display.getFestivalTableCell(event);

		          if (selected != null) {
		        	 if(token == "Festival"){
		        		 eventBus.fireEvent(new FestivalClickedEvent(festivalList.get(selected.getRowIndex())));
		        	 } else if(token == "Band"){
		        		 eventBus.fireEvent(new FestivalClickedEvent(bandList.get(selected.getRowIndex())));
		        	 } else if(token == "PFestival"){
		        		 eventBus.fireEvent(new FestivalClickedEvent(festivalList.get(selected.getRowIndex())));
		        	 }
		        }

		      }
	  
		  });
		  
		    display.getDeleteButton().addClickHandler(new ClickHandler() {   
		        public void onClick(ClickEvent event) {
		          deleteSelectedBands();
		        }
		    });
	  }
	  
	  protected void getAllFestivals() {
		  
		  if(token == "Festival"){
			rpcService.getAllFestivals(new AsyncCallback<ArrayList<Festival>>() {
		
				public void onSuccess(ArrayList<Festival> result) {
					festivalList = result;
					display.setFestivalData(festivalList);
					
				}
		
				public void onFailure(Throwable caught) {
			        Window.alert("Error fetching festival details");
					
				}
			
			});
		  } else if (token == "Band"){
				bandService.getAllBands(new AsyncCallback<ArrayList<Band>>() {
					
					public void onSuccess(ArrayList<Band> result) {
						bandList = result;
						display.setBandData(bandList);
						
					}
			
					public void onFailure(Throwable caught) {
				        Window.alert("Error fetching festival details");
						
					}
				
				});
				
		  } else if( token == "BandList"){

			  userService.getBandList(current, new AsyncCallback<ArrayList<String>>(){
				public void onFailure(Throwable caught) {
					caught.printStackTrace();
					
				}

				public void onSuccess(ArrayList<String> result) {
					bandList = new ArrayList<Band>();
					userbands = result;
					for(String bands : userbands){
						bandService.getBands(bands, new AsyncCallback<ArrayList<Band>>(){

							public void onSuccess(ArrayList<Band> result) {
								bandList.add(result.get(0));
								display.setBandData(bandList);
								
							}	

							public void onFailure(Throwable caught) {
								caught.printStackTrace();
								
							}							
							
						});
					}

					
				}				  
					  
					  
			  });
		  } else if (token == "PFestival") {
			  festivalList = new ArrayList<Festival>();
			  userService.getBandList(current, new AsyncCallback<ArrayList<String>>(){

				public void onFailure(Throwable caught) {
					caught.printStackTrace();
					
				}

				public void onSuccess(ArrayList<String> result) {
					userbands = result;
					for(String band : userbands){
						rpcService.getFestivalByBandName(band, new AsyncCallback<ArrayList<Festival>>(){

							public void onFailure(Throwable caught) {
								caught.printStackTrace();
								
							}

							public void onSuccess(ArrayList<Festival> result) {
								if(!result.isEmpty()){
									for(Festival festival : result){
										rpcService.getFestivalById(festival.getfestId(), new AsyncCallback<ArrayList<Festival>>(){
									
											public void onFailure(Throwable caught) {
												caught.printStackTrace();
											
											}

											public void onSuccess(ArrayList<Festival> result) {
												Festival festival = result.get(0);
													festivalList.add(festival);
													//festivalList = sortList(festivalList);
													display.setFestivalData(festivalList);
											}
																						
										});
									}
								} else {
									display.setFestivalData(festivalList);
								}
							}
							
						});

					}
				} 				  
			  });
			  
			  
		 
		  
		  }
		  
		  
	  
	
}
  

  private void deleteSelectedBands() {
List<Integer> selectedRows = display.getSelectedRows();
    
    for (int i = 0; i < selectedRows.size(); ++i) {
    	Band band = bandList.get(selectedRows.get(i));
    	bandList.remove(band);
    	userService.removeFromBandList(current, band, new AsyncCallback<Void>(){

		public void onFailure(Throwable caught) {
			caught.printStackTrace();
			
		}

		public void onSuccess(Void result) {
			display.setBandData(bandList);
			
		}
    	
    });
        
      }
    
  }


public void go(final HasWidgets container) {
	   bind();
	   container.clear();
	   container.add(display.asWidget());
}

}

