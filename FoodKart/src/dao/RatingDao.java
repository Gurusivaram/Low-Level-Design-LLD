package dao;

import javafx.util.Pair;
import model.Rating;
import model.Restaurant;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RatingDao {
    private static Map<User, Map<Restaurant,ArrayList<Rating>>> ratingMap;
    private static RatingDao instance;

    private void RatingDao() {}

    public static RatingDao getInstance() {
        synchronized (RatingDao.class) {
            if(instance == null) {
                instance = new RatingDao();
                ratingMap = new HashMap<>();
            }
        }
        return instance;
    }

    public void addRating(User user, Restaurant restaurant, Rating rating) {
        if(ratingMap.get(user) != null) {
            Map<Restaurant,ArrayList<Rating>> ratingMp = ratingMap.get(user);
            if(ratingMp.get(restaurant) != null) {
                ratingMp.get(restaurant).add(rating);
            } else {
                ArrayList<Rating> list = new ArrayList<>();
                list.add(rating);
                ratingMp.put(restaurant, list);
                ratingMap.put(user, ratingMp);
            }
        } else {
            Map<Restaurant,ArrayList<Rating>> ratingMp = new HashMap<>();
            ArrayList<Rating> list = new ArrayList<>();
            list.add(rating);
            ratingMp.put(restaurant, list);
            ratingMap.put(user, ratingMp);
        }
    }

    public ArrayList<Pair<Integer, Restaurant>> getAverageRating() {
        ArrayList<Pair<Integer, Restaurant>> avgRatings = new ArrayList<>();
        for(Map<Restaurant,ArrayList<Rating>> mp : ratingMap.values()) {
            for(Restaurant restaurant : mp.keySet()) {
                int count = 0;
                int total = 0;
                for(Rating rating : mp.get(restaurant)) {
                    ++count;
                    total += rating.getStars();
                }
                if(count != 0) {
                    avgRatings.add(new Pair<>(total/count, restaurant));
                }
            }
        }
        return avgRatings;
    }
}
