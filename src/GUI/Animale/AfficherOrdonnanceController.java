/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Animale;

import entites.Ordonnance;
import entites.Rendezvous;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.OrdonnanceService;
import services.RendezvousService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author expert
 */
public class AfficherOrdonnanceController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Ordonnance> animatable;
    private TableColumn<Ordonnance, String> description;
    private TableColumn<Ordonnance, String> traitement;
    private TableColumn<Ordonnance, String> date;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;

    int idOrdonnance;

    /**
     * Initializes the controller class.
     */
    OrdonnanceService ordonnanceService = new OrdonnanceService();
    ObservableList<Ordonnance> oList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> desc_col;
    @FXML
    private TableColumn<?, ?> trait_col;
    @FXML
    private TableColumn<?, ?> date_col;

    @FXML
    void add() throws IOException {
        Parent fmxlLoader = FXMLLoader.load(getClass().getResource("OrdonnnaceFXML.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fmxlLoader);

    }

    @FXML
    public void catDelete() throws SQLException {

        Ordonnance cat = animatable.getSelectionModel().getSelectedItem();
        int num = animatable.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cofirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE Categorie : " + cat.getDate() + "?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {

            ordonnanceService.supprimerOrdonnance(cat.getId());
            showOrdonnance();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Deleted!");
            alert.showAndWait();

        }
    }

    @FXML
    public void aniimalUpdate() throws IOException {
        Ordonnance cat = animatable.getSelectionModel().getSelectedItem();
        int num = animatable.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierordonnanceFXML.fxml"));

        Parent root1 = loader.load();

        TextField txt_id = (TextField) loader.getNamespace().get("txt_id");
        TextField txt_desc = (TextField) loader.getNamespace().get("txt_desc");
        TextField txt_trait = (TextField) loader.getNamespace().get("txt_trait");
        DatePicker txt_date = (DatePicker) loader.getNamespace().get("txt_date");

        idOrdonnance = cat.getId();
        System.out.println(idOrdonnance);

        txt_id.setText(String.valueOf(idOrdonnance));
        txt_desc.setText(cat.getDescription());
        txt_trait.setText(cat.getTraitement());
        txt_date.setValue(Instant.ofEpochMilli(cat.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());

        root.getChildren().removeAll();
        root.getChildren().setAll(root1);

    }

    private void loadDate() {
        desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        trait_col.setCellValueFactory(new PropertyValueFactory<>("traitement"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));

    }
    RendezvousService rendezvousService = new RendezvousService();

    void showOrdonnance() {
        try {
            OrdonnanceService us = new OrdonnanceService();
            List<Ordonnance> ordonnances = us.afficherListeO();
            ObservableList<Ordonnance> list = FXCollections.observableArrayList(ordonnances);

            Connection con = DataSource.getInstance().getCnx();
            ResultSet rs = con.createStatement().executeQuery("select * from ordonnance");

            while (rs.next()) {

                Rendezvous rendezVous = rendezvousService.afficherA(rs.getInt("rendezvous_id"));

                oList.add(new Ordonnance(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getString("traitement"),
                        rs.getDate("date"),
                        rendezVous
                ));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        loadDate();
        animatable.setItems(oList);
    }

    @FXML
    void generatePdf(MouseEvent event) throws IOException, SQLException {

        Ordonnance ordonnance = animatable.getSelectionModel().getSelectedItem();
        int num = animatable.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }

        ordonnanceService.pdfOrdonnance(ordonnance);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showOrdonnance();

    }

}
