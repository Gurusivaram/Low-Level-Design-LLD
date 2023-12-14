package app;

import dao.CartDao;
import dao.RatingDao;
import dao.RestaurantDao;
import dao.UserDao;
import javafx.util.Pair;
import model.*;
import netscape.javascript.JSObject;
import util.RestaurantUtil;

import java.util.*;

public class Application {
    private static Application instance;
    private static int userId = 0;
    private static User loggedInUser;
    private Application() {
    }

    public static Application getInstance() {
        synchronized (Application.class) {
            if(instance == null) {
                instance = new Application();
            }
        }
        return instance;
    }

    public boolean loginUser(String phone) {
        if(UserDao.getInstance().getUser(phone) != null) {
            loggedInUser = UserDao.getInstance().getUser(phone);
            return true;
        }
        return false;
    }

    public void registerUser(String name, String gender, String phone, String pincode) {
        User user = new User(++userId, name, gender, phone, pincode);
        UserDao.getInstance().addUser(user);
    }

    public void registerRestaurant(String name, String pinCodes, String foodItem, String price, String quantity) {
        Restaurant restaurant = new Restaurant(name, RestaurantUtil.getLocationSet(pinCodes), new FoodItem(foodItem, Integer.valueOf(price)), Integer.valueOf(quantity));
        RestaurantDao.getInstance().addRestaurant(loggedInUser, restaurant);
    }

    public boolean updateQuantity(String restaurantName, int quantity) {
        Restaurant restaurant = RestaurantDao.getInstance().getRestaurant(loggedInUser, restaurantName);
        if(restaurant == null) return false;
        restaurant.updateQuantity(quantity);
        RestaurantDao.getInstance().updateRestaurant(loggedInUser, restaurant);
        return true;
    }

    public Pair<String, Integer> getQuantity(String restaurantName) {
        Restaurant restaurant = RestaurantDao.getInstance().getRestaurant(loggedInUser, restaurantName);
        if(restaurant == null) return null;
        return new Pair<>(restaurant.getName(), restaurant.getQuantity());
    }

    public boolean updateLocation(String restaurantName, String locations) {
        Restaurant restaurant = RestaurantDao.getInstance().getRestaurant(loggedInUser, restaurantName);
        if(restaurant == null) return false;
        restaurant.updateLocation(RestaurantUtil.getLocationSet(locations));
        RestaurantDao.getInstance().updateRestaurant(loggedInUser, restaurant);
        return true;
    }

    public Pair<String, String> getLocations(String restaurantName) {
        Restaurant restaurant = RestaurantDao.getInstance().getRestaurant(loggedInUser, restaurantName);
        if(restaurant == null) return null;
        return new Pair<>(restaurant.getName(), restaurant.getLocations());
    }

    public void createReview(String restaurantName, int rating, String comment) {
        Restaurant restaurant = RestaurantDao.getInstance().getRestaurant(restaurantName);
        RatingDao.getInstance().addRating(loggedInUser, restaurant, new Rating(rating, comment));
    }

    public String getLoggedInUserLocation() {
        if(loggedInUser != null) return loggedInUser.getLocation();
        return null;
    }

    public ArrayList<Pair<String, String>> showRestaurant(String type) {
        ArrayList<Pair<String, String>> pairs = new ArrayList<>();
        switch (type) {
            case "price": {
                ArrayList<Restaurant> list = RestaurantDao.getInstance().getRestaurantsBasedOnLocation(loggedInUser.getLocation());
                for(Restaurant restaurant : list) {
                    pairs.add(new Pair<>(restaurant.getName(), restaurant.getFoodName()));
                }
            }
            break;

            case "rating": {
                ArrayList<Pair<Integer, Restaurant>> avgRatings = RatingDao.getInstance().getAverageRating();
                avgRatings.sort(Comparator.comparing(Pair::getKey));
                Collections.reverse(avgRatings);

                for(Pair<Integer, Restaurant> pair: avgRatings) {
                    pairs.add(new Pair<>(pair.getValue().getName(), pair.getValue().getFoodName()));
                }
            }
            break;

            default: return null;
        }
        return pairs;
    }

    public boolean placeOrder(String restaurantName, int quantity) {
        Restaurant restaurant = RestaurantDao.getInstance().getRestaurant(restaurantName);
        if(restaurant == null || restaurant.getQuantity() < quantity || !RestaurantUtil.isServiceable(restaurant, loggedInUser.getLocation())) return false;

        restaurant.setQuantity(Math.abs(quantity - restaurant.getQuantity()));
        CartDao.getInstance().updateCart(loggedInUser, restaurant, quantity);
        RestaurantDao.getInstance().updateRestaurant(loggedInUser, restaurant);
        return true;
    }

    public ArrayList<Pair<String , Integer>> getOrders() {
        return CartDao.getInstance().getCartForUser(loggedInUser);
    }

    public boolean isUserLoggedIn() {
        return loggedInUser != null;
    }

    public String  getLoggedInUserName() {
        if(loggedInUser != null) {
            return loggedInUser.getName();
        }
        return "";
    }
}
