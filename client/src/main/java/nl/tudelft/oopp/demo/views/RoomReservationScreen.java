package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.controllers.ClassroomController;
import nl.tudelft.oopp.demo.models.Building;
import nl.tudelft.oopp.demo.models.ClassRoom;


public class RoomReservationScreen extends Application implements Initializable {

    public static Stage stage;

    HashMap<Building, List<ClassRoom>> data;
    List<Pane> classroomElements = new ArrayList<>();


    private Building buildingForRooms;
    public static ClassRoom selectedRoom;

    @FXML
    private Pane mainPane;
    @FXML
    private VBox classrooms;

    @FXML
    private VBox buildings;

    @FXML
    private Text rightTitle;

    //Filtering elements
    @FXML
    private Pane filteringMenu;

    @FXML
    private CheckBox projectorCheckbox;

    @FXML
    private CheckBox whiteboardCheckbox;

    @FXML
    private CheckBox blackboardCheckbox;

    @FXML
    private Slider capacitySlider;

    @FXML
    private RadioButton capacityHtLBox;

    @FXML
    private RadioButton capacityLtHBox;

    @FXML
    private ImageView logo;

    @FXML
    private VBox vboxNode;

    private boolean sortAscending = false;
    private boolean blackBoard = false;
    private boolean whiteBoard = false;
    private boolean projector = false;
    private int minimalCapacity = 0;

    @FXML
    ImageView backButton;

