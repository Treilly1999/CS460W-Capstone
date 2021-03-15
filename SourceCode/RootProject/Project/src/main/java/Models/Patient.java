/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleLongProperty;

/**
 *
 * @author Tyler
 */
public class Patient {
    
    
    public SimpleStringProperty  name;
    public SimpleIntegerProperty  age;     
    public SimpleStringProperty phoneNumber;
    public SimpleIntegerProperty  id; 
    
    //RESEARCH WHAT INSURANCE INFO WILL BE NEEDED
    private SimpleStringProperty provider; 
    
    ArrayList<String> progressReports;
    
    private SimpleIntegerProperty ssn;
    
    private SimpleStringProperty physicianName;
    private SimpleStringProperty physicianNumber;
    
    private SimpleStringProperty medicalHistory;
    private SimpleStringProperty symptoms;
    
    private SimpleStringProperty dischargeInstructions;
    private SimpleStringProperty assignedDoctor;
    private Boolean admitted;
     
    
    public Patient()
    {
        
    }
    
    public Patient(String name, Integer age, String phoneNumber, Integer ssn, String physicianName, 
            String physicianNumber)
    {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.ssn = new SimpleIntegerProperty(ssn);
        this.physicianName = new SimpleStringProperty(physicianName);
        this.physicianNumber = new SimpleStringProperty(physicianNumber);
        
    }
    
    public String getName() { return name.get(); }    
    public void setName(String name) { this.name = new SimpleStringProperty(name); }
    public final SimpleStringProperty nameProperty() { return name; }
    
    
    public int getAge() { return age.get(); }    
    public void setAge(int age) { this.age = new SimpleIntegerProperty(age); }
    public final SimpleIntegerProperty ageProperty() { return age; }
   
    
    public String getPhone() { return phoneNumber.get(); }
    public void setPhone(String number) { this.phoneNumber = new SimpleStringProperty(number); }
    public final SimpleStringProperty phoneNumberProperty() { return phoneNumber; }
    
    public int getSSN(){ return ssn.get(); }
    
    public void setSSN(int social) { this.ssn = new SimpleIntegerProperty(social); }
    
    public String getPhysician() { return physicianName.get(); }
    public void setPhysician(String physician){ this.physicianName = new SimpleStringProperty(physician); } 
    
    public String getPhysicianNumber() { return physicianNumber.get(); }
    public void setPhysicianNumber(String physNum) { this.physicianNumber = new SimpleStringProperty(physNum); }
    
    public String getMedicalHistory() { return medicalHistory.get(); }
    public void setMedicalHistory(SimpleStringProperty medHist) { this.medicalHistory = medHist; }
    
    public String getSymptoms() { return symptoms.get(); }
    public void setSymptoms(SimpleStringProperty symptoms) { this.symptoms = symptoms; }

    public String getProvider() { return provider.get(); }
    public void setProvider(SimpleStringProperty provider) { this.provider = provider; }
    
    public String getDischargeInstructions() { return dischargeInstructions.get(); }
    public void setDischargeInstructions(SimpleStringProperty dischargeInstructions) { this.dischargeInstructions = dischargeInstructions; }
    
    public String getAssignedDoctor() { return assignedDoctor.get(); }
    public void setAssignedDoctor(SimpleStringProperty assignedDoctor) { this.assignedDoctor = assignedDoctor; }
    
    public Boolean getAdmitted() { return admitted; }
    public void setAdmitted(Boolean isAdmitted) { this.admitted = admitted; }
    
    public ArrayList<String> getProgressReports() { return progressReports; }
    public void setProgressReports(String report) { progressReports.add(report); }
    
    public String toString()
    {
        return "Name: " + getName();
    }
    
}
