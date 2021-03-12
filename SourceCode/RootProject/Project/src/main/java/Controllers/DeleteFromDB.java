/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Patient;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Tyler
 */
public class DeleteFromDB 
{
    
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("hospital");
    
    public static void removePatient(Patient id)
    {
        
    }
     
    //Requires more complexity based on other system req
    /*
    Author: Tyler
    Description: Removes an entry from specified DB based on the query provided,
    either deleting a specific entry or all that match.
    */
    public static void removeEntry(MongoDatabase database, Document query, Boolean allMatch)
    {
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
    
}
