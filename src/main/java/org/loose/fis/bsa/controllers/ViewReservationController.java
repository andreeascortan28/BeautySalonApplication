package org.loose.fis.bsa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.bsa.model.LoggedUser;
import org.loose.fis.bsa.model.Reservation;
import org.loose.fis.bsa.services.UserService;

import java.io.IOException;

public class ViewReservationController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Reservation, String> date_col;

    @FXML
    private TableColumn<Reservation, String> departmentfacility_col;

    @FXML
    private TableColumn<Reservation, String> hour_col;

    @FXML
    private TableView<Reservation> table;

    @FXML
    private TableColumn<Reservation, String> username_col;
    @FXML
    private TableColumn<Reservation, Integer> price_col;

    private static ObjectRepository<Reservation> reservationRepository = UserService.getReservationRepository();

    @FXML
    public void loadList() {
        username_col.setCellValueFactory(new PropertyValueFactory<Reservation, String>("username"));
        departmentfacility_col.setCellValueFactory(new PropertyValueFactory<Reservation, String>("departmentfacility"));
        date_col.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));
        hour_col.setCellValueFactory(new PropertyValueFactory<Reservation, String>("hour"));
        price_col.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("price"));

        ObservableList<Reservation> reservationsList = FXCollections.observableArrayList();

        for(Reservation reservation : reservationRepository.find()) {
            if(LoggedUser.getLoggedUser().equals(reservation.getUsername()))
                reservationsList.add(reservation);
        }
        table.setItems(reservationsList);
    }


    @FXML
    public void initialize() {
        loadList();
    }



    @FXML
    public void handleBackAction() throws IOException {
        Parent rootSignOut = FXMLLoader.load(getClass().getClassLoader().getResource("customerPage.fxml"));
        Stage stage = (Stage) (backButton.getScene().getWindow());
        stage.setTitle("Customer page");
        stage.setScene(new Scene(rootSignOut, 600, 400));
        stage.show();
    }
}
