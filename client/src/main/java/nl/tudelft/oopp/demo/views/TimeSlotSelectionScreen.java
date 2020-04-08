package nl.tudelft.oopp.demo.views;

import static nl.tudelft.oopp.demo.views.RoomReservationScreen.selectedRoom;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.MainApp;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.controllers.RoomReservationController;
import nl.tudelft.oopp.demo.controllers.TimeSlotsController;
import nl.tudelft.oopp.demo.models.ClassRoom;
import nl.tudelft.oopp.demo.models.ReservationRequestModel;
import nl.tudelft.oopp.demo.models.RoomReservation;
import nl.tudelft.oopp.demo.models.TimeSlots;


public class TimeSlotSelectionScreen extends Application implements Initializable {

    private ClassRoom room;

    public static Stage stage;

    @FXML
    private Text firstTitle;

    @FXML
    private Text secondTitle;

    @FXML
    private Pane timeslotsContainer;

    @FXML
    private Pane selectButton;

    @FXML
    private Pane calendarPane;

    private TimeSlots[] timeslots;

    private TimeSlots selectedTimeslot = null;
    private String selectedDate = null;

    //Predefined timeslots
    @FXML
    private Pane ts1011;
    @FXML
    private Pane ts1112;
    @FXML
    private Pane ts1213;
    @FXML
    private Pane ts1314;
    @FXML
    private Pane ts1415;
    @FXML
    private Pane ts1516;
    @FXML
    private Pane ts1617;
    @FXML
    private Pane ts1718;
    @FXML
    private Pane ts1819;
    @FXML
    private Pane ts1920;

    @FXML
    private ImageView imgView;

    @FXML
    private Text message;

    @FXML
    private Text address;

    @FXML Text overrideWarning;

    @FXML
    private Pane showMapButton;

    private Pane[] timeslotsGui;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.getIcons().add(new Image("Images/TU-Delft-Logo.jpg"));


        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/TimeSlotSelectionScreen.fxml"));

