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
public class Symptoms 
{
    
    private SimpleStringProperty symptom;
    
    public Symptoms(String symptom)
    {
        this.symptom = new SimpleStringProperty(symptom);
    }
    
    public String getSymptom(){ return symptom.get(); }
    public void setSymptom(String symptom) { this.symptom = new SimpleStringProperty(symptom); }
        
    public String toString()
    {
        return getSymptom();
    }
}
