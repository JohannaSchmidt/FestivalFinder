package com.de.client.view;

import com.de.client.presenter.LoggedInPresenter;
import com.de.client.presenter.LoginPresenter;
import com.de.shared.User;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoggedInView extends Composite implements LoggedInPresenter.Display{

	Button logout;
	Label userName;
	Label email;
		
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
		
			
	}
	
	 public HasClickHandlers getLogoutButton() {
		  return  logout;
	}

	
	public Widget asWidget(){
		return this;		
	}

		
}
