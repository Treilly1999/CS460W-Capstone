/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Tyler
 */
public class Staff_Model {
    
    enum USER_ROLE
    {
        DOCTOR,
        NURSE,
        BILLING,
        REGISTER
    }
    
    USER_ROLE role;
    int id;
    String name, userName;
   
    public Staff_Model(USER_ROLE role, int id, String name, String userName)
    {
        this.role = role;
        this.id = id;
        this.name = name;
        this.userName = userName;
    }
    
    public USER_ROLE getUSER_ROLE() { return role; }
    public void setUSER_ROLE(USER_ROLE role) { this.role = role; }
    
    public int getID() { return id; }
    public void setID(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getUserName() { return userName; }
    public void setUserName() { this.userName = userName; }  
    
}
