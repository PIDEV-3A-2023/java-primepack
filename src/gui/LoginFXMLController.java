/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;

/**
 *
 * @author user
 */
public class LoginFXMLController implements Initializable {
    
   @FXML
    private TextField nom;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Hyperlink PfPass;
        @FXML
    private Button exit;

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
      
     @FXML
    private void btnLogin(ActionEvent event) throws SQLException, IOException {
        UserService uti = new UserService();
        
        if (nom.getText().equals("") || password.getText().equals("")) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Controle de saisie");
            al.setContentText("Veuillez remplir tout les champs !!");
            al.show();
        } else {
            if (!nom.getText().contains("@")  || !nom.getText().contains(".")) {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Controle de saisie");
                al.setContentText("Respecter format email !!");
                al.show();
            }else if (uti.login(new User(nom.getText(), password.getText())).equals("[\"ROLE_Veterinaire\"]")) {
                Stage stage = (Stage) login.getScene().getWindow();
                stage.close();
                Parent page = FXMLLoader.load(getClass().getResource("ProfilCoachFXML.fxml"));

                Scene scene = new Scene(page);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();
            } else if (uti.login(new User(nom.getText(), password.getText())).equals("[\"ROLE_CLIENT\"]")) {
                Stage stage = (Stage) login.getScene().getWindow();
                stage.close();
                Parent page = FXMLLoader.load(getClass().getResource("ProfilClientFXML.fxml"));

                Scene scene = new Scene(page);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();
            } else if (uti.login(new User(nom.getText(), password.getText())).equals("[\"ROLE_ADMIN\"]")) {
                Stage stage = (Stage) login.getScene().getWindow();
                stage.close();
                Parent page = FXMLLoader.load(getClass().getResource("ListClientForAdminFXML.fxml"));

                Scene scene = new Scene(page);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();
            } 
        }
    }

    @FXML
    private void PageforgotPass(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgotPasswordFXML.fxml"));
            Parent root = loader.load();
            PfPass.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
   
    }
    
  

