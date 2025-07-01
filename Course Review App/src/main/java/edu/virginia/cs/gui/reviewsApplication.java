package edu.virginia.cs.gui;

import edu.virginia.cs.reviews.DataLayer;
import edu.virginia.cs.reviews.Login;
import edu.virginia.cs.reviews.MainMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class reviewsApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            // connect to database
            DataLayer dl = new DataLayer();
            dl.connect();
//            dl.printData();
            Login.setDataLayer(dl);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("edu.virginia.cs.gui/log_in_screen.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Course Reviews");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch();
    }
}