package com.de.client.presenter;



import java.sql.Date;
import java.util.ArrayList;

import com.de.client.BandService;
import com.de.client.BandServiceAsync;
import com.de.client.FestivalServiceAsync;
import com.de.client.UserServiceAsync;
import com.de.client.event.AddBandEvent;
import com.de.client.event.RegisterEvent;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.de.shared.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MainCreateBandPresenter implements Presenter{

	  public interface Display {
		    HasClickHandlers getAddBandButton();
		    HasClickHandlers getAddFestivalButton();
		    HasClickHandlers getDeleteBandButton();
		    HasClickHandlers getDeleteFestivalButton();
		    void setOracleFestival(ArrayList<Festival> words);
		    void setOracleBand(ArrayList<Band> words);
		    String getToken();
		    String getDeleteName();
		    String getName();
		    String getGenre();
		    String getGJahr();
		    String getSite();
		    
		    String getfId();
		    String getfName();
		    String getfsDatum();
		    String getfeDatum();
		    String getOrt();
		    String getfWeb();
		    Widget asWidget();
		  }
	  
	  private final BandServiceAsync rpcService;  
	  private final FestivalServiceAsync festivalService;
	  private final HandlerManager eventBus;
	  private final Display display;
	  public  Festival festival;
	  public Band band;
	  private final User current;
	  
	  public MainCreateBandPresenter(BandServiceAsync rpcService, FestivalServiceAsync festivalService, HandlerManager eventBus, Display view, User current) {
		    this.rpcService = rpcService;
		    this.festivalService = festivalService;
		    this.eventBus = eventBus;
		    this.display = view;
		    this.current = current;
		  }
	  
	  public void bind() {
	    display.getAddBandButton().addClickHandler(new ClickHandler() {   
	      public void onClick(ClickEvent event) {
		    	  String name = display.getName();
		    	  String genre = display.getGenre();
		    	  String gJahr = display.getGJahr();
		    	  int jahr = Integer.parseInt(gJahr);  
		    	  String site = display.getSite();
		    	  band = new Band(name, genre, jahr, site, current.getName());
		    	  
		    	  rpcService.onAddBand(band, new AsyncCallback<Void>() {
	
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						
					}
	
					public void onSuccess(Void result) {					
						eventBus.fireEvent(new AddBandEvent(band));
					}
		    		     		 	    		  
		    	  });

	      }
	    });
	    display.getAddFestivalButton().addClickHandler(new ClickHandler() {   
		      public void onClick(ClickEvent event) {
			        // First, we validate the input.
			        String festId = display.getfId();
			        String fName = display.getfName();
			        String stDatum  = display.getfsDatum();
			        String enDatum = display.getfeDatum();
			        String format = "yyyy-MM-dd";
		            DateTimeFormat sdfToDate = DateTimeFormat.getFormat(format);
					Date sDatum = new Date(sdfToDate.parse(stDatum).getTime());
					Date eDatum = new Date(sdfToDate.parse(enDatum).getTime());
		
		            String fOrt = display.getOrt();
		            String fWebsite = display.getfWeb();

			        festival = new Festival(festId, fName, sDatum, eDatum, fOrt, fWebsite);
			        // Then, we send the input to the server.
			        festivalService.onAddFestival(festival, new AsyncCallback<Void>() {
				          public void onSuccess(Void result) {
								eventBus.fireEvent(new AddBandEvent(festival));
				          }
				          public void onFailure(Throwable caught) {
				        	  caught.printStackTrace();
				          }
			        });
		      }
	    });
	    
	    display.getDeleteBandButton().addClickHandler(new ClickHandler() {   
		      public void onClick(ClickEvent event) {
		    	 String bandName =  display.getDeleteName();
		    	 rpcService.onDeleteBand(bandName, new AsyncCallback<Void>(){

					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						
					}

					public void onSuccess(Void result) {
						Window.alert("Band wurde geloescht");
						
					}
		    		 
		    	 });
		      }
	    });
	    
	    display.getDeleteFestivalButton().addClickHandler(new ClickHandler() {   
		      public void onClick(ClickEvent event) {
		    	 String festivalName =  display.getDeleteName();
		    	festivalService.onDeleteFestival(festivalName, new AsyncCallback<Void>(){;

					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						
					}

					public void onSuccess(Void result) {
						Window.alert("Festival wurde geloescht");
						
					}
		      });
		    		 
	    	}
	    });
	    
	    if(display.getToken().equals("DeleteFestival")){
	    	festivalService.getAllFestivals(new AsyncCallback<ArrayList<Festival>>(){

				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				public void onSuccess(ArrayList<Festival> result) {
					display.setOracleFestival(result);
					
				}
	    		
	    	});
	    	
	    } else if(display.getToken().equals("DeleteBand")){
	    	rpcService.getAllBands(new AsyncCallback<ArrayList<Band>>(){

				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				public void onSuccess(ArrayList<Band> result) {
					display.setOracleBand(result);
					
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
	
