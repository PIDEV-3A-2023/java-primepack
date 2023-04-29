/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Veterimal;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ennou
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/LoginFXML.fxml")) ;
            
            Scene scene = new Scene(root, 600, 400);
            
            primaryStage.setTitle("Vetorimal");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
//    private Stage primaryStage;
//    private Parent parentPage;
//   
//    @Override
//    public void start(Stage primaryStage) throws IOException {
//        this.primaryStage = primaryStage;
//        this.primaryStage.setTitle("Hello World");
//        
//        parentPage = FXMLLoader.load(getClass().getResource("/gui/LoginFXML.fxml"));
//        Scene scene = new Scene(parentPage);
//        this.primaryStage.setScene(scene);
//        this.primaryStage.show();
//
//    }

  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
