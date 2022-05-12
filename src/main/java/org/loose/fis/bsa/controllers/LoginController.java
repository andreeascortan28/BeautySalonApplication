package org.loose.fis.bsa.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.LightBase;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.bsa.exceptions.UsernameDoesNotExistException;
import org.loose.fis.bsa.exceptions.WrongPasswordException;
import org.loose.fis.bsa.exceptions.WrongRoleException;
import org.loose.fis.bsa.model.LoggedUser;
import org.loose.fis.bsa.services.UserService;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ChoiceBox<String> role;

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
    public void handleLoginAction() throws UsernameDoesNotExistException, WrongPasswordException, WrongRoleException, IOException {
        try {
            //System.out.println("handle login action");
            UserService.checkCredentials(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            loginUserMessage.setText("Login successfully!");

            selectedRole = (String) role.getValue();
            //System.out.println("selected role" + selectedRole);

            if (selectedRole.equals("Customer")) {

                LoggedUser.setLoggedUser(usernameField.getText());
                LoggedUser.setLoggedPassw(passwordField.getText());
                LoggedUser.setLoggedRole(selectedRole);
                Parent rootCustomer = FXMLLoader.load(getClass().getClassLoader().getResource("customerPage.fxml"));
                Stage stage = (Stage) (loginButton.getScene().getWindow());
                stage.setTitle("Customer Page");
                stage.setScene(new Scene(rootCustomer, 600, 400));
                stage.show();

            } else if (selectedRole.equals("Employee")) {

                LoggedUser.setLoggedUser(usernameField.getText());
                LoggedUser.setLoggedPassw(passwordField.getText());
                LoggedUser.setLoggedRole(selectedRole);
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
        } catch (IOException e) {
            throw new RuntimeException(e);
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
