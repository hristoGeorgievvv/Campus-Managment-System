package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.MainApp;
import nl.tudelft.oopp.demo.controllers.FoodController;
import nl.tudelft.oopp.demo.controllers.RoomReservationController;
import nl.tudelft.oopp.demo.models.FoodMenu;
import nl.tudelft.oopp.demo.models.FoodVendor;
import nl.tudelft.oopp.demo.models.MainOrder;
import nl.tudelft.oopp.demo.models.Order;
import nl.tudelft.oopp.demo.models.OrderRequestModel;
import nl.tudelft.oopp.demo.models.RoomReservation;

public class FoodScreen extends Application implements Initializable {

    public static Stage stage;

    private String reservation = null;

    private Currency cur = Currency.getInstance(Locale.FRANCE);

    private String euroSymbol = cur.getSymbol();

    private DropShadow shadow = new DropShadow();

    private MainOrder mainOrder = MainApp.getMainOrder();

    private HashMap<FoodVendor, List<FoodMenu>> data;

    private boolean isSuccessful;

    private long reservationId;

    @FXML
    private Button orderButton;

    @FXML
    private VBox restaurants;

    @FXML
    private VBox order;

    @Override
    public void start(final Stage primaryStage) throws IllegalAccessException {
        stage = primaryStage;
        stage.getIcons().add(new Image("Images/TU-Delft-Logo.jpg"));

        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/Views/FoodScreen.fxml"));
            setRootProperties(primaryStage, root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
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
        scene.getStylesheets().addAll("/CSS/FoodScreen.css");
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

    @FXML
    private void cursorToHand() {
        orderButton.setCursor(Cursor.HAND);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = FoodController.generateData();

        shadow.setRadius(6.3);
        shadow.setSpread(0.1);
        shadow.setHeight(13.58);
        shadow.setWidth(12.2);
        shadow.setColor(Color.color(0, 0, 0));

        if (data == null) {
            throw new NullPointerException();
        }

        Iterator it = data.keySet().iterator();
        order.getChildren().addAll(displayOrders(mainOrder.getOrders()));
        List<VBox> restaurantElements = new ArrayList<>();
        while (it.hasNext()) {
            FoodVendor foodVendor = (FoodVendor) it.next();
            String text = foodVendor.getName()
                    .replaceAll("[^\'^a-zA-Z0-9]", " ");
            VBox element = generateVendorElement(foodVendor, text);
            restaurants.getChildren().add(element);
            restaurantElements.add(element);
        }
    }

    private VBox generateVendorElement(FoodVendor foodVendor, String text) {
        restaurants.setAlignment(Pos.CENTER);

        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);

        Text title = new Text(text);
        title.setFont(new Font("AvenirNextLTPro-Bold", 24.0));
        title.setWrappingWidth(315);
        title.setTextAlignment(TextAlignment.CENTER);

        HBox textbox = new HBox(region1, title, region2);

        textbox.setPadding(new Insets(10, 10, 10, 10));
        textbox.setStyle("-fx-background-color: #F1EEEE");
        textbox.setEffect(shadow);

        HBox menu = generateMenu(foodVendor);
        menu.setId("menu");

        menu.setPadding(new Insets(25, 25, 25, 25));

        VBox main = new VBox(textbox, menu);
        VBox.setMargin(main, new Insets(20, 0, 8, 5));
        main.setId("main");
        main.setPadding(new Insets(10, 10, 10, 10));

        return main;
    }

    private HBox generateMenu(FoodVendor foodVendor) {
        List<FoodMenu> menuItems = foodVendor.getMenuItems(foodVendor);
        List<Button> buttons = generateButtons(menuItems);

        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);

        HBox menu = new HBox(buttons.get(0), region1, buttons.get(1), region2, buttons.get(2));
        menu.setEffect(shadow);

        return menu;
    }

    private List<Button> generateButtons(List<FoodMenu> menuItems) {

        Button item1 = new Button(menuItems.get(0).getFoodName()
                + "\n" + menuItems.get(0).getPrice() + euroSymbol);
        item1.setId("item1");
        item1.setEffect(shadow);
        item1.setCursor(Cursor.HAND);
        item1.setOnMouseClicked(e -> {
            addToOrder(menuItems.get(0));
        });

        Button item2 = new Button(menuItems.get(1).getFoodName()
                + "\n" + menuItems.get(1).getPrice() + euroSymbol);
        item2.setId("item1");
        item2.setEffect(shadow);
        item2.setCursor(Cursor.HAND);
        item2.setOnMouseClicked(e -> {
            addToOrder(menuItems.get(1));
        });

        Button item3 = new Button(menuItems.get(2).getFoodName()
                + "\n" + menuItems.get(2).getPrice() + euroSymbol);
        item3.setCursor(Cursor.HAND);
        item3.setId("item1");
        item3.setEffect(shadow);
        item3.setOnMouseClicked(e -> {
            addToOrder(menuItems.get(2));
        });

        List<Button> elements = new ArrayList<>();
        elements.add(item1);
        elements.add(item2);
        elements.add(item3);

        return elements;
    }

