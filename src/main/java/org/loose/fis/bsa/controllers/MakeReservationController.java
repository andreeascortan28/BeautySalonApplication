package org.loose.fis.bsa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class MakeReservationController implements Initializable {

    ObservableList list_departments = FXCollections.observableArrayList();

    ObservableList list_hours = FXCollections.observableArrayList();

    ObservableList list_facilities = FXCollections.observableArrayList();

    @FXML
    private Text makeReservationMessage;

    @FXML
    private Button backButton;

    @FXML
    private Button makeReservationButton;

    @FXML
    private ChoiceBox<String> department;

    private String selectedDepartment;

    @FXML
    private ChoiceBox<String> facility;

    @FXML
    private ChoiceBox<String> hour;

    @FXML
    private DatePicker date;
    private String selectedDate;

    @FXML
    public void handleBack() throws IOException {
        Parent rootSignOut = FXMLLoader.load(getClass().getClassLoader().getResource("customerPage.fxml"));
        Stage stage = (Stage) (backButton.getScene().getWindow());
        stage.setTitle("Sign in");
        stage.setScene(new Scene(rootSignOut, 600, 400));
        stage.show();
    }

    private void loadDepartments() {
        list_departments.remove(list_departments);
        list_departments.addAll("Hair salon", "Make-up", "Hair removal", "Nails", "Facial treatments", "Massage");
        department.getItems().addAll(list_departments);
    }

    private void loadFacilities() {
        selectedDepartment = department.getValue();
        if(selectedDepartment.equals("Hair salon")) {
            list_facilities.remove(list_facilities);
            list_facilities.addAll("Hair-cutting", "Colouring", "Styling");
            facility.getItems().addAll(list_facilities);
        }
        else if (selectedDepartment.equals("Make-up")) {
            list_facilities.remove(list_facilities);
            list_facilities.addAll("Everyday make-up", "Wedding make-up");
            facility.getItems().addAll(list_facilities);
        }
        else if (selectedDepartment.equals("Hair removal")) {
            list_facilities.remove(list_facilities);
            list_facilities.addAll("Face", "Legs", "Back and chest", "Underarms", "Arms");
            facility.getItems().addAll(list_facilities);
        }
        else if(selectedDepartment.equals("Nails")) {
            list_facilities.remove(list_facilities);
            list_facilities.addAll("Manicure", "Pedicure", "Mani-Pedi");
            facility.getItems().addAll(list_facilities);
        }
        else if(selectedDepartment.equals("Facial treatments")) {
            list_facilities.remove(list_facilities);
            list_facilities.addAll("Classic facial", "Acne reduction facial", "LED light therapy", "Acupuncture facial", "Laser resurfacing");
            facility.getItems().addAll(list_facilities);
        }
        else if(selectedDepartment.equals("Massage")) {
            list_facilities.remove(list_facilities);
            list_facilities.addAll("Classic massage", "Deep tissue massage", "Hot stone massage", "Sports massage", "Thai massage");
            facility.getItems().addAll(list_facilities);
        }
    }

    private void loadHour() {
        list_hours.removeAll(list_hours);
        list_hours.addAll("8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00");
        hour.getItems().addAll(list_hours);
    }

    private void loadDate() {
        LocalDate myDate = date.getValue();
        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("dd-mm-yyyy"));
        selectedDate = (String) myFormattedDate;
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDepartments();
        loadFacilities();
        loadDate();
        loadHour();
    }

}
