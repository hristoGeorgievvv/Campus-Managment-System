package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.controllers.LoginController;

public class LoginScreen extends Application {
    private static Stage stage;
    private int feedbackNum = 0;

    @FXML
    public TextField userText;

    @FXML
    public PasswordField passwordText;

    @FXML
    public Button registerButton;

    @FXML
    public Label noUser;

    @Override
    public void start(final Stage primaryStage) {
        stage = primaryStage;
        stage.getIcons().add(new Image("Images/TU-Delft-Logo.jpg"));
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/LoginScreen.fxml"));
            setRootProperties(primaryStage, root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.show();
    }

    @FXML
    private RegisterScreen registerAction() {
        RegisterScreen regScreen = new RegisterScreen();
        regScreen.start(stage);
        //stage.close();
        return regScreen;
    }

    @FXML
    public void onEnter() {
        loginAction();
    }

    @FXML
    private Application loginAction() {
        //login here
        boolean login1 = LoginController.login(userText.getText(), passwordText.getText());
        if (login1) {
            HomeScreen homeScreen = new HomeScreen();
            homeScreen.start(stage);
            return homeScreen;
        } else if (feedbackNum == 1) {
            feedbackNum = 0;
            noUser.setText("There is no user with those credentials");
        } else {
            feedbackNum = 1;
            noUser.setText("Those credentials do not match a user");
        }
        return new LoginScreen();
    }


    /**
     * Creates the scene using the style document and sets it on the primaryStage.
     * @param primaryStage the primaryStage of the start Method
     * @param root the Parent
     * @return returns the Stage with the built Scene
     */
    public static Stage setRootProperties(Stage primaryStage, Parent root) {
        primaryStage.setTitle("CampusMng");
        primaryStage.centerOnScreen();

        Scene scene = new Scene(root, 1336, 768);
        primaryStage.setMinHeight(680);
        primaryStage.setMinWidth(730);
        primaryStage.setScene(scene);
        return primaryStage;
    }

    public static void execute(String[]args) {
        launch(args);
    }


}

