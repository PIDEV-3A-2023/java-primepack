/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterimalintegration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Houssem Charef
 */
public class VeterimalIntegration extends Application {

    @Override
    public void start(Stage primaryStage) {

        Parent root;
        try {
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();

            root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.getIcons().add(new Image("/Image/logo.png"));
        } catch (IOException ex) {
            Logger.getLogger(VeterimalIntegration.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
