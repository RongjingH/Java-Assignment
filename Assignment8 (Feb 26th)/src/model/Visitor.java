package model;

import comparator.SortTasks;
import dao.TaskDAO;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Visitor extends User implements Runnable{
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

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Visitor run method");
        while (true) {
            System.out.println("\nVisitor Menu:");
            System.out.println("1. See Your Tasks");
            System.out.println("2. Arrange Tasks by Completion Dates");
            System.out.println("3. Mark a Task Completed");
            System.out.println("4. See all Tasks Completion States");
            System.out.println("0. Logout");

            int choice = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    choice = scanner.nextInt();
                    validInput = true;
                    scanner.nextLine(); // Consume the invalid input
                } catch (Exception e) {
                    System.out.println("Error: Invalid input. Please enter an integer value.");
                }
            }

            switch (choice) {
                case 1: //Display all tasks
                    if (tasks == null || tasks.size() == 0) {
                        System.out.println("You don't have any tasks");
                    } else {
                        System.out.println("! There are all your tasks: ");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.print(tasks.get(i).getTaskTitle() + ";  ");
                        }
                    }
                    break;
                case 2: //Arrange Tasks by Completion Dates
                    Collections.sort(tasks, new SortTasks());
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.print(tasks.get(i).getTaskTitle() + ";  ");
                    }
                    break;
                case 3: // Mark a Task Completed
                    System.out.println("Enter task title");
                    String completedTaskTitle = scanner.nextLine();

                    //create a completed task reference
                    Task cpmpletedTask = null;
                    for (Task task : tasks) {
                        if (task.getTaskTitle().equals(completedTaskTitle)) {
                            cpmpletedTask = task;
                        }
                    } //by this way the task title have to unique, which I need to limit later.

                    if (cpmpletedTask != null) {
                        cpmpletedTask.setCompleteState(true);
                        System.out.println("Succeed!");
                    } else {
                        System.out.println("Error: Can't find the task! Please make sure you enter a correct task title");
                    }
                    break;
                case 4: //See all Tasks Completion States
                    List<Task> completedTasks = this.getCompletedTasks(tasks);
                    System.out.println("There are all completed tasks: ");
                    for (int i = 0; i < completedTasks.size(); i++) {
                        System.out.print(completedTasks.get(i).getTaskTitle() + ";  ");
                    }
                    System.out.println();
                    List<Task> uncompletedTasks = this.getIncompleteTasks(tasks);
                    System.out.println("There are all uncompleted tasks: ");
                    for (int i = 0; i < uncompletedTasks.size(); i++) {
                        System.out.print(uncompletedTasks.get(i).getTaskTitle() + ";  ");
                    }
                    System.out.println();
                    break;
                case 0:
                     // Clear current user
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
