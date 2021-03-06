package com.de.client.view;

import com.de.client.presenter.MainRegisterPresenter;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainRegisterView extends Composite  implements MainRegisterPresenter.Display{
	private TextBox name;
	private TextBox email;
	private PasswordTextBox pwd;
	private PasswordTextBox pwd2;
	private Button reg;
	private Label nameLabel;
	private Label emailLabel;
	private Label pwdLabel;
	private Label pwdLabel2;
		
	public MainRegisterView(){
		FlowPanel vPanel = new FlowPanel();
		initWidget(vPanel);
		vPanel.setStyleName("seite");
		
		name = new TextBox();
		nameLabel = new Label("Username:");
		nameLabel.setStyleName("text");
		email = new TextBox();
		emailLabel = new Label ("email:");
		emailLabel.setStyleName("text");
		pwd = new PasswordTextBox();
		pwdLabel = new Label ("Passwort:");
		pwdLabel.setStyleName("text");
		
		pwd2 = new PasswordTextBox();
		pwdLabel2 = new Label ("Passwort wiederholen:");
		pwdLabel2.setStyleName("text");

		reg = new Button("Registrieren");
		reg.setStyleName("clickbuttonseinloggen");
		reg.getElement().getStyle().setCursor(Cursor.POINTER);

			
		vPanel.add(nameLabel);
	    vPanel.add(name);
	    vPanel.add(emailLabel);	    
	    vPanel.add(email);
	    vPanel.add(pwdLabel);
	    vPanel.add(pwd);
	    vPanel.add(pwdLabel2);
	    vPanel.add(pwd2);
	    vPanel.add(reg);
		
			
	}
	
	 public HasClickHandlers getRegButton() {
		  return reg;
	}
		
	 public String getName() {
		 if(name.getText() !=null){
			 return name.getText();
		 }
		 return null;
	}
	 
	 public String getMail() {
		 if(email.getText() !=null){
			 return email.getText();
		 }
		 return null;
	}
	 
	 public String getPwd() {
		 if(pwd.getText() !=null){
			 return pwd.getText();
		 }
		 return null;
	}
	 
	 public String getPwd2() {
		 if(pwd2.getText() !=null){
			 return pwd2.getText();
		 }
		 return null;
	}
	
	public Widget asWidget(){
		return this;		
	}

	
		
}