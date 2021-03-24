package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import static Controllers.CreatePatient.startTestCreate;
import Models.MedicalHistory;
 import com.mongodb.*;
 import com.mongodb.MongoClient;
 import Models.Patient;
import Models.ProgressReport;
import Models.Symptom;
import com.google.common.base.CharMatcher;
 import com.mongodb.client.MongoCollection;
 import com.mongodb.client.MongoDatabase;
 import com.mongodb.client.model.Projections;
 import com.mongodb.client.model.Filters;
 import com.mongodb.client.*;
 import static com.mongodb.client.model.Filters.*;
 import static com.mongodb.client.model.Projections.*;
 import com.mongodb.client.model.Sorts;
 import java.util.Arrays;
 import org.bson.Document;
 import com.mongodb.gridfs.GridFSDBFile;
 import java.util.*;
 import java.util.ArrayList;
 import com.google.common.base.Splitter;
 import java.lang.Integer;
 import java.util.Scanner;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Tyler
 */
public class DBConnection {
    
    static ArrayList<Patient> patientList = new ArrayList<Patient>();
    
    public ArrayList<Patient> getPatients()
    {
        return patientList;
    }
    
    
    /*
    Author: Tyler
    Description: Main is for testing mongoDB commands with output to the console.
    */
    public static void main(String[] args)
    {
        //createPatient(database, "Billy", 21, "123-123-1234", 12345678, "Dr. Craig", "3243245643");        
                
        //startQuery();
        //startTestCreate(true);
        updateTest();
    }  
    
    /*
    Author: Tyler
    Description: For testing purposes.
    */
    public static void startQuery()
    {        
        patientList.clear();
        Scanner sc = new Scanner(System.in);

        Document query = new Document();               
        
        System.out.println("Enter 0 to choose a query, and 1 for no query.");
        int option = sc.nextInt();
        
        String param1 = "";
        String param2 = "";
        
        if(option == 0)
        {
            System.out.println("What would you like to query?");
            param1 = sc.next();
            
            System.out.printf("What is the value of %s%n", param1);
            param2 = sc.next();
            query.put(param1, param2);  
        }      
        
        parsePatients(query);  
       
        patientList.forEach((n) -> System.out.println(n));
        
        System.out.println("Press 1 to search for more. Press 2 to exit");
        int cont = sc.nextInt();
        if(cont == 1)
        {
            startQuery();
        }
    }
    
