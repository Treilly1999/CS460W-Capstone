package UHART.Controllers;

import UHART.Models.Address;
import UHART.Models.Bill;

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
import UHART.Models.Search;
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
 import UHART.Controllers.AES256;
/**
 *
 * @author Tyler
 * 
 * This is a class library that is used in the controllers for database connectivity.
 * 
 */
public class DBConnection {
    
    //TODO: Research MongoDB date object    
    private AES256 aes = new AES256();
  
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> patientCollection; 
    public DBConnection()
    {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("hospital");
        patientCollection = database.getCollection("patients");  
    }
    
    
    
    public MongoDatabase getDB() { return database; }  
    
    /*
    Author: Tyler
    Description: Get names for search table. Once a name is clicked on the table, it will build the model for the patient.
    
    USE PATIENT SSN || NAME || ID to search
    */
    public ArrayList<Search> parsePatients()
    {       
        
        ArrayList<Search> patientList = new ArrayList<Search>();
          
        
        ArrayList<Document> patients = new ArrayList<Document>();
        
        FindIterable<Document> findIterable;
        
        
        findIterable = patientCollection.find();        
        
        try
        (MongoCursor<Document> cursor = findIterable.iterator()) {
            while(cursor.hasNext())
            {               
                patients.add(cursor.next());
            }
        }      
        
        for(Document patient: patients)
        {
            patientList.add(buildNameForSearch(patient));
        }

        return patientList;
    }

    /*
    Author: Tyler Reilly
    Description: Get names for search table. Once a name is clicked on the table, it will build the model for the patient.
    */
    public Search buildNameForSearch(Document patient)
    {
        String name;
        int ssn;

        name = patient.getString("name");
        ssn = Integer.parseInt(aes.decrypt(patient.getString("ssn")));

        Search search = new Search(name, ssn, patient);
        return search;
    }

    
    //Return single patient
    public Patient parsePatient(Document query)
    {       
        
        
        FindIterable<Document> findIterable;
                
        findIterable = patientCollection.find(query);
              
        Document patientDoc = new Document();
        
        try(MongoCursor<Document> cursor = findIterable.iterator()) 
        {
            while(cursor.hasNext())
            {               
                patientDoc = cursor.next();
            }
        }             
       
        Patient patient = buildPatient(patientDoc);
        

        return patient;
    }

