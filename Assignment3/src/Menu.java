import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] arg) {
        Menu menu = new Menu();

        ArrayList<User> userArrayList = new ArrayList<>();
        User admin  = new User("Admin");
        userArrayList.add(admin);
        User user1 = new User("user1");
        userArrayList.add(user1);
        ArrayList<Task> taskArrayList = new ArrayList<>();
        Task task1 = new Task("task1");
        taskArrayList.add(task1);
        Task task2 = new Task("task2");
        taskArrayList.add(task2);

        menu.instructions();
        Scanner keyboard = new Scanner(System.in);
        int input = keyboard.nextInt();
        while (input != 0) {
            if (input == 1) {
                if (admin.getTaskArray().length == 0) {
                    System.out.println("You don't have any tasks in your array. Please add some tasks.");
                } else {
                    System.out.print("Your task array is: ");
                    for (int i = 0; i < admin.getTaskArray().length; i++) {
                        System.out.print(admin.getTaskArray()[i].getTaskTitle() + "  ");
                    }
                }
                System.out.println();
                menu.instructions();
            }else if (input == 2) {
                System.out.print("There are some tasks in our database: " );
                for (int i = 0; i < taskArrayList.size(); i++) {
                    System.out.print("No." + (i + 1) + ": " + taskArrayList.get(i).getTaskTitle() + ";  ");
                }
                System.out.println();
                System.out.println("If you want to add one of them, please input 1");
                System.out.println("If you want to create a new one, please input 2");
                int addMethod = keyboard.nextInt();
                if (addMethod == 0) {
                    System.exit(1);
                } else if (addMethod == 1) {
                    System.out.println("Which task do you want to add, please enter the task number.");
                    int number = keyboard.nextInt();
                    if ((number == 0)) {
                        System.exit(1);
                    }
                    admin.addTask(taskArrayList.get(--number));
                } else if (addMethod == 2) {
                    System.out.println("Please enter a name for the new task.");
                    String x = keyboard.nextLine(); //
                    String newTaskTitle = keyboard.nextLine();
                    Task newTask = new Task(newTaskTitle);
                    taskArrayList.add(newTask);
                    admin.addTask(newTask);
                } else {
                    System.out.println("Please enter a valid number!");
                }
                System.out.println();
                menu.instructions();
            } else if (input == 3) {
                if (admin.getTaskArray().length == 0) {
                    System.out.println("You don't have any tasks in your array. So you can't delete anything.");
                } else {
                    System.out.print("Your task array is: ");
                    for (int i = 0; i < admin.getTaskArray().length; i++) {
                        System.out.print("No." + (i +1) + " " +  admin.getTaskArray()[i].getTaskTitle() + ";  ");
                    }
                    System.out.println();
                    System.out.println("Which task do you want to delete, please enter the task number.");
                    int number = keyboard.nextInt();
                    if (number == 0) {
                        System.exit(1);
                    } else if (number <= admin.getTaskArray().length) {
                        admin.deleteTask(admin.getTaskArray()[--number]);
                    } else {
                        System.out.println("Please enter a valid number!");
                    }
                }
                System.out.println();
                menu.instructions();
            }else if (input == 4) {
                System.out.print("There are all tasks in our database: " );
                for (int i = 0; i < taskArrayList.size(); i++) {
                    System.out.print("No." + (i + 1) + ": " + taskArrayList.get(i).getTaskTitle() + ";  ");
                }
                System.out.println();
                System.out.println("Which task do you want to add, please enter the task number.");
                int number = keyboard.nextInt();
                if (number == 0) {
                    System.exit(1);
                } else if (number <= taskArrayList.size()) {
                    Task task = taskArrayList.get(--number);
                    System.out.println("The task is " + task.getTaskTitle());
                    System.out.println("If you want update its title, please enter 1");
                    System.out.println("If you want update its text, please enter 2");
                    int category = keyboard.nextInt();
                    if (category == 0) {
                        System.exit(1);
                    } else if (category == 1) {
                        System.out.println("Please enter a new title");
                        String x = keyboard.nextLine(); //
                        String newTitle = keyboard.nextLine();
                        task.setTaskTitle(newTitle);
                        System.out.println("The new title is " + task.getTaskTitle());
                    } else if (category == 2) {
                        System.out.println("Please enter a new text");
                        String x = keyboard.nextLine(); //
                        String newText = keyboard.nextLine();
                        task.setTaskText(newText);
                        System.out.println("The new text of " + task.getTaskTitle() + " is " + task.getTaskText());
                    }
                } else {
                    System.out.println("Please enter a valid number!");
                }
                System.out.println();
                menu.instructions();
            } else if (input == 5) {
                System.out.println("Please enter the task title to search");
                String x = keyboard.nextLine(); //
                String taskTitle = keyboard.nextLine();
                int[] searchRes = admin.searchTask(taskTitle);
                if (searchRes.length == 0) {
                    System.out.println("This task is not in your array");
                } else {
                    System.out.print("The index(indices) of task " + taskTitle + " is: ");
                    for (int i = 0; i < searchRes.length; i++) {
                        System.out.print(searchRes[i] + "  ");
                    }
                    System.out.println();
                }
                System.out.println();
                menu.instructions();
            } else if (input == 6) {
                System.out.print("There are all tasks in our database: " );
                for (int i = 0; i < taskArrayList.size(); i++) {
                    System.out.print("No." + (i + 1) + ": " + taskArrayList.get(i).getTaskTitle() + ";  ");
                }
                System.out.println();
                System.out.println("Which task do you want to assign, please enter the task number.");
                int taskNumber = keyboard.nextInt();
                if (taskNumber == 0) {
                    System.exit(1);
                } else if (taskNumber > taskArrayList.size()) {
                    System.out.println("Please enter a valid number!");
                } else {
                    Task task = taskArrayList.get(--taskNumber);
                    System.out.print("There are all users in our database: ");
                    for(int i = 0; i < userArrayList.size(); i++) {
                        System.out.print("No." + (i + 1) + ": " + userArrayList.get(i).getUserName() + ";  ");
                    }
                    System.out.println();
                    System.out.println("Who do you want to assign the task to, please enter the user number.");
                    int userNumber = keyboard.nextInt();
                    if (userNumber == 0) {
                        System.exit(1);
                    } else if (userNumber > userArrayList.size()) {
                        System.out.println("Please enter a valid number!");
                    } else {
                        User user = userArrayList.get(--userNumber);
                        System.out.println("You want to assign task '" + task.getTaskTitle() + "' to user '" + user.getUserName() + " '");
                        admin.assignTask(task, user);
                    }
                }
                System.out.println();
                menu.instructions();
            } else {
                System.out.println("Please enter a valid number!");
            }

            input = keyboard.nextInt();
        }

    }

    private void instructions() {
        System.out.println("0: If you want to exit, enter 0 please");
        System.out.println("1: If you want to check your task array, please input 1! ");
        System.out.println("2: If you want to add a task into your task array, please input 2! ");
        System.out.println("3: If you want to delete a task from your task array, please input 3! ");
        System.out.println("4: If you want to update a task in task array, please input 4! ");
        System.out.println("5: If you want to search a task in your task array, please input 5! ");
        System.out.println("6: If you want to assign a task to an user, please input 6!");
    }
}
