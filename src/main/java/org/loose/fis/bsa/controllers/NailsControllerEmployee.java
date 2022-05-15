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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.loose.fis.bsa.model.Edit;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class NailsControllerEmployee implements Initializable {
    @FXML
    private Button back;
    @FXML
    private Button save;
    @FXML
    private TableView<Edit> table;
    @FXML
    private TableColumn<Edit,String> FacilityColumn;
    @FXML
    private TableColumn<Edit,String> PriceColumn;

    public void handleInapoiAction() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("viewFacilitiesEmployee.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        stage.setScene(new Scene(root,600,400));
    }

    public void initialize(URL location, ResourceBundle resource){
        FacilityColumn.setCellValueFactory(new PropertyValueFactory<>("Facility"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        table.setItems(observableList);
        table.setEditable(true);
        PriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    ObservableList<Edit> observableList = FXCollections.observableArrayList(
            new Edit("Manicure","250 RON"), new Edit("Pedicure", "200 RON"),
            new Edit("Mani-Pedi","450 RON"));

    public void onEditChange(TableColumn.CellEditEvent<Edit, String> editStringCellEditEvent) {
        Edit e1 = table.getSelectionModel().getSelectedItem();
        e1.setPrice(editStringCellEditEvent.getNewValue());
    }
}
