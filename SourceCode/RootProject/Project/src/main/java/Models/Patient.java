/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
    private String id; 
    private SimpleStringProperty gender;
    private SimpleStringProperty address;
    
    private SimpleStringProperty provider; 
    
    private List<ProgressReport> progressReports;
    
    private SimpleIntegerProperty ssn;
    
    private SimpleStringProperty physicianName;
    private SimpleStringProperty physicianNumber;
    
    private List<MedicalHistory> medicalHistory;
    
    private List<Symptoms> symptoms;    
    
    private SimpleStringProperty dischargeInstructions;
    private SimpleStringProperty assignedDoctor;
    private Boolean admitted;
    
    private ArrayList<Diagnoses> diagnoses;
    private ArrayList<Tests_procedures> tests_procedures;
    private ArrayList<Medications> medications;
    
    //TODO: Implement allergies, current medications
    //private List<Allergies> allergies;
    
    public Patient()
    {
        
    }
    
    //Initial creation without the variables that are added once they are in the system
    public Patient(String name, Integer age, String phoneNumber, Integer ssn, String physicianName, 
            String physicianNumber, String provider, ArrayList<Symptoms> symptoms, String gender)
    {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.ssn = new SimpleIntegerProperty(ssn);
        this.physicianName = new SimpleStringProperty(physicianName);
        this.physicianNumber = new SimpleStringProperty(physicianNumber);
        this.provider = new SimpleStringProperty(provider);
        this.symptoms = symptoms;
        this.gender = new SimpleStringProperty(gender);
    }
    
    
    //Retrieve all variables after everything is set
    public Patient(String id, String name, Integer age, String phoneNumber, Integer ssn, String physicianName, 
            String physicianNumber, String provider, List<Symptoms> symptoms, String assignedDoctor, Boolean admitted, 
            List<MedicalHistory> medicalHistory, List<ProgressReport> progressReports, String dischargeInstructions, String gender)
    {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.ssn = new SimpleIntegerProperty(ssn);
        this.physicianName = new SimpleStringProperty(physicianName);
        this.physicianNumber = new SimpleStringProperty(physicianNumber);
        this.id = id;
        this.assignedDoctor = new SimpleStringProperty(assignedDoctor);
        this.provider = new SimpleStringProperty(provider);
        this.admitted = admitted;
        this.dischargeInstructions = new SimpleStringProperty(dischargeInstructions);
        this.medicalHistory = medicalHistory;
        this.progressReports = progressReports;
        this.symptoms = symptoms;
        this.gender = new SimpleStringProperty(gender);
    }
    
    public String getID() { return id; }
    public void setID(String id) { this.id = id ; }
    
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
    
    public List<MedicalHistory> getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(MedicalHistory medHist) { medicalHistory.add(medHist); }
    
    public List<Symptoms> getSymptoms() { return symptoms; }
    public void setSymptoms(Symptoms symptom) { this.symptoms.add(symptom); }

    public String getProvider() { return provider.get(); }
    public void setProvider(SimpleStringProperty provider) { this.provider = provider; }
    
    public String getDischargeInstructions() { return dischargeInstructions.get(); }
    public void setDischargeInstructions(SimpleStringProperty dischargeInstructions) { this.dischargeInstructions = dischargeInstructions; }
    
    public String getAssignedDoctor() { return assignedDoctor.get(); }
    public void setAssignedDoctor(SimpleStringProperty assignedDoctor) { this.assignedDoctor = assignedDoctor; }
    
    public Boolean getAdmittedBool() { return admitted; }
    
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
    
    public List<ProgressReport> getProgressReports() { return progressReports; }
    public void setProgressReports(ProgressReport report) { progressReports.add(report); }
    
    public String getGender() { return gender.get(); }
    public void setString(String gender) { this.gender = new SimpleStringProperty(gender); }
    
    public String getAddress() { return address.get(); }
    public void setAddress(String address) { this.address = new SimpleStringProperty(address); }
    
    
    //Author: Jakob
    public ArrayList<Diagnoses> getDiagnosis() { return diagnoses; }
    public void addDiagnosis(Diagnoses d) { diagnoses.add(d); }
    
    //Author: Jakob
    public ArrayList<Tests_procedures> getTests() { return tests_procedures; }
    public void addTests(Tests_procedures t) { tests_procedures.add(t); }
    
    //Author: Jakob
    public ArrayList<Medications> getMedications() { return medications; }
    public void addMedication(Medications m) { medications.add(m); }
    
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
