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
    int user_id;
    String name, userName, password;
   
    
}
