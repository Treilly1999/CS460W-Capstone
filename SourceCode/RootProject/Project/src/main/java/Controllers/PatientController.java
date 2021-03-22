/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Patient;
import Models.Staff_Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import login.*;

/**
 *
 * @author Tyler
 */
public class PatientController implements Initializable {
        
    @FXML private Button logoutButton;
     
    DBConnection dbCalls = new DBConnection();
    
    //NULL MEANS NO QUERY
    static ArrayList<Patient> patientList = DBConnection.parsePatients(null);
    
    //These variables are called within the FXMLDocument
    @FXML
    private TableView<Patient> pData;
    @FXML
    private TableColumn<Patient, String> name;
    @FXML
    private TableColumn<Patient, Integer> age;
    @FXML
    private TableColumn<Patient, String> phoneNumber;
    @FXML
    private TableColumn<Patient, Integer> socialSecurity;
    @FXML
    private TableColumn<Patient, String> physicianName;
    @FXML
    private TableColumn<Patient, String> physicianNumber;
    
    
    //This list is filled with ArrayList<Patient>
    ObservableList<Patient> patients = FXCollections.observableList(dbCalls.getPatients());
    
   
    
    //Sets all the variables for the FXML document
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {    
        /*
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        socialSecurity.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        physicianName.setCellValueFactory(new PropertyValueFactory<>("Physician"));
        physicianNumber.setCellValueFactory(new PropertyValueFactory<>("PhysicianNumber"));

        pData.setItems(patients);        
        */
    }
    
    /*
    Author: Tyler
    Description: Initializes the patient list that uses the login manager  for logout purposes
    */
    public void initPatientList(final LoginManager loginManager)
    {
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        socialSecurity.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        physicianName.setCellValueFactory(new PropertyValueFactory<>("Physician"));
        physicianNumber.setCellValueFactory(new PropertyValueFactory<>("PhysicianNumber"));

        pData.setItems(patients);     
        
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loginManager.logout();
          }
        }); 
    }
    
    
    
}
