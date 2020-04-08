package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.controllers.RegisterController;

public class RegisterScreen extends Application implements Initializable {

    public static Stage stage;

    @FXML public TextField userText1;

    @FXML public TextField emailText;

    @FXML public PasswordField passwordText1;

    @FXML public PasswordField passwordText2;

    @FXML public Button registerButton;

    @FXML public Label pwrdMistake;


    /**
     * Starts the stage.
     * @param primaryStage the stage
     */
    public void start(final Stage primaryStage) {
        stage = primaryStage;
        stage.getIcons().add(new Image("Images/TU-Delft-Logo.jpg"));
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/RegisterScreen.fxml"));
            setRootProperties(primaryStage, root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.show();
    }

    /**
     * Creates the scene using the style document and sets it on the primaryStage.
     * @param primaryStage the primaryStage of the start Method
     * @param root the Parent
     * @return returns the Stage with the built Scene
     */
    public static Stage setRootProperties(Stage primaryStage, Parent root) {
        Scene scene = new Scene(root, 1336,768);
        primaryStage.setMinHeight(680);
        primaryStage.setMinWidth(730);
        scene.getStylesheets().addAll("/css/view.css");
        primaryStage.setScene(scene);
        return primaryStage;
    }

    @FXML
    private void backAction() {
        LoginScreen login = new LoginScreen();
        login.start(stage);
    }

    /*We cant shorten this method because its all unique and required
    checks for our registration system*/
    @FXML
    private void addUserAction() {
        String user = userText1.getText();
        String email = emailText.getText();
        String password1 = passwordText1.getText();
        String password2 = passwordText2.getText();
        String type = "Student";

        String userType = email.toLowerCase();

        if (userType.contains("teacher") || userType.contains("professor")
                || userType.contains("staff")) {
            type = "Staff";
        }

        if (user.isBlank() || email.isBlank() || password1.isBlank()) {
            pwrdMistake.setText("You have not filled in all text boxes");
            return;
        }

        if (user.length() <= 2) {
            pwrdMistake.setText("Minimum name length is 3");
            return;
        }

        if (user.contains(":")) {
            pwrdMistake.setText("Usernames should only have 'normal' text");
            return;
        }

        if (RegisterController.exists(user)) {
            pwrdMistake.setText("This user already exists");
        } else {
            if (password1.equals(password2)) {
                RegisterController.addUser(user, password1, email, type, false);
                backAction();
            } else {
                pwrdMistake.setText("The passwords do not match");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