    /*
     Author: Tyler
     Description: Builds individual patient's models based on the patients parsed
     from parsePatients.
     */
    public Patient buildPatient(Document patients)
    {          
                
        String name = "", physicianName = "", physicianNumber = "",
              dischargeInstructions = "",  assignedDoctor = "",
              provider = "", phoneNumber = "", id = "";
        int age = 1, ssn = 1;
        Boolean admitted = false, checkedOut = false;   
        //TODO: Convert to date
        Date dateOfBirth = new Date();
        Date dateAdmitted = new Date();
        Date dateLeft = new Date();
        String gender = "";
        Address address = new Address();
        List<MedicalHistory> medicalHistory = new ArrayList<MedicalHistory>();
        List<Symptoms> symptoms = new ArrayList<Symptoms>();
        List<ProgressReport> progressReports = new ArrayList<ProgressReport>();
        List<String> allergies = new ArrayList<String>();
        List<String> medications = new ArrayList<String>();
        List<String> diagnosis = new ArrayList<String>();
        List<String> tests = new ArrayList<String>();
        Bill bill = new Bill(false);
        try
        {

            name = patients.getString("name");
            //age = patients.getInteger("age");
            phoneNumber = patients.getString("phoneNumber");
            //ssn = patients.getInteger("ssn");
            physicianName = patients.getString("physicianName");
            physicianNumber = patients.getString("physicianNumber");  
            admitted = patients.getBoolean("admitted");
            checkedOut = patients.getBoolean("checkedOut");
            dischargeInstructions = patients.getString("dischargeInstructions");
            assignedDoctor = patients.getString("assignedDoctor");       
            provider = aes.decrypt(patients.getString("provider"));
            id = patients.getString("id");  
            gender = patients.getString("gender");
            dateOfBirth = patients.getDate("dateOfBirth");           
            allergies = buildLists(patients, "allergies");

            if(patients.toString().contains("medicalHistory"))
            {
                medicalHistory = buildLists(patients, "medicalHistory"); 
            }               
            
            symptoms = buildLists(patients, "symptoms");              
            
            
            if(patients.toString().contains("medications"))
            {
                medications = buildLists(patients, "medications");
            }           
            
            if(patients.toString().contains("diagnosis"))
            {
                diagnosis = buildLists(patients, "diagnosis");
            }     
           
            if(patients.toString().contains("progressReports"))
            {
                progressReports = buildLists(patients, "progressReports"); 
            } 

            if(patients.toString().contains("tests"))
            {
                tests = buildLists(patients, "tests"); 
            }   
            
            dischargeInstructions = patients.getString("dischargeInstructions");
            

            for(Document addr : (List<Document>)patients.get("address"))
            {
                address = new Address(aes.decrypt(addr.getString("street")), aes.decrypt(addr.getString("zipcode")), 
                aes.decrypt(addr.getString("state")), aes.decrypt(addr.getString("country")), aes.decrypt(addr.getString("city")));
            }

            if(patients.toString().contains("bill") && patients.toString().contains("cost"))
            {
                for(Document eachBill : (List<Document>)patients.get("bill"))
                {
                    bill.addPrice(eachBill.getInteger("cost"));
                }  
            }
            else if(patients.toString().contains("bill") && patients.toString().contains("paid"))
            {
                for(Document eachBill : (List<Document>)patients.get("bill"))
                {
                    if(eachBill.getBoolean("paid"))
                        bill.markPaid();
                }  
            }   
            
            // try{                
            //     dateAdmitted = patients.getDate("dateAdmitted");
            // }
            // catch(Exception e)
            // {
            //     System.out.println("Patient not admitted.");
            // }
            // try{
                
            //     dateLeft = patients.getDate("dateLeft");
            // }
            // catch(Exception e)
            // {
            //     System.out.println("Patient either not admitted or still in hospital.");
            // }
            
            
          } catch (Exception e)
          {
              System.out.println(e);
              System.out.println("Some query parameters were not found in " + name + " profile");
          }
    
        // Document patientID = new Document();
        // patientID.put("patientID", "" + id);  
        
        Patient patient = new Patient(id, name,  dateOfBirth,  phoneNumber, Integer.parseInt(aes.decrypt(patients.getString("ssn"))),  physicianName, 
                physicianNumber, provider,  symptoms,  assignedDoctor,  admitted, medicalHistory,  progressReports, 
                dischargeInstructions, gender, address, allergies, medications, diagnosis, bill, dateAdmitted, dateLeft,
                tests, checkedOut);
        return patient;
      
    } 
    
