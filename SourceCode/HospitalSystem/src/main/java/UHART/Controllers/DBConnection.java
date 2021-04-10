package UHART.Controllers;

import UHART.Models.Address;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 import UHART.Models.Diagnoses;
 import UHART.Models.MedicalHistory;
 import UHART.Models.Medications;
 import com.mongodb.MongoClient;
 import UHART.Models.Patient;
 import UHART.Models.ProgressReport;
 import UHART.Models.Staff_Model;
 import UHART.Models.Symptoms;
 import UHART.Models.Tests_procedures;
 import com.mongodb.client.MongoCollection;
 import com.mongodb.client.MongoDatabase;
 import com.mongodb.client.*;
 import static com.mongodb.client.model.Filters.*;
 import org.bson.Document;
 import java.util.*;
 import java.util.ArrayList;
/**
 *
 * @author Tyler
 * 
 * This is a class library that is used in the controllers for database connectivity.
 * 
 */
public class DBConnection {
    
    //TODO: Research MongoDB date object
    private ArrayList<Patient> patientList = new ArrayList<Patient>();
    
    public ArrayList<Patient> getPatients()
    {
        return patientList;
    }
    
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    public DBConnection()
    {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("hospital");
    }
    
    
    
    public MongoDatabase getDB() { return database; }  
    
    /*
    Author: Tyler
    Description: Parses the database for all patients or specific patients that
    match a query. 
    
    USE PATIENT SSN || NAME || ID to search
    */
    public ArrayList<Patient> parsePatients(Document query)
    {       
        patientList.clear();
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
        
        try
        (MongoCursor<Document> cursor = findIterable.iterator()) {
            while(cursor.hasNext())
            {               
                patients.add(cursor.next());
            }
        }
        //patients.forEach((n) -> System.out.println(n.toString()));
        //patients.forEach((n) -> System.out.println(n.toString()));        
        
        for(Document patient: patients)
        {
            buildPatients(patient, collection);
        }


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
        //TODO: Convert to date
        Date dateOfBirth = new Date();
        String gender = "";
        Address address = new Address();
        List<MedicalHistory> medicalHistory = new ArrayList<MedicalHistory>();
        List<Symptoms> symptoms = new ArrayList<Symptoms>();
        List<ProgressReport> progressReports = new ArrayList<ProgressReport>();
        List<String> allergies = new ArrayList<String>();
        try
        {
            name = patients.getString("name");
            //age = patients.getInteger("age");
            phoneNumber = patients.getString("phoneNumber");
            //ssn = patients.getInteger("ssn");
            physicianName = patients.getString("physicianName");
            physicianNumber = patients.getString("physicianNumber");           
            dischargeInstructions = patients.getString("dischargeInstructions");
            assignedDoctor = patients.getString("assignedDoctor");
            admitted = Boolean.parseBoolean(patients.getString("admitted"));
            provider = patients.getString("provider");
            id = patients.getString("id");  
            gender = patients.getString("gender");
            dateOfBirth = patients.getDate("dateOfBirth");            
            
            allergies = buildLists(patients, "allergies", collection);
            medicalHistory = buildLists(patients, "medicalHistory", collection);           
            symptoms = buildLists(patients, "symptoms", collection); 
            progressReports = buildLists(patients, "progressReports", collection); 
       

            for(Document addr : (List<Document>)patients.get("address"))
            {
                address = new Address(addr.getString("street"),addr.getString("zipcode"),addr.getString("state"),addr.getString("country"),addr.getString("city"));
            }
            
            
          } catch (Exception e)
          {
              System.out.println(e);
              System.out.println("Some query parameters were not found in " + name + " profile");
          }
        
        Document patientID = new Document();
        patientID.put("patientID", "" + id);  
        
        patientList.add(new Patient(id, name,  patients.getInteger("age"),  phoneNumber, patients.getInteger("ssn"),  physicianName, 
                physicianNumber, provider,  symptoms,  assignedDoctor,  admitted, 
                medicalHistory,  progressReports, dischargeInstructions, gender, address, allergies));
        
      
    } 
    
