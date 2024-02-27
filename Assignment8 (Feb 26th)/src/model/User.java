package model;

import java.util.List;

abstract public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void arrangeTasksByDate(List<Task> tasks, boolean ascending) {
        tasks.sort((t1, t2) -> {
            if (ascending) {
                return t1.getCompletionDate().compareTo(t2.getCompletionDate());
            } else {
                return t2.getCompletionDate().compareTo(t1.getCompletionDate());
            }
        });
    }
}
