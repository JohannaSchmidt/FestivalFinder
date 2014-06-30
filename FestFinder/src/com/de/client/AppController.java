package com.de.client;


import java.util.ArrayList;

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
import com.de.client.event.SearchBandClickedEvent;
import com.de.client.event.SearchBandClickedEventHandler;
import com.de.client.event.SearchedBandClickedEvent;
import com.de.client.event.SearchedBandClickedEventHandler;
import com.de.client.event.ZurueckEvent;
import com.de.client.event.ZurueckEventHandler;
import com.de.client.presenter.FestivalInfoPresenter;
import com.de.client.presenter.LoggedInPresenter;
import com.de.client.presenter.LoginPresenter;
import com.de.client.presenter.LogoPresenter;
import com.de.client.presenter.MainPresenter;
import com.de.client.presenter.MainRegisterPresenter;
import com.de.client.presenter.MainSearchedBandPresenter;
import com.de.client.presenter.MenuPresenter;
import com.de.client.presenter.Presenter;
import com.de.client.presenter.MenuSearchBandPresenter;
import com.de.client.view.FestivalInfoView;
import com.de.client.view.LoggedInView;
import com.de.client.view.LoginView;
import com.de.client.view.LogoView;
import com.de.client.view.MainRegisterView;
import com.de.client.view.MainSearchedBandView;
import com.de.client.view.MainTextView;
import com.de.client.view.MenuView;
import com.de.client.view.MenuSearchBandView;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.de.shared.User;
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
	  private Festival currentFestival = null;
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
    
    // Band gesucht nach Namen
    eventBus.addHandler(SearchedBandClickedEvent.TYPE,
        new SearchedBandClickedEventHandler() {

		public void onSearchBandClicked(SearchedBandClickedEvent event) {
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
    
    //Auf Suchen nach Band gedrückt
    eventBus.addHandler(SearchBandClickedEvent.TYPE,
            new SearchBandClickedEventHandler() {

    		public void onSearchBandClicked(SearchBandClickedEvent event) {
    			userService.getCurrentUser(new AsyncCallback<User>(){

    				public void onFailure(Throwable caught) {
    					Window.alert("Nicht möglich");
    					
    				}

    				public void onSuccess(User result) {
    					doChangeSearchBand();
    					
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
			doChangeFestivalView(event.getCurrentFestival());
			
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
  
  private void doChangeFestivalView(Festival clickedFestival) {
    History.newItem("festival", false);
	Presenter festival = new FestivalInfoPresenter(bandService, eventBus, new FestivalInfoView(clickedFestival), clickedFestival);
	festival.go(centerPanel);
    
  }
  
  private void doChangeRegister() {
	    History.newItem("register");
  }
  
  private void doChangeRegistered() {
	    History.newItem("registered");
}
  
  private void doChangeSearchedBand(ArrayList<Band> bands){
	  	History.newItem("searchedBand", false);
	    Presenter searchedBand = new MainSearchedBandPresenter(bandService, eventBus, new MainSearchedBandView(), bands);
	    searchedBand.go(centerPanel);
	  	
  }
  
  private void doChangeSearchBand(){
	    History.newItem("searchBand");
  }
 
  private void doChangeBack() {
    History.newItem("main");
  }
//  
//  private void doEditContactCancelled() {
//    History.newItem("list");
//  }
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
      //Presenter presenter = null;

      if (token.equals("home")) {
	          Presenter presenter = new MainPresenter(rpcService, eventBus, new MainTextView());
	          presenter.go(centerPanel);
	          Presenter menu = new MenuPresenter(rpcService, eventBus, new MenuView());
	          menu.go(westPanel);
	          Presenter logo = new LogoPresenter(rpcService, eventBus, new LogoView());
	          logo.go(northPanel);
	          Presenter login = new LoginPresenter(userService, eventBus, new LoginView());
	          login.go(northPanel);
      } else if (token.equals("register")) {
	          Presenter main = new MainRegisterPresenter(userService, eventBus, new MainRegisterView());
	          main.go(centerPanel);
      } else if (token.equals("registered")) {
		      Presenter main = new MainPresenter(rpcService, eventBus, new MainTextView());
		      main.go(centerPanel);
		      Presenter logo = new LogoPresenter(rpcService, eventBus, new LogoView());
		      logo.go(northPanel);
		      Presenter loggedIn = new LoggedInPresenter(userService, eventBus, new LoggedInView(currentUser));
		      loggedIn.go(northPanel);      
      } else if (token.equals("searchBand")){
    	  	Presenter searchBand = new MenuSearchBandPresenter(rpcService, bandService, eventBus, new MenuSearchBandView());
    	  	searchBand.go(westPanel);
      } else if (token.equals("main")){
          Presenter presenter = new MainPresenter(rpcService, eventBus, new MainTextView());
          presenter.go(centerPanel);
      } 
      
    }
  }

}