    /*
    Author: Tyler Reilly
    Description: Builds the medical history,  symptoms, and progress reports for patient. 
    TODO: Refactor to where the collection isnt queried each time. || Might need a query || Use storage instead of the List<Document> patients
    */
    public <T> List<T> buildLists(Document storage, String type, MongoCollection<Document> collection)
    {
        
        List<T> returnList = new ArrayList<T>();       

        //List<Document> patients = (List<Document>)(ArrayList<?>)collection.find().into(new ArrayList<>());     
        

        if(type.equals("symptoms"))
        {

            for(Document symptom : (List<Document>)storage.get("symptoms"))
            {
                returnList.add((T)new Symptoms(symptom.getString("name")));
            }

            // ArrayList<Symptoms> symptoms = (ArrayList<Symptoms>)storage.get("symptoms");
            // returnList = (List<T>)symptoms;
        }
        else if(type.equals("progressReports"))
        {
            ArrayList<ProgressReport> progressReports = (ArrayList<ProgressReport>)storage.get("progressReports");       
            returnList = (List<T>)progressReports;
        }
        else if(type.equals("medicalHistory"))
        {
            ArrayList<MedicalHistory> medHist = (ArrayList<MedicalHistory>)storage.get("progressReports");       
            returnList = (List<T>)medHist;               
        }
        else if(type.equals("allergies"))
        {
            for(Document allergy : (List<Document>)storage.get("allergies"))
            {
                returnList.add((T)new String(allergy.getString("allergy")));
            }     
        }        

        //TODO: Convert to switch
        // for(Document patient : patients)
        // {
        //     List<Document> insideArray = (List<Document>) patient.get(type);
            
        //     if(type.equals("symptoms"))
        //     {
        //         for (Document symptom : insideArray) {
        //             returnList.add((T)new Symptoms(symptom.getString("name")));
        //         }
        //     }
        //     else if(type.equals("progressReports"))
        //     {
        //         for(Document progRep : insideArray)
        //         {
        //             returnList.add((T)new ProgressReport(progRep.getString("nurseName"), progRep.getString("date"), progRep.getString("note")));
        //         }                
        //     }
        //     else if(type.equals("medicalHistory"))
        //     {
        //         for (Document medHis : insideArray) {
        //             returnList.add((T)new MedicalHistory(medHis.getString("date"), medHis.getString("reason")));
        //         }
                
                    
        //     }
        //     else if(type.equals("allergies"))
        //     {
        //         for (Document allergy : insideArray) {
        //             returnList.add((T)allergy.getString("allergy"));
        //         }                
        //     }
            
        // }
        
        return (List<T>)returnList;  
    }
    
 // ---------------------------------------------------------
   /*
    Author: Tyler Reilly
    Description: Updates one or many of the found documents with update.
    TODO: Refactor without objModel
    */    
    public Boolean update(Staff_Model user, Patient patient, String type, Object objModel)
    {
        //Query
        Document patientID = new Document();
        patientID.put("id", patient.getID());
        
        Boolean successful = false;
        MongoCollection<Document> collection = database.getCollection("patients");
        Document update = new Document();
        
        //TODO: Convert to switch
        if(user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.DOCTOR)
        {
            if(type.equals("medications"))
            {
                for(int i = 0; i < patient.getMedications().size(); i++)
                {
                    createMedications(patient.getMedications().get(i), user, collection,patientID);
                    successful = true;
                }
            }
            else if(type.equals("diagnosis"))
            {
                for(int i = 0; i < patient.getDiagnosis().size(); i++)
                {
                    createDiagnosis(patient.getDiagnosis().get(i),user, collection, patientID);
                    successful = true;
                }
            }
            else if(type.equals("tests"))
            {
                for(int i = 0; i <patient.getTests().size(); i++)
                {
                    createTestsProcedures(patient.getTests().get(i), user, collection, patientID);
                    successful = true;
                }
            }
        }
        else if(user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.NURSE)
        {
            if(type.equals("progressReport"))
            {
                ProgressReport progress = (ProgressReport)objModel;
                createProgressReports(progress, user, collection, patientID);
                successful = true;
            }
            else if(type.equals("admitted"))
            {
                String admission = (String)objModel;
                update.put("admitted", admission);
                updateDocument(collection, patientID, update, false);
                successful = true;
            }
        }
        //TODO: Implement Billing
        else if(user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.BILLING)
        {
            
        }
        
        return successful;        
    }
 
            
    
