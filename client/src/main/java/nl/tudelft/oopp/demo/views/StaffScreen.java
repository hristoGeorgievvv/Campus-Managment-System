package nl.tudelft.oopp.demo.views;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jfxtras.scene.control.agenda.Agenda;
import nl.tudelft.oopp.demo.MainApp;
import nl.tudelft.oopp.demo.communication.ServerCommunication;



public class StaffScreen extends Application implements Initializable {
    public static Stage stage;

    @FXML
    Text userName;

    @FXML Text emailText;

    @FXML Text idText;

    @FXML Text userType;

    @FXML
    ImageView backButton;

    @FXML ImageView logo;

    @FXML Agenda currentReservations;

    @Override
    public void start(final Stage primaryStage) throws IllegalAccessException {
        stage = primaryStage;

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/StaffScreen.fxml"));

            setRootProperties(primaryStage, root);

        } catch (IOException e) {
            e.printStackTrace();
        }


        stage.show();
    }

    @FXML
    private void getReservations() {
        CurrentReservationsScreen.getCurrentReservations();
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
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return primaryStage;
    }

    @FXML
    private void backAction() {
        Stage homeStage = new Stage();
        HomeScreen home = new HomeScreen();
        home.start(homeStage);
        homeStage.show();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        currentReservations.setAllowDragging(false);
        currentReservations.setAllowResize(false);
        CurrentReservationsScreen.getCurrentReservations(currentReservations);

        HttpResponse<String> response = ServerCommunication.getUtility(
                "users/details/" + MainApp.getUserName());

        if (response == null) {
            return;
        }

        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
        List<String> details = new Gson().fromJson(response.body(), listType);

        userName.setText(details.get(0));
        emailText.setText(details.get(1));
        idText.setText(details.get(2));
        userType.setText(details.get(3));

    }
}