    @Override
    public void start(final Stage primaryStage) throws IllegalAccessException {

        stage = primaryStage;
        stage.getIcons().add(new Image("Images/TU-Delft-Logo.jpg"));

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/RoomReservationScreen.fxml"));
            setRootProperties(primaryStage, root);
            stage.show();
        } catch (IOException e) {
            HomeScreen.unauthenticatedAction(stage);
            e.printStackTrace();
        }
    }


    /**
     * Creates the scene using the style document and sets it on the primaryStage.
     *
     * @param primaryStage the primaryStage of the start Method
     * @param root         the Parent
     * @return returns the Stage with the built Scene
     */
    public static Stage setRootProperties(Stage primaryStage, Parent root) {
        primaryStage.setTitle("CampusMng");
        primaryStage.centerOnScreen();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll("/css/RoomReservationScreen.css");
        primaryStage.setScene(scene);
        stage.setMinWidth(1320);
        stage.setMinHeight(710);
        return primaryStage;


    }

    @FXML
    private void backAction() {
        HomeScreen home = new HomeScreen();
        home.start(stage);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        data = ClassroomController.generateData();
        if (data == null) {
            throw new NullPointerException();
        }

        Iterator it = data.keySet().iterator();

        List<Pane> buildingElements = new ArrayList<>();
        while (it.hasNext()) {
            Building building = (Building) it.next();
            Pane element = generateBuildingElement(building);
            buildings.getChildren().add(element);
            buildingElements.add(element);
        }

        //Filtering
        filteringMenu.setVisible(false);
        final ToggleGroup group = new ToggleGroup();
        capacityHtLBox.setToggleGroup(group);
        capacityLtHBox.setToggleGroup(group);
        capacityHtLBox.setSelected(true);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            sortAscending = newValue == capacityLtHBox;
            filterRooms();

        });
        addListeners();

        mainPane.heightProperty().addListener((observableValue, number, t1) ->
                vboxNode.setMinHeight((double)t1 - 70));

        mainPane.widthProperty().addListener((observableValue, number, t1) ->
                logo.setLayoutX((double)t1 - 138));
    }

    /**
     * Adds listeners to the checkboxes and capacitySlider.
     */
    public void addListeners() {
        projectorCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            projector = newValue;
            filterRooms();
        });

        whiteboardCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            whiteBoard = newValue;
            filterRooms();
        });

        blackboardCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            blackBoard = newValue;
            filterRooms();
        });

        capacitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            minimalCapacity = newValue.intValue();
            filterRooms();
        });
    }

    /**
     * Generates a pane with the information of a specific building.
     * @param building building that the pane will be modelled off of
     * @return a pane with the information of the input building
     */
    public Pane generateBuildingElement(Building building) {
        Pane main = generateDefaultBuildingPane();
        BikeRentScreen.setShadow(main);
        BikeRentScreen.setImage(building, main);
        BikeRentScreen.setPaneText(building, main);

        Text address;
        if (building.getLocation().indexOf("\n") == -1) {
            address = new Text(building.getLocation());
        } else {
            address = new Text(building.getLocation()
                    .substring(0, building.getLocation().indexOf("\n")));
        }

        BikeRentScreen.setTextStyle(main, address);

        //Select button
        Image btnPic = new Image("./Images/select-button-big.png");
        ImageView btn = new ImageView(btnPic);
        btn.setLayoutX(462.0);
        btn.setLayoutY(17.0);
        btn.setFitWidth(110);
        btn.setFitHeight(36);
        btn.setCursor(Cursor.HAND);
        btn.setOnMouseClicked(e -> {
            buildingForRooms = building;
            filteringMenu.setVisible(true);
            showRoomsFromBuilding(building);
        });
        main.getChildren().add(btn);

        return main;
    }

    /**
     * generates a pane with 'standard' size for our building instances.
     * @return a pane with a 'standard' size and style
     */
    static Pane generateDefaultBuildingPane() {
        Pane main = new Pane();
        main.setMinHeight(70);
        main.setMaxHeight(70);
        main.setMinWidth(580);
        main.setMaxWidth(580);
        main.getStyleClass().add("building");

        VBox.setMargin(main, new Insets(10, 0, 8, 5));
        return main;
    }

    /**
     * Shows all the rooms from the building that was selected on
     * the right side of the room reservation screen.
     * @param building the building that was selected by the button
     */
    private void showRoomsFromBuilding(Building building) {
        classrooms.getChildren().clear();
        classroomElements = new ArrayList<>();

        rightTitle.setText("Select room in " + building.getBuildingName());

        if (!data.containsKey(building)) {
            return;
        }

        List<ClassRoom> classRooms = data.get(building);

        if (sortAscending) {
            Collections.sort(classRooms, (o1, o2) -> o1.getNumberOfPeople()
                    - o2.getNumberOfPeople());
        } else {
            Collections.sort(classRooms, (o1, o2) -> o2.getNumberOfPeople()
                    - o1.getNumberOfPeople());
        }

        for (ClassRoom classRoom : classRooms) {

            if (blackBoard && !classRoom.isBlackBoard()) {
                continue;
            }
            if (projector && !classRoom.isProjectorBoard()) {
                continue;
            }
            if (whiteBoard && !classRoom.isWhiteBoard()) {
                continue;
            }
            if (classRoom.getNumberOfPeople() < minimalCapacity) {
                continue;
            }
            classrooms.getChildren().add(generateRoomElement(classRoom, building));
        }
    }


    public void filterRooms() {
        showRoomsFromBuilding(buildingForRooms);
    }

    /**
     * Generates a Pane with the information of a specific classroom.
     * @param classRoom the ClassRoom that the information will be modelled after
     * @param building  the Building that the classroom is in
     * @return a Pane with the information of the chosen classroom
     */
    public Pane generateRoomElement(ClassRoom classRoom, Building building) {
        Pane main = generateDefaultBuildingPane();

        // Shadow behind the building Pane
        BikeRentScreen.setShadow(main);

        ImageView picture = generatePicture(building);
        main.getChildren().add(picture);

        Text title = generateText(classRoom);
        main.getChildren().add(title);

        Text capacity = generateTextCapacity(classRoom);
        main.getChildren().add(capacity);

        //Select button
        Image btnPic = new Image("./Images/select-button-big.png");
        ImageView btn = generateImageView(btnPic, classRoom);

        main.getChildren().add(btn);

        return main;
    }

    /**
     * Generates styled text with the capacity of the room.
     * @param classRoom the classroom that the text will base its information off of
     * @return text stating the capacity of the classroom
     */
    private Text generateTextCapacity(ClassRoom classRoom) {
        Text capacity = new Text("Capacity: " + classRoom.getNumberOfPeople() + " people");
        capacity.setFont(new Font("AvenirNextLTPro-Bold", 16.0));
        capacity.setStyle("-fx-text-fill: #959595;");
        capacity.setLayoutX(147);
        capacity.setLayoutY(58);
        capacity.setWrappingWidth(292);
        capacity.setTextAlignment(TextAlignment.CENTER);
        return capacity;
    }

    /**
     * Generates an imageView with an image which opens the selected room which clicked.
     * @param btnPic the image of the classroom
     * @param classRoom the classroom which is opened when the image is clicked
     * @return an ImageView with an image of the classroom on it
     */
    private ImageView generateImageView(Image btnPic, ClassRoom classRoom) {
        ImageView btn = new ImageView(btnPic);
        btn.setLayoutX(462.0);
        btn.setLayoutY(17.0);
        btn.setFitWidth(110);
        btn.setFitHeight(36);
        btn.setCursor(Cursor.HAND);
        btn.setOnMouseClicked(e -> {
            try {
                userSelectedRoom(classRoom);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return btn;
    }

    /**
     * Generates a the room name for the classroom with the desired styling.
     * @param classRoom the classroom that the text is made for
     * @return a Text instance with specified styling and the name of the classroom
     */
    private Text generateText(ClassRoom classRoom) {
        Text title = new Text(classRoom.getRoomName());
        title.setFont(new Font("AvenirNextLTPro-Bold", 24.0));
        title.setLayoutX(136.0);
        title.setLayoutY(36.0);
        title.setWrappingWidth(315);
        title.setTextAlignment(TextAlignment.CENTER);
        return title;
    }

    /**
     * Generates an image of the desired building from resources.
     * @param building the building the image is of
     * @return an ImageView with specific size and with an image of the input building
     */
    private ImageView generatePicture(Building building) {
        Image img = null;
        try {
            img = new Image("./Images/buildingimgs/" + building.getBuildingName() + ".png");
        } catch (Exception ignored) {
            img = new Image("Images/buildingimgs/EEMCS.png");
        }
        ImageView picture = new ImageView(img);
        picture.setLayoutX(7.0);
        picture.setLayoutY(7.0);
        picture.setFitHeight(57);
        picture.setFitWidth(88);
        return picture;
    }

    /**
     * Opens a new TimeSlotSelectionScreen for the classroom the user has selected.
     * @param room the classroom that the TimeSlotSelectionScreen will be for
     * @throws Exception possible that it throws an exception if there is an error
     */
    private void userSelectedRoom(ClassRoom room) throws Exception {
        selectedRoom = room;
        TimeSlotSelectionScreen timeSlotSelectionScreen = new TimeSlotSelectionScreen();
        timeSlotSelectionScreen.setSelectedRoom(room);
        timeSlotSelectionScreen.start(stage);

    }


}
