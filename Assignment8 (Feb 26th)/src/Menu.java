import com.sun.tools.javac.Main;
import comparator.SortTasks;
import dao.TaskDAO;
import dao.TaskDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.Client;
import model.Task;
import model.User;
import model.Visitor;

import java.util.*;

public class Menu {
    private static UserDAO userDAO = new UserDAOImpl();
    private static TaskDAO taskDAO = new TaskDAOImpl();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] arg) {
        Task t1 = new Task("t1");
        Task t2 = new Task("t2");
        Task t3 = new Task("t3");
        Task t4 = new Task("t4");
        taskDAO.addTask(t1);
        taskDAO.addTask(t2);
        taskDAO.addTask(t3);
        taskDAO.addTask(t4);
        t1.setCompletionDate(new Date(20230101));
        t2.setCompletionDate(new Date(20220101));
        t3.setCompletionDate(new Date(20240101));
        t4.setCompletionDate(new Date(20210101));
        taskDAO.getTasksSortedByCompletionDate();
        //taskDAO.presentTask();
        Visitor visitor = new Visitor("v1", "123");
        userDAO.addUser(visitor);
        taskDAO.assignTask(t1.getTaskTitle(), visitor);
        taskDAO.assignTask(t2.getTaskTitle(), visitor);
        taskDAO.assignTask(t3.getTaskTitle(), visitor);
        taskDAO.assignTask(t4.getTaskTitle(), visitor);

        //create a client
        Client client = new Client("c1", "123");
        userDAO.addUser(client);


        while (true) {
            currentUser = null;
            System.out.println("Menu:");
            System.out.println("1. Register as Client");
            System.out.println("2. Register as Visitor");
            System.out.println("3. Login");
            System.out.println("0. Exit");

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
                case 1:
                    registerUser("client");
                    break;
                case 2:
                    registerUser("visitor");
                    break;
                case 3:
                    try{
                        loginUser();
                    } catch (Exception e) {
                        System.out.println("thread join failed");
                    }

                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser(String role) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User existingUser = userDAO.getUser(username);
        if (existingUser != null) {
            System.out.println("Username already exists. Please choose another username.");
            return;
        }

        User newUser;
        if (role.equals("client")) {
            newUser = new Client(username, password);
        } else if (role.equals("visitor")) {
            newUser = new Visitor(username, password);
        } else {
            System.out.println("Invalid role.");
            return;
        }

        userDAO.addUser(newUser);
        System.out.println("Registration successful as " + role + ".");
    }

    private static void loginUser() throws InterruptedException {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User user = userDAO.getUser(username);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Invalid username or password. Please try again.");
            return;
        }

        currentUser = user; // Store current user

        System.out.println("Login successful. Welcome, " + username + "!");
        if (user instanceof Client) {
            //clientMenu();
            Client client = new Client(currentUser.getUsername(), currentUser.getPassword(), userDAO, taskDAO);
            Thread thread = new Thread(client);
            thread.start();
            thread.join();
        } else if (user instanceof Visitor) {
            //visitorMenu();
            Visitor visitor = (Visitor) currentUser;
            Thread t1 = new Thread(visitor);
            t1.start();
            t1.join();
        }
    }

    private static void clientMenu() {
        //Client client = (Client) currentUser;
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
                    currentUser = null; // Clear current user
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void visitorMenu() {
        Visitor visitor = (Visitor) currentUser;
        List<Task> tasks = visitor.getTasks();

        while (true) {
            System.out.println("\nClient Menu:");
            System.out.println("1. See Your Tasks");
            System.out.println("2. Arrange Tasks by Completion Dates");
            System.out.println("3. Mark a Task Completed");
            System.out.println("4. See all Tasks Completion States");
            System.out.println("0. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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

                    Task cpmpletedTask = taskDAO.searchTask("completedTaskTitle");
                    if (cpmpletedTask != null) {
                        cpmpletedTask.setCompleteState(true);
                    }
                    break;
                case 4:
                    List<Task> completedTasks = visitor.getCompletedTasks(tasks);
                    System.out.println("There are all completed tasks: ");
                    for (int i = 0; i < completedTasks.size(); i++) {
                        System.out.print(completedTasks.get(i).getTaskTitle() + ";  ");
                    }
                    System.out.println();
                    List<Task> uncompletedTasks = visitor.getIncompleteTasks(tasks);
                    System.out.println("There are all uncompleted tasks: ");
                    for (int i = 0; i < uncompletedTasks.size(); i++) {
                        System.out.print(uncompletedTasks.get(i).getTaskTitle() + ";  ");
                    }
                case 0:
                    currentUser = null; // Clear current user
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
