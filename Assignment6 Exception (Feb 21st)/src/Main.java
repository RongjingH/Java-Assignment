import dao.TaskDAO;
import dao.TaskDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import exception.IllegalLoginException;
import exception.InvalidBoardingException;
import exception.InvalidClientMenuException;
import model.Client;
import model.Task;
import model.User;
import model.Visitor;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static UserDAO userDAO = new UserDAOImpl();
    private static TaskDAO taskDAO = new TaskDAOImpl();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] arg) throws InvalidBoardingException {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Register as Client");
            System.out.println("2. Register as Visitor");
            System.out.println("3. Login");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try{
                switch (choice) {
                    case 1:
                        registerUser("client");
                        break;
                    case 2:
                        registerUser("visitor");
                        break;
                    case 3:
                        loginUser();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        //System.out.println("Invalid choice. Please try again.");
                        throw new InvalidBoardingException("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice of " + choice + ". Please try again.");
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

    private static void loginUser() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User user = userDAO.getUser(username);
//        if (user == null || !user.getPassword().equals(password)) {
//            System.out.println("Invalid username or password. Please try again.");
//            return;
//        }
        try {
            if (user == null || !user.getPassword().equals(password)) {
                throw new IllegalLoginException("Invalid username or password. Please try again.");
            }
        }catch (Exception e) {
            System.out.println("Invalid username or password. Please try again.");
            //loginUser(); this method works but I think it's more logical to return
            return;
        }

        currentUser = user; // Store current user

        System.out.println("Login successful. Welcome, " + username + "!");
        if (user instanceof Client) {
                clientMenu();
        } else if (user instanceof Visitor) {
            visitorMenu();
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
            System.out.println("0. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.println("Enter task title");
                        String newTaskTitle = scanner.nextLine();

                        Task newTask = new Task(newTaskTitle);

                        System.out.println("Enter task text");
                        String newTaskText = scanner.nextLine();
                        newTask.setTaskText(newTaskText);

                        taskDAO.addTask(newTask);
                        break;
                    case 2:
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
                    case 3:
                        System.out.println("Enter task title");
                        String deleteTaskTitle = scanner.nextLine();

                        taskDAO.deleteTask(deleteTaskTitle);
                        break;
                    case 4:
                        System.out.println("Enter task title");
                        String searchTaskTitle = scanner.nextLine();
                        Task searchTack = taskDAO.searchTask(searchTaskTitle);
                        if (searchTack == null) {
                            System.out.println("There is no " + searchTaskTitle);
                        } else {
                            System.out.println("The id of task " + searchTaskTitle + " is " + searchTack.getTaskId());
                        }
                        break;
                    case 5:
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
                    case 6:
                        taskDAO.presentTask();
                        break;
                    case 0:
                        currentUser = null; // Clear current user
                        return;
                    default:
                        throw new InvalidClientMenuException("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice of " + choice + ". Please try again.");
            }

        }
    }


    private static void visitorMenu() {
        Visitor visitor = (Visitor) currentUser;
        List<Task> tasks = visitor.getTasks();
        if (tasks == null || tasks.size() == 0) {
            System.out.println("You don't have any tasks");
        } else {
            System.out.println("! There are all your tasks: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print(tasks.get(i).getTaskTitle() + ";  ");
            }
        }
        System.out.println();
    }

}
