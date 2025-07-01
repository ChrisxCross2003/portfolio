package edu.virginia.cs.gui;

import edu.virginia.cs.reviews.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class loginController {
    @FXML
    public TextField usernameField;
    @FXML
    public Button loginButton;
    @FXML
    public TextField passwordField;
    @FXML
    public Button createAccountButton;
    @FXML
    public Label loginErrorLabel;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    Login login = new Login();
    public void logIn(javafx.event.ActionEvent actionEvent) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (Objects.equals(username, "") || Objects.equals(password, "")) {
            loginErrorLabel.setText("Not all fields were filled out!");
            loginErrorLabel.setVisible(true);
        } else {
            boolean isValid = login.checkReturningUser(username, password);
            if (isValid) {
                loginErrorLabel.setVisible(false);
                Login.goToMainMenu(username, password);
                switchToMainMenuFromLogin(actionEvent);
            } else {
                loginErrorLabel.setVisible(true);
            }
        }
    }
    public void switchToMainMenuFromLogin(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("edu.virginia.cs.gui/main_menu.fxml")));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCreateAccountFromLogin(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("edu.virginia.cs.gui/createAccount.fxml")));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Create New Account");
        stage.setScene(scene);
        stage.show();
    }
}