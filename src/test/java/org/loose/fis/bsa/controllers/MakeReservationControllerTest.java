package org.loose.fis.bsa.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
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
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class MakeReservationControllerTest {

    public static final String USERNAME = "username";
    public static final String DEPARTMENTFACIlITY = "Hair salon - Styling";
    public static final String DATE = "02/04/2022";
    public static final String HOUR = "14:00";
    public static final int PRICE = 150;

    @AfterEach
    void tearDown() {
        UserService.getDatabase().close();
        System.out.println("After each");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-BeautySalonApplication";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationFolder().toFile());
        UserService.initDatabase();
    }

    @Start
    void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("makeReservation.fxml"));
        stage.setScene(new Scene(root, 600,400));
        stage.setTitle("Beauty Salon App");
        stage.show();
    }

    @Test
    @DisplayName("The reservation was made successfully")
    void testMakeReservation(FxRobot robot) throws EmptyDepartmentFieldException, EmptyHourFieldException, EmptyDateFieldException, MakingReservationException, NotFreeWindowException, WrongDateException {
        UserService.addReservation(USERNAME, DEPARTMENTFACIlITY, DATE, HOUR, PRICE);
        robot.clickOn("#departmentfacility");
        robot.clickOn(DEPARTMENTFACIlITY);
        robot.clickOn("#date");
        robot.write(DATE);
        robot.clickOn("#hour");
        robot.clickOn(HOUR);
        robot.clickOn("#makeReservationButton");

    }


}