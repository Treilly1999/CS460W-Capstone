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
public class Symptoms 
{
    
    private String symptom;
    
    public Symptoms(String symptom)
    {
        this.symptom = symptom;
    }
    
    public String getSymptom(){ return symptom; }
    public void setSymptom(String symptom) { this.symptom = symptom; }
        
    public String toString()
    {
        return getSymptom();
    }
}
