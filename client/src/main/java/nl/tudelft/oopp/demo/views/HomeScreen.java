package nl.tudelft.oopp.demo.views;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.MainApp;
import nl.tudelft.oopp.demo.communication.ServerCommunication;

public class HomeScreen extends Application {

    public static Stage stage;

    @FXML Button userTypeButton;
    @FXML Button studentButton;
    @FXML Button staffButton;
    @FXML Button bikesRented;
    @FXML Button viewOrderButton;

    @Override
    public void start(final Stage primaryStage) {
        stage = primaryStage;
        stage.getIcons().add(new Image("Images/TU-Delft-Logo.jpg"));
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/HomeScreen.fxml"));
            //ad.setText("Admin");
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
        primaryStage.setMinHeight(680);
        primaryStage.setMinWidth(730);
        primaryStage.setResizable(true);
        Scene scene = new Scene(root, 1336, 768);
        primaryStage.setScene(scene);
        return primaryStage;
    }

    /**
     * logs out the user and sends them to the login screen.
     */
    @FXML
    public void logoutAction() {
        MainApp.logout();
        LoginScreen login = new LoginScreen();
        login.start(stage);
    }

    /**
     * Initializes the RoomReservationStage.
     * @return The RoomReservation Stage.
     * @throws IllegalAccessException to be written!
     */
    @FXML
    public RoomReservationScreen roomReservationAction() throws IllegalAccessException {
        RoomReservationScreen roomReservationScreen = new RoomReservationScreen();
        roomReservationScreen.start(stage);
        return roomReservationScreen;
    }

    /**
     * Initializes the BikeRentScreen Stage.
     * @return the BikeRentScreen Stage.
     * @throws IllegalAccessException to be added.
     */
    @FXML
    public BikeRentScreen bikeRentAction() throws IllegalAccessException {
        BikeRentScreen bikeRentScreen = new BikeRentScreen();
        bikeRentScreen.start(stage);
        return bikeRentScreen;
    }

    /**
     * Initializes the FoodScreen Stage.
     * @return the FoodScreen Stage.
     * @throws IllegalAccessException to be added.
     */
    @FXML
    public FoodScreen foodAction() throws IllegalAccessException {
        FoodScreen foodScreen = new FoodScreen();
        foodScreen.start(stage);
        return foodScreen;
    }

    /**
     * Initializes the admin Stage.
     * @return the AdminPage Stage.
     * @throws IllegalAccessException to be added.
     */

    @FXML
    public AdminScreen adminAction() throws IllegalAccessException {
        AdminScreen adminScreen = new AdminScreen();
        adminScreen.start(stage);
        return adminScreen;
    }

    @FXML
    private Text userMessage;

    /**
     * Opens the student info screen.
     * @return the student info screen
     * @throws IllegalAccessException If the access is denied or something
     */
    @FXML
    public StudentScreen studentAction() throws IllegalAccessException {
        StudentScreen studentScreen = new StudentScreen();
        studentScreen.start(stage);
        return studentScreen;
    }

    /**
     * Opens the staff info screen.
     * @return the staff info screen
     * @throws IllegalAccessException If access is denied, or user is unauthenticated
     */
    @FXML
    public StaffScreen staffAction() throws IllegalAccessException {
        StaffScreen staffScreen = new StaffScreen();
        staffScreen.start(stage);
        return staffScreen;
    }

    @FXML
    private OrderScreen viewOrders() throws IllegalAccessException {
        Stage orders = new Stage();
        OrderScreen orderScreen = new OrderScreen();
        orderScreen.start(orders);
        stage.close();
        return orderScreen;
    }


    @FXML
    private void initialize() {
        userMessage.setText("Hello " + MainApp.getUserName() + ", please select an action.");

        HttpResponse<String> response = ServerCommunication.getUtility(
                "users/test/" + MainApp.getUserName());
        if (response == null) {
            userTypeButton.setText("Cursed user");
            return;
        }

        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
        List<String> userInfo = new Gson().fromJson(response.body(), listType);

        String userType = userInfo.get(0);
        Boolean bike = (userInfo.get(1).equals("true"));

        if (userType.equals("Student")) {
            studentButton.setVisible(true);
        } else if (userType.equals("Staff")) {
            staffButton.setVisible(true);
        } else {
            userTypeButton.setVisible(true);
            userTypeButton.setText((userType));
        }

        if (bike) {
            bikesRented.setText("You have a bike rented");
            bikesRented.setStyle("-fx-background-color: #ffcccc");
        } else {
            bikesRented.setText("You can rent a bike");
            bikesRented.setStyle("-fx-background-color: #ccffcc");
        }

    }

    @FXML
    private void getReservations() {
        CurrentReservationsScreen.getCurrentReservations();
    }



    /**
     * Attempts to logout the user and send the user back
     * to the login screen if they become unauthenticated.
     */
    public static void unauthenticatedAction(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error message");
        alert.setHeaderText("You are unauthenticated, you will now be logged out");
        alert.showAndWait();

        MainApp.logout();
        LoginScreen login = new LoginScreen();
        login.start(stage);
    }
}
