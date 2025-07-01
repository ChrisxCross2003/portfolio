package edu.virginia.cs.gui;

import edu.virginia.cs.reviews.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class mainMenuController implements Initializable {
    public static String addedCourse;
    @FXML
    public Button submitReviewButton;
    @FXML
    public Button logOutButton;
    @FXML
    public Button viewReviewButton;
    @FXML
    public Label helloText;
    public Text addedNotification;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    public void switchToSubmitReviewFromMainMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "submitReview.fxml","Submit a Review");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        helloText.setText("Hello, "+MainMenu.getStudent()[0]);
        if (addedCourse != null) {
            // if a course was added...
            addedNotification.setText(addedCourse);
            addedNotification.setVisible(true);
            addedCourse = null;
        }
    }

    public void switchToViewReviewsFromMainMenu(ActionEvent actionEvent) throws IOException {
        MainMenu.goToReviews();
        changeScene(actionEvent, "viewReviews.fxml","View Course Reviews");
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "log_in_screen.fxml","Login");
    }

    private void changeScene(ActionEvent actionEvent, String newSceneLocation, String title) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("edu.virginia.cs.gui/"+newSceneLocation)));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}


