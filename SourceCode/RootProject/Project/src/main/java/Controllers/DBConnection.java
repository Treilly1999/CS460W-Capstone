package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 import Models.MedicalHistory;
 import com.mongodb.MongoClient;
 import Models.Patient;
 import Models.ProgressReport;
import Models.Staff_Model;
 import Models.Symptoms;
 import com.mongodb.client.MongoCollection;
 import com.mongodb.client.MongoDatabase;
 import com.mongodb.client.*;
 import static com.mongodb.client.model.Filters.*;
 import org.bson.Document;
 import java.util.*;
 import java.util.ArrayList;
 import java.lang.Integer;
 import java.util.Scanner;
/**
 *
 * @author Tyler
 * 
 * This is a class library that is used in the controllers for database connectivity.
 * 
 */
public class DBConnection {
    
    static ArrayList<Patient> patientList = new ArrayList<Patient>();
    
    public ArrayList<Patient> getPatients()
    {
        return patientList;
    }
    
    private static MongoClient mongoClient = new MongoClient("localhost", 27017);
    private static MongoDatabase database = mongoClient.getDatabase("hospital");
    
    public static MongoDatabase getDB() { return database; }  
    
    /*
    Author: Tyler
    Description: Parses the database for all patients or specific patients that
    match a query. 
    */
    public ArrayList<Patient> parsePatients(Document query)
    {       
        //MongoClient mongoClient = new MongoClient("localhost", 27017);
        //MongoDatabase database = mongoClient.getDatabase("hospital");
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
        
        patients.forEach((n) -> buildPatients(n, collection));
              
        return patientList;
    }
    
    /*
     Author: Tyler
     Description: Builds individual patient's models based on the patients parsed
     from parsePatients.
     */
    public void buildPatients(Document patients, MongoCollection<Document> collection)
    {
          
                
        String name = "", physicianName = "", physicianNumber = "",
              dischargeInstructions = "",  assignedDoctor = "",
              provider = "", phoneNumber = "", id = "";
        int age = 1, ssn = 1;
        Boolean admitted = false;   
        
        String gender = "";
        
        List<MedicalHistory> medicalHistory = new ArrayList<MedicalHistory>();
        List<Symptoms> symptoms = new ArrayList<Symptoms>();
        List<ProgressReport> progressReports = new ArrayList<ProgressReport>();
        try
        {
            name = patients.getString("name");
            age = Integer.parseInt(patients.getString("age"));
            phoneNumber = patients.getString("phoneNumber");
            ssn = Integer.parseInt(patients.getString("ssn"));
            physicianName = patients.getString("physicianName");
            physicianNumber = patients.getString("physicianNumber");           
            dischargeInstructions = patients.getString("dischargeInstructions");
            assignedDoctor = patients.getString("assignedDoctor");
            admitted = Boolean.parseBoolean(patients.getString("admitted"));
            provider = patients.getString("provider");
            id = patients.getString("id");  
            gender = patients.getString("gender");
            
            medicalHistory = buildLists(patients, "medicalHistory", collection);           
            symptoms = buildLists(patients, "symptoms", collection); 
            progressReports = buildLists(patients, "progressReports", collection); 
       
            
          } catch (Exception e)
          {
              System.out.println("Some query parameters were not found in " + name + " profile");
          }
        
        Document patientID = new Document();
        patientID.put("patientID", "" + id);
        
        
        
        patientList.add(new Patient(id, name,  age,  phoneNumber, ssn,  physicianName, 
                physicianNumber, provider,  symptoms,  assignedDoctor,  admitted, 
                medicalHistory,  progressReports, dischargeInstructions, gender));
        
      
    } 
    
    /*
    Author: Tyler Reilly
    Description: Buildsthe medical history,  symptoms, and progress reports for patient. 
    TODO: Refactor to where the collection isnt queried each time.
    */
    public <T> List<T> buildLists(Document storage, String type, MongoCollection<Document> collection)
    {
        
        List<T> returnList = new ArrayList<T>();
                
        List<Document> patients = (List<Document>)collection.find().into(new ArrayList<Document>());
        
        for(Document patient : patients)
        {
            List<Document> insideArray = (List<Document>) patient.get(type);
            
            if(type.equals("symptoms"))
            {
                for(Document symptom : insideArray)
                {
                    returnList.add((T)new Symptoms(symptom.getString("name")));
                }
            }
            else if(type.equals("progressReports"))
            {
                for(Document progRep : insideArray)
                {
                    returnList.add((T)new ProgressReport(progRep.getString("nurseName"), progRep.getString("date"), progRep.getString("note")));
                }
            }
            if(type.equals("medicalHistory"))
            {
                for(Document medHis : insideArray)
                {
                    returnList.add((T)new MedicalHistory(medHis.getString("date"), medHis.getString("reason")));
                }
            }
            
        }
        
        return (List<T>)returnList;  
    }
    
