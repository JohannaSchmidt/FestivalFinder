package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.UserServiceAsync;
import com.de.client.event.RegisterEvent;
import com.de.shared.Festival;
import com.de.shared.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MainRegisterPresenter implements Presenter {  

	  ArrayList<Festival> festivalList;
	
	  public interface Display {
	    HasClickHandlers getRegButton();
	    String getName();
	    String getMail();
	    String getPwd();
	    String getPwd2();
	    Widget asWidget();
	  }
	  
	  private final UserServiceAsync rpcService;
	  private final HandlerManager eventBus;
	  private final Display display;
	  
	  public MainRegisterPresenter(UserServiceAsync rpcService, HandlerManager eventBus, Display view) {
	    this.rpcService = rpcService;
	    this.eventBus = eventBus;
	    this.display = view;
	  }
	  
	  public void bind() {
	    display.getRegButton().addClickHandler(new ClickHandler() {   
	      public void onClick(ClickEvent event) {
	    	  String name = display.getName();
	    	  String email = display.getMail();
	    	  String pwd = display.getPwd();
	    	  String pwd2 = display.getPwd2();
	    	  if(pwd.equals(pwd2)){
		    	  final User user = new User(name, pwd, email, 1, "user");
		    	  
		    	  rpcService.onAddUser(user, new AsyncCallback<Void>(){
	
					public void onFailure(Throwable caught) {
						Window.alert("User anlegen fehlgeschlagen");
						
					}
	
					public void onSuccess(Void result) {
						rpcService.setCurrentUser(user, new AsyncCallback<Void>(){
	
							public void onFailure(Throwable caught) {
								Window.alert("Einloggen fehlgeschlagen");
								
							}
	
							public void onSuccess(Void result) {
						        eventBus.fireEvent(new RegisterEvent());
							}
							
						});
				        
						
					}
		    		     		 	    		  
		    	  });
		    	
		      } else {
		    	  Window.alert("Die Passwoerter stimmen nicht ueberein");
		      }
	      }
	    });

	  }
	
	
		public void go(final HasWidgets container) {
		    bind();
		    container.clear();
		    container.add(display.asWidget());
		  }
	
	}
	
