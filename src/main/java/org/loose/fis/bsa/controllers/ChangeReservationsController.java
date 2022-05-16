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


public class ChangeReservationsController {
    @FXML
    private Button back;

    @FXML
    private TableColumn<Reservation, String> date;

    @FXML
    private TableColumn<Reservation, String> depfac;

    @FXML
    private TableColumn<Reservation, String> hour;

    @FXML
    private TableColumn<Reservation, Integer> price;

    @FXML
    private Button save;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<Reservation, String> user;

    @FXML
    void handleInapoiAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("viewFacilitiesEmployee.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        stage.setScene(new Scene(root,600,400));
    }

    @FXML
    public void loadList() {
        user.setCellValueFactory(new PropertyValueFactory<Reservation, String>("user"));
        depfac.setCellValueFactory(new PropertyValueFactory<Reservation, String>("depfac"));
        date.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));
        hour.setCellValueFactory(new PropertyValueFactory<Reservation, String>("hour"));
        price.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("price"));

        ObservableList<Reservation> reservationsList = FXCollections.observableArrayList();
        /*private static ObjectRepository<Reservation> reservationRepository = UserService.getReservationRepository();
        for(Reservation reservation : reservationRepository.find()) {
            if(LoggedUser.getLoggedUser().equals(reservation.getUsername()))
                reservationsList.add(reservation);
        }
        table.setItems(reservationsList);*/
    }


    @FXML
    public void initialize() {
        loadList();
    }
}
