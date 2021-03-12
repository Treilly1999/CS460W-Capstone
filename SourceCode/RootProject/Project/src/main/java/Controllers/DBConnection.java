package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 import com.mongodb.*;
 import com.mongodb.MongoClient;
 import Models.Patient;
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
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("hospital");
        
        startQuery(database);
        
    }  
    
    /*
    Author: Tyler
    Description: For testing purposes.
    */
    public static void startQuery(MongoDatabase database)
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
        
        parsePatients(database, query);  
       
        patientList.forEach((n) -> System.out.println(n));
        
        System.out.println("Press 1 to search for more. Press 2 to exit");
        int cont = sc.nextInt();
        if(cont == 1)
        {
            startQuery(database);
        }
    }
    
    public static void parsePatients(MongoDatabase database, Document query)
    {
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
            .withKeyValueSeparator("=")
            .split(documentString);
        
        String name = queryParameters.get("name");
        int age = Integer.parseInt(queryParameters.get("age"));
        String phoneNumber = queryParameters.get("phoneNumber");
        int ssn = Integer.parseInt(queryParameters.get("ssn"));
        String physicianName = queryParameters.get("physicianName");
        String physicianNumber = queryParameters.get("physicianNumber");
        
        patientList.add(new Patient(name, age, phoneNumber, ssn, physicianName, physicianNumber));
      
    }   
}
    
    

