/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controllers.LoginManager;
import java.awt.EventQueue;
/**
 *
 * @author Tyler
 */
public class HospitalSystem  {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                        try {
                                LoginManager loginManager = new LoginManager();
                                loginManager.showLoginScreen();   
                                
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
        });
    }
    
}
