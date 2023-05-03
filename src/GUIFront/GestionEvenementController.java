/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import GUI.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.EvenementService;

public class GestionEvenementController implements Initializable {

    EvenementService evenementService;
    ObservableList<Evenement> listEvenements = FXCollections.observableArrayList();
    Evenement evenement;
    GestionEvenementController gestionEvenementController;

    @FXML
    private TableView<Evenement> evenementTableView;
    @FXML
    private TableColumn<Evenement, String> nomCell;
    @FXML
    private TableColumn<Evenement, String> dateCell;
    @FXML
    private TableColumn<Evenement, String> descCell;
    @FXML
    private TableColumn<Evenement, String> sponsorCell;
    @FXML
    private Button rechrcheBtn;
    @FXML
    private DatePicker dateRechrcheField;
    @FXML
    private TableColumn<Evenement, String> nbTicketCell;
    @FXML
    private TableColumn<Evenement, String> availableCell;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gestionEvenementController = this;
        evenementService = new EvenementService();
        LoadData();
    }

    public void refreshTable() {
        listEvenements.clear();
        if (dateRechrcheField.getValue() != null) {
            listEvenements.addAll(evenementService.getAllByDate(dateRechrcheField.getValue()));

        } else {
            listEvenements.addAll(evenementService.getAll());

        }

        evenementTableView.setItems(listEvenements);

    }

    private void LoadData() {

        refreshTable();
        nomCell.setCellValueFactory(new PropertyValueFactory<>("nom"));
        dateCell.setCellValueFactory(new PropertyValueFactory<>("date"));
        descCell.setCellValueFactory(new PropertyValueFactory<>("description"));
        sponsorCell.setCellValueFactory(new PropertyValueFactory<>("sponsor"));
        nbTicketCell.setCellValueFactory(new PropertyValueFactory<>("nb_ticket"));
        availableCell.setCellValueFactory(new PropertyValueFactory<>("available"));

    }

    @FXML
    private void Recherche(ActionEvent event) {
        refreshTable();
    }
}
