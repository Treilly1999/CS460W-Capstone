/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UHART.Models;

/**
 *
 * @author Tyler
 */
public class MedicalHistory {
    
    private String date;
    private String reason;
    
    public MedicalHistory(String date, String reason)
    {
        this.date = date;
        this.reason = reason;       
    }
    
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
 
    
    public String toString()
    {
        return
                "Date of Hospitalization: " + getDate() + ". Reasoning: " + getReason();
    }
}

