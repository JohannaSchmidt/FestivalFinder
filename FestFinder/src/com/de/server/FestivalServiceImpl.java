package com.de.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.de.client.FestivalService;
import com.de.shared.Festival;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class FestivalServiceImpl extends RemoteServiceServlet implements
    FestivalService {

	private Connection connect = null;
	private Statement statement = null;


	
	private void connectDataBase() throws Exception {
		  Class.forName("com.mysql.jdbc.Driver");
		  connect = DriverManager.getConnection("jdbc:mysql://195.37.176.178:11336/Sieben", "Sieben", "Sieben");
		  statement = connect.createStatement();
		  
	  }
	
	private ArrayList<Festival> writeResultSet(ResultSet resultSet){
		ArrayList<Festival> festivals = new ArrayList<Festival>();
		  try {
			while (resultSet.next()) {
				   String fId = resultSet.getString("festivalId");
				   String fName = resultSet.getString("fName");
				   Date sDatum = resultSet.getDate("startDatum");
				   Date eDatum = resultSet.getDate("endDatum");
				   String ort = resultSet.getString("ort");
				   String website = resultSet.getString("website");
				   
				   Festival festival = new Festival(fId, fName, sDatum, eDatum, ort, website);
				   festivals.add(festival);

			   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return festivals;
	  }
	 
	public ArrayList<Festival> getAllFestivals() throws Exception {
		ArrayList<Festival> festivalListe = null;
		ResultSet resultSet = null;
		try {
			connectDataBase();
			resultSet = statement.executeQuery("Select * from Festivals");
			festivalListe = writeResultSet(resultSet);
		} catch (Exception ex){
			System.out.println("Keine Verbindung");
		}
//		System.out.println("Jooo");
		resultSet.close();
		statement.close();
		connect.close();
		
		return festivalListe;
		  
	}

	public void onAddFestival(Festival festival) throws Exception {
		ArrayList<Festival> festivals = getAllFestivals();
		for(Festival f : festivals){
			if(festival.getName().equals(f.getName())){
				try {
					connectDataBase();
					statement.executeUpdate("Update Bands Set festivalId= '" + festival.getfestId() + "', fName= '" + festival.getName() + "', startDatum='" + festival.getsDatum().toString()
					+ "', endDatum= '" + festival.geteDatum().toString() + "',ort ='" + festival.getOrt() + "',website = '" + festival.getWebsite() + "' where festivalId = '" + festival.getfestId() + "';");
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
			statement.executeUpdate("Insert into Festivals values('" + festival.getfestId() + "','" + festival.getName() + "','" + festival.getsDatum().toString()
					+ "','" + festival.geteDatum().toString() + "','" + festival.getOrt() + "','" + festival.getWebsite() + "');");
		} catch (Exception e) {
			System.out.println("Keine Verbindung");
			e.printStackTrace();
		}
		statement.close();
		connect.close();
		
	}

	public void onDeleteFestival(String name) throws SQLException {
		try {
			
		connectDataBase();
		statement.executeUpdate("DELETE from Festivals where fName = '" + name + "'");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		statement.close();
		connect.close();
		
	}
	
	public ArrayList<Festival> getFestivals(String name) throws Exception {
		ArrayList<Festival> festivalListe = null;
		ResultSet resultSet = null;
		try {
			connectDataBase();
			resultSet = statement.executeQuery("Select * from Festivals where fName = '" + name + "'");
			festivalListe = writeResultSet(resultSet);
		} catch (Exception ex){
			System.out.println("Keine Verbindung");
		}
		resultSet.close();
		statement.close();
		connect.close();
		
		return festivalListe;	  
	}
	
	public ArrayList<Festival> getFestivalById(String id) throws Exception {
		ArrayList<Festival> festivalListe = null;
		ResultSet resultSet = null;
		try {
			connectDataBase();
			resultSet = statement.executeQuery("Select * from Festivals where festivalId = '" + id + "'");
			festivalListe = writeResultSet(resultSet);
		} catch (Exception ex){
			System.out.println("Keine Verbindung");
		}
		resultSet.close();
		statement.close();
		connect.close();
		
		return festivalListe;	  
	}
	
	public ArrayList<Festival> getFestivalByBandName(String bName) throws Exception {
		ArrayList<String> festivalListe = null;
		ArrayList<Festival> festival = null;
		ResultSet resultSet = null;
		try {
			connectDataBase();
			resultSet = statement.executeQuery("Select festivalID from FestivalBandListe where bName = '" + bName + "'");
			festivalListe = writeFestivalBandResultSet(resultSet);
		} catch (Exception ex){
			System.out.println("Keine Verbindung");
		}
		resultSet.close();
		statement.close();
		connect.close();
		try {
			connectDataBase();
			resultSet = statement.executeQuery("Select * from Festivals where festivalId = '" + festivalListe.get(0) + "'");
			festival = writeResultSet(resultSet);
		} catch (Exception ex){
			System.out.println("Keine Verbindung");
		}
		resultSet.close();
		statement.close();
		connect.close();
		
		return festival;
		  
	}

	private ArrayList<String> writeFestivalBandResultSet(ResultSet resultSet) {
			ArrayList<String> festivals = new ArrayList<String>();
			  try {
				while (resultSet.next()) {
					   String bName = resultSet.getString("festivalID");

					   festivals.add(bName);

				   }
			} catch (Exception e) {
				e.printStackTrace();
			}
			  return festivals;
		  
	}
	
}