public class User {
    private String userName;
    private Task[] taskArray;

    public User(String userName) {
        this.userName = userName;
        this.taskArray = new Task[0];
    }

    public void addTask(Task task) {
        task.setAssignedTo(this);
        Task[] newTaskArray = new Task[this.taskArray.length + 1];
        for (int i = 0; i < this.taskArray.length; i++) {
            newTaskArray[i] = this.taskArray[i];
        }
        newTaskArray[newTaskArray.length - 1] = task;
        this.taskArray = newTaskArray;
    }

    //return the index(indices) of the task
    public int[] searchTask(Task task) {
        int[] index = new int[this.taskArray.length];
        int count = 0;
        for (int i = 0; i < this.taskArray.length; i++) {
            if (this.taskArray[i] == task) {
                index[count++] = i;
            }
        }
        int[] res = new int[count];
        while ( --count >= 0) {
            res[count] = index[count];
        }
        return res;
    }

    public int[] searchTask(String taskTitle) {
        int[] index = new int[this.taskArray.length];
        int count = 0;
        for (int i = 0; i < this.taskArray.length; i++) {
            if (this.taskArray[i].getTaskTitle().equals(taskTitle) ) {
                index[count++] = i;
            }
        }
        int[] res = new int[count];
        while ( --count >= 0) {
            res[count] = index[count];
        }
        return res;
    }

    public void updateTask(Task pre, Task cur){
        int[] indices = searchTask(pre);
        if (indices.length == 0) {
            System.out.println("The task you want to update does not exist in the array!");
        } else {
            for (int i = 0; i < indices.length; i++) {
                this.taskArray[indices[i]] = cur;
            }
        }
    }

    public void deleteTask(Task task) {
        //If there are repeated tasks, it will delete all.
        int[] indices = searchTask(task);
        if (indices.length == 0) {
            System.out.println("The task you want to delete does not exist in the array!");
        } else {
            Task[] newTaskArray = new Task[this.taskArray.length - indices.length];
            int count = indices.length;
            int index = 0;
            for (int i = 0; i < this.taskArray.length; i++) {
                if (count > 0) {
                    if ( i != indices[index]) {
                        newTaskArray[i - index] = this.taskArray[i];
                    } else {
                        index++;
                        count--;
                    }
                } else {
                    newTaskArray[i - index] = this.taskArray[i];
                }
            }
            this.taskArray = newTaskArray;
        }
    }

    public void assignTask(Task task, User user) {
        task.setAssignedTo(user);
        user.addTask(task);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Task[] getTaskArray() {
        return taskArray;
    }

    public void setTaskArray(Task[] taskArray) {
        this.taskArray = taskArray;
    }
}
