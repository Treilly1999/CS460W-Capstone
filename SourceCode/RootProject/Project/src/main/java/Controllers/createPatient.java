/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Scanner;
import org.bson.Document;

/**
 *
 * @author Tyler
 */
public class createPatient {
    
    public static void startTestCreate()
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("hospital");
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Do you want to create a patient profile? If yes, enter 0. Else 1.");
        int opt = sc.nextInt();
        
        if(opt == 0)
        {
            System.out.println("Enter patient name.");
            String name = sc.nextLine();
            System.out.println("Enter patient age.");
            int age = sc.nextInt();
            System.out.println("Enter patient phone number.");
            String phoneNumber = sc.nextLine();
            System.out.println("Enter patient's physician.");
            String physicianName = sc.nextLine();
            System.out.println("Enter patient physician's phone number.");
            String physicianNumber = sc.nextLine();
            System.out.println("What is the patient's SSN?");
            int ssn = sc.nextInt();
            createPatient(database, name, age, phoneNumber, ssn, physicianName, physicianNumber); 
        }
        
        
    }
    
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
