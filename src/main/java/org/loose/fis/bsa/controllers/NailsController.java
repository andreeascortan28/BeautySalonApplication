package org.loose.fis.bsa.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.awt.*;
public class NailsController {
    @FXML
    private Button backField;

    public void handleInapoiAction() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("viewFacilities.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (backField.getScene().getWindow());
        stage.setScene(new Scene(root,600,400));
    }
}
