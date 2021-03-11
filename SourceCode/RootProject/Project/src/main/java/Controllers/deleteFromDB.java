/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Patient;
import org.bson.Document;

/**
 *
 * @author Tyler
 */
public class deleteFromDB 
{
    
    public static void removePatient(Patient id)
    {
        
    }
    
    public static void removeEntry(Document query, Boolean allMatch)
    {
        
        if(allMatch == true)
        {
            //deleteMany
        }
        else
        {
            //deleteOne
        }
        
    }
    
    
}
