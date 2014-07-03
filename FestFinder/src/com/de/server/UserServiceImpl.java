package com.de.server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.de.client.UserService;
import com.de.shared.Band;
import com.de.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


@SuppressWarnings("serial")

public class UserServiceImpl extends RemoteServiceServlet implements 
    UserService {

	private User currentUser;
	private Connection connect = null;
	private Statement statement = null;
	
	private void connectDataBase() throws Exception {
		  Class.forName("com.mysql.jdbc.Driver");
		  connect = DriverManager.getConnection("jdbc:mysql://195.37.176.178:11336/Sieben", "Sieben", "Sieben");
		  statement = connect.createStatement();	  
	  }
	
	private ArrayList<User> writeResultSet(ResultSet resultSet){
		ArrayList<User> users = new ArrayList<User>();
		  try {
			while (resultSet.next()) {
				   String name = resultSet.getString("benutzerName");
				   String pw = resultSet.getString("passwort");
				   String email = resultSet.getString("eMail");
				   int loggedIn = resultSet.getInt("eingeloggt");
				   String role = resultSet.getString("rolle");
				   
				   User user = new User(name, pw, email, loggedIn, role);
				   users.add(user);

			   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return users;
	  }
	
	
	public ArrayList<User> getAllUsers() throws Exception {
		ArrayList<User> userListe = null;
		ResultSet resultSet = null;
		try {
			connectDataBase();
			resultSet = statement.executeQuery("Select * from Benutzer");
			userListe = writeResultSet(resultSet);
		} catch (Exception ex){
			System.out.println("Keine Verbindung");
		}
		System.out.println("Jooo");
		resultSet.close();
		statement.close();
		connect.close();
		
		return userListe;  
	}
	
	
	
	public void onAddUser(User user) throws Exception {
		ArrayList<User> users = getAllUsers();
		for(User u : users){
			if(user.getName().equals(u.getName()) && user.getPw().equals(u.getPw())){
				try {
					connectDataBase();
					System.out.println("Sie sind eingeloggt!");
					
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
			statement.executeUpdate("Insert into Benutzer values('" + user.getName() + "','" + user.getPw() + "','" + user.getMail() + "','" 
									+ user.getEingeloggt() + "','" + user.getRolle() + "');");
		} catch (Exception e) {
			System.out.println("Keine Verbindung");
		}
		statement.close();
		connect.close();
		
	}
	
	public ArrayList<String> getBandList(User user) throws Exception {
		ArrayList<String> userBands = null;
		ResultSet resultSet = null;
		try {
			connectDataBase();
			resultSet = statement.executeQuery("Select bName from BandListe where benutzerName = '"+ user.getName() + "'");
			userBands = writeBandResultSet(resultSet);
		} catch (Exception ex){
			System.out.println("Keine Verbindung");
		}
		System.out.println("Jooo");
		resultSet.close();
		statement.close();
		connect.close();
		
		return userBands;  
	}
	
	
	private ArrayList<String> writeBandResultSet(ResultSet resultSet) {
		ArrayList<String> bands = new ArrayList<String>();
		  try {
			while (resultSet.next()) {
				   String name = resultSet.getString("bName");
				   bands.add(name);

			   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return bands;
	  }

	public User getCurrentUser(){
		return currentUser;
	}
	
	public void setCurrentUser(User user){
		this.currentUser = user;
	}
	
	
	public void addToBandList(User user, Band band) throws Exception {
		try {
			connectDataBase();
			statement.executeUpdate("Insert into BandListe values('" + user.getName() + "','" + band.getName() + "')");
		} catch (Exception ex){
			ex.printStackTrace();
		}
		statement.close();
		connect.close();
		
	}
	
	public void removeFromBandList(User user, Band band) throws Exception {
		try {
			connectDataBase();
			statement.executeUpdate("Delete from BandListe where benutzerName ='" + user.getName() + "' and bName ='" + band.getName() + "'");
		} catch (Exception ex){
			ex.printStackTrace();
		}
		statement.close();
		connect.close();
		
	}

	public void onAddBand(Band band) throws Exception {
		System.out.println("Jooop");
		try {
			connectDataBase();
			statement.executeUpdate("Insert into Bands values('" + band.getName() + "','" + band.getGenre() + "','" + band.getJahr() + "','" + band.getWebsite() + "','" + "Marlena')");

		} catch (Exception ex){
			ex.printStackTrace();
		}

		statement.close();
		connect.close();
	}

}
