package edu.virginia.cs.gui;

import edu.virginia.cs.reviews.AccessReviews;
import edu.virginia.cs.reviews.MainMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

public class reviewsController implements Initializable{
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public ComboBox<String> selectCourseBox;
    public ListView<String> reviewList;
    public Label courseAverage;
    public Button returnMainMenuButton;
    private AccessReviews accessReviews;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accessReviews = new AccessReviews();
        // This is needed to initialize the dropdown menu so that all courses are shown.
        try {
            List<String> courseList = MainMenu.getCourseList();
            selectCourseBox.getItems().addAll(courseList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void switchToMainMenuFromViewReviews(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("edu.virginia.cs.gui/main_menu.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void getReviews(ActionEvent actionEvent) throws SQLException {
        if (reviewList.getItems() != null) {
            reviewList.getItems().clear();
        }
        String course = selectCourseBox.getValue();
        List<String> reviews = AccessReviews.getReviews(course);
        reviewList.getItems().addAll(reviews);
        String averageStr = String.valueOf(df.format(accessReviews.getAverage()));
        if (averageStr.equals("NaN"))
        {
            courseAverage.setText("  N/A");
        }
        else
        {
            // added df format to truncate decimal value if it is a repeating decimal.
            courseAverage.setText("  " + averageStr);
        }
    }
}

