/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Commande;
import entites.Produit;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CommandeService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class AddUpdateCommandeController implements Initializable {

    ProduitService produitService;
    CommandeService commandeService;
    Commande commande;
    String type;
    GestionCommandeController gestionCommandeController;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label TitleLabel;
    @FXML
    private TextField quantiteField;
    @FXML
    private Button actionButton;
    @FXML
    private ComboBox<Produit> produitCB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produitService = new ProduitService();
        commandeService = new CommandeService();
        ObservableList<Produit> list = FXCollections.observableArrayList();
        list.addAll(produitService.getAll());
        produitCB.setItems(list);
    }

    @FXML
    private void ajoutOrDelete(ActionEvent event) {
        if (controleDeSaisie()) {
            if (type.equals("Update")) {

                commande.setProduit(produitCB.getSelectionModel().getSelectedItem());
                commande.setQuantite((int) Float.parseFloat(quantiteField.getText()));
                update(commande);
            } else {
                commande = new Commande();
                commande.setProduit(produitCB.getSelectionModel().getSelectedItem());
                commande.setQuantite((int) Float.parseFloat(quantiteField.getText()));
                ajout(commande);
            }
            gestionCommandeController.refreshTable();
        }

    }

    private void update(Commande c) {

        if (commandeService.update(c)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("mise à jour avec succès");
            alert.setTitle("Succès");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("mise à jour fail !! ");
            alert.setTitle("fail");
            alert.show();
        }
    }

    private void ajout(Commande c) {

        if (commandeService.insert(c)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("ajout avec succès");
            alert.setTitle("Succès");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ajout fail ");
            alert.setTitle("fail");
            alert.show();
        }

    }

    public void setWindowType(String type) {
        this.type = type;
        actionButton.setText(type);
        TitleLabel.setText(type + " Commande");
    }

    public void initializeTextField(Commande c) {
        commande = c;
        produitCB.getSelectionModel().select(c.getProduit());
        quantiteField.setText(c.getQuantite() + "");

    }

    private boolean controleDeSaisie() {

        if (produitCB.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le Produit");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        try {
            Float.parseFloat(quantiteField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(quantiteField.getText() + " n'est pas un nombre valide (nombre)");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        return true;
    }

    void initializeCommandeController(GestionCommandeController gestionCommandeController) {
        this.gestionCommandeController = gestionCommandeController;
    }

}
