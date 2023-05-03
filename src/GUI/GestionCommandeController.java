/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Commande;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class GestionCommandeController implements Initializable {

    CommandeService commandeService;
    ObservableList<Commande> listCommandes = FXCollections.observableArrayList();
    Commande commande;
    GestionCommandeController gestionCommandeController;

    @FXML
    private TableView<Commande> categorieTableView;
    @FXML
    private TableColumn<Commande, String> quantiteCell;
    @FXML
    private TableColumn<Commande, String> nomProduitCell;
    @FXML
    private TableColumn<Commande, String> userCell;
    @FXML
    private TableColumn<Commande, String> ActionCell;
    @FXML
    private Button ButtonAjout;
    @FXML
    private TableColumn<Commande, String> totaleCell;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gestionCommandeController = this;
        commandeService = new CommandeService();
        LoadData();
    }

    public void refreshTable() {

        listCommandes.clear();
        listCommandes.addAll(commandeService.getAll());
        categorieTableView.setItems(listCommandes);

    }

    private void LoadData() {

        refreshTable();
        quantiteCell.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        nomProduitCell.setCellValueFactory(cellData
                -> {
            return new SimpleStringProperty(cellData.getValue().getProduit().getNom());
        });
        userCell.setCellValueFactory(cellData
                -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser() + "");
        });

        totaleCell.setCellValueFactory(cellData
                -> {
            return new SimpleStringProperty(cellData.getValue().getProduit().getPrix() * cellData.getValue().getQuantite() + "");
        });

        ActionCell.setCellFactory(createActionButton());

    }

    private Callback<TableColumn<Commande, String>, TableCell<Commande, String>> createActionButton() {
        Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFoctory = (TableColumn<Commande, String> param) -> {
            // make cell containing buttons
            final TableCell<Commande, String> cell = new TableCell<Commande, String>() {
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
                                commande = categorieTableView.getSelectionModel().getSelectedItem();
                                commandeService.delete(commande);
                                refreshTable();
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {

                                commande = categorieTableView.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdateCommande.fxml"));

                                Parent root;
                                root = loader.load();

                                AddUpdateCommandeController addUpdateCommandeController = loader.getController();
                                addUpdateCommandeController.initializeCommandeController(gestionCommandeController);
                                addUpdateCommandeController.initializeTextField(commande);
                                addUpdateCommandeController.setWindowType("Update");
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(GestionCategorieController.class.getName()).log(Level.SEVERE, null, ex);
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
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("AddUpdateCommande.fxml"));
            Parent root = fxml.load();
            AddUpdateCommandeController addUpdateCommandeController = fxml.getController();
            addUpdateCommandeController.setWindowType("Ajout");
            addUpdateCommandeController.initializeCommandeController(this);
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();

            Scene scene = new Scene(root, 600.0, Math.min(400.0, screenBounds.getHeight()));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void export(ActionEvent event) {

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Sheet1");
        HSSFRow firstRow = hssfSheet.createRow(0);
        String absolutePath = new File("").getAbsolutePath();

        ///set titles of columns
        for (short i = 0; i < categorieTableView.getColumns().size(); i++) {

            firstRow.createCell(i).setCellValue(categorieTableView.getColumns().get(i).getText());

        }

        for (short row = 0; row < categorieTableView.getItems().size(); row++) {

            HSSFRow hssfRow = hssfSheet.createRow(row + 1);

            for (short col = 0; col < 4; col++) {

                System.out.println(col);

                Object celValue = categorieTableView.getColumns().get(col).getCellObservableValue(row).getValue();

                try {
                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
                        hssfRow.createCell(col).setCellValue(Double.parseDouble(celValue.toString()));
                    }
                } catch (NumberFormatException e) {

                    hssfRow.createCell(col).setCellValue(celValue.toString());
                }

            }

        }

        //save excel file and close the workbook
        try {
            hssfWorkbook.write(new FileOutputStream(absolutePath + "\\src\\EXEL\\" + LocalDate.now() + ".xls"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("export succès");
            alert.setTitle("Succès");
            alert.show();
            hssfWorkbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file = new File(absolutePath + "\\src\\EXEL");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(GestionCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
