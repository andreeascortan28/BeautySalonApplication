package org.loose.fis.bsa.controllers;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.util.converter.IntegerStringConverter;
import org.loose.fis.bsa.model.User;
import org.loose.fis.bsa.model.Edit;
import org.loose.fis.bsa.services.UserService;

import java.net.URL;
import java.util.ResourceBundle;
import java.awt.*;

import static javafx.scene.control.cell.TextFieldTableCell.*;

public class FacialTreatmentsControllerEmployee implements Initializable {
    private final String DEPT = "Facial treatments";
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

    @FXML
    public void initialize(URL location, ResourceBundle resource){
        try {

            FacilityColumn.setCellValueFactory(new PropertyValueFactory<>("Facility"));
            PriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
            table.setItems(UserService.getAllFacilitiesByDept("Facial treatments"));
            table.setEditable(true);
            PriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void onEditChange(TableColumn.CellEditEvent<Edit, Integer> editStringCellEditEvent) {
        Edit e1 = table.getSelectionModel().getSelectedItem();
        e1.setPrice(editStringCellEditEvent.getNewValue());
    }

    public void saveChanges(ActionEvent evt) {
        for(Edit e : table.getItems()){
            UserService.updateDeptPrice(DEPT+" - "+e.getFacility(),e.getPrice());
        }
    }
}
