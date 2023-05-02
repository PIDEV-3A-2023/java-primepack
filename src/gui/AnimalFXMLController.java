/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Animal;
import entites.Ordonnance;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.AnimalService;

/**
 * FXML Controller class
 *
 * @author expert
 */
public class AnimalFXMLController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfrace;
    @FXML
    private TextField tfgenre;
    @FXML
    private TextField tfage;
    @FXML
    private TextField tfdesc;
    @FXML
    private Button btnajouter;
    
    
    

    public void setTfnom(String tfnom) {
        this.tfnom.setText(tfnom);
    }

    public void setTfrace(String tfrace) {
        this.tfrace.setText(tfrace);
    }

    public void setTfgenre(String tfgenre) {
        this.tfgenre.setText(tfgenre);
    }

    public void setTfage(String tfage) {
        this.tfage.setText(tfage) ;
    }

    public void setTfdesc(String tfdesc) {
        this.tfdesc.setText(tfdesc) ;
    }
    
    @FXML
    private AnchorPane root;
    AnimalService animalService  = new AnimalService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void ajoutAnimal(ActionEvent event) throws SQLException {
        
        
        
        
        try {
            Alert alert;

            if (tfage.getText().isEmpty()
                    || tfdesc.getText().isEmpty() || tfgenre.getText().isEmpty() 
                    || tfnom.getText().isEmpty() 
                    || tfrace.getText().isEmpty()
                    
                
            ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Les champs sont obligatoires");
                alert.showAndWait();
            }else if(tfnom.getText().length()<8){
                 alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Le nom doit etre compose de 8 caracteres au minimum");
                alert.showAndWait();
            }else if(tfdesc.getText().length()<20){
                 alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("LDescription doit etre compose de 20 caracteres au minimum");
                alert.showAndWait();
            } else {
             
                       Animal a = new Animal();
                        a.setNom(tfnom.getText());
                          a.setRace(tfrace.getText());
                          a.setGenre(tfgenre.getText());
                      a.setAge(tfage.getText());
                      a.setDescription(tfdesc.getText());

        animalService.ajoutAnimal(a);
                        
                
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();
               
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        //***********************************************
      
        
        
        
    }
    @FXML
    void update(MouseEvent event) throws SQLException, IOException {
       Animal animal = new Animal();
           int idAnimal  = Integer.valueOf(tfid.getText()) ;
        animal.setNom(tfnom.getText());
        animal.setRace(tfrace.getText());
        animal.setGenre(tfgenre.getText());
        animal.setAge(tfage.getText());
        animal.setDescription(tfdesc.getText());

        
        

        animalService.modifier(idAnimal,animal);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAnimal.fxml"));
        
        Parent root1 = loader.load();
        root.getChildren().removeAll();
        root.getChildren().setAll(root1);

    }

   @FXML
    void tesr() throws IOException {
        Parent fmxlLoader = FXMLLoader.load(getClass().getResource("AfficherAnimal.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fmxlLoader);

    } 
    
}
