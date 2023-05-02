/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import entites.Animal;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.data.general.DefaultPieDataset;
import services.AnimalService;

/**
 * FXML Controller class
 *
 * @author expert
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane root;
        @FXML
    private AnchorPane root1;
            @FXML
    private StackPane dashPane;

    /**
     * Initializes the controller class.
     */
           
    AnimalService animalService= new AnimalService();
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
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

                dashPane.getChildren().add(chartViewer);

            });
        });
    }    

    
    public void animal() throws IOException {
          Parent fmxlLoader = FXMLLoader.load(getClass().getResource("AfficherAnimal.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fmxlLoader);
        
    }

    
    public void ordonnance() throws IOException {
         Parent fmxlLoader = FXMLLoader.load(getClass().getResource("AfficherOrdonnanceFXML.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fmxlLoader);
    }
    
     public void rendezVous() throws IOException {
        Parent fmxlLoader = FXMLLoader.load(getClass().getResource("AfficherRendezVous.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fmxlLoader);
    }
     
     @FXML
    void home(MouseEvent event) throws IOException {
             Parent fmxlLoader = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        root1.getChildren().removeAll();
        root1.getChildren().setAll(fmxlLoader);

    }
    
    
    
}
