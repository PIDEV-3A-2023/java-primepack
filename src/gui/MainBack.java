/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.MyDB;

/**
 *
 * @author expert
 */
public class MainBack extends Application {
    
      public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
         // Amira.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

        Scene scene = new Scene(root);
       // primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
      public static void main(String[] args) {
      
        launch();
        // TODO code application logic here
        MyDB mc = MyDB.getInstance();
    }
    
}