    public static void parsePatient(Document query)
    {
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("hospital");

        MongoCollection<Document> collection = database.getCollection("patients");    
        
        ArrayList<Document> patients = new ArrayList<Document>();
        
        FindIterable<Document> findIterable;
        
        if(query != null)
        {
           findIterable = collection.find(query);
        }
        else
        {
           findIterable = collection.find();
        }
        
        MongoCursor<Document> cursor = findIterable.iterator();
        
        try
        {
            while(cursor.hasNext())
            {               
                patients.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        System.out.println(patients.size());
        patients.forEach((n) -> buildPatients(n));
    }
    
    /*
    Author: Tyler
    Description: Parses the database for all patients or specific patients that
    match a query. 
    */
    public static ArrayList<Patient> parsePatients(Document query)
    {
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("hospital");
        MongoCollection<Document> collection = database.getCollection("patients");    
        
        ArrayList<Document> patients = new ArrayList<Document>();
        
        FindIterable<Document> findIterable;
        
        if(query != null)
        {
           findIterable = collection.find(query);
        }
        else
        {
           findIterable = collection.find();
        }
        
        MongoCursor<Document> cursor = findIterable.iterator();
        
        try
        {
            while(cursor.hasNext())
            {               
                patients.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        //patients.forEach((n) -> System.out.println(n.toString()));
        
        patients.forEach((n) -> buildPatients(n));
              
        return patientList;
    }
    
    /*
     Author: Tyler
     Description: Builds individual patient's models based on the patients parsed
     from parsePatients.
     */
    public static void buildPatients(Document patients)
    {
          
        String documentString = patients.toString();
        System.out.println(documentString);
        
        Map<String,String> queryParameters = Splitter
            .on(", ")
            .trimResults(CharMatcher.is('}'))
            .withKeyValueSeparator("=")
            .split(documentString);
        
        String name = "", physicianName = "", physicianNumber = "",
              dischargeInstructions = "",  assignedDoctor = "",
              provider = "", phoneNumber = "";
        int age = 1, id = 1, ssn = 1;
        Boolean admitted = false;   
        
        try
        {
            name = queryParameters.get("name");
            age = Integer.parseInt(queryParameters.get("age"));
            phoneNumber = queryParameters.get("phoneNumber");
            ssn = Integer.parseInt(queryParameters.get("ssn"));
            physicianName = queryParameters.get("physicianName");
            physicianNumber = queryParameters.get("physicianNumber");           
            dischargeInstructions = queryParameters.get("dischargeInstructions");
            assignedDoctor = queryParameters.get("assignedDoctor");
            admitted = Boolean.parseBoolean(queryParameters.get("admitted"));
            provider = queryParameters.get("provider");
            id = Integer.parseInt(queryParameters.get("id"));  
            
          } catch (Exception e)
          {
              System.out.println("Some query parameters were not found in " + name + " profile");
          }
        
        Document medHisDoc = new Document();
        medHisDoc.put("patientID", "" + id);
        ArrayList<MedicalHistory> medicalHistory = parseArrays(medHisDoc, "medicalHistory");
        //medicalHistory.forEach((n) -> n.toString());
                
        Document symptomDoc = new Document();
        symptomDoc.put("patientID", "" + id);
        ArrayList<Symptom> symptoms = parseArrays(symptomDoc, "symptoms");
        //symptoms.forEach((n) -> n.toString());
        
        Document progressDoc = new Document();
        progressDoc.put("patientID", "" + id);
        ArrayList<ProgressReport> progressReports = parseArrays(progressDoc, "progressReports");
       
        
        patientList.add(new Patient(id, name,  age,  phoneNumber, ssn,  physicianName, 
                physicianNumber, provider,  symptoms,  assignedDoctor,  admitted, 
                medicalHistory,  progressReports, dischargeInstructions));
        
      
    } 
    
    /*
    Author: Tyler
    Description: These create the array lists for medical history, symptoms, and progress reports for a patient. 
    Each are their own collection. Each document has a correlating patientID to query the database to match the documents.
    It would be better to have it all in one document, but I'm not sure how to extract the arrays from the document on their own.
    */
    public static <T> ArrayList<T> parseArrays(Document query, String type)
    {
        System.out.println("IN PARSEARRAYS");
        System.out.println(query.toString());
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("hospital");
        MongoCollection<Document> collection = database.getCollection(type);    
        
        System.out.println(collection.count());
        
        ArrayList<Document> patients = new ArrayList<Document>();
        
        ArrayList<T> returnList = new ArrayList<T>();
        
        
        FindIterable<Document> findIterable;
        
        
        if(query != null)
        {
           findIterable = collection.find(query);
        }
        else
        {
           findIterable = collection.find();
        }
        
        MongoCursor<Document> cursor = findIterable.iterator();
        
        try
        {
            while(cursor.hasNext())
            {   
                System.out.println("YES HAS NEXT");
                patients.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        //patients.forEach((n) -> System.out.println(n.toString()));
        
        System.out.println(patients.size());
        
        
        
        for(int i = 0; i < patients.size(); i++)
        {         
                          
            returnList.add(getArrays(patients.get(i), type));
            
        }       
        
        //patients.forEach((n) -> getArrays(n, type));
              
        return returnList;
    }
    
    /*
    Author: Tyler
    Description: Builds symptoms
    ToDo: refactor completely.
    */
//    public static <T> ArrayList<T> getSymptoms(Document storage)
//    {
//        Map<String,String> queryParameters = Splitter
//        .on(", ")
//        .trimResults(CharMatcher.is('}'))
//        .withKeyValueSeparator("=")
//        .split(storage.toString());
//        
//        ArrayList<T> symptoms = new  ArrayList<T>();
//
//        if(Boolean.parseBoolean(queryParameters.get("FEVER")))
//        {
//           System.out.println("IN FEVER PARSE BOOLEAN");
//           symptoms.add((T)new Symptom(Models.Symptom.Symptoms_Type.FEVER));
//        }
//        else if(Boolean.parseBoolean(queryParameters.get("COUGH")))
//        {
//            symptoms.add((T)new Symptom(Models.Symptom.Symptoms_Type.COUGH));
//        }
//        else if(Boolean.parseBoolean(queryParameters.get("NAUSEA")))
//        {
//            symptoms.add((T)new Symptom(Models.Symptom.Symptoms_Type.NAUSEA));
//        }
//        else if(Boolean.parseBoolean(queryParameters.get("CHEST_PAIN")))
//        {
//            symptoms.add((T)new Symptom(Models.Symptom.Symptoms_Type.CHEST_PAIN));
//        }
//        else if(Boolean.parseBoolean(queryParameters.get("SNEEZING")))
//        {
//            symptoms.add((T)new Symptom(Models.Symptom.Symptoms_Type.SNEEZING));
//        }
//        else if(Boolean.parseBoolean(queryParameters.get("FATIGUE")))
//        {
//            symptoms.add((T)new Symptom(Models.Symptom.Symptoms_Type.FATIGUE));
//        }
//        else if(Boolean.parseBoolean(queryParameters.get("ACHES")))
//        {
//            symptoms.add((T)new Symptom(Models.Symptom.Symptoms_Type.ACHES));
//        }
//        else if(Boolean.parseBoolean(queryParameters.get("SORE_THROAT")))
//        {
//            symptoms.add((T)new Symptom(Models.Symptom.Symptoms_Type.SORE_THROAT));
//        }
//        else if(Boolean.parseBoolean(queryParameters.get("CONGESTION")))
//        {
//            symptoms.add((T)new Symptom(Models.Symptom.Symptoms_Type.CONGESTION));                
//        }         
//
//        return symptoms;
//    }
            
    
    public static <T> T getArrays(Document storage, String type)
    {               
                
        System.out.println("INSIDE GETARRAYS");
        Map<String,String> queryParameters = Splitter
        .on(", ")
        .trimResults(CharMatcher.is('}'))
        .withKeyValueSeparator("=")
        .split(storage.toString());
        
        System.out.println(storage.toString());
        
        String nurse = "", date = "" ,note = "";
        String hospitalization = "";
        
        if(type.equals("medicalHistory"))
        {           
           
            date = queryParameters.get("date");
            hospitalization = queryParameters.get("hospitalization");
            
            MedicalHistory medicalHistory = new MedicalHistory(date, hospitalization);
            return (T)medicalHistory;
          
        }
        else if(type.equals("progressReports"))
        {
            date = queryParameters.get("date");
            nurse = queryParameters.get("nurse");
            note = queryParameters.get("report");
            
            ProgressReport progressReport = new ProgressReport(nurse, date, note);
            
            return (T)progressReport;
        }       
        else if(type.equals("symptoms"))
        {
           Boolean fever, cough, nausea, chestPain, sneezing, fatigue, aches, soreThroat, congestion;
           
           fever = Boolean.parseBoolean(queryParameters.get("FEVER"));
           cough = Boolean.parseBoolean(queryParameters.get("COUGH"));
           nausea = Boolean.parseBoolean(queryParameters.get("NAUSEA"));
           chestPain = Boolean.parseBoolean(queryParameters.get("CHEST_PAIN"));
           sneezing = Boolean.parseBoolean(queryParameters.get("SNEEZING"));
           fatigue = Boolean.parseBoolean(queryParameters.get("FATIGUE"));
           aches = Boolean.parseBoolean(queryParameters.get("ACHES"));
           soreThroat = Boolean.parseBoolean(queryParameters.get("SORE_THROAT"));
           congestion = Boolean.parseBoolean(queryParameters.get("CONGESTION"));
           
           Symptom symptoms = new Symptom(fever, cough, nausea, chestPain, sneezing, fatigue, aches, soreThroat, congestion);
                   
           return (T)symptoms;
        }
        
        return null;
    }
    
 // ---------------------------------------------------------
    public static void updateTest()
    {
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("hospital");

        
        Scanner sc = new Scanner(System.in);
        Document before = new Document();       
        Document afterQuery = new Document();
        
        System.out.println("What would you like to update?");
        String param1 = sc.nextLine();
        sc.nextLine();
        
        System.out.printf("What is the value of %s%n", param1);
        String param2 = sc.next();
        sc.nextLine();
        
        System.out.println("What would you like to change?");
        String param3 = sc.next();
        sc.nextLine();
        
        System.out.printf("What would you like to change in %s%n?", param3);
        String after = sc.nextLine();
        
        before.put(param1, param2);
        afterQuery.put(param1, after);
        
        parsePatient(before);
        
        updateEntry("patients", param1, param2, param3, after, false);
        
        parsePatient(afterQuery);
    }
     
     
     /*
     Author: Tyler Reilly
     Description: Updates a single entry or all entries in the given collection.
     TODO: NEEDS DYNAMIC TYPES, Object type? && TEST MORE
     */
    public static void updateEntry(String collectionName, String field, Object value, String afterField, Object afterValue, Boolean isMultiple)
    {
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("hospital");

        MongoCollection<Document> collection = database.getCollection(collectionName);    
        
        if(isMultiple)
        {
            collection.updateMany(eq(field, value), new Document("$set", new Document(afterField, afterValue)));
            System.out.println("Update all entries from " + value + " to " + afterValue);
        }
        else
        {
            
            collection.updateOne(eq(field, value), new Document("$set", new Document(afterField, afterValue)));
            System.out.println("Updated the entry where " + value
            + " is met and changed it to " + afterValue);
        }
        
    }
    
//---------------------------------------------------
    //TODO: TEST MORE
    public static void removeEntry(Document query, Boolean allMatch)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("hospital");
        MongoCollection<Document> collection = database.getCollection("patients");    
                 
        if(allMatch == true)
        {
            collection.deleteMany(query);
            System.out.println("Deleted all documents in " + database.getName() + " that matched " + query.toString());
        }
        else
        {
            collection.deleteOne(query);
            System.out.println("Deleted a single document in " + database.getName() + " that matched " + query.toString());
        }
        
    }   
    
//----------------------------------------------------
    
     /*
    Author: Tyler
    Description: Testing method that uses createPatient from console inputs
    */
    public static void startTestCreate(Boolean firstTry)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("hospital");
        
        Scanner sc = new Scanner(System.in);
        int opt;
        
        if(firstTry == true)
        {
            System.out.println("Do you want to create a patient profile? If yes, enter 0. Else 1.");
            opt = sc.nextInt();
        }
        else
        {
            opt = 0;
        }
        
        
        if(opt == 0)
        {
            System.out.println("Enter patient name.");
            String name = sc.next();
            System.out.println("Enter patient age.");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.println("What is the patient's SSN?");
            int ssn = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter patient phone number.");
            String phoneNumber = sc.next();
            System.out.println("Enter patient's physician.");
            sc.nextLine();
            String physicianName = sc.nextLine();
            System.out.println("Enter patient physician's phone number.");
            String physicianNumber = sc.nextLine();
            
            createPatient(database, name, age, phoneNumber, ssn, physicianName, physicianNumber); 
            
            System.out.println("more? 1 yes 2 no");
            int cont = sc.nextInt();
            
            if(cont == 1)
            {
                startTestCreate(false);
            }
        }
        
        
    }
    
    /*
    Author: Tyler
    Description: Inserts a patient into the database HOSPITAL
    TODO: ADD ALL PATIENT VARIABLES
    */
    public static void createPatient(MongoDatabase database, String name, int age, String phoneNumber, 
            int ssn, String physicianName, String physicianNumber)
    {
        MongoCollection<Document> collection = database.getCollection("patients");    
            
        Document document = new Document();
        
        document.put("name", name);
        document.put("age", age);
        document.put("phoneNumber", phoneNumber);
        document.put("ssn", ssn);
        document.put("physicianName", physicianName);
        document.put("physicianNumber", physicianNumber);
        
        collection.insertOne(document);
    }
    
}
    
    

