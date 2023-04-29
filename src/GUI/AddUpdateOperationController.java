/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Maladie;
import entites.Operation;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import services.MaladieService;
import services.OperationService;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class AddUpdateOperationController implements Initializable {

    OperationService service;
    MaladieService maladieService;
    Operation operation;
    String type;
    private GestionOperationController controller;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label TitleLabel;
    @FXML
    private DatePicker dateF;
    @FXML
    private TextField medecinFiled;
    @FXML
    private TextField coutFiled;
    @FXML
    private TextField NoteFiled;
    @FXML
    private Button actionButton;
    @FXML
    private ComboBox<Maladie> maldieCB;
    @FXML
    private ComboBox<?> animeleCB;
    @FXML
    private TextField typeFiled;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new OperationService();
        maladieService = new MaladieService();

        ObservableList<Maladie> list = FXCollections.observableArrayList();
        list.addAll(maladieService.getAll());
        maldieCB.setItems(list);
    }

    @FXML
    private void ajoutOrDelete(ActionEvent event) {
        if (controleDeSaisie()) {
            if (type.equals("Update")) {

                operation.setMaladie(maldieCB.getSelectionModel().getSelectedItem());
                operation.setAnimaleId(1);
                operation.setDateOperation(Timestamp.valueOf(dateF.getValue().atStartOfDay()));
                operation.setTypeOperation(typeFiled.getText());
                operation.setNomMedecin(medecinFiled.getText());
                operation.setCoutOperation(Float.parseFloat(coutFiled.getText()));
                operation.setNoteOperation(NoteFiled.getText());

                update(operation);
            } else {
                Operation operation = new Operation();
                operation.setMaladie(maldieCB.getSelectionModel().getSelectedItem());
                operation.setAnimaleId(1);
                operation.setDateOperation(Timestamp.valueOf(dateF.getValue().atStartOfDay()));
                operation.setTypeOperation(typeFiled.getText());
                operation.setNomMedecin(medecinFiled.getText());
                operation.setCoutOperation(Float.parseFloat(coutFiled.getText()));
                operation.setNoteOperation(NoteFiled.getText());
                ajout(operation);
            }
            controller.refreshTable();
        }
    }

    private void update(Operation o) {

        if (service.update(o)) {
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

    private void ajout(Operation o) {

        if (service.insert(o)) {

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
        TitleLabel.setText(type + " Operation");
    }

    public void initializeTextField(Operation o) {
        operation = o;

        maldieCB.getSelectionModel().select(o.getMaladie());
        dateF.setValue(o.getDateOperation().toLocalDateTime().toLocalDate());
        typeFiled.setText(o.getTypeOperation());
        medecinFiled.setText(o.getNomMedecin());
        coutFiled.setText(o.getCoutOperation() + "");
        NoteFiled.setText(o.getNoteOperation());

    }

    private boolean controleDeSaisie() {

        if (dateF.getValue() == null) {

            TrayNotification tray = new TrayNotification();
            tray.setTitle("fail");
            tray.setMessage("Veuillez selecet Date");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (dateF.getValue().isBefore(LocalDate.now())) {

            TrayNotification tray = new TrayNotification();
            tray.setTitle("fail");
            tray.setMessage("Veuillez selecet Date > date aujourduit");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }
        try {
            Float.parseFloat(coutFiled.getText());
        } catch (NumberFormatException e) {

            TrayNotification tray = new TrayNotification();
            tray.setTitle("fail");
            tray.setMessage(coutFiled.getText() + " n'est pas un nombre valide (nombre)");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }
        if (medecinFiled.getText().equals("")) {

            TrayNotification tray = new TrayNotification();
            tray.setTitle("fail");
            tray.setMessage("Veuillez saisir le nom medecin");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (typeFiled.getText().equals("")) {
            TrayNotification tray = new TrayNotification();
            tray.setTitle("fail");
            tray.setMessage("Veuillez saisir le type");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));

            return false;
        }

        if (NoteFiled.getText().equals("")) {

            TrayNotification tray = new TrayNotification();
            tray.setTitle("fail");
            tray.setMessage("Veuillez saisir le note");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }
        if (maldieCB.getSelectionModel().getSelectedItem() == null) {

            TrayNotification tray = new TrayNotification();
            tray.setTitle("fail");
            tray.setMessage("Veuillez saisir le maladie");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        return true;
    }

    void initializeController(GestionOperationController controller) {
        this.controller = controller;
    }

}
