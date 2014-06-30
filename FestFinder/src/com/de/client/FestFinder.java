package com.de.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FestFinder implements EntryPoint {
	
	public void onModuleLoad(){
	    FestivalServiceAsync rpcService = GWT.create(FestivalService.class);
	    HandlerManager eventBus = new HandlerManager(null);
	    AppController appViewer = new AppController(rpcService, eventBus);
	    appViewer.go(RootPanel.get());
	}
}

//	/**
//	 * Create a remote service proxy to talk to the server-side Greeting service.
//	 */
//
//	private static final BandServiceAsync bandService = GWT.create(BandService.class);
//	private static final FestivalServiceAsync festivalService = GWT.create(FestivalService.class);
//	private static final UserServiceAsync userService = GWT.create(UserService.class);
//	
//	private ArrayList<Band> bandListe;
//
//	/**
//	 * This is the entry point method.
//	 */
//	public void onModuleLoad() {
//
//		//User
//		final TextBox userNameField = new TextBox();
//		final TextBox pwField = new TextBox();
//		final TextBox emailField = new TextBox();
//		
//		final Button userRegLoginButton = new Button("Registrieren/ Login");
//		
//		userNameField.setText("Username");
//		pwField.setText("Passwort");
//		emailField.setText("yourmail@test.de");
//		
//		userRegLoginButton.addStyleName("sendButton");
//		
//	    RootPanel.get("userNameFieldContainer").add(userNameField);
//	    RootPanel.get("pwFieldContainer").add(pwField);
//	    RootPanel.get("emailFieldContainer").add(emailField);
//	    RootPanel.get("userRegLoginContainer").add(userRegLoginButton);
//
//	    // Focus the cursor on the name field when the app loads
//	    userNameField.setFocus(true);
//	    userNameField.selectAll();
//	    
//	    
//		// Bands
//	    final TextBox nameField = new TextBox();
//	    final TextBox genreField = new TextBox();
//	    final TextBox jahrField = new TextBox();
//	    final TextBox websiteField = new TextBox();
//	    final TextBox deleteField = new TextBox();
//
//	    final Button bandAnlegenButton = new Button("Band anlegen");
//	    final Button bandsAnzeigenButton = new Button("Alle Bands anzeigen");
//	    final Button bandsLoeschenButton = new Button("Band Loeschen");
//
//
//	    nameField.setText("Bandname");
//	    genreField.setText("Genre");
//	    jahrField.setText("Gruendungsjahr");
//	    websiteField.setText("Website");
//	    deleteField.setText("Bandname zum Loeschen eingeben!");
//    
//	    final Label errorLabel = new Label();
//
//	    bandAnlegenButton.addStyleName("sendButton");
//	    bandsAnzeigenButton.addStyleName("sendButton");
//	    bandsLoeschenButton.addStyleName("sendButton");
//
//	    RootPanel.get("nameFieldContainer").add(nameField);
//	    RootPanel.get("genreFieldContainer").add(genreField);
//	    RootPanel.get("jahrFieldContainer").add(jahrField);
//	    RootPanel.get("websiteFieldContainer").add(websiteField);
//	    RootPanel.get("bandAnlegenContainer").add(bandAnlegenButton);
//	    RootPanel.get("bandAnzeigenContainer").add(bandsAnzeigenButton);
//	    RootPanel.get("bandLoeschenFieldContainer").add(deleteField);
//	    RootPanel.get("bandLoeschenContainer").add(bandsLoeschenButton);
//	    RootPanel.get("errorLabelContainer").add(errorLabel);
//	
//
//	
//	    
//	    
//	    // Festivals	    
//	    final TextBox festID = new TextBox();
//	    final TextBox festName = new TextBox();
//	    final TextBox sDatum = new TextBox();
//	    final TextBox eDatum = new TextBox();
//	    final TextBox ort = new TextBox();
//	    final TextBox website = new TextBox();
//	    final TextBox fdelete = new TextBox();
//
//	    final Button festivalAnlegenButton = new Button("Festival anlegen");
//	    final Button festivalAnzeigenButton = new Button("Alle Festivals anzeigen");
//	    final Button festivalLoeschenButton = new Button("Festival Loeschen");
//
//	    festID.setText("FestivalID");
//	    festName.setText("Festivalname");
//	    sDatum.setText("Startdatum: yyyy-mm-dd");
//	    eDatum.setText("Enddatum: yyyy-mm-dd");
//	    ort.setText("Ort");
//	    website.setText("Website");
//	    fdelete.setText("Festivalname zum Loeschen eingeben!");
//    
//	    // We can add style names to widgets
//	    festivalAnlegenButton.addStyleName("sendButton");
//	    festivalAnzeigenButton.addStyleName("sendButton");
//	    festivalLoeschenButton.addStyleName("sendButton");
//
//    // Add the nameField and sendButton to the RootPanel
//    // Use RootPanel.get() to get the entire body element
//	    RootPanel.get("fIDFieldContainer").add(festID);
//	    RootPanel.get("fNameFieldContainer").add(festName);
//	    RootPanel.get("startDatumFieldContainer").add(sDatum);
//	    RootPanel.get("endDatumFieldContainer").add(eDatum);
//	    RootPanel.get("ortFieldContainer").add(ort);
//	    RootPanel.get("fwebsiteFieldContainer").add(website);
//	    RootPanel.get("festivalAnlegenContainer").add(festivalAnlegenButton);
//	    RootPanel.get("festivalAnzeigenContainer").add(festivalAnzeigenButton);
//	    RootPanel.get("festivalLoeschenFieldContainer").add(fdelete);
//	    RootPanel.get("festivalLoeschenContainer").add(festivalLoeschenButton);
//	    	    
//	    
//	    // Create the popup dialog box
//	    final DialogBox dialogBox = new DialogBox();
//	    dialogBox.setText("Bandliste");
//	    dialogBox.setAnimationEnabled(true);
//	    final Button closeButton = new Button("Close");
//	    // We can set the id of a widget by accessing its Element
//	    closeButton.getElement().setId("closeButton");
//	    final Label bandLabel = new Label();
//
//	    VerticalPanel dialogVPanel = new VerticalPanel();
//	    dialogVPanel.addStyleName("dialogVPanel");
//	    dialogVPanel.add(new HTML("<br><b>Bandliste: </b>"));
//	    dialogVPanel.add(bandLabel);
//	    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
//	    dialogVPanel.add(closeButton);
//	    dialogBox.setWidget(dialogVPanel);
//	
//	    // Add a handler to close the DialogBox
//	    closeButton.addClickHandler(new ClickHandler() {
//	      public void onClick(ClickEvent event) {
//	        dialogBox.hide();
//	        bandsAnzeigenButton.setEnabled(true);
//	        festivalAnzeigenButton.setEnabled(true);
//	        bandsAnzeigenButton.setFocus(true);
//	      }
//	    });
//
//    // Create a handler for the sendButton and nameField
//    class MyHandler implements ClickHandler, KeyUpHandler {
//	      /**
//	       * Fired when the user clicks on the sendButton.
//	       */
//	      public void onClick(ClickEvent event) {
//	        bandAnlegen();
//	      }
//	
//	      /**
//	       * Fired when the user types in the nameField.
//	       */
//	      public void onKeyUp(KeyUpEvent event) {
//	        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//	          bandAnlegen();
//	        }
//	      }
//	      private void bandAnlegen() {
//	    	Band band;
//	        // First, we validate the input.
//	        String bName = nameField.getText();
//	        String genre = genreField.getText();
//	        String jahr  = jahrField.getText();
//	        int gJahr = Integer.parseInt(jahr);
//	        String website = websiteField.getText();
//	        if (!FieldVerifier.isValidName(bName)) {
//	          errorLabel.setText("Please enter at least four characters");
//	          return;
//	        }
//	        band = new Band(bName, genre, gJahr, website, "Jojo");
//	        // Then, we send the input to the server.
//	        bandService.onAddBand(band, new AsyncCallback<Void>() {
//	          public void onSuccess(Void result) {
//	        	  Window.alert("Band angelegt!");
//	          }
//	          public void onFailure(Throwable caught) {
//	        	  caught.printStackTrace();
//	            System.out.println("Das hat nicht geklappt");   
//	          }
//	
//	
//	
//	        });
//	      }
//    }
//    
//    class AnzeigenHandler implements ClickHandler {
//	      /**
//	       * Fired when the user clicks on the sendButton.
//	       */
//	      public void onClick(ClickEvent event) {
//	        bandsAnzeigen();
//	      }
//	
//	      /**
//	       * Send the name from the nameField to the server and wait for a response.
//	       */
//	      private void bandsAnzeigen() {
//
//	        bandsAnzeigenButton.setEnabled(false);
//	        bandService.getAllBands(new AsyncCallback<ArrayList<Band>>() {
//		      public void onSuccess(ArrayList<Band> result) {
//			     dialogBox.setText("Das hat geklappt");
//			     bandLabel.setText("Bands: ");
//			     for(int i = 0; i< result.size(); i++){
//			    	 bandLabel.setText(bandLabel.getText() +", "+ result.get(i).toString());
//			     }
//		         dialogBox.center();
//		         closeButton.setFocus(true);
//			  }
//	          public void onFailure(Throwable caught) {
//	        	caught.printStackTrace();
//	            dialogBox.setText("Das hat nicht geklappt");
//	            dialogBox.center();
//	            closeButton.setFocus(true);
//	          }
//
//	        });
//	      }
//
//	    }
//    
//    class DeleteHandler implements ClickHandler {
//	      /**
//	       * Fired when the user clicks on the sendButton.
//	       */
//	      public void onClick(ClickEvent event) {
//	        bandLoeschen();
//	      }
//	
//	      /**
//	       * Send the name from the nameField to the server and wait for a response.
//	       */
//	      private void bandLoeschen() {
//	    	  
//	    	  final String bName = deleteField.getText();
//	    	  bandService.onDeleteBand(bName, new AsyncCallback<Void>() {
//		      public void onSuccess(Void result) {
//			     Window.alert("Band: " + bName + "geloescht");
//			  }
//	          public void onFailure(Throwable caught) {
//	        	caught.printStackTrace();
//	            
//	          }
//
//	        });
//	      }
//
//	    }
//    
//    class AddFestivalHandler implements ClickHandler, KeyUpHandler {
//	      /**
//	       * Fired when the user clicks on the sendButton.
//	       */
//	      public void onClick(ClickEvent event) {
//	        festivalAnlegen();
//	      }
//	
//	      /**
//	       * Fired when the user types in the nameField.
//	       */
//	      public void onKeyUp(KeyUpEvent event) {
//	        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//	          festivalAnlegen();
//	        }
//	      }
//	      private void festivalAnlegen() {
//	    	Festival festival;
//	        // First, we validate the input.
//	        String festId = festID.getText();
//	        String fName = festName.getText();
//	        String stDatum  = sDatum.getText();
//	        String enDatum = eDatum.getText();
//	        String format = "yyyy-MM-dd";
//            DateTimeFormat sdfToDate = DateTimeFormat.getFormat(format);
//			Date sDatum = new Date(sdfToDate.parse(stDatum).getTime());
//			Date eDatum = new Date(sdfToDate.parse(enDatum).getTime());
//
//            String fOrt = ort.getText();
//            String fWebsite = website.getText();
//	        if (!FieldVerifier.isValidName(fName)) {
//	          errorLabel.setText("Please enter at least four characters");
//	          return;
//	        }
//	        festival = new Festival(festId, fName, sDatum, eDatum, fOrt, fWebsite);
//	        // Then, we send the input to the server.
//	        festivalService.onAddFestival(festival, new AsyncCallback<Void>() {
//	          public void onSuccess(Void result) {
//	        	  Window.alert("Festival angelegt!");
//	          }
//	          public void onFailure(Throwable caught) {
//	        	  caught.printStackTrace();
//	            System.out.println("Das hat nicht geklappt");   
//	          }
//	
//	
//	
//	        });
//	      }
//  }
//  
//  class FestivalsAnzeigenHandler implements ClickHandler {
//	      /**
//	       * Fired when the user clicks on the sendButton.
//	       */
//	      public void onClick(ClickEvent event) {
//	        festivalsAnzeigen();
//	      }
//	
//	      /**
//	       * Send the name from the nameField to the server and wait for a response.
//	       */
//	      private void festivalsAnzeigen() {
//
//	        festivalAnzeigenButton.setEnabled(false);
//	        festivalService.getAllFestivals(new AsyncCallback<ArrayList<Festival>>() {
//		      public void onSuccess(ArrayList<Festival> result) {
//			     dialogBox.setText("Das hat geklappt");
//			     bandLabel.setText("Festivals: ");
//			     for(int i = 0; i< result.size(); i++){
//			    	 bandLabel.setText(bandLabel.getText() +", "+ result.get(i).toString());
//			     }
//		         dialogBox.center();
//		         closeButton.setFocus(true);
//			  }
//	          public void onFailure(Throwable caught) {
//	        	caught.printStackTrace();
//	            dialogBox.setText("Das hat nicht geklappt");
//	            dialogBox.center();
//	            closeButton.setFocus(true);
//	          }
//
//	        });
//	      }
//
//	    }
//  
//  class DeleteFestivalHandler implements ClickHandler {
//	      /**
//	       * Fired when the user clicks on the sendButton.
//	       */
//	      public void onClick(ClickEvent event) {
//	        festivalLoeschen();
//	      }
//	
//	      /**
//	       * Send the name from the nameField to the server and wait for a response.
//	       */
//	      private void festivalLoeschen() {
//	    	  
//	    	  final String fName = fdelete.getText();
//	    	  festivalService.onDeleteFestival(fName, new AsyncCallback<Void>() {
//		      public void onSuccess(Void result) {
//			     Window.alert("Festival: " + fName + "geloescht");
//			  }
//	          public void onFailure(Throwable caught) {
//	        	caught.printStackTrace();
//	            
//	          }
//
//	        });
//	      }
//
//	    }
//  
//  
//  //Registrieren bzw. Anmelden eines Users
//  class RegistrierenLoginHandler implements ClickHandler, KeyUpHandler {
//      /**
//       * Fired when the user clicks on the sendButton.
//       */
//      public void onClick(ClickEvent event) {
//        userAnlegen();
//      }
//
//      /**
//       * Fired when the user types in the nameField.
//       */
//      public void onKeyUp(KeyUpEvent event) {
//        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//          userAnlegen();
//        }
//      }
//      private void userAnlegen() {
//    	User user;
//        // First, we validate the input.
//        String name = userNameField.getText();
//        String pw = pwField.getText();
//        String email  = emailField.getText();
//        if (!FieldVerifier.isValidName(name)) {
//          errorLabel.setText("Please enter at least four characters!");
//          return;
//        }
//        user = new User(name, pw, email, '1', "User");
//        // Then, we send the input to the server.
//        userService.onAddUser(user, new AsyncCallback<Void>() {
//          public void onSuccess(Void result) {
//        	  Window.alert("User angelegt/ eingeloggt!");
//          }
//          public void onFailure(Throwable caught) {
//        	  caught.printStackTrace();
//        	  
//        	Window.alert("Das hat nicht geklappt!");
//            System.out.println("Das hat nicht geklappt");   
//          }
//
//
//
//        });
//      }
//}
//  
//  		// User
//  		RegistrierenLoginHandler reglogin = new RegistrierenLoginHandler();
//  		userRegLoginButton.addClickHandler(reglogin);
//  		websiteField.addKeyUpHandler(reglogin);
//  
//
//  		// Bands
//	    // Add a handler to send the name to the server
//	    MyHandler handler = new MyHandler();
//	    bandAnlegenButton.addClickHandler(handler);
//	    websiteField.addKeyUpHandler(handler);
//	    
//	    // Add a handler to send the name to the server
//	    AnzeigenHandler anzeige = new AnzeigenHandler();
//	    bandsAnzeigenButton.addClickHandler(anzeige);
//	    
//	    // Add a handler to send the name to the server
//	    DeleteHandler delete = new DeleteHandler();
//	    bandsLoeschenButton.addClickHandler(delete);
//	    
//	    
//	    // Festivals
//	    // Add a handler to send the name to the server
//	    AddFestivalHandler addHandler = new AddFestivalHandler();
//	    festivalAnlegenButton.addClickHandler(addHandler);
//	    website.addKeyUpHandler(handler);
//	    
//	    // Add a handler to send the name to the server
//	    FestivalsAnzeigenHandler festivalAnzeigen = new FestivalsAnzeigenHandler();
//	    festivalAnzeigenButton.addClickHandler(festivalAnzeigen);
//	    
//	    // Add a handler to send the name to the server
//	    DeleteFestivalHandler deleteFestival = new DeleteFestivalHandler();
//	    festivalLoeschenButton.addClickHandler(deleteFestival);
//	  }
//}
