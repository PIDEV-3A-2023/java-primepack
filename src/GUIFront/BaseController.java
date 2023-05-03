/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

import entites.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import services.UserService;

public class BaseController implements Initializable {

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
    private HBox GestionPostButon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService us = new UserService();
//        currentUser = us.AfficherU();
//        GUI.BaseController.currentUser = currentUser;
        ConnectedUserNameLabel.setText(currentUser.getNom());

        baseController = this;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GestionEvenement.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Evenement");
            gestionEvenementButton.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeSelectedStyle() {
        gestionEvenementButton.getStyleClass().clear();
        GestionTicketButon.getStyleClass().clear();
        gestionProduitButton.getStyleClass().clear();
        GestionCategorieButon.getStyleClass().clear();
        GestionCommandeButon.getStyleClass().clear();
        GestionAnimaleButon.getStyleClass().clear();
        GestionRendezVousButon.getStyleClass().clear();
        GestionPostButon.getStyleClass().clear();

        gestionEvenementButton.getStyleClass().add("btns");
        GestionTicketButon.getStyleClass().add("btns");
        gestionProduitButton.getStyleClass().add("btns");
        GestionCategorieButon.getStyleClass().add("btns");
        GestionCommandeButon.getStyleClass().add("btns");
        GestionAnimaleButon.getStyleClass().add("btns");
        GestionRendezVousButon.getStyleClass().add("btns");
        GestionPostButon.getStyleClass().add("btns");

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
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/Animale/AnimalFXML.fxml"));
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
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/GUI/Animale/RendezvousFXML.fxml"));
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
    private void LoadGestionPost(MouseEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/GUI/post/postFront.fxml"));
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
            Logger.getLogger(GUI.BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
