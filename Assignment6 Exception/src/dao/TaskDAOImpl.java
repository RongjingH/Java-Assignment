package dao;

import model.Task;
import model.Visitor;

import java.util.ArrayList;

public class TaskDAOImpl implements TaskDAO {
    private ArrayList<Task> tasks = new ArrayList<>();

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added successfully.");
    }

    @Override
    public void updateTask(Task task, String newTitle, String newText) {
        task.setTaskTitle(newTitle);
        task.setTaskText(newText);
    }

    @Override
    public void deleteTask(String taskName) {
        if (tasks.removeIf(task -> task.getTaskTitle().equals(taskName))) {
            System.out.println("Delete successfully!");
        } else {
            System.out.println("Task not found!");
        }
    }

    @Override
    public Task searchTask(String taskName) {
        for (Task task : tasks) {
            if (task.getTaskTitle().equals(taskName)){
                return task;
            }
        }
        return null;
    }

    @Override
    public void assignTask(String taskName, Visitor visitor) {
        for (Task task : tasks) {
            if (task.getTaskTitle().equals(taskName)) {
                task.setAssignedTo(visitor);
                visitor.getTasks().add(task);
                System.out.println("Task assigned successfully.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    @Override
    public void presentTask() {
        System.out.println("There are all tasks in our database: ");
        System.out.println("Task ID                          | Task title | Text text");
        for (Task task : tasks) {
            System.out.println(task.getTaskId() + "  " + task.getTaskTitle() + "  " + task.getTaskText());
        }
    }
}
