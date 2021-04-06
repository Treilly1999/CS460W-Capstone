package Controllers;

import Models.Staff_Model;
import Views.*;
import java.awt.EventQueue;
import javax.swing.JFrame;

/** Manages control flow for logins */
public class LoginManager extends JFrame {
  
  public LoginManager() {
        setTitle("Hospital System");
        setBounds(100, 100, 849, 567);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  /**
   * Author: Tyler
   * Description: Callback method invoked to notify that a user has been authenticated.
   * Will show screen depending on who logs in
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
   */   
  public void logout() {
    showLoginScreen();
  }  
  
  private LoginFrm loginFrame;
  private AddPatientFrm registerFrame;
  private AddDiagnosisFrm diagnosisFrame;
  private BillFrm billFrame;
  private CheckInfoFrm checkInfo;
  private UpdatePFrm updatePatient;
  private MainFrm main;
  
  /*
  Description: Shows the login panel. This is done by default by the constructor. 
  */
  public void showLoginScreen() 
  {
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
  
  public void showUpdatePanel(Staff_Model user) 
  {
      LoginManager login = this;
      EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updatePatient = new UpdatePFrm(login, user);
                                       
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }
  
   /**
   * Author: Tyler
   * Description: Will show the Doctor application screen.
   */ 
  public void showDiagnosisPanel(Staff_Model user) 
  {
    LoginManager login = this;
      EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					diagnosisFrame = new AddDiagnosisFrm(login, user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }
  
  public void showRegisterPanel(Staff_Model user) 
  {
      LoginManager login = this;
      EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerFrame = new AddPatientFrm(login, user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }
  
  public void showBillingPanel(Staff_Model user)
  {
      LoginManager login = this;
      EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					billFrame = new BillFrm(login, user);
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }
  
  public void showPatientPanel(Staff_Model user)
  {
      LoginManager login = this;
      EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					checkInfo = new CheckInfoFrm(login, user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }

}