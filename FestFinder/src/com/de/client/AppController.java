package com.de.client;


import java.util.ArrayList;

import com.de.client.event.AddBandButtonEvent;
import com.de.client.event.AddBandButtonEventHandler;
import com.de.client.event.AddBandEvent;
import com.de.client.event.AddBandEventHandler;
import com.de.client.event.AddBandToBandListEvent;
import com.de.client.event.AddBandToBandListEventHandler;
import com.de.client.event.BandListEvent;
import com.de.client.event.BandListEventHandler;
import com.de.client.event.FestivalClickedEvent;
import com.de.client.event.FestivalClickedEventHandler;
import com.de.client.event.LoginEvent;
import com.de.client.event.LoginEventHandler;
import com.de.client.event.LogoutEvent;
import com.de.client.event.LogoutEventHandler;
import com.de.client.event.RegisterClickedEvent;
import com.de.client.event.RegisterClickedEventHandler;
import com.de.client.event.RegisterEvent;
import com.de.client.event.RegisterEventHandler;
import com.de.client.event.SearchClickedEvent;
import com.de.client.event.SearchClickedEventHandler;
import com.de.client.event.SearchEvent;
import com.de.client.event.SearchEventHandler;
import com.de.client.event.SearchedBandClickedEvent;
import com.de.client.event.SearchedBandClickedEventHandler;
import com.de.client.event.ZurueckEvent;
import com.de.client.event.ZurueckEventHandler;
import com.de.client.presenter.FestivalInfoPresenter;
import com.de.client.presenter.LoggedInPresenter;
import com.de.client.presenter.LoginPresenter;
import com.de.client.presenter.LogoPresenter;
import com.de.client.presenter.MainCreateBandPresenter;
import com.de.client.presenter.MainPresenter;
import com.de.client.presenter.MainRegisterPresenter;
import com.de.client.presenter.MainSearchedPresenter;
import com.de.client.presenter.MenuPresenter;
import com.de.client.presenter.MenuSearchPresenter;
import com.de.client.presenter.Presenter;
import com.de.client.view.FestivalInfoView;
import com.de.client.view.LoggedInView;
import com.de.client.view.LoginView;
import com.de.client.view.LogoView;
import com.de.client.view.MainCreateBandView;
import com.de.client.view.MainRegisterView;
import com.de.client.view.MainSearchedView;
import com.de.client.view.MainTextView;
import com.de.client.view.MenuSearchView;
import com.de.client.view.MenuView;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.de.shared.User;
import com.gargoylesoftware.htmlunit.javascript.host.Console;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class AppController implements Presenter, ValueChangeHandler<String> {
	
	  private User currentUser = null;
	  private final HandlerManager eventBus;
	  private final FestivalServiceAsync rpcService; 
	  private final FestivalServiceAsync festivalService = GWT.create(FestivalService.class);
	  private final BandServiceAsync bandService = GWT.create(BandService.class);
	  private final UserServiceAsync userService = GWT.create(UserService.class);
	  private HasWidgets container;
	  private final DockPanel dockLayoutPanel = new DockPanel();
	  private final SimplePanel centerPanel = new SimplePanel();
	  private final SimplePanel westPanel = new SimplePanel();
	  private final HorizontalPanel northPanel = new HorizontalPanel();
  
  public AppController(FestivalServiceAsync rpcService, HandlerManager eventBus) {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    bind();
  }
  
  private void bind() {
    History.addValueChangeHandler(this);

    //Auf zurueck geklickt
    eventBus.addHandler(ZurueckEvent.TYPE,
            new ZurueckEventHandler() {

    		public void onZurueckClicked(ZurueckEvent event) {
    			doChangeBack();
    			
    		}
         });
    
    // Registrieren im LoginView geklickt
    eventBus.addHandler(RegisterClickedEvent.TYPE,
        new RegisterClickedEventHandler() {

		public void onRegisterClicked(RegisterClickedEvent event) {
			doChangeRegister();
			
		}
     });
    
    
    // Band Hinzufügen im MainView geklickt (Wenn keine Band gefunden wurde)
    eventBus.addHandler(AddBandButtonEvent.TYPE,
        new AddBandButtonEventHandler() {

		public void onAddBandClicked(AddBandButtonEvent event) {
			doChangeAddBand();
		}
     });
    
    
    // Band zur Datenbank hinzufügen
    eventBus.addHandler(AddBandEvent.TYPE,
    	new AddBandEventHandler() {
    	
    	public void onBandAdded(AddBandEvent event) {
    		doChangeBandAdded();
    	}
    	
    });
    
    // Band gesucht nach Namen
    eventBus.addHandler(SearchedBandClickedEvent.TYPE,
        new SearchedBandClickedEventHandler() {

		public void onSearchBandClicked(SearchedBandClickedEvent event) {
			if (event.getSearchedFestivals() != null){
				doChangeSearchedFestival(event.getSearchedFestivals());
			}
			if(event.getSearchedBands() != null)
				doChangeSearchedBand(event.getSearchedBands());		
		}
     });
    
    // Login im LoginView geklickt
    eventBus.addHandler(LoginEvent.TYPE,
        new LoginEventHandler() {

		public void onLogin(LoginEvent event) {
			userService.getCurrentUser(new AsyncCallback<User>(){

				public void onFailure(Throwable caught) {
					Window.alert("Einloggen nicht möglich");
					
				}

				public void onSuccess(User result) {
					currentUser = result;
					doChangeRegistered();
					
				}
				
			});

		}
     });
        
    
    // registrieren im MainRegisterView geklickt
    eventBus.addHandler(RegisterEvent.TYPE,
            new RegisterEventHandler() {

			public void onRegister(RegisterEvent event) {
				userService.getCurrentUser(new AsyncCallback<User>(){

					public void onFailure(Throwable caught) {
						Window.alert("Einloggen nicht möglich");
						
					}

					public void onSuccess(User result) {
						currentUser = result;
						doChangeRegistered();
						
					}
					
				});

			}
         });
    
    // registrieren im MainRegisterView geklickt
    eventBus.addHandler(LogoutEvent.TYPE,
            new LogoutEventHandler() {

			public void onLogout(LogoutEvent event) {
				userService.setCurrentUser(null, new AsyncCallback<Void>(){

					public void onFailure(Throwable caught) {
						Window.alert("Ausloggen nicht möglich");
						
					}

					public void onSuccess(Void result) {
						currentUser = null;
						getHome();
						
					}
					
				});
				
			}
         });
    
    // Auf ein Festival in der Festivalliste geklickt
    eventBus.addHandler(FestivalClickedEvent.TYPE,
        new FestivalClickedEventHandler() {

		public void onFestivalClicked(FestivalClickedEvent event) {
				doChangeFestivalView(event.getCurrent());
			
		}

     });
    
    // Auf Suchen geklickt
    eventBus.addHandler(SearchEvent.TYPE,
        new SearchEventHandler() {

		public void onSearchClicked(SearchEvent searchEvent) {
			doChangeMain(searchEvent.getToken());
			
		}

     });
    
    eventBus.addHandler(BandListEvent.TYPE,
            new BandListEventHandler() {

			public void onBandListClicked(BandListEvent event) {
				doChangeMain("BandList");
				
			}

         });
    
    
    
    // Auf hinzufügen in der Bandview geklickt
    eventBus.addHandler(AddBandToBandListEvent.TYPE,
        new AddBandToBandListEventHandler() {

		public void onBandToBandListAdded(AddBandToBandListEvent event) {

			if(currentUser == null){
				Window.alert("Bitte logge dich zuerst ein!");
			}
			if(currentUser != null){
				userService.addToBandList(currentUser, event.getBand(), new AsyncCallback<Void>(){

					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					public void onSuccess(Void result) {
						Window.alert("Das hat geklappt");
						
					}					
				});
			}
			
		}

     });
  
  
    
    dockLayoutPanel.add(northPanel, DockPanel.NORTH);
    dockLayoutPanel.add(centerPanel, DockPanel.EAST);
    dockLayoutPanel.add(westPanel, DockPanel.WEST);
    dockLayoutPanel.setSpacing(3);
    dockLayoutPanel.setStyleName("frame");
    RootPanel.get().setStyleName("rootPanel");
    RootPanel.get().add(dockLayoutPanel);
 
  }
  
  private void getHome(){
	  History.newItem("home");
  }
  
  private void doChangeFestivalView(Object clickedObject) {
	  if(clickedObject instanceof Festival){
	    History.newItem("festival", false);
		Presenter festival = new FestivalInfoPresenter(bandService, eventBus, new FestivalInfoView(clickedObject), (Festival) clickedObject);
		festival.go(centerPanel);
	  } else if(clickedObject instanceof Band){
		   History.newItem("band", false);
		Presenter band = new FestivalInfoPresenter(bandService, eventBus, new FestivalInfoView(clickedObject));
			band.go(centerPanel);
	  }
    
  }
  
  private void doChangeRegister() {
	    History.newItem("register");
  }
  
  private void doChangeAddBand(){
	  History.newItem("addBand");
  }
  
  private void doChangeRegistered() {
	    History.newItem("registered");
}
  
  private void doChangeBandAdded(){
	  if(currentUser != null){
	  History.newItem("registered");
	  } else {
		  History.newItem("home");
	  }
  }
  
  private void doChangeSearchedBand(ArrayList<Band> bands){
	  	History.newItem("searchedBand", false);
	    Presenter searchedBand = new MainSearchedPresenter(bandService, eventBus, new MainSearchedView(), bands);
	    searchedBand.go(centerPanel);
	  	
  }
  
  private void doChangeSearchedFestival(ArrayList<Festival> festivals){
	  	History.newItem("searchedFestival", false);
	    Presenter searchedFestival = new MainSearchedPresenter(bandService, eventBus, festivals, new MainSearchedView());
	    searchedFestival.go(centerPanel);
	  	
}
 
  private void doChangeBack() {
    History.back();
    History.fireCurrentHistoryState();
  }
  
  private void doChangeMain(String token) {
	if(token == "BandList"){
	    History.newItem("bandList");
        Presenter presenter = new MainPresenter(rpcService, bandService, userService, eventBus, new MainTextView(), token, currentUser);
        presenter.go(centerPanel);
	} else {
		
		History.newItem("main");
        Presenter presenter = new MainPresenter(rpcService, bandService, eventBus, new MainTextView(), token);
        presenter.go(centerPanel);
    } 
  }
 
//  
//  private void doContactUpdated() {
//    History.newItem("list");
//  }
//  
  public void go(final HasWidgets container) {
    this.container = container;
    if ("".equals(History.getToken())) {
      History.newItem("home");
    }
    else {
      History.fireCurrentHistoryState();
    }
  }

  public void onValueChange(ValueChangeEvent<String> event) {
    String token = event.getValue();
    
    if (token != null) {

      if (token.equals("home")) {
	          Presenter presenter = new MainPresenter(rpcService, bandService, eventBus, new MainTextView(), "Festival");
	          presenter.go(centerPanel);
	          Presenter menu = new MenuPresenter(rpcService, bandService, eventBus, new MenuView());
	          menu.go(westPanel);
	          Presenter logo = new LogoPresenter(rpcService, eventBus, new LogoView());
	          logo.go(northPanel);
	          Presenter login = new LoginPresenter(userService, eventBus, new LoginView());
	          login.go(northPanel);
      } else if (token.equals("register")) {
	          Presenter main = new MainRegisterPresenter(userService, eventBus, new MainRegisterView());
	          main.go(centerPanel);
      } else if (token.equals("registered")) {
		      Presenter main = new MainPresenter(rpcService, bandService, userService, eventBus, new MainTextView(), "PFestival", currentUser);
		      main.go(centerPanel);
		      Presenter logo = new LogoPresenter(rpcService, eventBus, new LogoView());
		      logo.go(northPanel);
		      Presenter loggedIn = new LoggedInPresenter(userService, eventBus, new LoggedInView(currentUser));
		      loggedIn.go(northPanel);      
     } else if (token.equals("addBand")){
    	 	 Presenter addBand = new MainCreateBandPresenter(bandService, eventBus, new MainCreateBandView());
    	 	 addBand.go(centerPanel);
     } 
      
    }
  }

}

