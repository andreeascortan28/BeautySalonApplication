package org.loose.fis.bsa.controllers;

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
import org.loose.fis.bsa.services.FileSystemService;
import org.loose.fis.bsa.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class ViewScheduleControllerTest {

    @AfterEach
    void tearDown() {
        UserService.getDatabase().close();
        System.out.println("After each");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.setApplicationFolder(".test-BeautySalonApplication");
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationFolder().toFile());
        UserService.initDatabase();
    }

    @Start
    void start(Stage primarystage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("viewSchedule.fxml"));
        primarystage.setScene(new Scene(root, 600,400));
        primarystage.setTitle("Beauty Salon App");
        primarystage.show();
    }

    @Test
    @DisplayName("The employee viewed the schedule")
    void testHomePageClient(FxRobot robot) {
        robot.clickOn("#backButton");
    }

}