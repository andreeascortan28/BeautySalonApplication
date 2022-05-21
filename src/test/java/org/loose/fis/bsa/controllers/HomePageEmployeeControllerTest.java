package org.loose.fis.bsa.controllers;
import static org.junit.jupiter.api.Assertions.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.bsa.services.FileSystemService;
import org.loose.fis.bsa.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;
@ExtendWith(ApplicationExtension.class)
public class HomePageEmployeeControllerTest {
    @AfterEach
    void tearDown() {
        UserService.getDatabase().close();
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.setApplicationFolder(".test-registration-example");
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationFolder().toFile());
        UserService.initDatabase();
    }

    @Start
    void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("employeePage.fxml"));
        primaryStage.setTitle("BEAUTY SALON");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    /*
    @Test
    void testPaginaAngajat(FxRobot robot) {
        robot.clickOn("#handleEditReservation");
        robot.clickOn("#backField");
        robot.clickOn("#handleChangePrices");
        robot.clickOn("#backField");
        robot.clickOn("#handleDeleteReservations");
        robot.clickOn("#backField");
        robot.clickOn("#handleViewSchedule");
        robot.clickOn("#backField");
        robot.clickOn("#signout");
    }
    */

    @Test
    void testHomePageEmployee(FxRobot robot) {
        robot.clickOn("#editReservationsButton");
        robot.clickOn("#back");

        //robot.clickOn("#changePricesButton");

        robot.clickOn("#deleteReservationsButton");
        robot.clickOn("#backButton");

        robot.clickOn("#viewScheduleButton");
        robot.clickOn("#backButton");

        robot.clickOn("#signOutButton");
    }
}
