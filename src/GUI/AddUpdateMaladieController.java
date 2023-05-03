/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Maladie;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import services.MaladieService;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class AddUpdateMaladieController implements Initializable {

    MaladieService service;
    Maladie maladie;
    String type;
    private GestionMaladieController controller;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label TitleLabel;
    @FXML
    private TextField nomField;
    @FXML
    private TextField descriptionFiled;
    @FXML
    private Button actionButton;

    @FXML
    private DatePicker DCreationPiker;
    @FXML
    private DatePicker DMAJPiker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new MaladieService();
    }

    @FXML
    private void ajoutOrDelete(ActionEvent event) {
        if (controleDeSaisie()) {
            if (type.equals("Update")) {

                maladie.setNom(nomField.getText());
                maladie.setDescription(descriptionFiled.getText());
                maladie.setDateCreation(Timestamp.valueOf(DCreationPiker.getValue().atStartOfDay()));
                maladie.setDateMaj(Timestamp.valueOf(DMAJPiker.getValue().atStartOfDay()));
                update(maladie);
            } else {
                maladie = new Maladie();
                maladie.setNom(nomField.getText());
                maladie.setDescription(descriptionFiled.getText());

                maladie.setDateCreation(Timestamp.valueOf(DCreationPiker.getValue().atStartOfDay()));
                maladie.setDateMaj(Timestamp.valueOf(DMAJPiker.getValue().atStartOfDay()));

                ajout(maladie);
            }
            controller.refreshTable();
        }
    }

    private void update(Maladie m) {

        if (service.update(m)) {

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Succès");
            tray.setMessage("mise à jour avec succès");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(10));
        } else {

            TrayNotification tray = new TrayNotification();
            tray.setTitle("fail");
            tray.setMessage("mise à jour fail ");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));
        }
    }

    private void ajout(Maladie m) {

        if (service.insert(m)) {
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Succès");
            tray.setMessage("ajout avec succès");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(10));

        } else {
            TrayNotification tray = new TrayNotification();
            tray.setTitle("fail");
            tray.setMessage("Ajout fail ");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));

        }

    }

    public void setWindowType(String type) {
        this.type = type;
        actionButton.setText(type);
        TitleLabel.setText(type + " Maladie");
    }

    public void initializeTextField(Maladie m) {
        maladie = m;
        nomField.setText(m.getNom());
        descriptionFiled.setText(m.getDescription());
        DCreationPiker.setValue(m.getDateCreation().toLocalDateTime().toLocalDate());
        DMAJPiker.setValue(m.getDateMaj().toLocalDateTime().toLocalDate());

    }

    private boolean controleDeSaisie() {

        if (nomField.getText().equals("")) {

            TrayNotification tray = new TrayNotification();
            tray.setTitle("fail");
            tray.setMessage("Ajout fail ");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }
        if (descriptionFiled.getText().equals("")) {

            TrayNotification tray = new TrayNotification();
            tray.setTitle("fail");
            tray.setMessage("Veuillez saisir le description");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (DCreationPiker.getValue() == null) {
            TrayNotification tray = new TrayNotification();
            tray.setTitle("fail");
            tray.setMessage("Veuillez saisir Date Maladie");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));

            return false;
        }

        if (DMAJPiker.getValue() == null) {
            TrayNotification tray = new TrayNotification();
            tray.setTitle("fail");
            tray.setMessage("Veuillez saisir Date Mise à Jour");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));

            return false;
        }

        return true;
    }

    void initializeCategorieController(GestionMaladieController controller) {
        this.controller = controller;
    }

}
