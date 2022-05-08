package org.loose.fis.bsa.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageCustomerController {

    @FXML
    private Button makeReservationButton;

    @FXML
    private Button viewFacilitiesButton;

    @FXML
    private Button viewReservationButton;

    @FXML
    private Button signOutButton;


    @FXML
    public void handleMakeReservation() throws IOException {
        Parent rootMakeReservation = FXMLLoader.load(getClass().getClassLoader().getResource("makeRegistration.fxml"));
        Stage stage = (Stage) (makeReservationButton.getScene().getWindow());
        stage.setTitle("Make Reservation");
        stage.setScene(new Scene(rootMakeReservation, 600, 400));
        stage.show();
    }

    @FXML
    public void handleViewFacilities() throws IOException {
        Parent rootViewFacilities = FXMLLoader.load(getClass().getClassLoader().getResource("viewFacilities.fxml"));
        Stage stage = (Stage) (viewFacilitiesButton.getScene().getWindow());
        stage.setTitle("View Facilities");
        stage.setScene(new Scene(rootViewFacilities, 600, 400));
        stage.show();
    }

    @FXML
    public void handleViewReservations() throws IOException {
        Parent rootViewReservations = FXMLLoader.load(getClass().getClassLoader().getResource("viewReservations.fxml"));
        Stage stage = (Stage) (viewReservationButton.getScene().getWindow());
        stage.setTitle("View Reservations");
        stage.setScene(new Scene(rootViewReservations, 600, 400));
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