 // ---------------------------------------------------------
     /*
     Author: Tyler Reilly
     Description: Updates a single entry or all entries in the given collection.
     TODO: NEEDS DYNAMIC TYPES, Object type? && TEST MORE
     */
    public static void updateEntry(String collectionName, String field, Object value, String afterField, Object afterValue, Boolean isMultiple)
    {
        
        //MongoClient mongoClient = new MongoClient("localhost", 27017);
        //MongoDatabase database = mongoClient.getDatabase("hospital");

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
        //MongoClient mongoClient = new MongoClient("localhost", 27017);
       // MongoDatabase database = mongoClient.getDatabase("hospital");
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
    
//--------------------------------------------------  
    /*
    Author: Tyler Reilly
    Description: Creates a patient using a patient object.
    */
    public static String createPatient(Patient patient)
    {
        MongoCollection<Document> patientCollection = database.getCollection("patients");            
            
        Document document = new Document();        
       
        String state = "SUCCESSFUL";
       
        UUID id = UUID.randomUUID();
        
        try
        {
            document.put("name", patient.getName());
            document.put("age", patient.getAge());
            document.put("phoneNumber", patient.getPhone());
            document.put("ssn", patient.getSSN());
            document.put("physicianName", patient.getPhysician());
            document.put("physicianNumber", patient.getPhysicianNumber());        
            //document.put("assignedDoctor", assignedDoctor);
            //document.put("dischargeInstructions", dischargeInstructions);     
            document.put("id", id.toString());        
            document.put("provider", patient.getProvider());   
            document.put("gender", patient.getGender());
            
            patientCollection.insertOne(document);      
            
            Document patientID = new Document();
            patientID.put("id", id.toString());
            
            for(int i = 0; i<patient.getSymptoms().size(); i++)
            {
                createSymptoms(patient.getSymptoms().get(i), patientCollection, patientID);
            }
            //Med his not implemented yet
//            for(int i = 0; i < patient.getMedicalHistory().size(); i++)
//            {
//                createMedicalHistory(patient.getMedicalHistory().get(i), patientCollection, patientID);
//            }
            
        } catch (Exception e)
        {
            state = "FAILURE";
        }
        
        return state;
    }   
    
    /*
    Author: Tyler Reilly
    Description: Helper Method for createPatient
    TODO: Way to do it without creating? Instead return nested doc?
    */
    public static void createSymptoms(Symptoms symptoms, MongoCollection<Document> patientCollection, Document find)
    {        
        
        Document sympt = new Document();
        
        sympt.put("name", symptoms.getSymptom());
        
        patientCollection.updateOne(find, new Document("$push", new Document("symptoms", sympt)));
    } 
    
    /*
    Author: Tyler Reilly
    Description: Helper Method for createPatient
    */
    public static void createMedicalHistory(MedicalHistory medicalHistory, MongoCollection<Document> patientCollection, Document find)
    {
        
        Document medDoc = new Document();        
        
        medDoc.put("date", medicalHistory.getDate());
        medDoc.put("hospitalization", medicalHistory.getReason());
        
        patientCollection.updateOne(find, new Document("$push", new Document("symptoms", medDoc)));      
    }
    
    /*
    Author: Tyler Reilly
    Description: Helper Method for createPatient
    */
    public static void createProgressReports(ProgressReport progressRep, Staff_Model staff, MongoCollection<Document> patientCollection, Document find)
    {       
        Document progressDoc = new Document();
        
        progressDoc.put("nurse", progressRep.getNurseName());
        progressDoc.put("nurseID", "" + staff.getID());
        progressDoc.put("date", progressRep.getDate());
        progressDoc.put("report", progressRep.getNote());
        
        patientCollection.updateOne(find, new Document("$push", new Document("symptoms", progressDoc)));         
    }
}
    
    

