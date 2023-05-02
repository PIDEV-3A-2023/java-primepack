/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author expert
 */
public class TemplateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane root;
       @FXML
    private AnchorPane root1;
    
    public void rendezVous(MouseEvent event) throws IOException {
        Parent fmxlLoader = FXMLLoader.load(getClass().getResource("RendezVousFXML.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fmxlLoader);
    }
    
     @FXML
    void animal(MouseEvent event) throws IOException {
        Parent fmxlLoader = FXMLLoader.load(getClass().getResource("AnimalFXML.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fmxlLoader);
    }

    @FXML
    void home(MouseEvent event) throws IOException {
        Parent fmxlLoader = FXMLLoader.load(getClass().getResource("Template.fxml"));
        root1.getChildren().removeAll();
        root1.getChildren().setAll(fmxlLoader);

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
