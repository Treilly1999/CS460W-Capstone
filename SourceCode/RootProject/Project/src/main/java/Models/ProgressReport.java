/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Tyler
 */
public class ProgressReport {
    
    private String nurseName;
    private String date;
    private String note;
    
    public ProgressReport(String nurseName, String date, String note)
    {
        this.nurseName = nurseName;
        this.date = date;
        this.note = note;
    }
    
    public String getNurseName() { return nurseName; }
    public void setNurseName(String nurseName) { this.nurseName = nurseName; }
     
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    
    
    public String toString()
    {
        String progressReports = "Reporting Nurse: " + getNurseName() + ". Date of report: " 
                + getDate() + ". Report: " + getNote();
        
        return progressReports;
    }
}
