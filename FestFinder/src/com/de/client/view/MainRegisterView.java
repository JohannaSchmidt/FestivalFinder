package com.de.client.view;

import com.de.client.presenter.MainRegisterPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainRegisterView extends Composite  implements MainRegisterPresenter.Display{
	private TextBox name;
	private TextBox email;
	private TextBox pwd;
	private Button reg;

		
	public MainRegisterView(){
		VerticalPanel vPanel = new VerticalPanel();
		initWidget(vPanel);
		vPanel.setStyleName("seite");
		
		name = new TextBox();
		email = new TextBox();
		pwd = new TextBox();

		reg = new Button("Registrieren");
			

	    vPanel.add(name);
	    vPanel.add(email);
	    vPanel.add(pwd);
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
	
	public Widget asWidget(){
		return this;		
	}

	
		
}