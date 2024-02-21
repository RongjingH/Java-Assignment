package dao;

import model.User;

import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    private ArrayList<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public User getUser(String username)  {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public void removeUser(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
    }
}
