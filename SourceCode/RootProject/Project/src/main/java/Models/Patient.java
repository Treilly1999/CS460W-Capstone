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
    
    private SimpleStringProperty  name;
    private SimpleIntegerProperty  age;     
    private SimpleStringProperty phoneNumber;
    private SimpleIntegerProperty  id; 
    
    private SimpleStringProperty provider; 
    
    private ArrayList<ProgressReport> progressReports;
    
    private SimpleIntegerProperty ssn;
    
    private SimpleStringProperty physicianName;
    private SimpleStringProperty physicianNumber;
    
    private ArrayList<MedicalHistory> medicalHistory;
    
    //TODO: DOES SYMPTOMS NEED TO BE AN ARRAYLIST?
    private ArrayList<Symptom> symptoms;
    
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
    
    public Patient(Integer id, String name, Integer age, String phoneNumber, Integer ssn, String physicianName, 
            String physicianNumber, String provider, ArrayList<Symptom> symptoms, String assignedDoctor, Boolean admitted, 
            ArrayList<MedicalHistory> medicalHistory, ArrayList<ProgressReport> progressReports, String dischargeInstructions)
    {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.ssn = new SimpleIntegerProperty(ssn);
        this.physicianName = new SimpleStringProperty(physicianName);
        this.physicianNumber = new SimpleStringProperty(physicianNumber);
        this.id = new SimpleIntegerProperty(id);
        this.assignedDoctor = new SimpleStringProperty(assignedDoctor);
        this.provider = new SimpleStringProperty(provider);
        this.admitted = admitted;
        this.dischargeInstructions = new SimpleStringProperty(dischargeInstructions);
        this.medicalHistory = medicalHistory;
        this.progressReports = progressReports;
        this.symptoms = symptoms;
    }
    
    public int getID() { return id.get(); }
    public void setID(int id) { this.id = new SimpleIntegerProperty(id); }
    
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
    
    public ArrayList<MedicalHistory> getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(MedicalHistory medHist) { medicalHistory.add(medHist); }
    
    public ArrayList<Symptom> getSymptoms() { return symptoms; }
    public void setSymptoms(Symptom symptom) { this.symptoms.add(symptom); }

    public String getProvider() { return provider.get(); }
    public void setProvider(SimpleStringProperty provider) { this.provider = provider; }
    
    public String getDischargeInstructions() { return dischargeInstructions.get(); }
    public void setDischargeInstructions(SimpleStringProperty dischargeInstructions) { this.dischargeInstructions = dischargeInstructions; }
    
    public String getAssignedDoctor() { return assignedDoctor.get(); }
    public void setAssignedDoctor(SimpleStringProperty assignedDoctor) { this.assignedDoctor = assignedDoctor; }
    
    public String getAdmitted() 
    {
        String admittedString;
        
        if(admitted)
        {
            admittedString = "YES";
        }
        else
        {
            admittedString = "NO";
        }
        return admittedString ; 
    }
    
    public void setAdmitted(Boolean isAdmitted) { this.admitted = admitted; }
    
    public ArrayList<ProgressReport> getProgressReports() { return progressReports; }
    public void setProgressReports(ProgressReport report) { progressReports.add(report); }
    
    public String toString()
    {
        return "Name: " + getName() + ". Age: " + getAge() + ". Phone Number: " + getPhone() + ". Physician Name: " +
                getPhysician() + ". Physician Phone Number: " + getPhysicianNumber() + ". Insurance Provider: " + getProvider()
                + ". Admitted? " + getAdmitted() + ". Assigned Doctor: " + getAssignedDoctor() +
                ". Medical History: " + getMedicalHistory().toString()
                + ". Symptoms: " + getSymptoms().toString() + ". Progress Reports: " + getProgressReports().toString()
                + ". Discharge Instructions: " + getDischargeInstructions();
    }
    
}
