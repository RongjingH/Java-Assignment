package model;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void markTaskAsCompleted(Task task) {
        task.setCompleteState(true);
    }

    public List<Task> getCompletedTasks(List<Task> tasks) {
        return tasks.stream().filter(Task::getCompleteState).collect(Collectors.toList());
    }

    public List<Task> getIncompleteTasks(List<Task> tasks) {
        return tasks.stream().filter(task -> !task.getCompleteState()).collect(Collectors.toList());
    }
}
