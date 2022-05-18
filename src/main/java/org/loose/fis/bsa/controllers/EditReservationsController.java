package org.loose.fis.bsa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.bsa.model.Edit;
import org.loose.fis.bsa.model.Reservation;
import org.loose.fis.bsa.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditReservationsController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private TableColumn<Reservation, String> date;

    @FXML
    private TableColumn<Reservation, String> depfac;

    @FXML
    private TableColumn<Reservation, String> hour;

    @FXML
    private TableColumn<Reservation, String> price;

    @FXML
    private Button save;

    @FXML
    private TableView<Reservation> table;

    @FXML
    private TableColumn<Reservation, String> username;

    @FXML
    void handleInapoiAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("employeePage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        stage.setScene(new Scene(root, 600, 400));

    }
    @FXML
    public void initialize(URL location, ResourceBundle resource) {
        table.setEditable(true);
        date.setCellFactory(TextFieldTableCell.forTableColumn());
        hour.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setCellFactory(TextFieldTableCell.forTableColumn());
        loadList();
    }
    public void onEditChange(TableColumn.CellEditEvent<Edit, String> editStringCellEditEvent) {
        Reservation e1 = table.getSelectionModel().getSelectedItem();
       // e1.setPrice(editStringCellEditEvent.getNewValue());// e un int, iar eu am facut cu String
        //trebuie sa facem si butonul de save si aici si la celelalt task
        // din cauza asta, nu se mai vede tabelul din rezervari
    }
    private static ObjectRepository<Reservation> reservationRepository = UserService.getReservationRepository();


    @FXML
    public void loadList() {
        username.setCellValueFactory(new PropertyValueFactory<Reservation, String>("username"));
        depfac.setCellValueFactory(new PropertyValueFactory<Reservation, String>("departmentfacility"));
        date.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));
        hour.setCellValueFactory(new PropertyValueFactory<Reservation, String>("hour"));
        price.setCellValueFactory(new PropertyValueFactory<Reservation, String>("price"));

        ObservableList<Reservation> reservationsList = FXCollections.observableArrayList();

        for(Reservation reservation : reservationRepository.find()) {
            reservationsList.add(reservation);
        }
        table.setItems(reservationsList);
    }
}
