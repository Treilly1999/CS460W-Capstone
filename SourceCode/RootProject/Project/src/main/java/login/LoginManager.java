package login;

import Controllers.NurseController;
import java.io.IOException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import Controllers.PatientController;
import Models.Staff_Model;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

/** Manages control flow for logins */
public class LoginManager {
  private Scene scene;

  public LoginManager(Scene scene) {
    this.scene = scene;
  }  
  
  public LoginManager() {}

  /**
   * Author: Tyler
   * Description: Callback method invoked to notify that a user has been authenticated.
   * Will show screen depending on who logs in
   * TODO: ADD SCREENS FOR DIFFERENT USERS
   */ 
    public void authenticated(Staff_Model user)
    {
        
        if(user.getUSER_ROLE() == Models.Staff_Model.USER_ROLE.NURSE)
        {
            showNurseView(user);
        }
        else if(user.getUSER_ROLE() == Models.Staff_Model.USER_ROLE.DOCTOR)
        {
            //Show doctor screen
        }
        else if(user.getUSER_ROLE() == Models.Staff_Model.USER_ROLE.BILLING)
        {
            //Show billing screen
        }
        else if(user.getUSER_ROLE() == Models.Staff_Model.USER_ROLE.REGISTER)
        {
            showPatientList();
        }
        //for testing only
        else
        {
            //UNAUTHORIZED USER
            System.out.println("USER UNAUTHORIZED");
            String errorMessage = "Unauthorized Access. Contact System Administrator.";
            logout();
            //logout();
            //showPatientList(sessionID);
        }
    
    }
    
  /**
   * Author: Tyler
   * Description Callback method invoked to notify that a user has logged out of the main application.
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

  /**
   * Author: Tyler
   * Description: Will show the nurse application screen.
   */ 
  private void showNurseView(Staff_Model user) {
    try {

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/nurseView.fxml"));
    Parent root = loader.load();        
    scene.setRoot(root);
    NurseController controller = 
    loader.<NurseController>getController();
    controller.initNurse(this, user);
    //controller.initSessionID(this, sessionID);
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
   /**
   * Author: Tyler
   * Description: Will show the patientList application screen.
   */ 
  private void showPatientList() {
    try {

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/FXMLDocument.fxml"));
    Parent root = loader.load();        
    scene.setRoot(root);
    
    PatientController controller = 
    loader.<PatientController>getController();
    controller.initPatientList(this);
    
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
  
}