    public Boolean updateDocument(MongoCollection<Document> collection, Document find, Document update, Boolean multiple)
    {        
        Boolean successUpdate = false;
        
        if(multiple)
        {
            collection.updateMany(eq(find), new Document("$set", update));
            successUpdate = true;            
        }
        else
        {
            collection.updateOne(eq(find), new Document("$set", update));
            successUpdate = true;
        }
        
        return successUpdate;
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
    public String createPatient(Patient patient, Staff_Model user)
    {
        MongoCollection<Document> patientCollection = database.getCollection("patients");            
            
        Document document = new Document();        
       
        String state = "SUCCESSFUL";
       
        
        
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
            document.put("id", patient.getID());        
            document.put("provider", patient.getProvider());   
            document.put("gender", patient.getGender());
            //document.put("dateOfBirth", patient.getDateOfBirth());
            
            patientCollection.insertOne(document);      
            
            Document patientID = new Document();
            patientID.put("id", patient.getID());
            
            createAddress(patient.getAddress(), patientCollection, patientID);

            for(int i = 0; i<patient.getSymptoms().size(); i++)
            {
                createSymptoms(patient.getSymptoms().get(i), patientCollection, patientID);
            }
            for(int i = 0; i<patient.getAllergies().size(); i++)
            {
                createAllergies(patient.getAllergies().get(i), user, patientCollection, patientID);
            }
            
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
    // Description: Helper Method for createPatient
    TODO: Way to do it without creating? Instead return nested doc?
    */
    public static void createAddress(Address address, MongoCollection<Document> patientCollection, Document find)
    {        
        
        Document addr = new Document();
        
        addr.put("street", address.getStreet());
        addr.put("city", address.getCity());
        addr.put("state", address.getState());
        addr.put("zipcode", address.getZipcode());
        addr.put("country", address.getcountry());
        
        patientCollection.updateOne(find, new Document("$push", new Document("address", addr)));
    } 
    
    /*
    Author: Tyler Reilly
    Description: Helper Method for createPatient
    */
    public void createMedicalHistory(MedicalHistory medicalHistory, Document find)
    {
        
        MongoCollection<Document> patientCollection = database.getCollection("patients");      

        Document medDoc = new Document();        
        
        medDoc.put("date", medicalHistory.getDate());
        medDoc.put("hospitalization", medicalHistory.getReason());
        
        patientCollection.updateOne(find, new Document("$push", new Document("medicalHistory", medDoc)));      
    }
    
    /*
    Author: Tyler Reilly
    Description: Helper Method for createPatient
    */
    public static void createProgressReports(ProgressReport progressRep, Staff_Model staff, MongoCollection<Document> patientCollection, Document find)
    {       
        if(staff.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.NURSE)
        {
            Document progressDoc = new Document();

            progressDoc.put("nurse", progressRep.getNurseName());
            progressDoc.put("nurseID", "" + staff.getID());
            progressDoc.put("date", progressRep.getDate());
            progressDoc.put("report", progressRep.getNote());

            patientCollection.updateOne(find, new Document("$push", new Document("progressReports", progressDoc)));        
        }
    }
    
     /*
    Author: Tyler Reilly
    Description: Called when a doctor assigns a diagnosis for a patient
    */
    public static void createDiagnosis(Diagnoses diagnosis, Staff_Model staff, MongoCollection<Document> patientCollection, Document find)
    {
        if(staff.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.DOCTOR)
        {
            Document diagnosisDoc = new Document();
        
            diagnosisDoc.put("diagnostic", diagnosis.toString());
            diagnosisDoc.put("doctorID", staff.getID());

            patientCollection.updateOne(find, new Document("$push", new Document("diagnosis", diagnosisDoc)));
        }
        
    }
    
    /*
    Author: Tyler Reilly
    Description: Called when a doctor is ordering tests for patient
    */
    public static void createTestsProcedures(Tests_procedures tests, Staff_Model user, MongoCollection<Document> patientCollection, Document find)
    {
        if(user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.DOCTOR)
        {
            Document testsDoc = new Document();
            
            testsDoc.put("test", tests.toString());
            testsDoc.put("doctorID", user.getID());

            patientCollection.updateOne(find, new Document("$push", new Document("tests", testsDoc)));
        }       
    }
    
    /*
    Author: Tyler Reilly
    Description: Called when a doctor is assigning medication for a patient    
    */
    public static void createMedications(Medications medication, Staff_Model user, MongoCollection<Document> patientCollection, Document find)
    {
        if(user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.DOCTOR)
        {
            Document medDoc = new Document();
            
            medDoc.put("medication", medication.toString());
            medDoc.put("doctorID", user.getID());

            patientCollection.updateOne(find, new Document("$push", new Document("medications", medDoc)));
        }       
    }
    
    public static void createAllergies(String allergy, Staff_Model user, MongoCollection<Document> patientCollection, Document find)
    {
        
        Document allergyDoc = new Document();

        allergyDoc.put("allergy", allergy);

        patientCollection.updateOne(find, new Document("$push", new Document("allergies", allergyDoc)));
             
    }
}
    
    

