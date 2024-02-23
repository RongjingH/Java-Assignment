import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class User {
    private String userName;

    //user can print his/her name
    public void printName() {
        System.out.println(this.userName);
    }

    //user can see all the tasks in increasing order by if-else
    public void increasingTasks(int[] tasks) {
        int[] temp = new int[tasks.length];
        for (int i = 0; i < tasks.length - 1; i++) {
            int target = tasks[i];
            int index = i;
            for (int j = i + 1; j < tasks.length; j++) {
                if (tasks[j] < target) {
                    target = tasks[j];
                    index = j;
                }
            }
            swap(tasks, i, index);
        }

        System.out.println("all the tasks in increasing order:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(tasks[i] + "  ");
        }
        System.out.println();
    }

    //user can see all the tasks in decreasing order
    public void decreasingTasks(int[] tasks) {
        Arrays.sort(tasks);
        //swap to decreasing order
        for (int i = 0; i < tasks.length / 2; i++) {
            swap(tasks, i, tasks.length - i - 1);
        }
        System.out.println("all the tasks in decreasing order:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(tasks[i] + "  ");
        }
        System.out.println();
    }

    //user can see all repeated tasks
    public void findRepeated(int[] tasks) {
        int[][] repeated = new int[tasks.length][2];
        int index = 0;
        int count = 0;
        //sort tasks
        increasingTasks(tasks);
        //find the repeated tasks and inset to repeated array
        for (int i = 0; i < tasks.length - 1; i++) {
            if (tasks[i] == tasks[i + 1] && count == 0) {
                repeated[index][0] = tasks[i];
                count++;
            } else if (tasks[i] == tasks[i + 1]) {
                count++;
            } else if (tasks[i] != tasks[i + 1] && count != 0){
                repeated[index++][1] = ++count;
                count = 0;
            }
        }
        System.out.println("There are repeated tasks");
        for (int i = 0; i < index; i++) {
            System.out.println("Task [" + repeated[i][0] + "] repeats " + repeated[i][1] + " times");

        }
        System.out.println();
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
