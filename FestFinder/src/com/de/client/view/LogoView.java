package com.de.client.view;

import com.de.client.presenter.LogoPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class LogoView extends Composite implements LogoPresenter.Display{
	
	HorizontalPanel hPanel;
	Image img;
		
	public LogoView(){
		hPanel = new HorizontalPanel();
		initWidget(hPanel);
		hPanel.setStyleName("header");
//		hPanel.setBorderWidth(1);
//		hPanel.setSpacing(2);
//		hPanel.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
		
		img = new Image("images/header2.jpg");
			
		//hPanel.add(img);
			
	}
	
	 public HasClickHandlers getPanel() {
		  return  img;
	}
		  
	
	public Widget asWidget(){
		return this;		
	}
		
}