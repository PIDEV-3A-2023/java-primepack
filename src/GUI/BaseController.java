/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Animal;
import entites.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.data.general.DefaultPieDataset;
import services.AnimalService;
import services.UserService;

public class BaseController implements Initializable {

    AnimalService animalService = new AnimalService();

    public static User currentUser;

    public static BaseController baseController;
    @FXML
    private Label TitreLabel;
    @FXML
    private StackPane AnchorePaneLayout;
    @FXML
    private Label ConnectedUserNameLabel;
    @FXML
    private ImageView logoImageview;
    @FXML
    private HBox gestionEvenementButton;
    @FXML
    private HBox GestionTicketButon;
    @FXML
    private HBox gestionProduitButton;
    @FXML
    private HBox GestionCategorieButon;
    @FXML
    private HBox GestionCommandeButon;
    @FXML
    private HBox GestionAnimaleButon;
    @FXML
    private HBox GestionRendezVousButon;
    @FXML
    private HBox GestionOrdonnanceButon;
    @FXML
    private HBox GestionDashBoardAnimaleButon;
    @FXML
    private HBox GestionMaldieButon;
    @FXML
    private HBox GestionOperationButon;
    @FXML
    private HBox GestionPostButon;
    @FXML
    private HBox GestionCommentButon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService us = new UserService();
//        currentUser = us.AfficherU();
        ConnectedUserNameLabel.setText(currentUser.getNom());
        baseController = this;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/GestionEvenement.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Evenement");
            gestionEvenementButton.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultPieDataset createDataset(List<Animal> animaux) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<String, Integer> typeCounts = new HashMap<>();

        for (Animal animal : animaux) {
            String type = animal.getRace();
            int count = typeCounts.getOrDefault(type, 0) + 1;
            typeCounts.put(type, count);
        }

        for (Map.Entry<String, Integer> entry : typeCounts.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        return dataset;
    }

    private void removeSelectedStyle() {
        gestionEvenementButton.getStyleClass().clear();
        GestionTicketButon.getStyleClass().clear();
        gestionProduitButton.getStyleClass().clear();
        GestionCategorieButon.getStyleClass().clear();
        GestionCommandeButon.getStyleClass().clear();
        GestionAnimaleButon.getStyleClass().clear();
        GestionRendezVousButon.getStyleClass().clear();
        GestionOrdonnanceButon.getStyleClass().clear();
        GestionDashBoardAnimaleButon.getStyleClass().clear();
        GestionMaldieButon.getStyleClass().clear();
        GestionOperationButon.getStyleClass().clear();
        GestionPostButon.getStyleClass().clear();
        GestionCommentButon.getStyleClass().clear();

        GestionMaldieButon.getStyleClass().add("btns");
        GestionOperationButon.getStyleClass().add("btns");
        gestionEvenementButton.getStyleClass().add("btns");
        GestionTicketButon.getStyleClass().add("btns");
        gestionProduitButton.getStyleClass().add("btns");
        GestionCategorieButon.getStyleClass().add("btns");
        GestionCommandeButon.getStyleClass().add("btns");
        GestionAnimaleButon.getStyleClass().add("btns");
        GestionRendezVousButon.getStyleClass().add("btns");
        GestionOrdonnanceButon.getStyleClass().add("btns");
        GestionDashBoardAnimaleButon.getStyleClass().add("btns");
        GestionPostButon.getStyleClass().add("btns");
        GestionCommentButon.getStyleClass().add("btns");

    }

    @FXML
    private void LoadGestionEvenement(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GestionEvenement.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Evenement ");
            gestionEvenementButton.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionTicket(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GestionTicket.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion ticket ");
            GestionTicketButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionProduit(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GestionProduit.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Produit ");
            gestionProduitButton.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionCategorie(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GestionCategorie.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Categorie");
            GestionCategorieButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionCommande(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GestionCommande.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Commande");
            GestionCommandeButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionAnimale(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/GUI/Animale/AfficherAnimal.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Animale");
            GestionAnimaleButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void LoadGestionRendezVous(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/Animale/AfficherRendezVous.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion RendezVous");
            GestionRendezVousButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionOrdonnance(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/Animale/AfficherOrdonnanceFXML.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Ordonnance");
            GestionOrdonnanceButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadDashBoardAnimale(MouseEvent event) {
        removeSelectedStyle();
        TitreLabel.setText("DashBoard Animale");
        GestionDashBoardAnimaleButon.getStyleClass().add("btn-selected");
        SwingUtilities.invokeLater(() -> {
            Platform.runLater(() -> {
                DefaultPieDataset dataset = null;
                try {
                    dataset = createDataset(animalService.afficherListeA());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                JFreeChart chart = ChartFactory.createPieChart(
                        "Animaux by Race",
                        dataset,
                        true,
                        true,
                        false
                );
                ChartViewer chartViewer = new ChartViewer(chart);
                AnchorePaneLayout.getChildren().removeAll();
                AnchorePaneLayout.getChildren().add(chartViewer);

            });
        });
    }

    @FXML
    private void LoadGestionMaladie(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GestionMaladie.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Maladie ");
            GestionMaldieButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void LoadGestionOperation(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GestionOperation.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Operation");
            GestionOperationButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionPost(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/post/PostBack.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Post");
            GestionPostButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void post(String fxml, String titre) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/post/" + fxml));
            removeSelectedStyle();
            TitreLabel.setText(titre);
            GestionPostButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionComment(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/post/ShowCommentBack.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Comment");
            GestionCommentButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
