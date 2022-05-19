package org.loose.fis.bsa.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.bsa.exceptions.*;
import org.loose.fis.bsa.model.User;
import org.loose.fis.bsa.services.FileSystemService;
import org.loose.fis.bsa.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.apache.commons.io.FileUtils;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
class LoginControllerTest {


    @AfterEach
    void tearDown() {
        UserService.getDatabase().close();
        System.out.println("After each");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-BeautySalonApplication";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @Start
    void start(Stage primarystage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primarystage.setScene(new Scene(root, 600,400));
        primarystage.setTitle("Beauty Salon App");
        primarystage.show();
    }


    @Test
    @DisplayName("Customer logged in successfully")
    void testLoginCustomer(FxRobot robot) throws UsernameDoesNotExistException, WrongPasswordException, WrongRoleException, IOException, AllFieldsAreMandatory, UsernameAlreadyExistsException {
        UserService.addUser("USERNAME", "PASSWORD", "Customer");
        robot.clickOn("#usernameField");
        robot.write("USERNAME");
        robot.clickOn("#passwordField");
        robot.write("PASSWORD");
        robot.clickOn("#loginButton");
    }

    @Test
    @DisplayName("Customer logged in successfully")
    void testLoginEmployee(FxRobot robot) throws UsernameDoesNotExistException, WrongPasswordException, WrongRoleException, IOException, AllFieldsAreMandatory, UsernameAlreadyExistsException {
        UserService.addUser("USERNAME", "PASSWORD", "Employee");
        robot.clickOn("#usernameField");
        robot.write("USERNAME");
        robot.clickOn("#passwordField");
        robot.write("PASSWORD");
        robot.clickOn("#loginButton");
    }
}