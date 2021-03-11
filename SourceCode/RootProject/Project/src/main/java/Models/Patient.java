/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleLongProperty;

/**
 *
 * @author Tyler
 */
public class Patient {
    
    
    public String name;
    public int  age;     
    public  String phoneNumber;
    
    //RESEARCH WHAT INSURANCE INFO WILL BE NEEDED
    
    private int socialSecurity;
    
    private String physicianName;
    private String physicianNumber;
    
    private String medicalHistory;
    private String symptoms;
    
    public Patient()
    {
        
    }
    
    public Patient(String name, Integer age, String phoneNumber, Integer socialSecurity, String physicianName, String physicianNumber)
    {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.socialSecurity = socialSecurity;
        this.physicianName = physicianName;
        this.physicianNumber = physicianNumber;
    }
    
    public String getName()
    {
        return name;
    }    
    public void setName(String name)
    {
        this.name = name;
    }
    public final String nameProperty() { return name; }
    
    
    public int getAge()
    {
        return age;
    }    
    public void setAge(int age)
    {
        this.age = age;
    }
    public final int ageProperty() { return age; }
   
    
    public String getPhone()
    {
        return phoneNumber;
    }
    public void setPhone(String number)
    {
        this.phoneNumber = number;
    }
    public final String phoneNumberProperty() { return phoneNumber; }
    
    public int getSS()
    {
        return socialSecurity;
    }
    public void setSS(int social)
    {
        this.socialSecurity = social;
    }
    
    public String getPhysician()
    {
        return physicianName;
    }
    public void setPhysician(String physician)
    {
        this.physicianName = physician;
    } 
    
    public String getPhysicianNumber()
    {
        return physicianNumber;
    }
    public void setPhysicianNumber(String physNum)
    {
        this.physicianNumber = physNum;
    }
    
    public String getMedicalHistory()
    {
        return medicalHistory;
    }
    public void setMedicalHistory(String medHist)
    {
        this.medicalHistory = medHist;
    }
    
    public String getSymptoms()
    {
        return symptoms;
    }
    public void setSymptoms(String symptoms)
    {
        this.symptoms = symptoms;
    }
    
    
    public String toString()
    {
        String ret = "Name: " + name + ". Age: " + age + 
                ". Phone Number: " + phoneNumber + ". SSN: " + socialSecurity +
                ". Physician: " + physicianName + ". Physicians Number: " + physicianNumber;
       
        return ret;
    }
    
    
}
