package edu.virginia.cs.gui;

import edu.virginia.cs.reviews.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class createAccountController {
    @FXML
    public Label errorLabel;
    @FXML
    public Button backToLogin;
    @FXML
    public TextField password;
    @FXML
    public Button createAccount;
    @FXML
    public TextField username;
    @FXML
    public TextField confirmPassword;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    Login login = new Login();
    public void back(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("edu.virginia.cs.gui/log_in_screen.fxml")));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void createAccount(ActionEvent actionEvent) throws IOException {
        String user = username.getText();
        String pass = password.getText();
        String pass1 = confirmPassword.getText();
        if (user.equals("") || pass.equals("") || pass1.equals("")) {
            errorLabel.setText("Not all fields were filled out!");
            errorLabel.setVisible(true);
        } else {
            if (Objects.equals(pass, pass1)) {
                if (Login.existingUser(user)) {
                    errorLabel.setText("Sorry, this username has been taken!");
                    errorLabel.setVisible(true);
                } else {
                    errorLabel.setVisible(false);
                    Login.addStudent(user,pass);
                    Login.goToMainMenu(user,pass);
                    switchToMainMenuFromCreateAccount(actionEvent);
                }
            } else {
                errorLabel.setText("Passwords do not match!");
                errorLabel.setVisible(true);
            }
        }
    }
    public void switchToMainMenuFromCreateAccount(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("edu.virginia.cs.gui/main_menu.fxml")));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
}
