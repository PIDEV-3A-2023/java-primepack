/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
    private TableColumn<Evenement, String> ActionCell;
    @FXML
    private Button ButtonAjout;
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

        ActionCell.setCellFactory(createActionButton());

    }

    private Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> createActionButton() {
        Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFoctory = (TableColumn<Evenement, String> param) -> {
            // make cell containing buttons
            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView logoIcon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_CIRCLE);
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirmation de suppression ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                            alert.showAndWait();

                            if (alert.getResult() == ButtonType.YES) {
                                evenement = evenementTableView.getSelectionModel().getSelectedItem();
                                evenementService.delete(evenement);
                                refreshTable();
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {

                                evenement = evenementTableView.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdateEvenement.fxml"));

                                Parent root;
                                root = loader.load();

                                AddUpdateEvenementController addUpdateEvenementController = loader.getController();
                                addUpdateEvenementController.initializeEvenementController(gestionEvenementController);
                                addUpdateEvenementController.initializeTextField(evenement);
                                addUpdateEvenementController.setWindowType("Update");
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(GestionEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                        logoIcon.setOnMouseClicked((MouseEvent event) -> {

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(logoIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        return cellFoctory;
    }

    @FXML
    private void openAjoutDialog(ActionEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("AddUpdateEvenement.fxml"));
            Parent root = fxml.load();
            AddUpdateEvenementController addUpdateEvenementController = fxml.getController();
            addUpdateEvenementController.setWindowType("Ajout");
            addUpdateEvenementController.initializeEvenementController(this);
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();

            Scene scene = new Scene(root, 600.0, Math.min(400.0, screenBounds.getHeight()));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Recherche(ActionEvent event) {
        refreshTable();
    }
}
