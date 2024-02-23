public class print {
    public static void main(String[] arg) {
        User user = new User();
        user.setUserName("User1");
        //print user name
        user.printName();

        //Since it's going to be a simple variable and sortable
        //So I just use numbers to represent the tasks
        //same number means repeated tasks

        //create an array of more than 5 tasks with some repeated tasks
        int[] taskList = new int[] {3, 3, 3, 6, 6, 7, 8, 9};

        //let user see all tasks in increasing order
        user.increasingTasks(taskList);

        //let user see all tasks in decreasing order
        user.decreasingTasks(taskList);

        //let user see the repeated tasks
        user.findRepeated(taskList);
    }
}
