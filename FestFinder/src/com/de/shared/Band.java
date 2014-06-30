package com.de.shared;

import java.io.Serializable;

public class Band implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bName;
	private String genre;
	private int gJahr;
	private String website;
	private String nutzer;
	
	
	public Band(){
		
	}
	
	public Band(String bName, String genre, int gJahr, String website,
			String nutzer) {
		this.bName = bName;
		this.genre = genre;
		this.gJahr = gJahr;
		this.website = website;
		this.nutzer = nutzer;
	}
	
	
	public String getName(){
		return bName;
	}
	
	public String getGenre(){
		return genre;
	}
	
	public int getJahr(){
		return gJahr;
	}
	
	public String getWebsite(){
		return website;
	}
	
	public String getNutzer(){
		return nutzer;
	}
	
	public String toString(){
		return (" " + bName);
	}

	public boolean equals(Band other){
		if(this == other){
			return true;
		}
		return false;
	}
	
}
