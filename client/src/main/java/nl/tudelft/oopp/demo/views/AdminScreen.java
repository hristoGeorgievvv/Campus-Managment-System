package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.MainApp;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.controllers.AdminScreenController;
import nl.tudelft.oopp.demo.controllers.BuildingController;
import nl.tudelft.oopp.demo.controllers.LoginController;
import nl.tudelft.oopp.demo.models.Building;
import nl.tudelft.oopp.demo.models.User;

public class AdminScreen extends Application implements Initializable {

    private static Stage stage;

    @FXML
    private ComboBox<String>  deleteBuildings;

    @FXML
    private TextField nameNew;
    @FXML
    private TextField departmentNew;
    @FXML
    private TextField locationNew;
    @FXML
    private TextField openingNew;
    @FXML
    private TextField bikesNew;

    @FXML
    private TextField departmentUpdate;
    @FXML
    private TextField  locationUpdate;
    @FXML
    private TextField openingUpdate;
    @FXML
    private TextField bikesUpdate;

    @FXML
    private ComboBox<String> nameUpdate;

    @FXML
    private ComboBox<String> userUpdate;

    @FXML
    private ComboBox<String> updateType;

    @Override
    public void start(final Stage primaryStage) {
        stage = primaryStage;
        stage.getIcons().add(new Image("Images/TU-Delft-Logo.jpg"));
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/AdminScreen.fxml"));
            setRootProperties(primaryStage, root);
            primaryStage.show();
        } catch (IOException e) {
            HomeScreen.unauthenticatedAction(this.stage);
            e.printStackTrace();
        }
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
        stage.setResizable(false);
        Scene scene = new Scene(root, 1336,768);
        primaryStage.setScene(scene);
        return primaryStage;
    }

    /**
     * When this is called, the screen moves to the previous page.
     * @param event An input mouse event
     */
    public void backAction(MouseEvent event) {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.start(stage);
    }

    /**
     * Adds a new building with the specifications that are present.
     * @param actionEvent An event
     */
    public void newBuilding(ActionEvent actionEvent) {
        boolean arefilledBoxes = areBoxesFilled();

        if (arefilledBoxes == false) {
            return;
        }
        String name = nameNew.getText();

        String location = locationNew.getText();
        String openingHours = openingNew.getText();
        String department = departmentNew.getText();
        int bikes = 0;
        try {
            bikes = Integer.parseInt(bikesNew.getText());
        } catch (Exception ex) {
            return;
        }
        Building building = new Building(openingHours,name,department,location,bikes);
        BuildingController.createBuilding(building);
        updateInformation();

    }

    /**
     * boolean check for if all the boxes are filled out.
     * @return true if they are all filled, false otherwise
     */
    private boolean areBoxesFilled() {
        boolean checked = isFilled(true, nameNew);
        checked = isFilled(checked, locationNew);
        checked = isFilled(checked, departmentNew);
        checked = isFilled(checked, openingNew);
        checked = isFilled(checked, bikesNew);

        return checked;
    }

    /**
     * Checks if the textField is filled out.
     * @param checked the previous boolean
     * @param toCheck the textField that is being checked
     * @return true if the text box is filled out
     */
    private boolean isFilled(boolean checked, TextField toCheck) {
        if (toCheck.getText() == null || toCheck.getText().isEmpty()) {
            toCheck.setStyle("-fx-border-color: red; -fx-border-radius: 3px;");
            checked = false;
        } else {
            toCheck.setStyle("-fx-border-color: null;");
        }
        return checked;
    }

    /**
     * Updates the details of an already existing building.
     * @param actionEvent An event
     */
    public void updateBuilding(ActionEvent actionEvent) {

        String name = nameUpdate.getValue();

        String location = locationUpdate.getText();
        String department = departmentUpdate.getText();
        String openingHours = openingUpdate.getText();

        int bikes = 0;
        try {
            bikes = Integer.parseInt(bikesUpdate.getText());
        } catch (Exception ex) {
            return;
        }

        Building building = new Building(openingHours,name,department,location,bikes);

        BuildingController.updateBuilding(building);

    }

    /**
     * Updates the details of an already existing building.
     * @param actionEvent An event
     */
    public void updateUser(ActionEvent actionEvent) {

        String name = userUpdate.getValue();

        String type = updateType.getValue();

        User user = new User(type, "null", "null", name, false);
        System.out.println(user.getType());

        AdminScreenController.updateType(user);

    }

    /**
     * Deletes a building based on its name.
     * @param actionEvent an event
     */
    public void deleteBuilding(ActionEvent actionEvent) {
        String buildingName = deleteBuildings.getValue();

        BuildingController.deleteBuilding(buildingName);
        updateInformation();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateInformation();
    }

    /**
     * Updates user Type information.
     */
    public void updateInformation() {
        List<String> buildingNames = BuildingController.getBuildingNames();
        deleteBuildings.getItems().addAll(buildingNames);
        nameUpdate.getItems().addAll(buildingNames);
        List<String> users = LoginController.getAllUsers();
        userUpdate.getItems().addAll(users);
        updateType.getItems().add("Student");
        updateType.getItems().add("Admin");
        updateType.getItems().add("Staff");
    }

    /**
     * Displays the current type of the user.
     */
    public void updateUserTextField() {
        String name = userUpdate.getValue();
        String res = AdminScreenController.getUserType(name);
        //userType.setText(res);
        updateType.setPromptText(res);
    }

    /**
     * Displays the current values of the building.
     */
    public void updateTextFields() {
        String name = nameUpdate.getValue();
        List<Building> buildings = BuildingController.getBuildings();
        Building building = null;
        for (Building b : buildings) {
            if (b.getBuildingName().equals(name)) {
                building = b;
            }
        }
        locationUpdate.setText(building.getLocation()); //address
        departmentUpdate.setText(building.getDepartment());
        openingUpdate.setText(building.getOpeningHours());
        int bikes = building.getNumberOfAvailablebikes();
        String bikeText = "" + bikes;
        bikesUpdate.setText(bikeText);
    }
}
