package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.controllers.BikeReservationController;
import nl.tudelft.oopp.demo.controllers.BuildingController;
import nl.tudelft.oopp.demo.models.Building;


public class BikeRentScreen extends Application implements Initializable {
    public Scene scene;
    public static Stage stage;
    private boolean userHasBike;


    @FXML
    private Pane mainPane;

    @FXML
    private ScrollPane buildings;

    @FXML
    private StackPane stack;

    List<Building> buildingsList;

    @FXML
    private Text stationTitle;

    @FXML
    private Text address;

    @FXML ImageView imgView;

    @FXML
    private ImageView rentButton;

    @FXML
    private Pane showMapButton;

    @FXML
    private ImageView returnButton;

    @FXML
    private ImageView noBikesButton;

    @FXML ImageView tuLogo;

    @FXML
    private Text reserveTitle;

    @FXML
    private VBox vboxNode;

    private Node[] stationGui;

    /**
     * Creates the scene using the style document and sets it on the primaryStage.
     *
     * @param primaryStage the primaryStage of the start Method
     * @param root         the Parent
     * @return returns the Stage with the built Scene
     */
    public Stage setRootProperties(Stage primaryStage, Parent root) {
        primaryStage.setTitle("CampusMng");
        primaryStage.centerOnScreen();
        stage.setMinWidth(1320);
        stage.setMinHeight(710);
        scene = new Scene(root);
        scene.getStylesheets().addAll("/css/RoomReservationScreen.css");
        primaryStage.setScene(scene);
        return primaryStage;
    }

