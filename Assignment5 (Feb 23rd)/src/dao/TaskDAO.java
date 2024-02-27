package dao;

import model.Task;
import model.Visitor;

import java.util.Date;
import java.util.List;

public interface TaskDAO {
    void addTask(Task task);
    void updateTask(Task task, String newTitle, String newText);
    void deleteTask(String taskName);
    Task searchTask(String taskName);
    void assignTask(String taskName, Visitor visitor);
    void presentTask();
    void assignCompletionDate(String taskName, Date completionDate);
    List<Task> getTasksSortedByCompletionDate();
    boolean contanins(Task task);
    void completeTask(Task task);
}
