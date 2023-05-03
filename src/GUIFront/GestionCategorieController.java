/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import entites.Categorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CategorieService;

public class GestionCategorieController implements Initializable {

    CategorieService categorieService;
    ObservableList<Categorie> listCategorie = FXCollections.observableArrayList();
    Categorie categorie;
    GestionCategorieController gestionCategorieController;
    @FXML
    private TableView<Categorie> categorieTableView;
    @FXML
    private TableColumn<Categorie, String> nomCell;
    @FXML
    private TableColumn<Categorie, String> iconeCell;
    @FXML
    private TableColumn<Categorie, String> descCell;
    @FXML
    private TableColumn<Categorie, String> noteCell;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gestionCategorieController = this;
        categorieService = new CategorieService();
        LoadData();

    }

    public void refreshTable() {

        listCategorie.clear();
        listCategorie.addAll(categorieService.getAll());
        categorieTableView.setItems(listCategorie);

    }

    private void LoadData() {

        refreshTable();
        nomCell.setCellValueFactory(new PropertyValueFactory<>("nom"));
        iconeCell.setCellValueFactory(new PropertyValueFactory<>("icone"));
        descCell.setCellValueFactory(new PropertyValueFactory<>("description"));
        noteCell.setCellValueFactory(new PropertyValueFactory<>("note"));

    }

}
