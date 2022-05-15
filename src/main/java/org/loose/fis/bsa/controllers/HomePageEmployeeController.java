package org.loose.fis.bsa.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageEmployeeController {

    @FXML
    private Button editReservationsButton;

    @FXML
    private Button changePricesButton;

    @FXML
    private Button deleteReservationsButton;

    @FXML
    private Button viewScheduleButton;

    @FXML
    private Button signOutButton;


    @FXML
    public void handleEditReservation() throws IOException {
        Parent rootEditReservation = FXMLLoader.load(getClass().getClassLoader().getResource("editReservations.fxml"));
        Stage stage = (Stage) (editReservationsButton.getScene().getWindow());
        stage.setTitle("Edit Reservations");
        stage.setScene(new Scene(rootEditReservation, 600, 400));
        stage.show();

    }

    @FXML
    public void handleChangePrices() throws IOException {
        Parent rootEditReservation = FXMLLoader.load(getClass().getClassLoader().getResource("viewFacilitiesEmployee.fxml"));
        Stage stage = (Stage) (changePricesButton.getScene().getWindow());
        stage.setTitle("Departments");
        stage.setScene(new Scene(rootEditReservation, 600, 400));
        stage.show();

    }

    @FXML
    public void handleDeleteReservations() throws IOException {
        Parent rootDeleteReservation = FXMLLoader.load(getClass().getClassLoader().getResource("deleteReservations.fxml"));
        Stage stage = (Stage) (deleteReservationsButton.getScene().getWindow());
        stage.setTitle("Delete Reservations");
        stage.setScene(new Scene(rootDeleteReservation, 600, 400));
        stage.show();

    }

    @FXML
    public void handleViewSchedule() throws IOException {
        Parent rootViewSchedule = FXMLLoader.load(getClass().getClassLoader().getResource("viewSchedule.fxml"));
        Stage stage = (Stage) (viewScheduleButton.getScene().getWindow());
        stage.setTitle("View Schedule");
        stage.setScene(new Scene(rootViewSchedule, 600, 400));
        stage.show();

    }

    @FXML
    public void handleSignOut() throws IOException {
        Parent rootSignOut = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage stage = (Stage) (signOutButton.getScene().getWindow());
        stage.setTitle("Sign in");
        stage.setScene(new Scene(rootSignOut, 600, 400));
        stage.show();
    }
}
