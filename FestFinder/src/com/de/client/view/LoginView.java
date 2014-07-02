package com.de.client.view;

import com.de.client.presenter.LoginPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginView extends Composite implements LoginPresenter.Display{
	
	Label lblName;
	Label lblPw;
	
	TextBox name; 
	TextBox pwd;

	Button login;
	Button reg;
		
	public LoginView(){
		VerticalPanel vPanel = new VerticalPanel();
		initWidget(vPanel);
		vPanel.setStyleName("einloggen");
		vPanel.setSpacing(3);
		
		lblName = new Label("Username:");
		vPanel.add(lblName);
		
		name = 	new TextBox();
		name.setName("Nutzername");
		vPanel.add(name);
		
		lblPw = new Label("Passwort:");
		vPanel.add(lblPw);
		
		pwd = new PasswordTextBox();
		vPanel.add(pwd);
		
		login = new Button("Einloggen");
		login.setStyleName("buttons");
		vPanel.add(login);
		
		reg = new Button("Registrieren");
		vPanel.add(reg);
		

		

			
	}
	
	 public HasClickHandlers getLoginButton() {
		  return  login;
	}
		
	 public HasClickHandlers getRegButton() {
		  return  reg;
	}
	 
	 public TextBox getName() {
		  return  name;
	}
		
	 public TextBox getPwd() {
		  return  pwd;
	}
	
	public Widget asWidget(){
		return this;		
	}

		
}
