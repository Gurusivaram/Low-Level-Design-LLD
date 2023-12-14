package dao;

import javafx.util.Pair;
import model.Rating;
import model.Restaurant;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartDao {
    private static Map<User, Map<Restaurant, Integer>> cartMap; //user, {rest, cartItemCount}
    private static CartDao instance;

    private void RatingDao() {}

    public static CartDao getInstance() {
        synchronized (CartDao.class) {
            if(instance == null) {
                instance = new CartDao();
                cartMap = new HashMap<>();
            }
        }
        return instance;
    }

    public void updateCart(User user, Restaurant restaurant, int orderCount) {
        if(cartMap.get(user) != null) {
            cartMap.get(user).put(restaurant, orderCount);
        } else {
            Map<Restaurant, Integer> mp = new HashMap<>();
            mp.put(restaurant, orderCount);
            cartMap.put(user, mp);
        }
    }

    public ArrayList<Pair<String , Integer>> getCartForUser(User user) {
        ArrayList<Pair<String , Integer>> list = new ArrayList<>();
        if(cartMap.get(user) != null) {
            for(Restaurant restaurant : cartMap.get(user).keySet()) {
                list.add(new Pair<>(restaurant.getName(), cartMap.get(user).get(restaurant)));
            }
        }
        return list;
    }
}
