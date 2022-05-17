package org.loose.fis.bsa.controllers;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import org.loose.fis.bsa.model.User;
import org.loose.fis.bsa.model.Edit;
import org.loose.fis.bsa.services.UserService;

import java.net.URL;
import java.util.ResourceBundle;
import java.awt.*;

import static javafx.scene.control.cell.TextFieldTableCell.*;

public class FacialTreatmentsControllerEmployee implements Initializable {
    @FXML
    private Button back;
    @FXML
    private Button save;
    @FXML
    private TableView<Edit> table;
    @FXML
    private TableColumn<Edit, String> FacilityColumn;
    @FXML
    private TableColumn<Edit, Integer> PriceColumn;

    public void handleInapoiAction() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("viewFacilitiesEmployee.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        stage.setScene(new Scene(root, 600, 400));
    }

    public void initialize(URL location, ResourceBundle resource) {
        FacilityColumn.setCellValueFactory(new PropertyValueFactory<>("Facility"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        table.setItems(observableList);
        table.setEditable(true);
        //PriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    ObservableList<Edit> observableList = FXCollections.observableArrayList(
            new Edit("Classical Facial", 500), new Edit("Acnee reduction facial", 200),
            new Edit("LED light therapy", 400), new Edit("Acupuncture facial", 300)
    );

    public void onEditChange(TableColumn.CellEditEvent<Edit, Integer> editIntegerCellEditEvent) {
        Edit e1 = table.getSelectionModel().getSelectedItem();
        e1.setPrice(editIntegerCellEditEvent.getNewValue());

    }

    public void saveChanges() throws Exception {


        TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        Edit item = table.getItems().get(row);
        TableColumn col = pos.getTableColumn();
        String new_price = (String) col.getCellObservableValue(item).getValue();
        //UserService.changePrice(FacilityColumn.getCellData(row), new_price);


    }
}