    /*
    Author: Tyler Reilly
    Description: Builds the medical history,  symptoms, and progress reports for patient. 
    */
    public <T> List<T> buildLists(Document storage, String type)
    {
        
        List<T> returnList = new ArrayList<T>();           
        
        switch(type)
        {
            case "symptoms" : 
                for(Document symptom : (List<Document>)storage.get("symptoms"))
                {
                    returnList.add((T)new Symptoms(symptom.getString("name")));
                }
                break;
            case "progressReports": 
                for(Document prog : (List<Document>)storage.get("progressReports"))
                {
                    returnList.add((T)new ProgressReport(prog.getString("nurse"), aes.decrypt(prog.getString("date")), aes.decrypt(prog.getString("report"))));
                }
                break;
            case "medicalHistory":
                for(Document medHist : (List<Document>) storage.get("medicalHistory"))
                {
                    returnList.add((T)new MedicalHistory(aes.decrypt(medHist.getString("date")), aes.decrypt(medHist.getString("hospitalization"))));
                }  
                break;
            case "allergies":
                for(Document allergy : (List<Document>)storage.get("allergies"))
                {
                    returnList.add((T)new String(allergy.getString("allergy")));
                } 
                break;
            case "medications":
                for(Document medication : (List<Document>)storage.get("medications"))
                {
                    returnList.add((T)(aes.decrypt(medication.getString("medication"))));
                }   
                break;
            case "diagnosis":
                for(Document diagnostic : (List<Document>)storage.get("diagnosis"))
                {
                    returnList.add((T)(aes.decrypt(diagnostic.getString("diagnostic"))));
                }  
                break;
            case "tests":
                for(Document test : (List<Document>)storage.get("tests"))
                {
                    returnList.add((T)(aes.decrypt(test.getString("test"))));
                }
                break;
        }     
                  
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
                    createMedications(patient.getMedications().get(i), user, patientID);
                    successful = true;
                }
            }
            else if(type.equals("diagnosis"))
            {
                for(int i = 0; i < patient.getDiagnosis().size(); i++)
                {
                    createDiagnosis(patient.getDiagnosis().get(i).toString(),user, patientID);
                    successful = true;
                }
            }
            else if(type.equals("tests"))
            {
                for(int i = 0; i <patient.getTests().size(); i++)
                {
                    //createTestsProcedures(patient.getTests().get(i), user, collection, patientID);
                    successful = true;
                }
            }
        }
        else if(user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.NURSE)
        {
            if(type.equals("progressReport"))
            {
                ProgressReport progress = (ProgressReport)objModel;
                createProgressReports(progress, user, patientID);
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
    public void removeEntry(Document query, Boolean allMatch)
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

    public void markBillPaid(Document query)
    {
        
        Document bill = new Document();
        bill.put("paid", true);

        patientCollection.updateOne(query, new Document("$pull", new Document("bill", new Document())));
        patientCollection.updateOne(query, new Document("$push", new Document("bill", bill)));
    }
    
//--------------------------------------------------  
    /*
    Author: Tyler Reilly
    Description: Creates a patient using a patient object.
    TODO: Return boolean
    */
    public String createPatient(Patient patient, Staff_Model user)    {          
            
        Document document = new Document();        
       
        String state = "SUCCESSFUL";  
        
        try
        {
            document.put("name", patient.getName());
            document.put("dateOfBirth", patient.getDateOfBirth());
            document.put("phoneNumber", patient.getPhone());
            document.put("ssn", aes.encrypt("" + patient.getSSN()));
            document.put("physicianName", patient.getPhysician());
            document.put("physicianNumber", patient.getPhysicianNumber());        
            document.put("id", patient.getID());        
            document.put("provider", aes.encrypt(patient.getProvider()));   
            document.put("gender", patient.getGender());
            document.put("admitted", false);
            document.put("checkedOut", false);
            document.put("dischargeInstructions", "");
            document.put("assignedDoctor", "");            
            
            patientCollection.insertOne(document);      
            
            Document patientID = new Document();
            patientID.put("id", patient.getID());
            
            createAddress(patient.getAddress(), patientID);
            createDiagnosis("", user, patientID);
            createMedications("", user, patientID);

            for(int i = 0; i<patient.getSymptoms().size(); i++)
            {
                createSymptoms(patient.getSymptoms().get(i), patientID);
            }
            for(int i = 0; i<patient.getAllergies().size(); i++)
            {
                createAllergies(patient.getAllergies().get(i), user, patientID);
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
    */
    public void createSymptoms(Symptoms symptoms, Document find)
    {        
        
        Document sympt = new Document();
        
        sympt.put("name", symptoms.getSymptom());
        
        patientCollection.updateOne(find, new Document("$push", new Document("symptoms", sympt)));
    } 
    
    /*
    Author: Tyler Reilly
    // Description: Helper Method for createPatient
    */
    public void createAddress(Address address, Document find)
    {        
        
        Document addr = new Document();
        
        addr.put("street", aes.encrypt(address.getStreet()));
        addr.put("city", aes.encrypt(address.getCity()));
        addr.put("state", aes.encrypt(address.getState()));
        addr.put("zipcode", aes.encrypt(address.getZipcode()));
        addr.put("country", aes.encrypt(address.getcountry()));
        
        patientCollection.updateOne(find, new Document("$push", new Document("address", addr)));
    } 
    
    /*
    Author: Tyler Reilly
    Description: Helper Method for createPatient
    */
    public void createMedicalHistory(MedicalHistory medicalHistory, Document find)
    {    

        Document medDoc = new Document();        
        
        medDoc.put("date", aes.encrypt(medicalHistory.getDate()));
        medDoc.put("hospitalization", aes.encrypt(medicalHistory.getReason()));
        
        patientCollection.updateOne(find, new Document("$push", new Document("medicalHistory", medDoc)));      
    }
    
    /*
    Author: Tyler Reilly
    Description: Helper Method for createPatient
    */
    public void createProgressReports(ProgressReport progressRep, Staff_Model staff, Document find)
    {       
        if(staff.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.NURSE)
        {
            Document progressDoc = new Document();

            progressDoc.put("nurse", progressRep.getNurseName());
            progressDoc.put("nurseID", "" + staff.getID());
            progressDoc.put("date", aes.encrypt(progressRep.getDate()));
            progressDoc.put("report", aes.encrypt(progressRep.getNote()));

            patientCollection.updateOne(find, new Document("$push", new Document("progressReports", progressDoc)));        
        }
    }
    
     /*
    Author: Tyler Reilly
    Description: Called when a doctor assigns a diagnosis for a patient
    */
    public void createDiagnosis(String diagnosis, Staff_Model staff, Document find)
    {
        if(staff.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.DOCTOR)
        {
            Document diagnosisDoc = new Document();
        
            diagnosisDoc.put("diagnostic", aes.encrypt(diagnosis));
            diagnosisDoc.put("doctorID", staff.getID());

            patientCollection.updateOne(find, new Document("$push", new Document("diagnosis", diagnosisDoc)));
        }
        
    }
    
    /*
    Author: Tyler Reilly
    Description: Called when a doctor is ordering tests for patient
    */
    public void createTestsProcedures(Tests_procedures tests, Staff_Model user, Document find)
    {
        if(user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.DOCTOR)
        {
            Document testsDoc = new Document();
            
            testsDoc.put("test", aes.encrypt(tests.toString()));
            testsDoc.put("doctorID", user.getID());

            patientCollection.updateOne(find, new Document("$push", new Document("tests", testsDoc)));
        }       
    }
    
    /*
    Author: Tyler Reilly
    Description: Called when a doctor is assigning medication for a patient    
    */
    public void createMedications(String medication, Staff_Model user, Document find)
    {
        if(user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.DOCTOR)
        {
            Document medDoc = new Document();
            
            medDoc.put("medication", aes.encrypt(medication));
            medDoc.put("doctorID", user.getID());

            patientCollection.updateOne(find, new Document("$push", new Document("medications", medDoc)));
        }       
    }
    
    public static void createAllergies(String allergy, Staff_Model user, Document find)
    {
        
        Document allergyDoc = new Document();

        allergyDoc.put("allergy", allergy);

        patientCollection.updateOne(find, new Document("$push", new Document("allergies", allergyDoc)));
             
    }

    public void addBill(int price, Document find)
    {
        
        Document billDoc = new Document();

        billDoc.put("cost", price);

        patientCollection.updateOne(find, new Document("$push", new Document("bill", billDoc)));
             
    }

    public void admit(Document find)
    {
        patientCollection.updateOne(find, new Document("$set", new Document("admitted", true)));
        patientCollection.updateOne(find, new Document("$set", new Document("checkedOut", false)));
    }

    public void changePassword(Staff_Model user, String password)
    {
        MongoCollection<Document> staffCollection = getDB().getCollection("staff");
        staffCollection.updateOne(new Document("id", "" + user.getID()), new Document("$set", new Document("password", password)));
    }

    public void checkOut(Document find)
    {
        patientCollection.updateOne(find, new Document("$set", new Document("admitted", false)));
        patientCollection.updateOne(find, new Document("$set", new Document("checkedOut", true)));
    }

    public void createDischargeInstructions(Document find, String instructions)
    {
        patientCollection.updateOne(find, new Document("$set", new Document("dischargeInstructions", instructions)));
    }

    
}
    
    

