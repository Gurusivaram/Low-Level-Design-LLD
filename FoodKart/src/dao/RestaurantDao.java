package dao;

import model.Restaurant;
import model.User;
import util.RestaurantUtil;

import java.util.*;

public class RestaurantDao {
    private static Map<User, Map<String, Restaurant>> restaurantMap; //user, {name, restaurant}
    private static ArrayList<Restaurant> restaurants;
    private static RestaurantDao instance;

    private void RestaurantDao() {}

    public static RestaurantDao getInstance() {
        synchronized (RestaurantDao.class) {
            if(instance == null) {
                instance = new RestaurantDao();
                restaurantMap = new HashMap<>();
                restaurants = new ArrayList<>();
            }
        }
        return instance;
    }

    public Restaurant getRestaurant(User user, String name) {
        if(restaurantMap.get(user) != null) {
            return restaurantMap.get(user).get(name);
        }
        return null;
    }

    public Restaurant getRestaurant(String name) {
        for(Map<String, Restaurant> mp : restaurantMap.values()) {
            for(Restaurant res : mp.values()) {
                if(res.getName().equalsIgnoreCase(name)) return res;
            }
        }
        return null;
    }

    public ArrayList<Restaurant> getRestaurantsBasedOnLocation(String location) {
        ArrayList<Restaurant> list = new ArrayList<>();
        for(Map<String, Restaurant> mp : restaurantMap.values()) {
            for(Restaurant restaurant : mp.values()) {
                if(RestaurantUtil.isServiceable(restaurant, location)) {
                    list.add(restaurant);
                }
            }
        }
        list.sort(Comparator.comparingInt(Restaurant::getPerItemPrice).reversed());

        return list;
    }

    public boolean updateRestaurant(User user, Restaurant restaurant) {
        if(restaurantMap.get(user) != null) {
            Map<String, Restaurant> map = restaurantMap.get(user);
            if(map.get(restaurant.getName()) != null) {
                map.put(restaurant.getName(), restaurant);
                return true;
            }
        }
        return false;
    }

    public void addRestaurant(User user, Restaurant restaurant) {
        if(restaurantMap.get(user) != null) {
            Map<String, Restaurant> map = restaurantMap.get(user);
            map.put(restaurant.getName(), restaurant);
        } else {
            Map<String, Restaurant> map = new HashMap<>();
            map.put(restaurant.getName(), restaurant);
            restaurantMap.put(user, map);
        }
        restaurants.add(restaurant);
    }
}
