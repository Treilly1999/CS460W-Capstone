package UHART;

import UHART.Controllers.LoginManager;
import java.awt.EventQueue;
public class App 
{
    public static void main( String[] args )
    {
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
