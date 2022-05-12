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
import org.loose.fis.bsa.exceptions.AllFieldsAreMandatory;
import org.loose.fis.bsa.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.bsa.services.UserService;
import javafx.scene.control.Label;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private Label registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private Button registerButton;
    @FXML
    private ChoiceBox role;
    @FXML
    private Button backField;

    @FXML
    public void initialize() {
        role.getItems().addAll("Customer", "Employee");
        //role.setValue("");
    }
    public void handleInapoiAction() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) (backField.getScene().getWindow());
        stage.setScene(new Scene(root,600,400));
    }

    @FXML
    public void handleRegisterAction() throws IOException, UsernameAlreadyExistsException {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            UserService.checkIfAllFieldsAreCompleted(passwordField.getText(), usernameField.getText(), firstNameField.getText(), lastNameField.getText(), phoneField.getText(), emailField.getText());
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        } catch (AllFieldsAreMandatory e) {
            registrationMessage.setText(e.getMessage());
        }
    }


    /*@FXML
    public void Backaction() throws Exception{
        try{
            Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Stage stage = (Stage) (registerButton.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();

        } catch(IOException e){
            registrationMessage.setText("eroare!");
        }
    }*/
}
