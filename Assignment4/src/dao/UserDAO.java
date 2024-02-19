package dao;

import model.User;

public interface UserDAO {
    void addUser(User user);
    User getUser(String username);
    void removeUser(String username);
}
