package org.loose.fis.bsa.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.bsa.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.bsa.exceptions.UsernameDoesNotExistException;
import org.loose.fis.bsa.exceptions.WrongPasswordException;
import org.loose.fis.bsa.exceptions.WrongRoleException;
import org.loose.fis.bsa.services.UserService;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ChoiceBox role;

    private String selectedRole;

    @FXML
    private Button loginButton;

    @FXML
    private Text loginUserMessage;

    @FXML
    private Button registerButton;

    @FXML
    public void initialize() {
        role.getItems().addAll("Customer", "Employee");
    }



    @FXML
    public void handleLoginAction() throws Exception {
        try {
            UserService.login(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            selectedRole = (String) role.getValue();
            if (selectedRole.equals("Customer")) {

                Parent rootCustomer = FXMLLoader.load(getClass().getClassLoader().getResource("customerPage.fxml"));
                Stage stage = (Stage) (loginButton.getScene().getWindow());
                stage.setTitle("Customer Page");
                stage.setScene(new Scene(rootCustomer, 600, 400));
                stage.show();

            } else if (selectedRole.equals("Employee")) {

                Parent rootEmployee = FXMLLoader.load(getClass().getClassLoader().getResource("employeePage.fxml"));
                Stage stage = (Stage) (loginButton.getScene().getWindow());
                stage.setTitle("Employee Page");
                stage.setScene(new Scene(rootEmployee, 600, 400));
                stage.show();

            }
        } catch (UsernameDoesNotExistException e) {
            loginUserMessage.setText(e.getMessage());
        } catch (WrongPasswordException e) {
            loginUserMessage.setText(e.getMessage());
        } catch (WrongRoleException e) {
            loginUserMessage.setText(e.getMessage());
        }

    }

    @FXML
    public void handleRegisterAction() throws Exception {
        Parent rootRegister = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        Stage stage = (Stage) (registerButton.getScene().getWindow());
        stage.setScene(new Scene(rootRegister, 600, 400));
        stage.show();
    }
}
