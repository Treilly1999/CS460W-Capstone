package login;

import java.io.IOException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import Controllers.PatientController;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

/** Manages control flow for logins */
public class LoginManager {
  private Scene scene;

  public LoginManager(Scene scene) {
    this.scene = scene;
  }

  /**
   * Callback method invoked to notify that a user has been authenticated.
   * Will show the main application screen.
   */ 
  public void authenticated(String sessionID) {
    //showMainView(sessionID);
    showPatientList(sessionID);
  }

  /**
   * Callback method invoked to notify that a user has logged out of the main application.
   * Will show the login application screen.
   */ 
  
  public void logout() {
    showLoginScreen();
  }
  
  public void showLoginScreen() {
    try {
      FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/login.fxml"));
    Parent root = loader.load();        
      scene.setRoot(root);
      LoginController controller = 
        loader.<LoginController>getController();
      controller.initManager(this);
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void showMainView(String sessionID) {
    try {

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/mainview.fxml"));
    Parent root = loader.load();        
    scene.setRoot(root);
    MainViewController controller = 
    loader.<MainViewController>getController();
    controller.initSessionID(this, sessionID);
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private void showPatientList(String sessionID) {
    try {

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/FXMLDocument.fxml"));
    Parent root = loader.load();        
    scene.setRoot(root);
    
    PatientController controller = 
    loader.<PatientController>getController();
    //controller.init();
    
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
  
}