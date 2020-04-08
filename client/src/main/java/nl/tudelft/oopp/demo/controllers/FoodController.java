package nl.tudelft.oopp.demo.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nl.tudelft.oopp.demo.MainApp;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.models.FoodMenu;
import nl.tudelft.oopp.demo.models.FoodVendor;
import nl.tudelft.oopp.demo.models.Order;
import nl.tudelft.oopp.demo.models.OrderRequestModel;


public class FoodController {

    /**
     * should have a proper javadoc.
     * @return .
     */
    public static HashMap<FoodVendor, List<FoodMenu>> generateData() {
        List<FoodVendor> restaurants = getRestaurants();

        if (restaurants == null) {
            return null;
        }

        HashMap<FoodVendor, List<FoodMenu>> data = new HashMap<>();

        for (FoodVendor vendor: restaurants) {
            List<FoodMenu> menu = getFoodMenus(vendor);
            if (menu == null) {
                continue;
            }
            for (FoodMenu item: menu) {
                item.setVendor(vendor);
            }
            data.put(vendor,menu);
        }

        return data;
    }

    /**
     * Returns a list of all food vendors in the database.
     * @return A list of all food vendors in the database
     */
    public static List<FoodVendor> getRestaurants() {
        HttpResponse<String> response = ServerCommunication.getUtility("All_FoodVendors");
        if (response == null) {
            return null;
        }
        Type listType = new TypeToken<ArrayList<FoodVendor>>(){}.getType();

        return new Gson().fromJson(response.body(), listType);
    }

    /**
     * Returns a list of all foodmenus of a vendor in the database.
     * @return A list of all foodmenus
     */
    public static List<FoodMenu> getFoodMenus(FoodVendor vendor) {
        HttpResponse<String> response = ServerCommunication.getUtility(
                "menu/byVendor/" + vendor.getName());
        if (response == null) {
            return null;
        }

        Type listType = new TypeToken<ArrayList<FoodMenu>>(){}.getType();

        return new Gson().fromJson(response.body(), listType);
    }


    /**
     * Sends an order to the server.
     * @param order the food order to be created
     * @return true if the response is ok
     */
    public static boolean sendOrder(OrderRequestModel order) {
        HttpResponse<String> response = ServerCommunication.postUtility("createOrder/", order);

        if (response == null || response.statusCode() != 200) {
            System.out.println(response.statusCode() + " FoodController: sendOrder");
            return false;
        }
        return true;

    }

    /**
     * Get the orders that a certain user has placed.
     * @return a list of that user's orders
     */
    public static List<Order> getOrders() {
        String mapping = "orders/" + MainApp.getUserName();
        HttpResponse<String> response = ServerCommunication.getUtility(mapping);

        if (response == null) {
            return null;
        }

        //System.out.println(response.body());

        Type listType = new TypeToken<ArrayList<OrderRequestModel>>(){}.getType();
        List<OrderRequestModel> orders = new Gson().fromJson(response.body(), listType);

        //System.out.println("FoodController: " + orders);

        List<Order> orders2 = new ArrayList<>();
        for (OrderRequestModel u : orders) {
            Order req = new Order(null, u.getQuantity(),
                    u.getFoodMenu(),MainApp.getUserName(), u.getResId());
            orders2.add(req);
        }

        return orders2;
    }


}
