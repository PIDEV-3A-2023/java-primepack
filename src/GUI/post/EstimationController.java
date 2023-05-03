/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.post;

import GUIFront.BaseController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class EstimationController implements Initializable {

    @FXML
    private Button carButton;
    @FXML
    private Button motorButton;
    @FXML
    private Button flight;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void redirectToCar(ActionEvent event) throws IOException {

        BaseController.baseController.post("CarbonCar.fxml", "Car Estimation");

    }

    @FXML
    private void redirectToMotor(ActionEvent event) throws IOException {

        BaseController.baseController.post("CarbonMotorBike.fxml", "Motorbike Estimation");

    }

    @FXML
    private void redirectToFlight(ActionEvent event) throws IOException {

        BaseController.baseController.post("CarbonFlight.fxml", "Flight Estimation");

    }

    @FXML
    private void back(ActionEvent event) throws IOException {

        BaseController.baseController.post("PostFront.fxml", "Post Front");

    }

}
