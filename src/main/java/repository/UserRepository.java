package repository;

import bean.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private static Map<String, User> users = new HashMap<>();

    public static String add(User user) {
        users.put(user.getLogin(), user);
        return user.getLogin();
    }

    public static Map<String, User> getUsers() {
        return users;
    }
}
