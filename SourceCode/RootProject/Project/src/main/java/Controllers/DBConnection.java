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
 */
public class DBConnection {
    
    static ArrayList<Patient> patientList = new ArrayList<Patient>();
    
    public ArrayList<Patient> getPatients()
    {
        return patientList;
    }
    
    public static  MongoClient mongoClient = new MongoClient("localhost", 27017);
    public static MongoDatabase database = mongoClient.getDatabase("hospital");
    
    public static MongoDatabase getDB() { return database; }
    
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
        System.out.println(patients.size());
        //patients.forEach((n) -> buildPatients(n));
    }
    
    /*
    Author: Tyler
    Description: Parses the database for all patients or specific patients that
    match a query. 
    */
    public static ArrayList<Patient> parsePatients(Document query)
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
    public static void buildPatients(Document patients, MongoCollection<Document> collection)
    {
          
                
        String name = "", physicianName = "", physicianNumber = "",
              dischargeInstructions = "",  assignedDoctor = "",
              provider = "", phoneNumber = "", id = "";
        int age = 1, ssn = 1;
        Boolean admitted = false;   
        
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
            
          } catch (Exception e)
          {
              System.out.println("Some query parameters were not found in " + name + " profile");
          }
        
        Document patientID = new Document();
        patientID.put("patientID", "" + id);
        
        List<MedicalHistory> medicalHistory = buildLists(patients, "medicalHistory", collection);           
        List<Symptoms> symptoms = buildLists(patients, "symptoms", collection); 
        List<ProgressReport> progressReports = buildLists(patients, "progressReports", collection); 
       
        
        patientList.add(new Patient(id, name,  age,  phoneNumber, ssn,  physicianName, 
                physicianNumber, provider,  symptoms,  assignedDoctor,  admitted, 
                medicalHistory,  progressReports, dischargeInstructions));
        
      
    } 
    
    /*
    Author: Tyler Reilly
    Description: Buildsthe medical history,  symptoms, and progress reports for patient. 
    TODO: Refactor to where the collection isnt queried each time.
    */
    public static <T> List<T> buildLists(Document storage, String type, MongoCollection<Document> collection)
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
    public static void updateTest()
    {
        
       // MongoClient mongoClient = new MongoClient("localhost", 27017);
        //MongoDatabase database = mongoClient.getDatabase("hospital");

        
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
    
//----------------------------------------------------
    
     /*
    Author: Tyler
    Description: Testing method that uses createPatient from console inputs
    */
    public static void startTestCreate(Boolean firstTry)
    {
        //MongoClient mongoClient = new MongoClient("localhost", 27017);
        //MongoDatabase database = mongoClient.getDatabase("hospital");
        
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
            
            //createPatient(database, name, age, phoneNumber, ssn, physicianName, physicianNumber); 
            
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
    TODO: ADD PATIENT LISTS
    */
    public static void createPatient(String name, int age, String phoneNumber, 
            int ssn, String physicianName, String physicianNumber, Boolean admitted, int id, String assignedDoctor,
            String dischargeInstructions, String provider, List<Symptoms> symptoms, 
            List<MedicalHistory> medicalHistory, List<ProgressReport> progressReports)
    {
        MongoCollection<Document> patientCollection = database.getCollection("patients");            
            
        Document document = new Document();
        
        document.put("name", name);
        document.put("age", age);
        document.put("phoneNumber", phoneNumber);
        document.put("ssn", ssn);
        document.put("physicianName", physicianName);
        document.put("physicianNumber", physicianNumber);
        
        if(admitted)
            document.put("admitted", "true");
        else
            document.put("admitted", "false");
        
        document.put("assignedDoctor", assignedDoctor);
        document.put("dischargeInstructions", dischargeInstructions);
        
        String _id = "" + id;        
        document.put("id", _id);
        
        document.put("provider", provider);    
        
        patientCollection.insertOne(document);
        
    }
    
    /*
    TODO: Create id assignment function
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
            
            patientCollection.insertOne(document);      
            
            for(int i = 0; i<patient.getSymptoms().size(); i++)
            {
                createSymptoms(patient.getSymptoms().get(i), patientCollection, new Document(eq("id", id.toString())));
            }
            
        } catch (Exception e)
        {
            state = "FAILURE";
        }
        
        return state;
    }   
    
    public static void createSymptoms(Symptoms symptoms, MongoCollection<Document> patientCollection, Document find)
    {        
        
        Document sympt = new Document();
        
        sympt.put("name", symptoms.getSymptom());
        
        patientCollection.updateOne(find, new Document("$push", new Document("symptoms", sympt)));
    } 
    
    
    //TODO: Refactor medical history and progress reports to match createSymptoms
    public static void createMedicalHistory(String patientID, String date, String reason)
    {
        
        MongoCollection<Document> medicalCollection = database.getCollection("medicalHistory");
        
        Document medDoc = new Document();
        
        String id = "" + patientID;
        
        medDoc.put("patientID", id);
        medDoc.put("date", date);
        medDoc.put("hospitalization", reason);
        
        medicalCollection.insertOne(medDoc);        
    }
    
    public static void createProgressReports(String patientID, String nurseName, int nurseID, String date, String report)
    {
        
        MongoCollection<Document> reportCollection = database.getCollection("progressReports");
        
        Document progressDoc = new Document();
        
        progressDoc.put("patientID", "" + patientID);
        progressDoc.put("nurse", nurseName);
        progressDoc.put("nurseID", "" + nurseID);
        progressDoc.put("date", date);
        progressDoc.put("report", report);
        
        reportCollection.insertOne(progressDoc);        
    }
    
    
    
}
    
    

