/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.DBConnection.parsePatients;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.Scanner;
import org.bson.Document;

/**
 *
 * @author Tyler
 */
public class Update 
{
    
     static MongoClient mongoClient = new MongoClient("localhost", 27017);
     static MongoDatabase database = mongoClient.getDatabase("hospital");
    
    
    public static void updateTest()
    {
        Scanner sc = new Scanner(System.in);
        Document before = new Document();       
        Document afterQuery = new Document();
        
        System.out.println("What would you like to update?");
        String param1 = sc.nextLine();
        sc.nextLine();
        
        System.out.printf("What is the value of %s%n", param1);
        String param2 = sc.next();
        sc.nextLine();
        
        System.out.printf("What would you like to change %s%n to?", param1);
        String after = sc.next();
        
        before.put(param1, param2);
        afterQuery.put(param1, after);
        
        parsePatients(database, before);
        
        updateEntry("patients", param1, param2, param1, after, true);
        
        parsePatients(database, afterQuery);
    }
     
     
     /*
     Author: Tyler Reilly
     Description: Updates a single entry or all entries in the given collection.
     TODO: NEEDS DYNAMIC TYPES, Object type?
     */
    public static void updateEntry(String collectionName, String field, Object value, String afterField, Object afterValue, Boolean isMultiple)
    {
        
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
    
    
}
