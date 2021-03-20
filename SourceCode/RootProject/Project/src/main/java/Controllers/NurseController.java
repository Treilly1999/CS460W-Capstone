/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author Tyler
 */
public class NurseController
{

    @FXML private String nurseName;
    
    Staff_Model nurse = new Staff_Model();

    if(Staff_Model.USER_ROLE != NURSE)
    {
        logout();
    }

    
}
