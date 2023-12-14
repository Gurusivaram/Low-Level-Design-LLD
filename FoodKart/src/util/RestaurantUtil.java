package util;

import model.Restaurant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RestaurantUtil {
    public static boolean isLocationFound(Restaurant restaurant, String location) {
        return restaurant.getLocationSet().contains(location);
    }

    public static boolean isServiceable(Restaurant restaurant, String location) {
        return isLocationFound(restaurant, location) && restaurant.getQuantity() != 0;
    }

    public static Set<String> getLocationSet(String locations) {
        String[] pins = locations.split("/", 0);
        Set<String> st = new HashSet<>();
        st.addAll(Arrays.asList(pins));
        return st;
    }
}