            stage = setRootProperties(primaryStage, root);

        } catch (IOException e) {
            e.printStackTrace();
        }


        stage.show();
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
        primaryStage.setScene(scene);
        return primaryStage;


    }

    /**
     * this method should have a proper javadoc.
     *
     * @param room room
     */
    public void setSelectedRoom(ClassRoom room) {
        this.room = room;
        System.out.println("User selected " + room.getRoomName()
                + " in " + room.getBuilding().getBuildingName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstTitle.setText(selectedRoom.getRoomName());
        secondTitle.setText(selectedRoom.getBuilding().getBuildingName());
        timeslotsGui = new Pane[]{ts1011, ts1112,
                                  ts1213, ts1314, ts1415, ts1516, ts1617, ts1718, ts1819, ts1920};

        timeslotsContainer.setVisible(false);

        Image img;
        try {
            img = new Image("./Images/buildingimgs/"
                    + selectedRoom.getBuilding().getBuildingName() + ".png");
        } catch (Exception ignored) {
            img = new Image("Images/buildingimgs/EEMCS.png");
        }

        imgView.setImage(img);


        String mess = generateString(selectedRoom);

        message.setText(mess);
        address.setText(selectedRoom.getBuilding().getLocation());

        showMapButton.setOnMouseClicked(event -> {
            showMap(selectedRoom.getBuilding().getBuildingName());
        });


        DatePicker datePicker = new DatePicker(LocalDate.now());
        List<LocalDate> localHolidays = TimeSlotsController.getHolidayDays();
        datePicker.setDayCellFactory(picker -> new DateCell() {

            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                if (empty ||  date.compareTo(today) < 0 || localHolidays.contains(date)) {

                    setDisable(true);
                } else {
                    setDisable(false);
                }

            }
        });

        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        Node calendar = datePickerSkin.getPopupContent();


        // DatePicker conf
        addListeners(datePicker);
        calendarPane.getChildren().add(calendar);
        selectedDate = formatter.format(LocalDate.now());
        setData();
        generateTimeslots();
    }

    private void addListeners(DatePicker datePicker) {
        datePicker.setOnAction(e -> {
            datePickerMethod(datePicker);
            setData();

            generateTimeslots();
        });
    }

    /**
     * Generates available time slots.
     */
    public void generateTimeslots() {
        final String disabled_css = "-fx-background-color:#707070;";
        final String active_css = "-fx-background-color:#ACACAC;";
        final String selected_css = "-fx-background-color:#518CBA;";
        //final String student_css = "-fx-background-color# #777777;";

        for (int i = 0; i < 10; i++) {
            if (timeslots[i] == null) {
                timeslotsGui[i].setVisible(false);
                timeslotsGui[i].setStyle(disabled_css);
                timeslotsGui[i].setCursor(Cursor.DEFAULT);
                timeslotsGui[i].setOnMouseClicked(event -> {
                    return;
                });
            } else {
                Pane tsGui = timeslotsGui[i];
                tsGui.setVisible(true);
                TimeSlots ts = timeslots[i];
                tsGui.setCursor(Cursor.HAND);
                if (ts.isStudentReservation()) {
                    tsGui.setStyle(disabled_css);
                } else {
                    tsGui.setStyle(active_css);
                }

                tsGui.setOnMouseClicked(event -> {
                    System.out.println(ts.getTimeDuration());
                    tsGui.setStyle(selected_css);

                    selectedTimeslot = ts;

                    selectButton.setCursor(Cursor.HAND);
                    selectButton.setOnMouseClicked(ev -> {
                        userSelectedTimeslot();
                        popupScreen();
                    });

                    for (int j = 0; j < 10; j++) {
                        if (timeslots[j] == ts) {
                            continue;
                        }
                        if (timeslots[j] == null) {
                            timeslotsGui[j].setVisible(false);
                            timeslotsGui[j].setStyle(disabled_css);
                        } else if (timeslots[j].isStudentReservation()) {
                            timeslotsGui[j].setStyle(disabled_css);
                        } else {
                            timeslotsGui[j].setStyle(active_css);
                        }
                    }
                });
            }
        }
        timeslotsContainer.setVisible(true);
    }

    private void datePickerMethod(DatePicker datePicker) {
        selectButton.setVisible(true);
        String date = "";
        if (datePicker.getValue() != null) {
            date = formatter.format(datePicker.getValue());
            selectedDate = date;
            selectedTimeslot = null;
            selectButton.setCursor(Cursor.DEFAULT);
            selectButton.setOnMouseClicked(event -> {
            });
        }
        System.out.println("Date selected : " + date);
    }

    private String generateString(ClassRoom selectedRoom) {
        int features = 0;
        if (selectedRoom.isProjectorBoard()) {
            features++;
        }
        if (selectedRoom.isBlackBoard()) {
            features++;
        }
        if (selectedRoom.isWhiteBoard()) {
            features++;
        }

        String mess = "This room is equipped with ";
        if (features == 0) {
            mess += "no special features";
        }
        if (features == 1) {
            if (selectedRoom.isProjectorBoard()) {
                mess += "a projector board.";
            }
            if (selectedRoom.isBlackBoard()) {
                mess += "a blackboard.";
            }
            if (selectedRoom.isWhiteBoard()) {
                mess += "a whiteboard";
            }
        }
        if (features == 2) {
            if (selectedRoom.isProjectorBoard()) {
                if (selectedRoom.isBlackBoard()) {
                    mess += "a projector board and a blackboard.";
                } else {
                    mess += "a projector board and a whiteboard.";
                }
            } else {
                mess += "a blackboard and a whiteboard.";
            }
        }
        if (features == 3) {
            mess += "a projector board, a black board and a white board.";
        }

        return mess += " It will fit " + selectedRoom.getNumberOfPeople() + " people.";

    }

    private void userSelectedTimeslot() {
        String timeslot = selectedTimeslot.getTimeDuration();
        ReservationRequestModel reservationRequestModel =
                new ReservationRequestModel(selectedRoom.getid(),
                        selectedDate,
                        timeslot,
                        MainApp.getUserName()
                );

        if (selectedTimeslot.isStudentReservation()) {
            RoomReservationController.deleteReservation(reservationRequestModel);
        }

        TimeSlotsController.createReservation(reservationRequestModel);

        try {
            roomReservationAction();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void setData() {
        timeslots = new TimeSlots[10];

        String type = ServerCommunication.getUtility("users/type/" + MainApp.getUserName()).body();

        List<TimeSlots> timeSlotsList = TimeSlotsController
                .getAvailableTimeSlots(selectedRoom.getRoomName(), selectedDate);

        List<TimeSlots> studentSlots = null;
        if (!type.equals("Student")) {
            overrideWarning.setVisible(true);
            studentSlots = TimeSlotsController.getStudentReservations(
                    selectedRoom.getRoomName(), selectedDate);
        }

        ListIterator<TimeSlots> iter = timeSlotsList.listIterator();
        while (iter.hasNext()) {
            TimeSlots slot = iter.next();
            long id = slot.getId();
            timeslots[(int) (id - 1)] = slot;
        }


        if (studentSlots != null) {
            iter = studentSlots.listIterator();
            while (iter.hasNext()) {
                TimeSlots slot = iter.next();
                slot.setStudentReservation(true);
                long id = slot.getId();
                timeslots[(int) (id - 1)] = slot;
            }
        }

    }

    /**
     * Initializes the RoomReservationStage.
     *
     * @return The RoomReservation Stage.
     * @throws IllegalAccessException to be written!
     */
    @FXML
    public RoomReservationScreen roomReservationAction() throws IllegalAccessException {

        Stage roomReservationStage = new Stage();
        RoomReservationScreen roomReservationScreen = new RoomReservationScreen();
        roomReservationScreen.start(roomReservationStage);
        stage.close();
        return roomReservationScreen;
    }

    @FXML
    private void backAction() {
        Stage homeStage = new Stage();
        HomeScreen home = new HomeScreen();
        home.start(homeStage);
        homeStage.show();
        stage.close();
    }

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
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void popupScreen() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Confirmation!");
        dialog.getIcons().add(new Image("Images/TU-Delft-Logo.jpg"));
        VBox dialogVbox = new VBox();
        dialogVbox.setAlignment(Pos.CENTER);

        StringBuilder sb = new StringBuilder();
        sb.append("You have reserved a room!");
        sb.append("\n");
        sb.append(selectedDate);
        sb.append(" ");
        sb.append(selectedTimeslot.getTimeDuration());
        sb.append(" - ");
        sb.append(selectedRoom.getRoomName());
        sb.append(" in ");
        sb.append(selectedRoom.getBuilding().getBuildingName());

        Text confirmation = new Text(sb.toString());
        confirmation.setStyle("-fx-font-family: \"AvenirNext LT Pro Bold\";\n"
                + "-fx-font-size: 20px;");
        dialogVbox.getChildren().addAll(confirmation);
        Scene dialogScreen = new Scene(dialogVbox, 800, 200);
        dialog.setScene(dialogScreen);
        dialog.showAndWait();
    }

}
