/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Tyler
 */
public class Symptom {  
    
    public enum Symptoms_Type
    {
        FEVER,
        COUGH,
        NAUSEA,
        CHEST_PAIN,
        SNEEZING,
        FATIGUE,
        ACHES,
        SORE_THROAT,
        CONGESTION
    }
    
    private ArrayList<SimpleStringProperty> symptoms;
    
    public Symptom(Symptoms_Type symptom)
    {
        if(symptom == Symptoms_Type.FEVER)
            this.symptoms.add(new SimpleStringProperty("fever"));
        if(symptom == Symptoms_Type.COUGH)
            this.symptoms.add(new SimpleStringProperty("cough"));
        if(symptom == Symptoms_Type.NAUSEA)
            this.symptoms.add(new SimpleStringProperty("nausea"));
        if(symptom == Symptoms_Type.CHEST_PAIN)
            this.symptoms.add(new SimpleStringProperty("chest pain"));
        if(symptom == Symptoms_Type.SNEEZING)
            this.symptoms.add(new SimpleStringProperty("sneezing"));
        if(symptom == Symptoms_Type.FATIGUE)
            this.symptoms.add(new SimpleStringProperty("fatigue"));
        if(symptom == Symptoms_Type.ACHES)
            this.symptoms.add(new SimpleStringProperty("aches"));
        if(symptom == Symptoms_Type.SORE_THROAT)
            this.symptoms.add(new SimpleStringProperty("sore throat"));
        if(symptom == Symptoms_Type.CONGESTION)
            this.symptoms.add(new SimpleStringProperty("congestion"));
    }
    
    public ArrayList<SimpleStringProperty> getSymptoms() { return symptoms; }
    
    public String toString()
    {                
        return getSymptoms().toString();
    }
    
}
