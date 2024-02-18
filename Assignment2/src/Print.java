import java.util.Scanner;

public class Print {
    public static void main(String[] arg) {
        Print p = new Print();
        User user = new User("User1");

        p.menu();

        Scanner keyboard = new Scanner(System.in);
        int input = keyboard.nextInt();

        while(input != 0) {
            if (input == 1) {
                if (user.getTasks().length == 0) {
                    System.out.println("You don't have task array. Please add some tasks.");
                } else {
                    System.out.print("Your task array is: ");
                    for (int i = 0; i < user.getTasks().length; i++) {
                        System.out.print(user.getTasks()[i] + "  ");
                    }
                    System.out.println();
                }
                p.menu();
            }
            if (input == 2) {
                System.out.println("Please enter the number of the task you want to add: ");
                int task = keyboard.nextInt();
                user.addTask(task);
                System.out.println("Succeed!");
                p.menu();
            }
            if (input == 3) {
                System.out.println("Please enter the number of the task you want to delete: ");
                int task = keyboard.nextInt();
                user.deleteTask(task);
                p.menu();
            }
            if (input == 4) {
                System.out.println("Please enter the number of the task you want to update:");
                int oldTask = keyboard.nextInt();
                System.out.println("Please enter the new version of the task: ");
                int newTask = keyboard.nextInt();
                user.update(oldTask, newTask);
                p.menu();
            }
            if (input == 5) {
                System.out.println("Please enter the number of the task you want to search: ");
                int task = keyboard.nextInt();
                int[] searchRes = user.search(task);
                if (searchRes.length == 0) {
                    System.out.println("This task is not in your array");
                } else {
                    System.out.print("The index(indices) of task " + task + "is: ");
                    for (int i = 0; i < searchRes.length; i++) {
                        System.out.print(searchRes[i] + "  ");
                    }
                }
                p.menu();
            }
            input = keyboard.nextInt();
        }

        //------The following are all manual test records------

//        //create an array of more than 5 tasks with some repeated tasks
//        int[] taskList = new int[] {3, 3, 3, 6, 6, 7, 8, 9};
//        //add this tasks array to user object
//        user.setTasks(taskList);

//        //add the task 10 into array
//        user.addTask(1);
//        //display the new task array
//        System.out.print("After add a new task to array: ");
//        for (int i = 0; i < user.getTasks().length; i++) {
//            System.out.print(user.getTasks()[i] + "  ");
//        }
//        System.out.println();
//        System.out.println();
//
//        //Search a task
//        int[] search = user.search(1);
//        //check it
//        System.out.print("Search the task: " );
//        if (search.length == 0) {
//            System.out.println("This task is not in your array");
//        } else {
//            for (int i = 0; i < search.length; i++) {
//                System.out.print(search[i] + "  ");
//            }
//        }
//        System.out.println();
//        System.out.println();
//
//        //Delete a task
//        user.deleteTask(1);
//        //check it
//        System.out.print("After delete a task to array: ");
//        for (int i = 0; i < user.getTasks().length; i++) {
//            System.out.print(user.getTasks()[i] + "  ");
//        }
//        System.out.println();
//        System.out.println();
//
//        //Update a task
//        user.update(6,2);
//        //check it
//        System.out.print("After update a task to array: ");
//        for (int i = 0; i < user.getTasks().length; i++) {
//            System.out.print(user.getTasks()[i] + "  ");
//        }
//        System.out.println();

    }

    private void menu() {
        System.out.println("If you want to exit, enter 0 please");
        System.out.println("If you want to check your task array, please input 1! ");
        System.out.println("If you want to add a task into your task array, please input 2! ");
        System.out.println("If you want to delete a task from your task array, please input 3! ");
        System.out.println("If you want to update a task in your task array, please input 4! ");
        System.out.println("If you want to search a task in your task array, please input 5! ");
    }
}
