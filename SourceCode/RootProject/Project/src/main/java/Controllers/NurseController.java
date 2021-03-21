/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Staff_Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import login.*;
import javafx.scene.control.Label;
import login.LoginController;
/**
 *
 * @author Tyler
 */
public class NurseController
{
    @FXML private Button logoutButton;
    @FXML private Label nurseName;
    
    //where everything happens
    public void initNurse(final LoginManager loginManager, Staff_Model user)
    {
        System.out.println(user.getName());
        nurseName.setText(user.getName());
        
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loginManager.logout();
          }
        });   

    }
}
