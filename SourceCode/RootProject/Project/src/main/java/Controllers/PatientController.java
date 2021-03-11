/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.DBConnection.patientList;
import Models.Patient;
import Views.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 *
 * @author Tyler
 */
public class PatientController implements Initializable {
    
    private Patient model;
    private PatientView view;
    
    DBConnection dbCalls = new DBConnection();
    
    //NULL MEANS NO QUERY
    static ArrayList<Patient> patientList = DBConnection.parsePatients(null);
    
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
    
    ObservableList<Patient> patients = FXCollections.observableList(dbCalls.getPatients());
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        socialSecurity.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        physicianName.setCellValueFactory(new PropertyValueFactory<>("Physician"));
        physicianNumber.setCellValueFactory(new PropertyValueFactory<>("PhysicianNumber"));
        
        pData.setItems(patients);
    }
    
   
    
    /*
    public PatientController(Patient model, PatientView view)
    {
        this.model = model;
        this.view = view;
    }
    
    
    public void setName(String  name)
    {
        model.setName(name);
    }
    public String getName()
    {
        return model.getName();
    }
    
    public void setPatientAge(int age)
    {
        model.setAge(age);
    }
    public int getPatientAge()
    {
        return model.getAge();
    }
    
    public void setPatientPhoneNumber(String phoneNumber)
    {
        model.setPhone(phoneNumber);
    }
    public String getPatientPhoneNumber()
    {
        return model.getPhone();
    }
    
    public void updateView()
    {
        view.printPatientDetaisl(model.getName(), model.getAge(), model.getPhone());
        //ptable.s
    }
    */
    
    
}
