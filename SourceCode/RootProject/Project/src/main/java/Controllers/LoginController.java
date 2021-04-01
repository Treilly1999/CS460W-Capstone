package Controllers;

import Controllers.DBConnection;
import Models.Staff_Model;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.bson.Document;

/** Controls the login screen */
public class LoginController {
   @FXML private TextField user;
   @FXML private PasswordField password;
   @FXML private Button loginButton;
   @FXML private Label errorMessage;
   @FXML ImageView logo;

    private String sha256hex;
    
    Document staffQuery = new Document();
    private static Staff_Model currentUser;
    
    public static Staff_Model getCurrentUser()
    {
        return currentUser;
    }    
  public void initialize() {}
  
  /*
  Author: Tyler
  Description: Initializes the login manager.
  */
  public void initManager(final LoginManager loginManager) throws FileNotFoundException {
    
    FileInputStream logoLocation = new FileInputStream("src/main/resources/logo_placeholder.png");
    Image image = new Image(logoLocation);
    logo.setImage(image);
      
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
                Boolean accepted = false; //authorize(currentUser);
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
        //MongoClient mongoClient = new MongoClient("localhost", 27017);
        //MongoDatabase database = mongoClient.getDatabase("hospital");
        MongoCollection<Document> collection = DBConnection.getDB().getCollection("staff");
        
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
        
        Models.Staff_Model.USER_ROLE role = Models.Staff_Model.USER_ROLE.DEFAULT;
        //= queryParameters.get("user_role");
       
        if(staffMember.getString("user_role").equals("NURSE"))            
            role = Models.Staff_Model.USER_ROLE.NURSE;                        
        if(staffMember.getString("user_role").equals("DOCTOR"))
            role = Models.Staff_Model.USER_ROLE.DOCTOR;
        if(staffMember.getString("user_role").equals("REGISTER"))
            role = Models.Staff_Model.USER_ROLE.REGISTER;
        if(staffMember.getString("user_role").equals("BILLING"))
            role = Models.Staff_Model.USER_ROLE.BILLING;        
       
        
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
//  private Boolean authorize(Staff_Model staffMember) {
//       //System.out.println("INSIDE AUTHORZE");
//       //System.out.println("Username: " + staffMember.getUserName() + ". Password: " + staffMember.getPassword());
//       
//       //TODO: ADD SALT TO PASSWORD TO MAKE EVERY PASSWORD DIFFERENT
//       sha256hex = Hashing.sha256().hashString(password.getText(), StandardCharsets.UTF_8).toString();
//    
//       //System.out.println("AFTER HASHING: " + sha256hex);
//       
//       return staffMember.getUserName().equals(user.getText()) && staffMember.getPassword().equals(sha256hex);
//  }
 
}