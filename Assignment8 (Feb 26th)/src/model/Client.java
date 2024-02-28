package model;

import dao.TaskDAO;
import dao.UserDAO;

import java.util.Date;
import java.util.Scanner;

public class Client extends User implements Runnable{

    private UserDAO userDAO;
    private TaskDAO taskDAO;

    public Client(String userName, String password) {
        super(userName, password);
    }

    public Client(String username, String password, UserDAO userDAO, TaskDAO taskDAO) {
        super(username, password);
        this.userDAO = userDAO;
        this.taskDAO = taskDAO;
    }

    @Override
    public void run() {

        System.out.println("Client run method");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nClient Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Search Task");
            System.out.println("5. Assign Task to Visitor");
            System.out.println("6. Present All Tasks");
            System.out.println("7. Assign the Completion Date");
            System.out.println("8. Arrange Tasks by Completion Dates");
            System.out.println("0. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: //add task
                    System.out.println("Enter task title");
                    String newTaskTitle = scanner.nextLine();

                    Task newTask = new Task(newTaskTitle);

                    System.out.println("Enter task text");
                    String newTaskText = scanner.nextLine();
                    newTask.setTaskText(newTaskText);

                    taskDAO.addTask(newTask);
                    break;
                case 2: // update task
                    System.out.println("Enter task title which you want to update");
                    String updateTaskPreviousTitle = scanner.nextLine();
                    Task updateTask = taskDAO.searchTask(updateTaskPreviousTitle);
                    if(updateTask == null) {
                        System.out.println("Task not found!");
                    } else {
                        System.out.println("Enter new task title");
                        String updateTaskNewTitle = scanner.nextLine();

                        System.out.println("Enter new task text");
                        String updateTaskNewText = scanner.nextLine();

                        taskDAO.updateTask(updateTask, updateTaskNewTitle, updateTaskNewText);
                    }
                    break;
                case 3: //delete task
                    System.out.println("Enter task title");
                    String deleteTaskTitle = scanner.nextLine();

                    taskDAO.deleteTask(deleteTaskTitle);
                    break;
                case 4: //search task
                    System.out.println("Enter task title");
                    String searchTaskTitle = scanner.nextLine();
                    Task searchTack = taskDAO.searchTask(searchTaskTitle);
                    if (searchTack == null) {
                        System.out.println("There is no " + searchTaskTitle);
                    } else {
                        System.out.println("The id of task " + searchTaskTitle + " is " + searchTack.getTaskId());
                    }
                    break;
                case 5: //Assign Task to Visitor
                    System.out.println("Enter task title");
                    String assignTaskTitle = scanner.nextLine();

                    System.out.println("Enter visitor name");
                    String assignUserName = scanner.nextLine();

                    User assignUser = userDAO.getUser(assignUserName);

                    if (assignUser != null && assignUser instanceof Visitor) {
                        Visitor assignVisitor = (Visitor) assignUser;
                        taskDAO.assignTask(assignTaskTitle, assignVisitor);
                    } else {
                        System.out.println("Visitor not found!");
                    }
                    break;
                case 6: //Present All Tasks
                    taskDAO.presentTask();
                    break;
                case 7: //Assign the Completion Date
                    System.out.println("Enter task title");
                    String assignCompletionDateTaskTitle = scanner.nextLine();

                    System.out.println("Enter completion date for the task (YYYYMMDD):");
                    Long completionDate = scanner.nextLong();
                    if (taskDAO.contanins(taskDAO.searchTask(assignCompletionDateTaskTitle))) {
                        taskDAO.searchTask(assignCompletionDateTaskTitle).setCompletionDate(new Date(completionDate));
                    } else {
                        System.out.println("Error: Invalid Task!");
                    }
                    break;
                case 8:
                    taskDAO.getTasksSortedByCompletionDate();
                    taskDAO.presentTask();
                    break;
                case 0:
                    //currentUser = null; // Clear current user
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
