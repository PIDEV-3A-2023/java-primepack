/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Animale;

import entites.Animal;
import entites.Ordonnance;
import entites.Rendezvous;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.AnimalService;
import services.RendezvousService;

/**
 * FXML Controller class
 *
 * @author expert
 */
public class RendezvousFXMLController implements Initializable {

    @FXML
    private ComboBox<Animal> comboAnimal;

    @FXML
    private TextField txtduree;

    @FXML
    private DatePicker txtdate;

    @FXML
    private Button btnajout;

    /**
     * Initializes the controller class.
     */
    RendezvousService rendezvousService = new RendezvousService();
    AnimalService animalService = new AnimalService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Animal> obAnimal = null;
        try {
            obAnimal = FXCollections.observableList(animalService.afficherListeA());
        } catch (SQLException ex) {
            Logger.getLogger(RendezvousFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboAnimal.setItems(obAnimal);
        comboAnimal.setCellFactory(param -> new ListCell<Animal>() {
            @Override
            protected void updateItem(Animal animal, boolean empty) {
                super.updateItem(animal, empty);
                if (empty || animal == null) {
                    setText(null);
                } else {
                    setText(animal.getNom());
                }
            }
        });
        comboAnimal.setButtonCell(new ListCell<Animal>() {
            @Override
            protected void updateItem(Animal animal, boolean empty) {
                super.updateItem(animal, empty);
                if (empty || animal == null) {
                    setText(null);
                } else {
                    setText(animal.getNom());
                }
            }
        });
    }

    @FXML
    private void ajoutRendezvous(ActionEvent event) throws SQLException {

    }

    @FXML
    void add(MouseEvent event) throws SQLException {

        try {
            Alert alert;

            if (txtduree.getText().isEmpty()
                    || txtdate.getValue() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Les champs sont obligatoires");
                alert.showAndWait();
            } else if (txtdate.getValue().isBefore(LocalDate.now())) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Date Invalide");
                alert.showAndWait();
            } else if (comboAnimal.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Choisir un animal");
                alert.showAndWait();
            } else {

                Rendezvous r = new Rendezvous();
                r.setDuree(Integer.valueOf(txtduree.getText()));
                r.setDate(Date.valueOf(txtdate.getValue()));
                r.setAnimal(comboAnimal.getSelectionModel().getSelectedItem());

                rendezvousService.ajoutRendezvous(r);

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

}
