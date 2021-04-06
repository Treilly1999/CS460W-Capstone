/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Patient;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

/**
 *
 * @author Tyler
 */
public class DoctorController 
{
    
    private ArrayList<Patient> patients;    
    private DBConnection db = new DBConnection();
    private String col[] = {"Name", "SSN"};
    
    /*
    Author: Tyler Reilly
    Description: Creates a table of patients based on a search parameter.
    */
    public DefaultTableModel getTable(Document search)
    {
        
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        if(search == null)
        {
            //Finds every single patient
            patients = db.parsePatients(null);
        }
        else
        {            
            //finds individual patient
            patients = db.parsePatients(search);
        }
        
        for(int i = 0; i < patients.size(); i++)
        {
            String name = patients.get(i).getName();
            String ssn = "" + patients.get(i).getSSN();
            Object[] data = {name, ssn};
            
            tableModel.addRow(data);
        }
        
        return tableModel;
    }
    
}
