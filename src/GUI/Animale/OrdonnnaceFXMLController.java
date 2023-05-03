/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Animale;

import entites.Animal;
import entites.Ordonnance;
import entites.Rendezvous;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.OrdonnanceService;
import services.RendezvousService;
import services.TwilioService;

/**
 * FXML Controller class
 *
 * @author expert
 */
public class OrdonnnaceFXMLController implements Initializable {

    @FXML
    private TextField txt_desc;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_trait;
    @FXML
    private Button btnajouter;
    @FXML
    private DatePicker txt_date;
    @FXML
    private AnchorPane root;
    @FXML
    private ComboBox<Rendezvous> comboOrdonnance;

    /**
     * Initializes the controller class.
     */
    OrdonnanceService ordonnanceService = new OrdonnanceService();
    RendezvousService rendezVousService = new RendezvousService();

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ObservableList<Rendezvous> obRendezVous = null;
        try {
            obRendezVous = FXCollections.observableList(rendezVousService.afficherListeR());
        } catch (SQLException ex) {
            Logger.getLogger(RendezvousFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboOrdonnance.setItems(obRendezVous);
        comboOrdonnance.setCellFactory(param -> new ListCell<Rendezvous>() {
            @Override
            protected void updateItem(Rendezvous rendezVous, boolean empty) {
                super.updateItem(rendezVous, empty);
                if (empty || rendezVous == null) {
                    setText(null);
                } else {
                    setText(rendezVous.getAnimal().getNom() + " " + rendezVous.getDate());
                }
            }
        });
        comboOrdonnance.setButtonCell(new ListCell<Rendezvous>() {
            @Override
            protected void updateItem(Rendezvous rendezVous, boolean empty) {
                super.updateItem(rendezVous, empty);
                if (empty || rendezVous == null) {
                    setText(null);
                } else {
                    setText(rendezVous.getAnimal().getNom() + " " + rendezVous.getDate());
                }
            }
        });

    }

    @FXML
    private void ajoutOrdonnance(ActionEvent event) throws SQLException, IOException {

        Ordonnance o = new Ordonnance();
        o.setDescription(txt_desc.getText());
        o.setTraitement(txt_trait.getText());
        o.setDate(Date.valueOf(txt_date.getValue()));

        Rendezvous rendezVous = comboOrdonnance.getSelectionModel().getSelectedItem();
        o.setRendezVous(rendezVous);

        ordonnanceService.ajoutOrdonnance(o);
        //TWILIO
        String msg = "Une ordonnance est ajouté pour votre rendez vous :" + o.getRendezVous().getDate() + " Animal : " + o.getRendezVous().getAnimal().getNom();
//        TwilioService.sendSms("+21656920743", msg);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherOrdonnanceFXML.fxml"));

        Parent root1 = loader.load();
        root.getChildren().removeAll();
        root.getChildren().setAll(root1);
    }

    AfficherOrdonnanceController afficherOrdonnanceController = new AfficherOrdonnanceController();

    @FXML
    void update(MouseEvent event) throws SQLException, IOException {
        Ordonnance o = new Ordonnance();
        System.out.println("AA");
        System.out.println(txt_id.getText() + "hhhhhh");
        int idOrdonnance = Integer.valueOf(txt_id.getText());
        o.setDescription(txt_desc.getText());
        o.setTraitement(txt_trait.getText());
        o.setDate(Date.valueOf(txt_date.getValue()));
        Rendezvous rendezVous = comboOrdonnance.getSelectionModel().getSelectedItem();
        o.setRendezVous(rendezVous);

        ordonnanceService.modifier(idOrdonnance, o);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherOrdonnanceFXML.fxml"));

        Parent root1 = loader.load();
        root.getChildren().removeAll();
        root.getChildren().setAll(root1);

    }

}
