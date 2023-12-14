package dao;

import app.Application;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String, User> userMap;
    private static UserDao instance;

    private void UserDao() {

    }

    public static UserDao getInstance() {
        synchronized (UserDao.class) {
            if(instance == null) {
                instance = new UserDao();
                userMap = new HashMap<>();
            }
        }
        return instance;
    }

    public User getUser(String phone) {
        return userMap.get(phone);
    }

    public void addUser(User user) {
        userMap.put(user.getPhone(), user);
    }

    public boolean deleteUser(User user) {
        if(userMap.get(user.getPhone()) != null) {
            userMap.remove(user.getPhone());
            return true;
        }
        return false;
    }
}
