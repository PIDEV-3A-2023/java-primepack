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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutFXMLController implements Initializable {
    
    @FXML
    private TextField nomTF;
    @FXML
    private TextField prenomTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField numeroTF;
    @FXML
    private TextField adresseTF;
    @FXML
    private TextField roleTF;
    @FXML
    private Label personnesListe;
    @FXML
    private Label label;
    @FXML
    private Button btnAffich;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnMod;

    
  
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    /*
      @FXML
    private void AjouterUser(ActionEvent event) {
        User p= new User(nomTF.getText(), prenomTF.getText(), emailTF.getText, passwordTF.getText, numeroTF.getText, adresseTF.getText, roleTF.getText);
        UserService ps = new UserService();
        try {
            ps.AjoutUser(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText("Personne ajoutée avec succes");
            alert.show();
            
            nomTF.setText("");
            prenomTF.setText("");
            emailTF.setText("");
            passwordTF.setText("");
            numeroTF.setText("");
            adresseTF.setText("");
            roleTF.setText("");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @FXML
    private void exit(ActionEvent event){
        System.exit(0);
    }
 
*/
}