    private void addToOrder(FoodMenu foodMenu) {
        order.getChildren().clear();
        boolean foundSame = false;

        List<Order> orders = mainOrder.getOrders();
        Order change = new Order(mainOrder, 1.0, foodMenu, MainApp.getUserName(), reservationId);
        for (Order o : orders) {
            if (o.equals(change)) {
                change.setQuantity(o.getQuantity() + 1.0);
                orders.remove(o);
                orders.add(change);
                mainOrder.setOrders(orders);
                foundSame = true;
                break;
            }
        }
        mainOrder.setOrders(orders);
        if (foundSame) {
            order.getChildren().addAll(displayOrders(orders));
        } else {
            orders.add(change);
            order.getChildren().addAll(displayOrders(orders));
        }

    }

    @FXML
    private void sendOrder() {
        List<RoomReservation> reservations = RoomReservationController.getReservationsPerUser();

        if (reservations.isEmpty() || mainOrder.getOrders().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No registrations");
            alert.setHeaderText("You have no reservations, or your basket is empty!");
            alert.showAndWait();
            return;
        }
        selectReservation(reservations);
        if (isSuccessful) {
            for (Order u : mainOrder.getOrders()) {
                OrderRequestModel req = new OrderRequestModel(u.getQuantity(),
                        u.getFoodMenu(),MainApp.getUserName(), reservationId);
                FoodController.sendOrder(req);
            }
            mainOrder.setOrders(new ArrayList<>());
            order.getChildren().clear();
        }
        System.out.println(isSuccessful);
    }

    private VBox displayOrders(List<Order> orders) {
        VBox vbox = new VBox();
        vbox.setSpacing(10.0);
        vbox.setId("vbox");
        double totalPrice = 0;
        for (Order o : orders) {
            HBox hbox = generateBasketElement(o);
            HBox.setMargin(hbox, new Insets(10,10,10,10));
            double price = o.getQuantity() * o.getFoodMenu().getPrice();
            vbox.getChildren().addAll(hbox);
            totalPrice += price;
        }
        if (mainOrder.getOrders().isEmpty()) {
            return vbox;
        }
        Region region1 = new Region();
        VBox.setVgrow(region1, Priority.ALWAYS);
        HBox priceBox = generateTotalBox(totalPrice);
        vbox.getChildren().addAll(new Line(6.0, 54.0, 476.0, 54.0));
        Text text = new Text("Total" + "\t" + totalPrice);
        text.setId("text");
        text.setTextAlignment(TextAlignment.RIGHT);
        vbox.getChildren().addAll(region1, text);
        return vbox;
    }

    private HBox generateBasketElement(Order o) {
        Label remove = new Label("\tREMOVE\t");
        remove.setCursor(Cursor.HAND);
        remove.setOnMouseClicked(e -> {
            List<Order> ordersMain = mainOrder.getOrders();
            if (o.getQuantity() > 1) {
                o.setQuantity(o.getQuantity() - 1);
                ordersMain.get(ordersMain.indexOf(o)).setQuantity(o.getQuantity());
            } else {
                ordersMain.remove(o);
                mainOrder.setOrders(ordersMain);
            }
            order.getChildren().clear();
            order.getChildren().addAll(displayOrders(mainOrder.getOrders()));
        });

        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);
        Region region3 = new Region();
        HBox.setHgrow(region3, Priority.ALWAYS);

        Text quantity = new Text(o.getQuantity() + " X");
        quantity.setId("text");
        Text foodName = new Text(o.getFoodMenu().getFoodName());
        foodName.setId("text");
        HBox element = new HBox(quantity, region1, foodName, region2, region3, remove);
        element.setSpacing(20.0);
        return element;
    }

    private HBox generateTotalBox(double price) {
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        Text total = new Text("Total");
        Text priceText = new Text(Double.toString(price) + euroSymbol);

        HBox element = new HBox(total, region1, priceText);
        return element;
    }

    private void selectReservation(List<RoomReservation> reservations) {
        Stage dialog = new Stage();
        dialog.setTitle("Reservation selection");
        dialog.getIcons().add(new Image("Images/TU-Delft-Logo.jpg"));
        isSuccessful = true;
        reservationId = 0;
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setOnHiding(e -> {
            if (reservation == null) {
                isSuccessful = false;
                reservationId = 0;
            }
        });
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        Text title = new Text("Select reservation to which you want to order");
        title.setId("text");
        dialogVbox.getChildren().addAll(title);

        for (RoomReservation rr : reservations) {
            HBox hbox = createDialogScreen(rr, dialog);
            dialogVbox.getChildren().addAll(hbox);
        }
        Scene dialogScene = new Scene(dialogVbox, 700, 500);
        dialogScene.getStylesheets().addAll("/CSS/FoodScreen.css");
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private HBox createDialogScreen(RoomReservation rr, Stage dialog) {
        HBox hbox = new HBox();
        hbox.setId("menu");
        hbox.setSpacing(20.0);
        hbox.setAlignment(Pos.CENTER);

        StringBuilder sb = new StringBuilder();
        sb.append(rr.getDate());
        sb.append(" ");
        sb.append(rr.getTimeSlots().getTimeDuration());
        sb.append(" in ");
        sb.append(rr.getClassRoom().getRoomName());

        Button select = new Button("Select");
        select.setCursor(Cursor.HAND);
        select.setId("selectButton");
        select.setOnMouseClicked(e -> {
            reservation = sb.toString();
            isSuccessful = true;
            reservationId = rr.getId();
            dialog.close();
        });
        Text res = new Text(sb.toString());
        res.setId("text");
        hbox.getChildren().addAll(select, res);
        return hbox;
    }
}
