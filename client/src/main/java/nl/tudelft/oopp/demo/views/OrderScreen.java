package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.MainApp;
import nl.tudelft.oopp.demo.controllers.FoodController;
import nl.tudelft.oopp.demo.controllers.RoomReservationController;
import nl.tudelft.oopp.demo.models.MainOrder;
import nl.tudelft.oopp.demo.models.Order;
import nl.tudelft.oopp.demo.models.RoomReservation;


public class OrderScreen extends Application implements Initializable {

    public static Stage stage;

    private MainOrder mainOrder = MainApp.getMainOrder();

    private List<RoomReservation> res = RoomReservationController.getReservationsPerUser();

    @FXML
    private VBox order;

    @Override
    public void start(final Stage primaryStage) throws IllegalAccessException {
        stage = primaryStage;
        stage.getIcons().add(new Image("Images/TU-Delft-Logo.jpg"));

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/OrderScreen.fxml"));

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
        scene.getStylesheets().addAll("/CSS/OrderScreen.css");
        primaryStage.setScene(scene);
        return primaryStage;
    }

    @FXML
    private void backAction() {
        MainApp.resetMainOrder();
        Stage homeStage = new Stage();
        HomeScreen home = new HomeScreen();
        home.start(homeStage);
        homeStage.show();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Order> orders = FoodController.getOrders();
        mainOrder.setOrders(orders);
        order.getChildren().addAll(displayOrdersO(mainOrder.getOrders()));


    }

    private VBox displayOrdersO(List<Order> orders) {
        if (!orders.isEmpty()) {
            VBox vbox = new VBox();
            vbox.setSpacing(20.0);
            vbox.setId("vbox");
            double totalPrice = 0;
            for (Order o : orders) {
                HBox hbox = generateBasketElement(o);
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
            vbox.getChildren().addAll(new Line(6.0, 54.0, 715.0, 54.0));
            Text text = new Text("Total" + "\t" + totalPrice);
            text.setId("text");
            text.setTextAlignment(TextAlignment.RIGHT);
            vbox.getChildren().addAll(region1, text);
            return vbox;
        }
        VBox vbox = new VBox();
        Text text = new Text("No orders placed");
        text.setId("text");
        text.setTextAlignment(TextAlignment.RIGHT);
        vbox.getChildren().addAll(text);
        return vbox;
    }

    private HBox generateBasketElement(Order o) {
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);
        Region region3 = new Region();
        HBox.setHgrow(region3, Priority.ALWAYS);

        Text quantity = new Text(o.getQuantity() + " X");
        RoomReservation orderRes = getReservation(res, o);
        Text foodName = new Text(o.getFoodMenu().getFoodName());
        Text location = new Text(orderRes.getDate() + " " + orderRes.getClassRoom().getRoomName());
        HBox element = new HBox(quantity, region1, foodName, region2, location);
        element.setSpacing(20.0);
        return element;
    }

    private HBox generateTotalBox(double price) {
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        Text total = new Text("Total");
        Text priceText = new Text(Double.toString(price));

        HBox element = new HBox(total, region1, priceText);
        return element;
    }

    private RoomReservation getReservation(List<RoomReservation> res, Order o) {
        for (RoomReservation rr : res) {
            if (rr.getId().equals(o.getResId())) {
                return rr;
            }
        }
        return null;
    }

}
