/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Ennou
 */
public class UserFXMLController implements Initializable {

//   
//
//    @FXML
//    private TextField nomTF;
//    @FXML
//    private TextField prenomTF;
//    @FXML
//    private TextField emailTF;
//    @FXML
//    private TextField passwordTF;
//    @FXML
//    private TextField numeroTF;
//    @FXML
//    private TextField adresseTF;
//    @FXML
//    private TextField roleTF;
//    @FXML
//    private Label personnesListe;
//
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
//
//    @FXML
//    private void AjoutUser(ActionEvent event) {
//        User u= new User(nomTF.getText(), prenomTF.getText(), emailTF.getText(), passwordTF.getText(), adresseTF.getText(), roleTF.getText());
//        UserService ps = new UserService();
//        try {
//            ps.AjoutUser(u);
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Succes");
//            alert.setHeaderText(null);
//            alert.setContentText("Personne ajoutée avec succes");
//            alert.show();
//            
//            nomTF.setText("");
//            prenomTF.setText("");
//            emailTF.setText("");
//            passwordTF.setText("");
//            adresseTF.setText("");
//            roleTF.setText("");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    @FXML
//    private void AfficherListeUser(ActionEvent event) {
//        UserService ps = new UserService();
//        try {
//            personnesListe.setText(ps.AfficherListeP().toString());
//        } catch (SQLException ex) {
//            ex.getMessage();
//        }
//  }
    
}
