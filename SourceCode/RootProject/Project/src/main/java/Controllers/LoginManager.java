package Controllers;

import Controllers.NurseController;
import java.io.IOException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import Controllers.PatientController;
import Models.Staff_Model;
import Views.AddDiagnosisFrm;
import Views.AddPatientFrm;
import Views.LoginFrm;
import Views.MainFrm;
import java.awt.EventQueue;
import javax.swing.JFrame;

/** Manages control flow for logins */
public class LoginManager extends JFrame {
  private Scene scene;

  public LoginManager(Scene scene) {
    this.scene = scene;
  }    
  public LoginManager() {
        setTitle("Hospital System");
        setBounds(100, 100, 849, 567);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public Scene getScene() { return scene; }
  
  /**
   * Author: Tyler
   * Description: Callback method invoked to notify that a user has been authenticated.
   * Will show screen depending on who logs in
   * TODO: ADD SCREENS FOR DIFFERENT USERS
   */ 
    public void authenticated(Staff_Model user)
    {        
        if(user.getUSER_ROLE() == Models.Staff_Model.USER_ROLE.NURSE || user.getUSER_ROLE() == Models.Staff_Model.USER_ROLE.DOCTOR ||
        user.getUSER_ROLE() == Models.Staff_Model.USER_ROLE.REGISTER || user.getUSER_ROLE() == Models.Staff_Model.USER_ROLE.BILLING)    
        {
            showMainPanel(user);  
        }
       
    }
    
  /**
   * Author: Tyler
   * Description Callback method invoked to notify that a user has logged out of the main application.
   * Will show the login application screen.
   * TODO: Fix logout button on Registration pages.
   */   
  public void logout() {
    showLoginScreen();
  }  
  
  private LoginFrm loginFrame;
  private AddPatientFrm registerFrame;
  private AddDiagnosisFrm diagnosisFrame;
  private MainFrm main;
  
  public void showLoginScreen() {
      LoginManager login = this;
      EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame = new LoginFrm(login);
                                        setContentPane(loginFrame.getLoginForm());
                                        setSize(1200,800);
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }

  /**
   * Author: Tyler
   * Description: Will show the nurse application screen.
   */ 
  public void showNurseView(Staff_Model user) {
    try {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/nurseView.fxml"));
        Parent root = loader.load();        
        scene.setRoot(root);
        NurseController controller = 
        loader.<NurseController>getController();
        controller.initNurse(this, user);
        //controller.initSessionID(this, sessionID);
        } catch (IOException ex) {
          Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
   /**
   * Author: Tyler
   * Description: Will show the patientList application screen.
   */ 
  public void showDiagnosisPanel(Staff_Model user) {
    LoginManager login = this;
      EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					diagnosisFrame = new AddDiagnosisFrm(login, user);
                                        //diagnosisFrame.setSize(1200,800);
					//diagnosisFrame.setVisible(true);
                                        //loginFrame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }
  
  public void showRegisterPanel(Staff_Model user) {
      LoginManager login = this;
      EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerFrame = new AddPatientFrm(login, user);
                                        //setContentPane(registerFrame.getPatientFormPanel());
                                        //registerFrame.setSize(1200,800);
					//registerFrame.setVisible(true);
                                        //loginFrame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }
  
  public void showMainPanel(Staff_Model user) 
  {
      LoginManager login = this;
      EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main = new MainFrm(login, user);
                                        //setContentPane(registerFrame.getPatientFormPanel());
                                        //registerFrame.setSize(1200,800);
					//registerFrame.setVisible(true);
                                        //loginFrame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }
//    try {
//
//    FXMLLoader loader = new FXMLLoader();
//    loader.setLocation(getClass().getResource("/RegisterMainView.fxml"));
//    Parent root = loader.load();        
//    scene.setRoot(root);
//    
//    RegisterController controller = 
//    loader.<RegisterController>getController();
//    controller.initRegisterForm(this, user);
//    
//    } catch (IOException ex) {
//      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    
}