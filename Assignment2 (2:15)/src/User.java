import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class User {
    private String userName;
    private int[] tasks;

    public User(String userName) {
        this.userName = userName;
        this.tasks = new int[0];
    }

    //add a task to tasks array
    public void addTask(int task) {
        int[] newtasks = new int[this.tasks.length + 1];
        for (int i = 0; i < this.tasks.length; i++) {
            newtasks[i] = this.tasks[i];
        }
        newtasks[newtasks.length - 1] = task;
        this.tasks = newtasks;
    }

    //delete a task in the array
    //if there are repeated tasks, it will delete only one.
    public void deleteTask(int task) {
        //check if there is this task
        if (search(task).length == 0) {
            System.out.println("This task is not in your array");
        } else {
            int delete = search(task)[0];
            int[] newTasks = new int[this.tasks.length - 1];
            boolean meet = false;
            for (int i = 0; i < this.tasks.length - 1; i++) {
                if (delete == i) {
                    meet = true;
                }
                if (meet) {
                    newTasks[i] = tasks[i + 1];
                } else {
                    newTasks[i] = tasks[i];
                }
            }
            this.tasks = newTasks;
            System.out.println("Deleted successfully!");
        }
    }

    // search a task from an array (return the index of the task
    //if there are repeated tasks, it will return all indexes.
    public int[] search(int task) {
        int[] index= new int[this.tasks.length];
        int count = 0;
        for (int i = 0; i < this.tasks.length; i++) {
            if (this.tasks[i] == task) {
                index[count++] = i;
            }
        }
        int[] res = new int[count];
        while ( --count >= 0) {
            res[count] = index[count];
        }
        return res;
    }

    //Update a task
    //if there is repeated tasks, all will be updated
    public void update(int pre, int cur) {
        int[] indices = search(pre);
        if (indices.length == 0) {
            System.out.println("This task you want to update is not in your array");
        } else {
            for (int i = 0; i < indices.length; i++) {
                this.tasks[indices[i]] = cur;
            }
            System.out.println("Updated successfully!");
        }
    }

    //user can print his/her name
    public void printName() {
        System.out.println(this.userName);
    }

    //user can see all the tasks in increasing order by if-else
    public void increasingTasks(int[] tasks) {
        Arrays.sort(tasks);

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

    public int[] getTasks() {
        return tasks;
    }

    public void setTasks(int[] tasks) {
        this.tasks = tasks;
    }
}
