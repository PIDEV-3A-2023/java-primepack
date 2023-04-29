/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Veterimal;

import java.io.IOException;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author user
 */
public class Vetomimal extends Application {

    private Stage primaryStage;
    private Parent parentPage;
   
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hello World");
        
        parentPage = FXMLLoader.load(getClass().getResource("/gui/LoginFXML.fxml"));
        Scene scene = new Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();

    }

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
