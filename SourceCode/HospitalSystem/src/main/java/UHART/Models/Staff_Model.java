/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UHART.Models;

/**
// *
 * @author Tyler
 */
public class Staff_Model {
    
    //This is for deciding what screen to show. **CRITICAL**
    public enum USER_ROLE
    {
        DOCTOR,
        NURSE,
        BILLING,
        REGISTER,
        DEFAULT
    }
    
    private USER_ROLE role;
    protected int id;
    protected String name, phoneNumber;
    private String userName, password;
    protected Address address;

       
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
    
    public Address getAddress() { return address; }

    public String getPassword() { return password; }

    public String toString()
    {
        return
                "Name: " + getName();
    }
    
}
