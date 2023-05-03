/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class LoginController implements Initializable {

    UserService userService;

    @FXML
    private Label lblErrors;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnSignin;
    @FXML
    private Label btnForgot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userService = new UserService();
    }

    @FXML
    private void handleButtonActionbut(ActionEvent event) {
        String adresse = txtUsername.getText();
        String psw = txtPassword.getText();

        if (adresse.equals("admin") && psw.equals("admin")) {
            try {
                BaseController.currentUser = userService.AfficherU("admin");
                GUIFront.BaseController.currentUser = userService.AfficherU("admin");
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/GUI/Base.fxml"));
                Parent root = fxml.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                stage = (Stage) txtUsername.getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (adresse.equals("user") && psw.equals("user")) {
            try {
                BaseController.currentUser = userService.AfficherU("user");
                GUIFront.BaseController.currentUser = userService.AfficherU("user");

                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/GUIFront/Base.fxml"));
                Parent root = fxml.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                stage = (Stage) txtUsername.getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("mot de pass ou email incorrect");
            alert.show();
        }
    }

}
