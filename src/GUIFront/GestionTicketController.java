/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Ticket;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.TicketService;

public class GestionTicketController implements Initializable {
    
    TicketService ticketService;
    ObservableList<Ticket> listTicket = FXCollections.observableArrayList();
    Ticket ticket;
    GestionTicketController gestionTicketController;
    @FXML
    private TableView<Ticket> evenementTableView;
    @FXML
    private TableColumn<Ticket, String> codeCell;
    @FXML
    private TableColumn<Ticket, String> prixCell;
    @FXML
    private TableColumn<Ticket, String> evenementCell;
    @FXML
    private TableColumn<Ticket, String> ActionCell;
    @FXML
    private Button ButtonAjout;
    @FXML
    private TableColumn<Ticket, Image> codeQRCell;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gestionTicketController = this;
        ticketService = new TicketService();
        LoadData();
    }
    
    public void refreshTable() {
        
        listTicket.clear();
        listTicket.addAll(ticketService.getAllByUser(BaseController.currentUser.getId()));
        evenementTableView.setItems(listTicket);
        
    }
    
    private void LoadData() {
        
        refreshTable();
        codeCell.setCellValueFactory(new PropertyValueFactory<>("code"));
        prixCell.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        evenementCell.setCellValueFactory(cellData
                -> {
            return new SimpleStringProperty(cellData.getValue().getEvnement().getNom());
        });
        
        codeQRCell.setMinWidth(100);
        codeQRCell.setCellValueFactory(param -> {
            try {
                String absolutePath = new File("").getAbsolutePath();
                String imageUrl = absolutePath + "\\src\\GUI\\QRCode\\" + param.getValue().getId() + ".jpg";
                System.out.println(imageUrl);
                return new SimpleObjectProperty<>(new Image(new File(imageUrl).toURI().toURL().toString(), 150, 150, true, true));
            } catch (MalformedURLException ex) {
                Logger.getLogger(GestionTicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        });
        
        codeQRCell.setCellFactory(column -> {
            return new TableCell<Ticket, Image>() {
                private final ImageView imageView = new ImageView();
                
                @Override
                protected void updateItem(Image item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        imageView.setImage(item);
                        setGraphic(imageView);
                    }
                }
            };
        });
        
        ActionCell.setCellFactory(createActionButton());
        
    }
    
    private Callback<TableColumn<Ticket, String>, TableCell<Ticket, String>> createActionButton() {
        Callback<TableColumn<Ticket, String>, TableCell<Ticket, String>> cellFoctory = (TableColumn<Ticket, String> param) -> {
            // make cell containing buttons
            final TableCell<Ticket, String> cell = new TableCell<Ticket, String>() {
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
                                ticket = evenementTableView.getSelectionModel().getSelectedItem();
                                ticketService.delete(ticket);
                                refreshTable();
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                
                                ticket = evenementTableView.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdateTicket.fxml"));
                                
                                Parent root;
                                root = loader.load();
                                
                                AddUpdateTicketController addUpdateTicketController = loader.getController();
                                addUpdateTicketController.initializeTicketController(gestionTicketController);
                                addUpdateTicketController.initializeTextField(ticket);
                                addUpdateTicketController.setWindowType("Update");
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
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("AddUpdateTicket.fxml"));
            Parent root = fxml.load();
            AddUpdateTicketController addUpdateTicketController = fxml.getController();
            addUpdateTicketController.setWindowType("Ajout");
            addUpdateTicketController.initializeTicketController(this);
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            
            Scene scene = new Scene(root, 600.0, Math.min(400.0, screenBounds.getHeight()));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
