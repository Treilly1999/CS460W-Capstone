package UHART.Controllers;

import UHART.Models.Patient;
import UHART.Models.Staff_Model;
import UHART.Views.*;
import java.awt.EventQueue;
import javax.swing.JFrame;

/** Manages control flow for logins */
public class LoginManager extends JFrame {
  
  /**
   *
   */
  private static final long serialVersionUID = 1L;

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
        if(user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.NURSE || user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.DOCTOR ||
        user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.REGISTER || user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.BILLING)    
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
  private BillFrm billFrame;
  private UpdatePFrm updatePatient;
  private MainFrm main;
  private PatientPageFrm patientProfile;
  private SearchPatientFrm searchPatient;
  private ChangePwFrm changePwd;
  private AboutUsFrm about;
  
  /*
  Description: Shows the login panel. This is done by default by the constructor. 
  */
  public void showLoginScreen() 
  {
      final LoginManager login = this;
      EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame = new LoginFrm(login);
                                        setContentPane(loginFrame.getLoginForm());
                                        setSize(750,560);
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  }
  
  public void showUpdatePanel(final Staff_Model user) 
  {
      final LoginManager login = this;
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
  
  public void showRegisterPanel(final Staff_Model user) 
  {
      final LoginManager login = this;
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
  
  public void showBillingPanel(final Staff_Model user)
  {
      final LoginManager login = this;
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
  
  public void showMainPanel(final Staff_Model user) 
  {
      final LoginManager login = this;
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
  
//  public void showPatientPanel(Staff_Model user)
//  {
//      LoginManager login = this;
//      EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					checkInfo = new CheckInfoFrm(login, user);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//  }
  
  public void showPatientPanel(final Patient patient, final Staff_Model user)
  {
    final LoginManager login = this;
    EventQueue.invokeLater(new Runnable() {
    public void run() {
      try {
        patientProfile = new PatientPageFrm(patient, user, login);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  });
  }

  public void search(final Staff_Model user)
  {
    final LoginManager login = this;
    EventQueue.invokeLater(new Runnable() {
    public void run() {
      try {
        searchPatient = new SearchPatientFrm(login, user);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  });
  }

  public void changePassword(final Staff_Model user)
  {
    final LoginManager login = this;
    EventQueue.invokeLater(new Runnable() {
    public void run() {
      try {
        changePwd = new ChangePwFrm(login, user);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  });
  }

  public void aboutUs(final Staff_Model user)
  {
    final LoginManager login = this;
    EventQueue.invokeLater(new Runnable() {
    public void run() {
      try {
        about = new AboutUsFrm(login, user);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  });
  }

}