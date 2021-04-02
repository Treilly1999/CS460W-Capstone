/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Patient;
import Models.Staff_Model;
import Models.Symptoms;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Tyler
 */
public class RegisterController 
{
    //TODO: implement back button.
    @FXML private Button logoutButton;
    @FXML private Button submitForm;
    @FXML private Button backButton;
    @FXML private Button addMedHis;
    
    @FXML private Label registerName;
    
    
    //To indicate successful/error message to user.
    @FXML private Label serverMessage;
    
    //Variables for creating patient
    @FXML private TextField patientName;
    @FXML private TextField patientAge;
    @FXML private TextField patientPhone;
    //@FXML private TextField patientID;
    @FXML private TextField patientProvider;
    @FXML private TextField patientSSN;
    @FXML private TextField patientPhysician;
    @FXML private TextField patientPhysicianNum;
    @FXML private TextField symptoms;
    
    Patient patient;
    
    DBConnection db = new DBConnection();
    LoginManager loginManager;
    
    //TODO: Add Textfields for medical history, progress reports
    public void initRegisterForm(final LoginManager loginManager, Staff_Model user)
    {   
        this.loginManager = loginManager;        
        
        
      
        
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                loginManager.logout();
          }
        });   
    }
    
    /*
    Author: Tyler
    Description: Dynamically add another form for medical history when applicable
    */
    public void addMedicalHistoryForm()
    {
        
    }
    
    private void showRegisterSecondary(Staff_Model user, Scene scene) {
    try {
        
        //TODO: Create user profile view

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/RegisterSecondaryView.fxml"));
        Parent root = loader.load();        
        scene.setRoot(root);

        RegisterController controller = 
        loader.<RegisterController>getController();

        } catch (IOException ex) {
          Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }

  }
    
}
