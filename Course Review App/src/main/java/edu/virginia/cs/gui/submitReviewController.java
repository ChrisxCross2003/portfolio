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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class submitReviewController implements Initializable {
    public Button submitReviewButton;
    public Button submitReviewButton1;
    @FXML
    public ChoiceBox<Integer> ratingBox;
    public TextField messageField;
    public TextField courseField;
    public Label submitErrorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // This is needed to initialize the dropdown menu.
        ratingBox.getItems().addAll(1,2,3,4,5);

    }
    public void switchToMainMenuFromSubmitReview(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("edu.virginia.cs.gui/main_menu.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void submitReview(ActionEvent actionEvent) throws IOException {
        String message = messageField.getText();
        String course = courseField.getText();
        Integer rating = ratingBox.getValue();
        // display error if either text field is empty
        if (message.equals("") || course.equals("")) {
            submitErrorLabel.setText("Error! Course or Message field is empty!");
            submitErrorLabel.setVisible(true);
        } else {
            // display error if course isn't valid
            if (!MainMenu.checkCourse(course)) {
                submitErrorLabel.setText("Error! Course not valid!");
                submitErrorLabel.setVisible(true);
            } else {
                // Check if the course is already in the database
                if (!MainMenu.checkIfCourseInDatabase(course)) {
                    // If the course isn't in the database yet, then add the course
                    MainMenu.addCourse(course);
                    // this code will update the main menu to show the user the course has been added.
                    mainMenuController.addedCourse = course + " has been added to Course Database";
                    // To avoid making the user submit a review twice,
                    // if a course is added it will add the review they wrote at the same time as well.
                    MainMenu.addReview(course, message, MainMenu.getStudent(), rating);
                    switchToMainMenuFromSubmitReview(actionEvent);
                } else {
                    // check if the user already reviewed a course.
                    if (MainMenu.alreadyReviewed(course, MainMenu.getStudent())) {
                        submitErrorLabel.setText("Error! You have already reviewed this course!");
                        submitErrorLabel.setVisible(true);
                    } else {
                        MainMenu.addReview(course, message, MainMenu.getStudent(), rating);
                        mainMenuController.addedCourse = course + " has been added to Review Database";
                        switchToMainMenuFromSubmitReview(actionEvent);
                    }
                }
//                System.out.println("Review submitted! should be going back to menu now...");
            }
        }
    }
}