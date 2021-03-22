/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
// *
 * @author Tyler
 */
public class Staff_Model {
    
    //This is for deciding what screen to show.
    public enum USER_ROLE
    {
        DOCTOR,
        NURSE,
        BILLING,
        REGISTER,
        DEFAULT
    }
    
    USER_ROLE role;
    int id;
    String name, userName, password;
    
    //uses google Guava library to encrypt password
    //will need revision
   
    public Staff_Model() {}
    
    public Staff_Model(USER_ROLE role, int id, String name, String userName, String password)
    {
        this.role = role;
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }
    
    public USER_ROLE getUSER_ROLE() { return role; }
    
    public int getID() { return id; }
    
    public String getName() { return name; }
    
    public String getUserName() { return userName; }

    public String getPassword() { return password; }

    
}
