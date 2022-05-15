package org.loose.fis.bsa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class ViewScheduleController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Reservation, String> clientUsername;

    @FXML
    private TableColumn<Reservation, String> date;

    @FXML
    private TableColumn<Reservation, String> departmentfacility;

    @FXML
    private TableColumn<Reservation, String> hour;

    @FXML
    private TableColumn<Reservation, Integer> price;

    @FXML
    private TableView<Reservation> table;

    private static ObjectRepository<Reservation> reservationRepository = UserService.getReservationRepository();

    @FXML
    public void handleBackButton() throws IOException {
        Parent rootSignOut = FXMLLoader.load(getClass().getClassLoader().getResource("customerPage.fxml"));
        Stage stage = (Stage) (backButton.getScene().getWindow());
        stage.setTitle("Customer page");
        stage.setScene(new Scene(rootSignOut, 600, 400));
        stage.show();
    }

    @FXML
    public void initialize() {
        loadList();

    }

    @FXML
    public void loadList() {
        clientUsername.setCellValueFactory(new PropertyValueFactory<Reservation, String>("username"));
        departmentfacility.setCellValueFactory(new PropertyValueFactory<Reservation, String>("departmentfacility"));
        date.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));
        hour.setCellValueFactory(new PropertyValueFactory<Reservation, String>("hour"));
        price.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("price"));

        ObservableList<Reservation> reservationsList = FXCollections.observableArrayList();

        for(Reservation reservation : reservationRepository.find()) {
                reservationsList.add(reservation);
        }
        table.setItems(reservationsList);
    }



}
