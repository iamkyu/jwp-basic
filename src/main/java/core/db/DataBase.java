package core.db;

import com.google.common.collect.Maps;
import next.model.User;

import java.util.Collection;
import java.util.Map;

public class DataBase {
    private static Map<String, User> users = Maps.newHashMap();

    static {
        User admin = new User("admin", "admin", "admin", "admin@mail.com");
        User user1 = new User("user1", "user1", "user1", "user1@mail.com");
        User user2 = new User("user2", "user2", "user2", "user2@mail.com");
        DataBase.addUser(admin);
        DataBase.addUser(user1);
        DataBase.addUser(user2);
    }

    public static void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static Collection<User> findAll() {
        return users.values();
    }
}
