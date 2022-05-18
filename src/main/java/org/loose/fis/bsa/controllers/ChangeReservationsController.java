package org.loose.fis.bsa.controllers;

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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.bsa.model.Edit;
import org.loose.fis.bsa.model.LoggedUser;
import org.loose.fis.bsa.model.Reservation;
import org.loose.fis.bsa.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ChangeReservationsController /* implements Initializable */{
    //private final String username = "Nails";
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
    private TableView<Reservation> table;

    @FXML
    private TableColumn<Reservation, String> user;
    ObjectRepository<Reservation> reservationRepository = UserService.getReservationRepository();


    @FXML
    public void initialize() {
        loadList();

        table.setEditable(true);
        price.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        date.setCellFactory(TextFieldTableCell.forTableColumn());
        hour.setCellFactory(TextFieldTableCell.forTableColumn());
        depfac.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    public void loadList() {
        user.setCellValueFactory(new PropertyValueFactory<Reservation, String>("username"));
        depfac.setCellValueFactory(new PropertyValueFactory<Reservation, String>("departmentfacility"));
        date.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));
        hour.setCellValueFactory(new PropertyValueFactory<Reservation, String>("hour"));
        price.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("price"));

        ObservableList<Reservation> reservationsList = FXCollections.observableArrayList();

        for(Reservation reservation : reservationRepository.find()) {
            reservationsList.add(reservation);
        }
        table.setItems(reservationsList);
    }


    public void onEditChange(TableColumn.CellEditEvent<Reservation, Integer> editStringCellEditEvent) {
        Reservation e1 = (Reservation) table.getSelectionModel().getSelectedItem();
        e1.setPrice(editStringCellEditEvent.getNewValue());
    }

    public void onEditChange2(TableColumn.CellEditEvent<Reservation, String> editStringCellEditEvent) {
        Reservation e2 = (Reservation) table.getSelectionModel().getSelectedItem();
        e2.setDate(editStringCellEditEvent.getNewValue());}

    public void onEditChange3(TableColumn.CellEditEvent<Reservation, String> editStringCellEditEvent){
        Reservation e3 = (Reservation) table.getSelectionModel().getSelectedItem();
        e3.setHour(editStringCellEditEvent.getNewValue());}

    public void onEditChange4(TableColumn.CellEditEvent<Reservation, String> editStringCellEditEvent){
        Reservation e4 = (Reservation) table.getSelectionModel().getSelectedItem();
        e4.setDepartmentfacility(editStringCellEditEvent.getNewValue());

    }

    public void saveChanges() {
        for(Reservation e : table.getItems()){
            UserService.updateReservations(e.getUsername(), e.getDepartmentfacility(),e.getDate(), e.getHour(),e.getPrice());
        }
    }

    @FXML
    void handleInapoiAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("employeePage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        stage.setScene(new Scene(root,600,400));
    }

}
