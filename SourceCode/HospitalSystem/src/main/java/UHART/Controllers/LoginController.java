package UHART.Controllers;

import UHART.Controllers.DBConnection;
import UHART.Models.Staff_Model;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

/** Controls the login screen */
public class LoginController {
  
    
    Document staffQuery = new Document();
    private Staff_Model currentUser;
    DBConnection db = new DBConnection();

    public LoginController(Document query)
    {
        currentUser = findStaffMember(query);
    }
    
    public Staff_Model getCurrentUser()
    {
        return currentUser;
    }     
    
              
    /*
    Author: Tyler
    Description: Finds the staff member in the login query, builds a current user profile from
    staff_model and decides to log them in or not.
    */
    public Staff_Model findStaffMember(Document query)
    {
        //MongoClient mongoClient = new MongoClient("localhost", 27017);
        //MongoDatabase database = mongoClient.getDatabase("hospital");
        MongoCollection<Document> collection = db.getDB().getCollection("staff");
        System.out.println("IN findStaffMember");
        Staff_Model staffMember = new Staff_Model();
            
        FindIterable<Document> findIterable = findIterable = collection.find(query);

        MongoCursor<Document> cursor = findIterable.iterator();

        Document staff = new Document();

        try
        {
            while(cursor.hasNext())
            {               
                staff = cursor.next();
            }
        } finally {
            cursor.close();
        }
              
        staffMember = buildCurrentUser(staff);
        
        //System.out.println(staffMember.getUSER_ROLE());
        
        return staffMember;
    }
  
    /*
    Author: Tyler
    Description: Builds a Staff_Model for the current user
    TODO: Consolidate with parsePatients? Less code && makes them more dynamic
    */
    public static Staff_Model buildCurrentUser(Document staffMember)
    {
                
        Staff_Model staff;
        
        String name = "", userName = "", password="";
        int id = 0;
        
        UHART.Models.Staff_Model.USER_ROLE role = UHART.Models.Staff_Model.USER_ROLE.DEFAULT;
        //= queryParameters.get("user_role");
       
        if(staffMember.getString("user_role").equals("NURSE"))            
            role = UHART.Models.Staff_Model.USER_ROLE.NURSE;                        
        if(staffMember.getString("user_role").equals("DOCTOR"))
            role = UHART.Models.Staff_Model.USER_ROLE.DOCTOR;
        if(staffMember.getString("user_role").equals("REGISTER"))
            role = UHART.Models.Staff_Model.USER_ROLE.REGISTER;
        if(staffMember.getString("user_role").equals("BILLING"))
            role = UHART.Models.Staff_Model.USER_ROLE.BILLING;        
       
        
        //System.out.println(queryParameters.get("user_role"));
        try
        {
            name = staffMember.getString("name");
            id = Integer.parseInt(staffMember.getString("id"));
            userName = staffMember.getString("userName");
            password = staffMember.getString("password");            
            
          } catch (Exception e)
          {
              System.out.println("Some query parameters were not found in " + name + " profile");
          }   
       
      //System.out.println("SUCCEDED");
      staff = new Staff_Model(role, id, name, userName, password);
      return staff;
    }

  /**
   * Check authorization credentials.
   */   
  public Boolean authorize(Staff_Model staffMember, String sha256hex, String userName) {
       //System.out.println("INSIDE AUTHORZE");
       //System.out.println("Username: " + staffMember.getUserName() + ". Password: " + staffMember.getPassword());
       
       //TODO: ADD SALT TO PASSWORD TO MAKE EVERY PASSWORD DIFFERENT       
       //System.out.println("AFTER HASHING: " + sha256hex);
       
       return staffMember.getUserName().equals(userName) && staffMember.getPassword().equals(sha256hex);
  }
 
}