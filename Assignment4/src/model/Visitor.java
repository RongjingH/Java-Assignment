package model;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class Visitor extends User {
    List<Task> tasks;

    public Visitor(String userName, String password) {
        super(userName, password);
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
