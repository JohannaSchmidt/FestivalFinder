package com.de.shared;

import java.io.Serializable;


public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String pw;
	private String email;
	private int loggedIn;
	private String role;
	
	public User(){
		
	}
	
    public User(String name, String pw, String email, int loggedIn, String role) {
    	this.name = name;
    	this.pw = pw;
    	this.email = email;
    	this.loggedIn = loggedIn;
    	this.role = role;
    }
    
    public String getName(){
    	return name;
    }
    
    public String getPw() {
    	return pw;
    }
    
    public String getMail() {
    	return email;
    }
    
    public int getEingeloggt() {
    	return loggedIn;
    }
    
    public String getRolle() {
    	return role;
    }
}
