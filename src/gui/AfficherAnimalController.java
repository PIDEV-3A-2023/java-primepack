/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Animal;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.AnimalService;
import static sun.io.Win32ErrorMode.initialize;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author expert
 */
public class AfficherAnimalController implements Initializable {

    @FXML
    private TableView<Animal> animatable;
    ObservableList<Animal> animalList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Animal, String> nomcol;
    @FXML
    private TableColumn<Animal, String> racecol;
    @FXML
    private TableColumn<Animal, String> genrecol;
    @FXML
    private TableColumn<Animal, String> agecol;
    @FXML
    private TableColumn<Animal, String> desccol;
    @FXML
    private AnchorPane root;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    
      @FXML
    private TextField txt_search;

    
    
    
    

    /**
     * Initializes the controller class.
     */
    AnimalService animalService = new AnimalService();
        @FXML
         void add() throws IOException {
        Parent fmxlLoader = FXMLLoader.load(getClass().getResource("AnimalFXML.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fmxlLoader);

    }
         public void catDelete() throws SQLException {
             
        Animal cat=animatable.getSelectionModel().getSelectedItem();      
        int num = animatable.getSelectionModel().getSelectedIndex();
        if((num - 1)<-1){return;}
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cofirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE animal : " + cat.getNom() + "?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {

           animalService.supprimerAnimal(cat.getId());
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Deleted!");
            alert.showAndWait();
            
            try {
            AnimalService us = new AnimalService();
            List<Animal> animal = us.afficherListeA();
            ObservableList<Animal> list = FXCollections.observableArrayList(animal);

            Connection con = MyDB.getInstance().getConx();
            ResultSet rs = con.createStatement().executeQuery("select * from animal");

            while (rs.next()) {
                animalList.add(new Animal(
                        rs.getString("nom"),
                        rs.getString("race"),
                  
                        rs.getString("genre"),
                        rs.getString("age"),
                        rs.getString("description")));
                       
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        loadDate();
        animatable.setItems(animalList);
           
                    
                    
                    
            
          
        }
    }
  
    @FXML
    public void aniimalUpdate() throws IOException {
        Animal animal=animatable.getSelectionModel().getSelectedItem();
        int num = animatable.getSelectionModel().getSelectedIndex();
        if((num - 1)<-1){return;}
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierFXML.fxml"));
        
        Parent root1 = loader.load();
     

        
        
        TextField tfid = (TextField) loader.getNamespace().get("tfid");
        TextField tfnom = (TextField) loader.getNamespace().get("tfnom");
        TextField tfrace = (TextField) loader.getNamespace().get("tfrace");
        TextField tfgenre = (TextField) loader.getNamespace().get("tfgenre");
        TextField tfage = (TextField) loader.getNamespace().get("tfage");
        TextField tfdesc = (TextField) loader.getNamespace().get("tfdesc");
       
        tfid.setText(String.valueOf(animal.getId()));

        tfnom.setText(animal.getNom());
        tfrace.setText(animal.getRace());
        tfgenre.setText(animal.getGenre());
 
        tfage.setText(animal.getAge());

        tfdesc.setText(animal.getDescription());
        
       

       
        root.getChildren().removeAll();
        root.getChildren().setAll(root1);
    }
         
    
        @FXML
    void search(KeyEvent event) throws SQLException {
        
        ObservableList<Animal> allAnimals = FXCollections.observableList(animalService.afficherListeA());
            FilteredList<Animal> filter = new FilteredList<>( allAnimals, e -> true);
        txt_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateAnimal -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (predicateAnimal.getNom().toLowerCase().contains(searchKey)) {
                    return true;
                }
                return false;
            });
        });
            SortedList<Animal> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(animatable.comparatorProperty());
        animatable.setItems(sortList);
    

    }
    
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       try {
            AnimalService us = new AnimalService();
            List<Animal> animal = us.afficherListeA();
            ObservableList<Animal> list = FXCollections.observableArrayList(animal);

            Connection con = MyDB.getInstance().getConx();
            ResultSet rs = con.createStatement().executeQuery("select * from animal");

            while (rs.next()) {
                animalList.add(new Animal(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("race"),
                  
                        rs.getString("genre"),
                        rs.getString("age"),
                        rs.getString("description")));
                       
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        loadDate();
        animatable.setItems(animalList);
   
        
}
    
 private void loadDate() {
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        racecol.setCellValueFactory(new PropertyValueFactory<>("race"));
        genrecol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        agecol.setCellValueFactory(new PropertyValueFactory<>("age"));
        desccol.setCellValueFactory(new PropertyValueFactory<>("description"));
       
    }

 
}