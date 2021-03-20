package login;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/** Controls the login screen */
public class LoginController {
   @FXML private TextField user;
   @FXML private TextField password;
   @FXML private Button loginButton;


    private String sha256hex = Hashing.sha256()
     .hashString(password.getText(), StandardCharsets.UTF_8).toString();

    Docuemnt staffQuery = new Document();
    staffQuery.put("userName", user);

    private Staff_Model currentUser = findStaffMember(staffQuery);  

    static ArrayList<Staff_Model> currentUserList = new ArrayList<Staff>();
    
    public Staff_Model getCurrentUser()
    {
        return currentUser;
    }
    

  public void initialize() {}
  
  public void initManager(final LoginManager loginManager) {
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        String sessionID = authorize();
        if (sessionID != null) {
          loginManager.authenticated(currentUser.getUSER_ROLE(), sessionID);
        }
      }
    });
  }
  
    /*
    Finds the staff member in the login query, builds a current user profile from
    staff_model and decides to log them in or not.
    */
    public Staff_Model findStaffMember(Document query)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("hospital");
        MongoCollection<Document> collection = database.getCollection("staff");

        FindIterable<Document> findIterable = findIterable = collection.find(query);

        MongoCursor<Document> cursor = findIterable.iterator();

        ArrayList<Document> staff = new ArrayList<Document>();

        try
        {
            while(cursor.hasNext())
            {               
                staff.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        staff.forEach((n) -> buildCurrentUser(n));
              
        return currentUser;
    }
  
    public static void buildCurrentUser(Document staffMember)
    {
        String documentString = staffMember.toString();
        System.out.println(documentString);
        
        Map<String,String> queryParameters = Splitter
            .on(", ")
            .withKeyValueSeparator("=")
            .split(documentString);
        
        String name = "", userName = "", password="";
        int id;
        USER_ROLE role = queryParameters.get("user_role");
        
        try
        {
            name = queryParameters.get("name");
            id = queryParameters.get("id");
            userName = queryParameters.get("userName");
            password = queryParameters.get("password");
            
          } catch (Exception e)
          {
              System.out.println("Some query parameters were not found in " + name + " profile");
          }   
      currentUser = new Staff_Model(role, id, name, userName, password);
    }

  /**
   * Check authorization credentials.
   * 
   * If accepted, return a sessionID for the authorized session
   * otherwise, return null.
   */   
  private String authorize() {
    return 
      currentUser.getUserName().equals(user.getText()) && currentUser.getPassword().equals(sha256hex) 
            ? generateSessionID() 
            : null;
  }
  
  private static int sessionID = 0;

  private String generateSessionID() {
    sessionID++;
    return "xyzzy - session " + sessionID;
  }
}