package org.loose.fis.bsa.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

import org.loose.fis.bsa.controllers.DeleteReservationsController;
import org.loose.fis.bsa.model.Reservation;
import org.loose.fis.bsa.services.UserService;


public class ReasonForDeletingReservationController {

    @FXML
    private Button okButton;

    @FXML
    private TextArea textArea;

    @FXML
    private Button backButton;


    @FXML
    void handleOkButton(ActionEvent event) {


    }

    @FXML
    public void handleBackButton() throws IOException {
        Parent rootBack = FXMLLoader.load(getClass().getClassLoader().getResource("deleteReservations.fxml"));
        Stage stage = (Stage) (backButton.getScene().getWindow());
        stage.setTitle("Customer page");
        stage.setScene(new Scene(rootBack, 600, 400));
        stage.show();
    }

}

