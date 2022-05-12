package org.loose.fis.bsa.controllers;

import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.bsa.model.Reservation;
import org.loose.fis.bsa.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewReservationsController implements Initializable {

    @FXML
    private Button backButton;

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

    @FXML
    private TableColumn<Reservation, String> username;

    private static ObjectRepository<Reservation> reservationRepository = UserService.getReservationRepository();

    ObservableList<Reservation> list =  FXCollections.observableArrayList();


    public ObservableList<Reservation> loadList() {
        for (Reservation reservation : reservationRepository.find()) {
            list.add(new Reservation(reservation.getUsername(), reservation.getDepartmentfacility(), reservation.getDate(), reservation.getHour(), reservation.getPrice()));
        }
        return list;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setCellValueFactory(new PropertyValueFactory<Reservation, String>("username"));
        date.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));
        hour.setCellValueFactory(new PropertyValueFactory<Reservation, String>("hour"));
        departmentfacility.setCellValueFactory(new PropertyValueFactory<Reservation, String>("departmentfacility"));
        price.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("price"));

        list = loadList();
        table.setItems(list);

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
