package com.de.server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.de.client.BandService;
import com.de.shared.Band;
import com.de.shared.Festival;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class BandServiceImpl extends RemoteServiceServlet implements
    BandService {

	private Connection connect = null;
	private Statement statement = null;


	
	private void connectDataBase() throws Exception {
		  Class.forName("com.mysql.jdbc.Driver");
		  connect = DriverManager.getConnection("jdbc:mysql://195.37.176.178:11336/Sieben", "Sieben", "Sieben");
		  statement = connect.createStatement();
		  
	  }
	
	private ArrayList<Band> writeResultSet(ResultSet resultSet){
		ArrayList<Band> bands = new ArrayList<Band>();
		  try {
			while (resultSet.next()) {
				   String bName = resultSet.getString("bName");
				   String genre = resultSet.getString("genre");
				   int gJahr = resultSet.getInt("gruendungsJahr");
				   String website = resultSet.getString("website");
				   String nutzer = resultSet.getString("benutzerName");
				   
				   Band band = new Band(bName, genre, gJahr, website, nutzer);
				   bands.add(band);
				   
				   System.out.println("Band: " + bName);
				   System.out.println("Genre: " + genre);
				   System.out.println("Gründungsjahr: " + gJahr);
				   System.out.println("Website: " + website);
				   System.out.println("Nutzer: " + nutzer);

			   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return bands;
	  }
	
	private ArrayList<String> writeFestivalBandResultSet(ResultSet resultSet){
		ArrayList<String> bands = new ArrayList<String>();
		  try {
			while (resultSet.next()) {
				   String bName = resultSet.getString("bName");

				   bands.add(bName);

			   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return bands;
	  }
	 
	public ArrayList<Band> getAllBands() throws Exception {
		ArrayList<Band> bandListe = null;
		ResultSet resultSet = null;
		try {
			connectDataBase();
			resultSet = statement.executeQuery("Select * from Bands");
			bandListe = writeResultSet(resultSet);
		} catch (Exception ex){
			System.out.println("Keine Verbindung");
		}
		System.out.println("Jooo");
		resultSet.close();
		statement.close();
		connect.close();
		
		return bandListe;
		  
	}

	public void onAddBand(Band band) throws Exception {
		ArrayList<Band> bands = getAllBands();
		for(Band b : bands){
			if(band.getName().equals(b.getName())){
				try {
					connectDataBase();
					statement.executeUpdate("Update Bands Set bName ='" + band.getName() + "', genre='" + band.getGenre() + "', gruendungsJahr=" + band.getJahr()
							+ ", website='" + band.getWebsite() + "', benutzerName='" + band.getNutzer() + "' where bNAme= '"+b.getName()+"';");
					System.out.println("Die Band: "+ b.getName() + " wird mit den Daten aktualisiert");
					statement.close();
					connect.close();
					return;
				} catch (Exception e) {
					System.out.println("Keine Verbindung");
				}
			}

		}
		try {
			connectDataBase();
			statement.executeUpdate("Insert into Bands values('" + band.getName() + "','" + band.getGenre() + "'," + band.getJahr()
					+ "','" + band.getWebsite() + "','" + band.getNutzer() + "');");
		} catch (Exception e) {
			System.out.println("Keine Verbindung");
		}
		statement.close();
		connect.close();
		
	}

	public void onDeleteBand(String name) throws SQLException {
		try {
			
		connectDataBase();
		statement.executeUpdate("DELETE from Bands where bName = '" + name + "'");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		statement.close();
		connect.close();
		
	}

	public ArrayList<String> getAllFestivalBands(Festival festival) throws Exception {
		ArrayList<String> bandListe = null;
		ResultSet resultSet = null;
		try {
			connectDataBase();
			resultSet = statement.executeQuery("Select bName from FestivalBandListe where festivalID = '" + festival.getfestId() + "'");
			bandListe = writeFestivalBandResultSet(resultSet);
		} catch (Exception ex){
			System.out.println("Keine Verbindung");
		}
		resultSet.close();
		statement.close();
		connect.close();
		
		return bandListe;
		  
	}

	public ArrayList<Band> getBands(String name) throws Exception {
		ArrayList<Band> bandListe = null;
		ResultSet resultSet = null;
		try {
			connectDataBase();
			resultSet = statement.executeQuery("Select * from Bands where bName = '" + name + "'");
			bandListe = writeResultSet(resultSet);
		} catch (Exception ex){
			System.out.println("Keine Verbindung");
		}
		resultSet.close();
		statement.close();
		connect.close();
		
		return bandListe;
	}

	public ArrayList<Band> getGenreBands(String genre) throws Exception {
		ArrayList<Band> bandListe = null;
		ResultSet resultSet = null;
		try {
			connectDataBase();
			resultSet = statement.executeQuery("Select * from Bands where genre = '" + genre + "'");
			bandListe = writeResultSet(resultSet);
		} catch (Exception ex){
			System.out.println("Keine Verbindung");
		}
		resultSet.close();
		statement.close();
		connect.close();
		
		return bandListe;
	}


	
}