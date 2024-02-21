package dao;

import model.Task;
import model.Visitor;

public interface TaskDAO {
    void addTask(Task task);
    void updateTask(Task task, String newTitle, String newText);
    void deleteTask(String taskName);
    Task searchTask(String taskName);
    void assignTask(String taskName, Visitor visitor);
    void presentTask();
}
