package UHART.Models;

import org.bson.Document;

/*
    Author: Tyler Reilly
    Description: This class is used in in the search algorithm for JTableButtonModel.
    Its purpose is to increase the load time of the search when searching for every patient.

    Fun fact: The redesign of the search reduced load time from 15 seconds to 3 seconds.
*/
public class Search {
    
    String name;
    int ssn;
    Document patientDoc;

    public Search(String name, int ssn, Document patientDoc)
    {
        this.name = name;
        this.ssn = ssn;
        this.patientDoc = patientDoc;
    }

    public String getName() { return name; }
    public int getSSN() { return ssn; }
    public Document getPatientDoc() { return patientDoc; }
}
