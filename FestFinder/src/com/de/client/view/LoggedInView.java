package com.de.client.view;

import com.de.client.presenter.LoggedInPresenter;
import com.de.shared.User;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoggedInView extends Composite implements LoggedInPresenter.Display{

	Button logout;
	Label userName;
	Label email;
	Button bandList;
		
	public LoggedInView(User user){
		VerticalPanel vPanel = new VerticalPanel();
		initWidget(vPanel);
		vPanel.setStyleName("einloggen");
		vPanel.setSpacing(3);

		userName = new Label("Hallo " + user.getName());
		vPanel.add(userName);
		
		email = new Label(user.getMail());
		vPanel.add(email);
		
		logout = new Button("ausloggen");
		vPanel.add(logout);
		
		
		bandList = new Button("persönliche Bandliste");
		vPanel.add(bandList);
		
			
	}
	
	 public HasClickHandlers getLogoutButton() {
		  return  logout;
	}
	 		
	 public HasClickHandlers getBandListButton() {
		  return  bandList;
	}

	
	public Widget asWidget(){
		return this;		
	}


		
}
