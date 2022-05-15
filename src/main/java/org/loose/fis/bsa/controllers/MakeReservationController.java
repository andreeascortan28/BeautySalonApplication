package org.loose.fis.bsa.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.bsa.exceptions.*;
import org.loose.fis.bsa.model.DepartmentFacility;
import org.loose.fis.bsa.model.LoggedUser;
import org.loose.fis.bsa.model.User;
import org.loose.fis.bsa.services.UserService;
import org.dizitart.no2.objects.ObjectRepository;
import java.io.IOException;
import java.util.Objects;


public class MakeReservationController  {

    private static ObjectRepository<DepartmentFacility> departmentFacilityRepository = UserService.getDepartmentFacilityRepository();

    ObservableList list_departments = FXCollections.observableArrayList();

    ObservableList list_hours = FXCollections.observableArrayList();

    @FXML
    private Text makeReservationMessage;

    @FXML
    private Button backButton;

    @FXML
    private Button makeReservationButton;

    @FXML
    private ChoiceBox<String> departmentfacility;

    @FXML
    private ChoiceBox<String> hour;

    @FXML
    private TextField date;

    private int price;


    @FXML
    public void handleBack() throws IOException {
        Parent rootSignOut = FXMLLoader.load(getClass().getClassLoader().getResource("customerPage.fxml"));
        Stage stage = (Stage) (backButton.getScene().getWindow());
        stage.setTitle("Customer page");
        stage.setScene(new Scene(rootSignOut, 600, 400));
        stage.show();
    }

    private void loadDepartments() {
        //System.out.println("am ajuns in load dep");
        list_departments.remove(list_departments);
        list_departments.addAll("Hair salon - hair cutting", "Hair salon - Colouring", "Hair salon - Styling",
                "Make-up - Everyday make-up", "Make-up - Wedding make-up",
                "Hair removal - Face", "Hair removal - Legs", "Hair removal - Back and chest", "Hair removal - Underarms", "Hair removal - Arms",
                "Nails - Manicure", "Nails - Pedicure", "Nails - Mani-Pedi",
                "Facial treatments - Classic facial", "Facial treatments - Acne reduction facial", "Facial treatments - LED light therapy", "Facial treatments - Acupuncture facial",
                "Massage - Classic massage", "Massage - Deep tissue massage", "Massage - Hot stone massage", "Massage - Sports massage", "Massage - Thai massage");
        departmentfacility.getItems().addAll(list_departments);
    }



    /*
    private void loadFacilities(String selecDep) {

        System.out.println("am ajuns in load facilities");
        if(Objects.equals(selecDep, "Hair salon")) {
            System.out.println("am ajuns in hair salon");
            list_facilities.remove(list_facilities);
            list_facilities.addAll("Hair-cutting", "Colouring", "Styling");
            facility.getItems().addAll(list_facilities);
        }
        else if (Objects.equals(selecDep, "Make-up")) {
            System.out.println("am ajuns in makeup");
            list_facilities.remove(list_facilities);
            list_facilities.addAll("Everyday make-up", "Wedding make-up");
            facility.getItems().addAll(list_facilities);
        }
        else if (Objects.equals(selecDep, "Hair removal")) {
            System.out.println("am ajuns in hair removal");
            list_facilities.remove(list_facilities);
            list_facilities.addAll("Face", "Legs", "Back and chest", "Underarms", "Arms");
            facility.getItems().addAll(list_facilities);
        }
        else if(Objects.equals(selecDep, "Nails")) {
            System.out.println("am ajuns in nails");
            list_facilities.remove(list_facilities);
            list_facilities.addAll("Manicure", "Pedicure", "Mani-Pedi");
            facility.getItems().addAll(list_facilities);
        }
        else if(Objects.equals(selecDep, "Facial treatments")) {
            System.out.println("am ajuns in facial treatment");
            list_facilities.remove(list_facilities);
            list_facilities.addAll("Classic facial", "Acne reduction facial", "LED light therapy", "Acupuncture facial");
            facility.getItems().addAll(list_facilities);
        }
        else if(Objects.equals(selecDep, "Massage")) {
            System.out.println("am ajuns in hair salon");
            list_facilities.remove(list_facilities);
            list_facilities.addAll("Classic massage", "Deep tissue massage", "Hot stone massage", "Sports massage", "Thai massage");
            facility.getItems().addAll(list_facilities);
        }
    }

     */
    private void loadHour() {
        list_hours.removeAll(list_hours);
        list_hours.addAll("8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00");
        hour.getItems().addAll(list_hours);
    }


