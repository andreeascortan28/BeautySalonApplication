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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import org.loose.fis.bsa.model.User;
import org.loose.fis.bsa.model.Edit;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.*;

import static javafx.scene.control.cell.TextFieldTableCell.*;

public class FacialTreatmentsController implements Initializable
{
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

    public void initialize(URL location, ResourceBundle resource){
        FacilityColumn.setCellValueFactory(new PropertyValueFactory<>("Facility"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        table.setItems(observableList);
    }

    ObservableList<Edit> observableList = FXCollections.observableArrayList(
            new Edit("Classical Facial",500 ), new Edit("Acnee reduction facial", 200 ),
            new Edit("LED light therapy", 400 ), new Edit("Acupuncture facial", 300)
    );
}
