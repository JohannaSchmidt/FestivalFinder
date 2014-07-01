package com.de.shared;

import java.io.Serializable;
import java.sql.Date;


public class Festival implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fId;
	private String fName;
	private Date sDatum;
	private Date eDatum;
	private String ort;
	private String website;

	public Festival(){
		
	}
	
	public Festival(String fId, String fName, Date sDatum, Date eDatum,
			String ort, String website) {
		
		this.fId = fId;
		this.fName = fName;
		this.sDatum = sDatum;
		this.eDatum = eDatum;
		this.ort = ort;
		this.website = website;

	}
	
	public String getfestId(){
		return fId;
	}
	
	public String getName(){
		return fName;
	}
	
	public Date getsDatum(){
		return sDatum;
	}
	
	public Date geteDatum(){
		return eDatum;
	}
	
	public String getOrt(){
		return ort;
	}
	
	public String getWebsite(){
		return website;
	}
	
	
	public String toString(){
		return(fName);
	}
	
}