    @FXML
    public void initialize() {
        loadDepartments();
        //selectedDepartment = (String) department.getValue();
        //loadFacilities(selectedDepartment);
        //loadDepartmentFacility();
        loadHour();
    }


    public void handleMakeReservation() throws EmptyDepartmentFieldException, EmptyDateFieldException, EmptyHourFieldException, MakingReservationException, NotFreeWindowException, IOException {
        try {
            UserService.checkEmptyFieldForReservation(departmentfacility, date.getText(), hour);
            UserService.addReservation(LoggedUser.getLoggedUser(), (String) departmentfacility.getValue(), date.getText(), (String) hour.getValue(), setPrice(departmentfacility));
            makeReservationMessage.setText("Your reservation was successful!");

        } catch (EmptyDepartmentFieldException e) {
            makeReservationMessage.setText(e.getMessage());
        } catch (EmptyDateFieldException e) {
            makeReservationMessage.setText(e.getMessage());
        } catch (EmptyHourFieldException e) {
            makeReservationMessage.setText(e.getMessage());
        }  catch (MakingReservationException e) {
            makeReservationMessage.setText(e.getMessage());
        } catch (NotFreeWindowException e) {
            makeReservationMessage.setText(e.getMessage());
        }

    }


    public int setPrice(ChoiceBox departmentfacility) {

        System.out.println("am intrat in setprice");
        int price = 0;
        String depfac = (String) departmentfacility.getValue();
        for(DepartmentFacility departmentFacility : departmentFacilityRepository.find())
        {
            if(Objects.equals(departmentFacility.getDepartmentfacility(), depfac))
                price = departmentFacility.getPrice();
        }
        return price;


        /*if (departmentfacility.getSelectionModel().isEmpty())
            return 0;
        else
        {
            String[] parts = ((String) departmentfacility.getValue()).split(" - ");
            String department = parts[0];
            String facility = parts[1];

            if (department.equals("Hair Salon")) {
                if (facility.equals("Hair cutting"))
                    price = 80;
                else if (facility.equals("Colouring"))
                    price = 220;
                else if (facility.equals("Styling"))
                    price = 150;
            } else if (department.equals("Make-up")) {
                if (facility.equals("Everyday make-up"))
                    price = 50;
                else if (facility.equals("Wedding make-up"))
                    price = 100;

            } else if (department.equals("Hair removal")) {
                if (facility.equals("Face"))
                    price = 30;
                else if (facility.equals("Legs"))
                    price = 100;
                else if (facility.equals("Back and chest"))
                    price = 50;
                else if (facility.equals("Underarms"))
                    price = 20;
                else if (facility.equals("Arms"))
                    price = 50;
            } else if (department.equals("Nails")) {
                if (facility.equals("Manicure"))
                    price = 80;
                else if (facility.equals("Pedicure"))
                    price = 50;
                else if (facility.equals("Mani-Pedi"))
                    price = 100;
            } else if (department.equals("Facial treatments")) {
                if (facility.equals("Classic facial"))
                    price = 100;
                else if (facility.equals("Acne reduction facial"))
                    price = 150;
                else if (facility.equals("LED light therapy"))
                    price = 300;
                else if (facility.equals("Acupuncture facial"))
                    price = 250;
            } else if (department.equals("Massage")) {
                if (facility.equals("Classic massage"))
                    price = 100;
                else if (facility.equals("Deep tissue massage"))
                    price = 150;
                else if (facility.equals("Hot stone massage"))
                    price = 200;
                else if (facility.equals("Sports massage"))
                    price = 150;
                else if (facility.equals("Thai massage"))
                    price = 300;
            }
            return price;
        }

         */

    }



}
