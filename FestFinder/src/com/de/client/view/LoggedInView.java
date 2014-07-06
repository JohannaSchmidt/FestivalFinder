package com.de.client.view;

import com.de.client.presenter.LoggedInPresenter;
import com.de.shared.User;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoggedInView extends Composite implements LoggedInPresenter.Display{

	Button logout;
	Label userName;
	Label email;
	Button bandList;
		
	public LoggedInView(User user){
		FlowPanel vPanel = new FlowPanel();
		initWidget(vPanel);
		vPanel.setStyleName("einloggen");

		userName = new Label("Hallo " + user.getName());
		userName.setStyleName("hallo");
		vPanel.add(userName);
		
		email = new Label(user.getMail());
		email.setStyleName("email");
		vPanel.add(email);
		
		logout = new Button("Ausloggen");
		logout.setStyleName("clickbuttonseinloggen");
		vPanel.add(logout);
		
		
		bandList = new Button("persönliche Bandliste");			
		bandList.setStyleName("clickbuttonseinloggen");
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
