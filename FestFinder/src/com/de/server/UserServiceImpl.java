package com.de.server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.de.client.UserService;
import com.de.shared.User;
import com.google.gwt.user.client.Window;
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
				   
				   System.out.println("Name: " + name);
				   System.out.println("Passwort: " + pw);
				   System.out.println("E-Mail: " + email);
				   System.out.println("Eingeloggt?:" + loggedIn);
				   System.out.println("Userstatus:" + role);

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
	
	public User getCurrentUser(){
		return currentUser;
	}
	
	public void setCurrentUser(User user){
		this.currentUser = user;
	}
}
