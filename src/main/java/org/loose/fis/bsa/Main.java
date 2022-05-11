package org.loose.fis.bsa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.bsa.services.FileSystemService;
import org.loose.fis.bsa.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage primarystage) throws IOException {
        UserService.initDatabase();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primarystage.setScene(new Scene(root, 600,400));
        primarystage.setTitle("Beauty Salon App");
        primarystage.show();
    }


    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
