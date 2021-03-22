package login;

import Models.Staff_Model;
import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.hash.Hashing;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.bson.Document;

/** Controls the login screen */
public class LoginController {
   @FXML private TextField user;
   @FXML private PasswordField password;
   @FXML private Button loginButton;
   @FXML private Label errorMessage;

   
   
   
    private String sha256hex;
    
    //DO THIS IN INITmANAGER
    Document staffQuery = new Document();
    private static Staff_Model currentUser;
    
    public static Staff_Model getCurrentUser()
    {
        return currentUser;
    }

    static ArrayList<Staff_Model> currentUserList = new ArrayList<Staff_Model>();
    
    

  public void initialize() {}
  
  /*
  Author: Tyler
  Description: Initializes the login manager.
  */
  public void initManager(final LoginManager loginManager) {
    
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
            staffQuery.put("userName", user.getText());

                        
            try
            {
                currentUser = findStaffMember(staffQuery);  

                if(currentUser.getUSER_ROLE() == Models.Staff_Model.USER_ROLE.DEFAULT)
                {
                    errorMessage.setText("Unauthorized Access. Contact your system administrator.");
                    errorMessage.setTextFill(Color.web("#0076a3"));
                }
                //System.out.println("BEFORE AUTHORZE");
                Boolean accepted = authorize(currentUser);
                //System.out.println("AFTER AUTHORZE");
                if (accepted) {
                  loginManager.authenticated(currentUser);
                }
            }
            catch (Exception e)
            {
                //System.out.println("STAFF MEMBER NOT FOUND");
                //TODO: WHAT TO DO IF LOGIN FAIL
                //loginManager.showLoginScreen(); 
                errorMessage.setText("Username/Password inccorect.");
                errorMessage.setTextFill(Color.web("#0076a3"));
            }
        }
    });
    
  }
  
    /*
    Author: Tyler
    Description: Finds the staff member in the login query, builds a current user profile from
    staff_model and decides to log them in or not.
    */
    public Staff_Model findStaffMember(Document query)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("hospital");
        MongoCollection<Document> collection = database.getCollection("staff");
        
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
    */
    public static Staff_Model buildCurrentUser(Document staffMember)
    {
        String documentString = staffMember.toString();
        //System.out.println(documentString);
        
        Staff_Model staff;
        
        Map<String,String> queryParameters = Splitter
            .on(", ")
            .trimResults(CharMatcher.is('}'))
            .withKeyValueSeparator("=")
            .split(documentString);
        
        String name = "", userName = "", password="";
        int id = 0;
        
        Models.Staff_Model.USER_ROLE role = Models.Staff_Model.USER_ROLE.DEFAULT;
        //= queryParameters.get("user_role");
       
        if(queryParameters.get("user_role").equals("NURSE"))            
            role = Models.Staff_Model.USER_ROLE.NURSE;                        
        if(queryParameters.get("user_role").equals("DOCTOR"))
            role = Models.Staff_Model.USER_ROLE.DOCTOR;
        if(queryParameters.get("user_role").equals("REGISTER"))
            role = Models.Staff_Model.USER_ROLE.REGISTER;
        if(queryParameters.get("user_role").equals("BILLING"))
            role = Models.Staff_Model.USER_ROLE.BILLING;        
       
        
        //System.out.println(queryParameters.get("user_role"));
        try
        {
            name = queryParameters.get("name");
            id = Integer.parseInt(queryParameters.get("id"));
            userName = queryParameters.get("userName");
            password = queryParameters.get("password");
            
            
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
  private Boolean authorize(Staff_Model staffMember) {
       //System.out.println("INSIDE AUTHORZE");
       //System.out.println("Username: " + staffMember.getUserName() + ". Password: " + staffMember.getPassword());
       
       //TODO: ADD SALT TO PASSWORD TO MAKE EVERY PASSWORD DIFFERENT
       sha256hex = Hashing.sha256().hashString(password.getText(), StandardCharsets.UTF_8).toString();
    
       //System.out.println("AFTER HASHING: " + sha256hex);
       
       return staffMember.getUserName().equals(user.getText()) && staffMember.getPassword().equals(sha256hex);
  }
 
}