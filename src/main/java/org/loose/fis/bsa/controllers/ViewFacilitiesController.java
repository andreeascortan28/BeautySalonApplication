package org.loose.fis.bsa.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.loose.fis.bsa.model.User;
import org.loose.fis.bsa.services.UserService;

import java.awt.*;

public class ViewFacilitiesController {
    @FXML
    private Button backField;
    @FXML
    private Button hairsalonField;
    @FXML
    private Button makeupField;
    @FXML
    private Button hairremovalField;
    @FXML
    private Button facialtreatmentsField;
    @FXML
    private Button nailsField;
    @FXML
    private Button massageField;

    public void handleInapoiAction() throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("customerPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) (backField.getScene().getWindow());
            stage.setScene(new Scene(root,600,400));
    }

    public void handleHairSalon() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("HairSalon.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (backField.getScene().getWindow());
        stage.setScene(new Scene(root, 600, 400));
    }

    public void handleFacialTreatments() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FacialTreatments.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (backField.getScene().getWindow());
        stage.setScene(new Scene(root, 600, 400));
    }

    public void handleHairRemoval() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("HairRemoval.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (backField.getScene().getWindow());
        stage.setScene(new Scene(root, 600, 400));
    }

    public void handleMakeUp() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MakeUp.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (backField.getScene().getWindow());
        stage.setScene(new Scene(root, 600, 400));
    }

    public void handleMassage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Massage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (backField.getScene().getWindow());
        stage.setScene(new Scene(root, 600, 400));
    }

    public void handleNails() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Nails.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (backField.getScene().getWindow());
        stage.setScene(new Scene(root, 600, 400));
    }
}

