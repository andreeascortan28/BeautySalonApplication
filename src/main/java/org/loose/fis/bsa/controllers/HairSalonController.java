package org.loose.fis.bsa.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.loose.fis.bsa.model.Edit;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HairSalonController /*implements Initializable*/ {
    @FXML
    private Button backField;
    @FXML
    private TableView<Edit> table;
    @FXML
    private TableColumn<Edit,String> FacilityColumn;
    @FXML
    private TableColumn<Edit,String> PriceColumn;

    public void handleInapoiAction() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("viewFacilities.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (backField.getScene().getWindow());
        stage.setScene(new Scene(root,600,400));
    }

    public void initialize(){
        FacilityColumn.setCellValueFactory(new PropertyValueFactory<>("Facility"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        table.setItems(observableList);
    }

    ObservableList<Edit> observableList = FXCollections.observableArrayList(
            new Edit("Face",50), new Edit("Legs", 100),
            new Edit("Back and chest", 100), new Edit("Underarms", 50),
            new Edit("Arms",50));

}



