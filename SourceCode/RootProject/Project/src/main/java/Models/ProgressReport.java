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
    
    private SimpleStringProperty nurseName;
    private SimpleStringProperty date;
    private SimpleStringProperty note;
    
    public ProgressReport(String nurseName, String date, String note)
    {
        this.nurseName = new SimpleStringProperty(nurseName);
        this.date = new SimpleStringProperty(date);
        this.note = new SimpleStringProperty(note);
    }
    
    public String getNurseName() { return nurseName.get(); }
    public void setNurseName(String nurseName) { this.nurseName = new SimpleStringProperty(nurseName); }
    
    public String getDate() { return date.get(); }
    public void setDate(String date) { this.date = new SimpleStringProperty(date); }
    
    public String getNote() { return note.get(); }
    public void setNote(String note) { this.note = new SimpleStringProperty(note); }
    
    
    public String toString()
    {
        String progressReports = "Reporting Nurse: " + getNurseName() + ". Date of report: " 
                + getDate() + ". Report: " + getNote();
        
        return progressReports;
    }
}
