package org.loose.fis.bsa;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.loose.fis.bsa.model.Edit;
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

    public void start2(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FacialTreatments.fxml"));
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
