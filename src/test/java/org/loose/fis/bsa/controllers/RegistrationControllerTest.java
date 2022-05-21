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
import java.nio.file.Path;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class RegistrationControllerTest {
    @AfterEach
    void tearDown() {

        UserService.getDatabase().close();
    }

   @BeforeEach
    void setUp() throws Exception {
        FileSystemService.setApplicationFolder(".test-Beauty-Salon-Application");
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationFolder().toFile());
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
    void testRegistration(FxRobot robot) {
        robot.clickOn("#firstNameField");
        robot.write("Rusznak");
        robot.clickOn("#lastNameField");
        robot.write("Erika");
        robot.clickOn("#usernameField");
        robot.write("Erika");
        robot.clickOn("#passwordField");
        robot.write("Ruszi");
        robot.clickOn("#phoneField");
        robot.write("0774521295");
        robot.clickOn("#emailField");
        robot.write("erikarusznak@yahoo.com");
        robot.clickOn("#role");
        robot.clickOn("Employee");

        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully");

        robot.clickOn("#backButton");
        robot.clickOn("#registrationButton");
        robot.clickOn("#firstNameField");
        robot.write("Erika");
        robot.clickOn("#lastNameField");
        robot.write("Rusznak");
        robot.clickOn("#usernameField");
        robot.write("Erika");
        robot.clickOn("#passwordField");
        robot.write("Ruszi");
        robot.clickOn("#phoneField");
        robot.write("0774521295");
        robot.clickOn("#emailField");
        robot.write("andreeascortan@yahoo.com");
        robot.clickOn("#role");
        robot.clickOn("Employee");
        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText(String.format("An account with this username already exists %s", "Erika"));

    }
}

