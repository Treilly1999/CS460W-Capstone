/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import Controllers.LoginManager;
import Views.AddDiagnosisFrm;
import java.awt.EventQueue;
/**
 *
 * @author Tyler
 */
public class HospitalSystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {        
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
