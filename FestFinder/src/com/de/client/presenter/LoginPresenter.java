package com.de.client.presenter;

import java.util.ArrayList;

import com.de.client.UserServiceAsync;
import com.de.client.event.LoginEvent;
import com.de.client.event.RegisterClickedEvent;
import com.de.shared.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginPresenter implements Presenter {  

	ArrayList<User> userList;

  public interface Display {
    HasClickHandlers getLoginButton();
    HasClickHandlers getRegButton();
    TextBox getName();
    TextBox getPwd();
    Widget asWidget();
  }
  
  private final UserServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;
  
  public LoginPresenter(UserServiceAsync rpcService, HandlerManager eventBus, Display view) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = view;
  }
  
  public void bind() {
    display.getLoginButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
    	  final String name = display.getName().getText();
    	  final String pwd = display.getPwd().getText();
    	  
    	  if(!name.isEmpty() && !pwd.isEmpty()){
    		  rpcService.getAllUsers(new AsyncCallback<ArrayList<User>>(){

				public void onFailure(Throwable caught) {
					Window.alert("Error loading Users");
					
				}

				public void onSuccess(ArrayList<User> result) {
					userList = result;
					
					for(int i = 0; i < userList.size(); i ++){
						if(userList.get(i).getName().equals(name)){
							if(userList.get(i).getPw().equals(pwd)){
								rpcService.setCurrentUser(userList.get(i), new AsyncCallback<Void>(){

									public void onFailure(Throwable caught) {
										Window.alert("Error logging in");
										
									}

									public void onSuccess(Void result) {
								        eventBus.fireEvent(new LoginEvent());
									}
									
								});
							} else {
								Window.alert("Wrong password or Username");
						} 
						}					
					}
					
				}
    			  
    		  });  		  
    		  
    	  }
    	  

      }
    });

    display.getRegButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
          eventBus.fireEvent(new RegisterClickedEvent());
      }
    });
    
  
  }

	public void go(final HasWidgets container) {
	    bind();
	   // container.clear();
	    container.add(display.asWidget());
	  }

}
