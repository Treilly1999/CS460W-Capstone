/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Tyler
 */
public class Update 
{
    
     static MongoClient mongoClient = new MongoClient("localhost", 27017);
     static MongoDatabase database = mongoClient.getDatabase("hospital");
    
    public static void updatePatient(String collectionName, Document before, Document after, Boolean isMultiple)
    {
        
        MongoCollection<Document> collection = database.getCollection(collectionName);    
        
        if(isMultiple == true)
        {
            collection.updateMany(before, after);
            System.out.println("Update all entries from " + before.toString() + " to " + after.toString());
        }
        else
        {
            collection.updateOne(before, after);
            System.out.println("Updated the entry where " + before.toString()
            + " is met and changed it to " + after.toString());
        }
        
    }
    
    
}
