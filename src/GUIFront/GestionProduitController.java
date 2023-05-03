/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ProduitService;

public class GestionProduitController implements Initializable {

    ProduitService produitService;
    ObservableList<Produit> listProduit = FXCollections.observableArrayList();
    Produit produit;
    GestionProduitController gestionProduitController;
    @FXML
    private TableView<Produit> categorieTableView;
    @FXML
    private TableColumn<Produit, String> nomCell;
    @FXML
    private TableColumn<Produit, String> prixCell;
    @FXML
    private TableColumn<Produit, String> stokCell;
    @FXML
    private TableColumn<Produit, String> availbaleCell;
    @FXML
    private TableColumn<Produit, String> categorieCell;
    @FXML
    private TextField searchTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gestionProduitController = this;
        produitService = new ProduitService();
        LoadData();
    }

    public void refreshTable() {

        listProduit.clear();
        listProduit.addAll(produitService.getAll());
        categorieTableView.setItems(listProduit);

        if (listProduit.size() > 0) {
            FilteredList<Produit> filterData = recherche(listProduit);
            String a = searchTextField.getText();
            categorieTableView.setItems(filterData);
        }

    }

    private void LoadData() {

        refreshTable();
        nomCell.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixCell.setCellValueFactory(new PropertyValueFactory<>("prix"));
        stokCell.setCellValueFactory(new PropertyValueFactory<>("stock"));
        availbaleCell.setCellValueFactory(cellData
                -> {
            return new SimpleStringProperty(cellData.getValue().getStock() > 0 ? "true" : "false");
        });
        categorieCell.setCellValueFactory(cellData
                -> {
            return new SimpleStringProperty(cellData.getValue().getCategory().getNom());
        });

    }

    private FilteredList<Produit> recherche(ObservableList matchList) {
        FilteredList<Produit> filterData = new FilteredList<Produit>(matchList, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(SearchModel -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String serachKeeyword = newValue.toLowerCase();
                if (((Produit) SearchModel).getCategory().getNom().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if ((((Produit) SearchModel).getNom() + "").toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if ((((Produit) SearchModel).getPrix() + "").toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if ((((Produit) SearchModel).getStock() + "").toLowerCase().contains(serachKeeyword)) {
                    return true;
                }

                return false;
            });

        });

        return filterData;
    }

}
