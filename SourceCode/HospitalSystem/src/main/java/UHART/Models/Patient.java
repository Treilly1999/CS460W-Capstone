/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UHART.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Tyler
 * Patient object model
 */
public class Patient {    
    
    //TODO: Implement Billing variables
    
    //BILL OBJECT?
    //private Bill b;    
    private Bill bill;
    private String  name;
    private int  age;     
    private String phoneNumber;
    private String id; 
    private String gender;
    
    private String provider; 
    
    private List<ProgressReport> progressReports;
    
    private int ssn;
    
    private String physicianName;
    private String physicianNumber;
    
    private List<MedicalHistory> medicalHistory;
    
    private List<Symptoms> symptoms;    
    
    private String dischargeInstructions;
    private String assignedDoctor;

    private Boolean admitted;
    private Date dateAdmitted;
    private Date dateLeft;
    
    private List<String> diagnosis;
    private ArrayList<Tests_procedures> tests_procedures;
    
    //assigned by doctor
    private List<String> medications;
    
    //assigned by registration
    private ArrayList<String> currentMedications;
    private List<String> allergies;
    private Address address;
    private Date dateOfBirth;       
    
    public Patient()
    {
        
    }
    
    //TODO: Add allergy list
    //Initial creation without the variables that are added once they are in the system
    public Patient(String id, String name, Date dateOfBirth, String phoneNumber, Integer ssn, String physicianName, 
            String physicianNumber, String provider, ArrayList<Symptoms> symptoms, String gender,
            List<String> allergies, Address address)
    {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.ssn = ssn;
        this.physicianName = physicianName;
        this.physicianNumber = physicianNumber;
        this.provider = provider;
        this.symptoms = symptoms;
        this.gender = gender;
       // this.dateOfBirth = dateOfBirth;
        this.allergies = allergies;
        this.address = address;
        //this.currentMedications = currentMedications;
        this.id = id;
    }
    
    
    //Retrieve all variables after everything is set
    public Patient(String id, String name, Date dob, String phoneNumber, Integer ssn, String physicianName, 
            String physicianNumber, String provider, List<Symptoms> symptoms, String assignedDoctor, Boolean admitted, 
            List<MedicalHistory> medicalHistory, List<ProgressReport> progressReports, String dischargeInstructions, String gender,
            Address address, List<String> allergies, List<String> medications, List<String> diagnosis, Bill bill, Date dateAdmitted
            , Date dateLeft)
    {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.ssn = ssn;
        this.physicianName = physicianName;
        this.physicianNumber = physicianNumber;
        this.id = id;
        this.assignedDoctor = assignedDoctor;
        this.provider = provider;
        this.admitted = admitted;
        this.dischargeInstructions = dischargeInstructions;
        this.medicalHistory = medicalHistory;
        this.progressReports = progressReports;
        this.symptoms = symptoms;
        this.gender = gender;
        this.address = address;
        this.allergies = allergies;
        this.medications = medications;
        this.diagnosis = diagnosis;
        this.bill = bill;
        this.dateAdmitted = dateAdmitted;
        this.dateLeft = dateLeft;
        dateOfBirth = dob;
        System.out.println(dateOfBirth.toString());
        if(admitted)
        {
            bill.calculateStay(dateLeft, dateAdmitted);
        }
    }
    //ID
    public String getID() { return id; }
    public void setID(String id) { this.id = id ; }
    
    //Name
    public String getName() { return name; }    
    public void setName(String name) { this.name = name; }    
    
    //Age
    public int getAge() { return age; }    
    public void setAge(int age) { this.age = age; }
    
    //Phone Number    
    public String getPhone() { return phoneNumber; }
    public void setPhone(String number) { this.phoneNumber = number; }
    
    //SSN
    public int getSSN(){ return ssn; }
    public void setSSN(int social) { this.ssn = social; }
    
    //Physician Name
    public String getPhysician() { return physicianName; }
    public void setPhysician(String physician){ this.physicianName = physician; } 
    
    
    //Physician Number
    public String getPhysicianNumber() { return physicianNumber; }
    public void setPhysicianNumber(String physNum) { this.physicianNumber = physNum; }
    
    //Medical History    
    public List<MedicalHistory> getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(MedicalHistory medHist) { medicalHistory.add(medHist); }
    
    //Symptoms
    public List<Symptoms> getSymptoms() { return symptoms; }
    public void setSymptoms(Symptoms symptom) { this.symptoms.add(symptom); }

    //Insurance Provider
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    
    //Discharge Instructions
    public String getDischargeInstructions() { return dischargeInstructions; }
    public void setDischargeInstructions(String dischargeInstructions) { this.dischargeInstructions = dischargeInstructions; }
    
    //Assigned Doctor Name
    public String getAssignedDoctor() { return assignedDoctor; }
    public void setAssignedDoctor(String assignedDoctor) { this.assignedDoctor = assignedDoctor; }
    
    //Admission?
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
    public void setAdmitted(Boolean isAdmitted) { this.admitted = isAdmitted; }
    
    //Progress Reports
    public List<ProgressReport> getProgressReports() { return progressReports; }
    public void setProgressReports(ProgressReport report) { progressReports.add(report); }
    
    //Gender
    public String getGender() { return gender; }
    public void setString(String gender) { this.gender = gender; }
    
    //Address
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    
    
    //Author: Jakob
    //Diagnosis
    public List<String> getDiagnosis() { return diagnosis; }
    public void addDiagnosis(String d) { diagnosis.add(d); }
    
    //Author: Jakob
    //Tests
    public ArrayList<Tests_procedures> getTests() { return tests_procedures; }
    public void addTests(Tests_procedures t) { tests_procedures.add(t); }
    
    //Author: Jakob
    //Medications
    public List<String> getMedications() { return medications; }
    public void addMedication(String m) { medications.add(m); }
    
    public Date getDateOfBirth() { return dateOfBirth; }
    public String getDateOfBirthString() { return removeTime(dateOfBirth).toString(); }
    public void setDateOfBirth(Date dob) { dateOfBirth = dob; }
    
    public List<String> getAllergies() { return allergies; }
    public void setAllergies(List<String> allergies) { this.allergies = allergies; }

    /*
    Author: Jakob
    */
    public Bill getBill() {return bill;}
    //TODO: Fix addPayment
    //public void addPayment(int i) {bill = bill+i;}
    public void markPaid() {bill.markPaid();}

    public ArrayList<String> getCurrentMedication() { return currentMedications; }

    public Date getDateAdmitted() { return dateAdmitted; }
    public Date getDateLeft() { return dateLeft; }
    
    public String toString()
    {
        return "Name: " + getName() + ". Age: " + getAge() + ". Phone Number: " + getPhone() + ". Physician Name: " +
                getPhysician() + ". Physician Phone Number: " + getPhysicianNumber() + ". Insurance Provider: " + getProvider()
                + ". Admitted? " + getAdmitted() + ". Assigned Doctor: " + getAssignedDoctor() +
                ". Medical History: " + getMedicalHistory().toString()
                + ". Symptoms: " + getSymptoms().toString() + ". Progress Reports: " + getProgressReports().toString()
                + ". Discharge Instructions: " + getDischargeInstructions();
    }    

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
