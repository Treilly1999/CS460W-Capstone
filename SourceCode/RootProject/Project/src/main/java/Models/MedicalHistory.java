/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Tyler
 */
public class MedicalHistory {
    
    private SimpleStringProperty date;
    private SimpleStringProperty reason;
    
    public MedicalHistory(String date, String reason)
    {
        this.date = new SimpleStringProperty(date);
        this.reason = new SimpleStringProperty(reason);       
    }
    
    public String getDate() { return date.get(); }
    public void setDate(String date) { this.date = new SimpleStringProperty(date); }
    
    public String getReason() { return reason.get(); }
    public void setReason(String reason) { this.reason = new SimpleStringProperty(reason); }
 
    
    public String toString()
    {
        return
                "Date of Hospitalization: " + getDate() + ". Reasoning: " + getReason();
    }
}

