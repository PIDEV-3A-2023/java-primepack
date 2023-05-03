/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Operation;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import jfxtras.scene.control.agenda.Agenda;
import services.OperationService;

/**
 * FXML Controller class
 *
 * @author nassi
 */
public class CalendarController implements Initializable {

@FXML
    private Agenda Calendar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OperationService service= new OperationService();
        List<Operation> list = service.getAll();
        System.out.println(list);
        for (Operation op : list) {
            Calendar.appointments().add(new Agenda.AppointmentImplLocal()
                    .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"))
                            .withSummary(op.getNomMedecin()+" , "+op.getTypeOperation())
                            
                    .withStartLocalDateTime(op.getDateOperation().toLocalDateTime())
                    .withEndLocalDateTime(op.getDateOperation().toLocalDateTime().plusHours(2))
                    );
        }
        // TODO
    }
    
    @FXML
    private void handleNextWeekButtonAction(ActionEvent event) {
        // Get the current displayed date and time
        LocalDateTime currentDateTime = Calendar.getDisplayedLocalDateTime();

        // Set the displayed date and time to the start of the next week
        LocalDateTime nextWeekDateTime = currentDateTime.plusWeeks(1);
        Calendar.setDisplayedLocalDateTime(nextWeekDateTime);
    }
    @FXML
    private void handlePreviousWeekButtonAction(ActionEvent event) {
        LocalDateTime currentDateTime = Calendar.getDisplayedLocalDateTime();
        LocalDateTime previousWeekDateTime = currentDateTime.minusWeeks(1);
        Calendar.setDisplayedLocalDateTime(previousWeekDateTime);
    }

    @FXML
    private void handlePreviousMonthButtonAction(ActionEvent event) {
        LocalDateTime currentDateTime = Calendar.getDisplayedLocalDateTime();
        LocalDateTime previousMonthDateTime = currentDateTime.minusMonths(1);
        Calendar.setDisplayedLocalDateTime(previousMonthDateTime);
    }

    @FXML
    private void handleNextMonthButtonAction(ActionEvent event) {
        LocalDateTime currentDateTime = Calendar.getDisplayedLocalDateTime();
        LocalDateTime nextMonthDateTime = currentDateTime.plusMonths(1);
        Calendar.setDisplayedLocalDateTime(nextMonthDateTime);
    }
    @FXML
    private void handlePreviousYearButtonAction(ActionEvent event) {
        LocalDateTime currentDateTime = Calendar.getDisplayedLocalDateTime();
        LocalDateTime previousYear = currentDateTime.minusYears(1);
        Calendar.setDisplayedLocalDateTime(previousYear);
    }
    @FXML
    private void handleNextYearButtonAction(ActionEvent event) {
        LocalDateTime currentDateTime = Calendar.getDisplayedLocalDateTime();
        LocalDateTime nextYear = currentDateTime.plusYears(1);
        Calendar.setDisplayedLocalDateTime(nextYear);
    }

      
    
}
