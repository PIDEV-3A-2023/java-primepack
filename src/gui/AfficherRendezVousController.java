/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Animal;
import entites.Rendezvous;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.RendezvousService;

/**
 * FXML Controller class
 *
 * @author expert
 */
public class AfficherRendezVousController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Rendezvous> rendezVousTable;

    @FXML
    private TableColumn<Rendezvous, String> animalCol;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> dureeCol;
        @FXML
    private AnchorPane root;
    
    RendezvousService rendezVousService = new RendezvousService();
    
    
    
        @FXML
    void add(MouseEvent event) throws IOException {
                Parent fmxlLoader = FXMLLoader.load(getClass().getResource("RendezvousFXML.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fmxlLoader);

    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        // TODO
        ObservableList<Rendezvous> ob = null;
        try {
            ob = FXCollections.observableList(rendezVousService.afficherListeR());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRendezVousController.class.getName()).log(Level.SEVERE, null, ex);
        }

        dureeCol.setCellValueFactory(new PropertyValueFactory<>("duree"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

       // col_don_association.setCellValueFactory(new PropertyValueFactory<>("association"));
        animalCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAnimal().getNom()));

     



        rendezVousTable.setItems(ob);
        
        
        
    }    
    
}