    @Override
    public void start(final Stage primaryStage) throws IllegalAccessException {

        stage = primaryStage;
        stage.getIcons().add(new Image("Images/TU-Delft-Logo.jpg"));

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/BikeRentScreen.fxml"));

            setRootProperties(primaryStage, root);
        } catch (IOException e) {
            HomeScreen.unauthenticatedAction(stage);
            e.printStackTrace();
        }
    }

    @FXML
    private void backAction() {
        HomeScreen home = new HomeScreen();
        home.start(stage);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stationGui = new Node[]{stationTitle, address, imgView, rentButton, returnButton,
                                noBikesButton, showMapButton};
        for (Node n: stationGui) {
            n.setVisible(false);
        }

        userHasBike = BikeReservationController.userHasBike();

        if (userHasBike) {
            reserveTitle.setText("Return a bike");
        }

        buildingsList = BuildingController.getBuildings();
        List<Pane> buildingElements = new ArrayList<>();

        for (Building building : buildingsList) {
            buildingElements.add(generateBuildingElement(building));
        }

        VBox veBox = new VBox();
        veBox.setPadding(new Insets(10));
        veBox.setSpacing(8);
        veBox.getChildren().addAll(buildingElements);


        stack = new StackPane(veBox);
        buildings.setContent(stack);

        mainPane.heightProperty().addListener((observableValue, number, t1) -> {
            vboxNode.setMinHeight((double)t1 - 70);
        });

        mainPane.widthProperty().addListener((observableValue, number, t1) ->
                tuLogo.setLayoutX((double)t1 - 138));
    }

    /**
     * generates a pane with the specifications of a specific building.
     * @param building the building the pane will be modelled for
     * @return a Pane with the specifications desired from the input building
     */
    public Pane generateBuildingElement(Building building) {
        Pane main = RoomReservationScreen.generateDefaultBuildingPane();

        setShadow(main);
        setImage(building, main);
        setPaneText(building, main);
        Text address = new Text("Number of bikes: " + building.getNumberOfAvailablebikes());

        setTextStyle(main, address);
        setSelectButton(building, main);

        return main;
    }

    /**
     * Sets a shadow behind a building pane.
     * @param main the pane which we add a shadow behind
     */
    static void setShadow(Pane main) {
        // Shadow behind the building Pane
        DropShadow shadow = new DropShadow();
        shadow.setRadius(6.3);
        shadow.setSpread(0.1);
        shadow.setHeight(13.58);
        shadow.setWidth(12.2);
        shadow.setColor(Color.color(0, 0, 0));
        main.setEffect(shadow);
    }

    /**
     * Sets the image of the desired building to the pane.
     * @param building building you want the image of
     * @param main the pane you are editing
     */
    static void setImage(Building building, Pane main) {
        // Picture of the faculty
        Image img;
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

        picture.setFitHeight(57.0);
        picture.setFitWidth(88.0);
        main.getChildren().add(picture);
    }

    /**
     * Sets text in the pane to be the name of the desired building.
     * @param building building the pane will have its text set from
     * @param main the pane that is being edited
     */
    static void setPaneText(Building building, Pane main) {
        Text title = new Text(building.getBuildingName());
        title.setFont(new Font("AvenirNextLTPro-Bold", 24.0));
        title.setLayoutX(136.0);
        title.setLayoutY(36.0);
        title.setWrappingWidth(315);
        title.setTextAlignment(TextAlignment.CENTER);
        main.getChildren().add(title);
    }

    /**
     * Sets the style of the text.
     * @param main the pane that is being edited
     * @param text the text which is having its style changed
     */
    static void setTextStyle(Pane main, Text text) {
        text.setFont(new Font("AvenirNextLTPro-Bold", 16.0));
        text.setStyle("-fx-text-fill: #959595;");
        text.setLayoutX(147);
        text.setLayoutY(58);
        text.setWrappingWidth(292);
        text.setTextAlignment(TextAlignment.CENTER);
        main.getChildren().add(text);
    }

    /**
     * Setting a select button to a pane that when clicked sets
     * the right side of the screen to the number of bicycles available for rent.
     * @param building building that the button opens information for
     * @param main the pane that is being edited
     */
    public void setSelectButton(Building building, Pane main) {
        //Select button
        Image btnPic = new Image("./Images/select-button-big.png");
        ImageView btn = new ImageView(btnPic);
        btn.setLayoutX(462.0);
        btn.setLayoutY(17.0);
        btn.setFitWidth(110);
        btn.setFitHeight(36);
        btn.setCursor(Cursor.HAND);
        btn.setOnMouseClicked(e -> {
            renderBuilding(building);
        });
        main.getChildren().add(btn);
    }

    /**
     * Generates the number of bicycles available within the building and
     * information of the building.
     * @param building building that we will get information from
     */
    public void renderBuilding(Building building) {
        stationTitle.setText(building.getBuildingName() + " ("
                + building.getNumberOfAvailablebikes() + " bikes)");
        address.setText(building.getLocation());
        Image img;
        try {
            img = new Image(String.format("./Images/buildingimgs/%s.png",
                    building.getBuildingName()));
        } catch (Exception ignored) {
            img = new Image("Images/buildingimgs/EEMCS.png");
        }
        imgView.setImage(img);

        for (Node n: stationGui) {
            n.setVisible(true);
        }

        setBikeButtonVisibility(building);

        setBikeButtonFunctionality(building);
    }

    /**
     * Sets the visibility of the bike rent button. If the number of available
     * bikes in the room is 0, they are invisible, and they are visible otherwise.
     * @param building building the bikes are in
     */
    public void setBikeButtonVisibility(Building building) {
        if (building.getNumberOfAvailablebikes() != 0) {
            noBikesButton.setVisible(false);
            if (userHasBike) {
                returnButton.setVisible(true);
                rentButton.setVisible(false);
            } else {
                returnButton.setVisible(false);
                rentButton.setVisible(true);
            }
        } else {
            rentButton.setVisible(false);
            if (userHasBike) {
                returnButton.setVisible(true);
                noBikesButton.setVisible(false);
            } else {
                returnButton.setVisible(false);
                noBikesButton.setVisible(true);
            }
        }
    }

    /**
     * sets functionality to the renting, map and return buttons to the appropriate building.
     * @param building the building we set the buttons to get data from
     */
    public void setBikeButtonFunctionality(Building building) {
        rentButton.setOnMouseClicked(e -> {
            BikeReservationController.makeOrDeleteReservation(building.getBuildingName());
            backAction();
        });

        returnButton.setOnMouseClicked(e -> {
            BikeReservationController.makeOrDeleteReservation(building.getBuildingName());
            backAction();
        });

        showMapButton.setOnMouseClicked(event -> {
            showMap(building.getBuildingName());
        });
    }

    /**
     * Shows a map of the campus, centered around the desired building.
     * @param name name of the building
     */
    private void showMap(String name) {
        WebView wv = new WebView();
        String mapp = "<div class=\"earth3dmap-com\"><iframe id=\"iframemap\" src=\"https://maps.google.com/maps?q=TU+Delft+"
                + name
                + "&amp;ie=UTF8&amp;iwloc=&amp;output=embed\" width=\"100%\" "
                + "height=\"500\" frameborder=\"0\" scrolling=\"yes\"></iframe>"
                + "<div style=\"color: #333;"
                + " font-size: 14px; font-family: Arial, Helvetica, sans-serif; "
                + "text-align: right; padding: 10px;\">"
                + "Map by <a style=\"color: #333; text-decoration: underline; font-size: 14px;"
                + " font-family: Arial, Helvetica, sans-serif; text-align: right;\" href=\"http://earth3dmap.com/?from=embed\""
                + " target=\"_blank\" >Earth3DMap.com</a></div>";
        wv.getEngine().loadContent(mapp);

        Stage stage = new Stage();
        Scene scene = new Scene(wv);
        stage.setScene(scene);
        stage.show();
    }


}

