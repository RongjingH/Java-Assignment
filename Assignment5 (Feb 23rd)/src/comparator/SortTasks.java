package comparator;

import model.Task;

import java.util.Comparator;

public class SortTasks implements Comparator<Task> {
    public int compare(Task task1, Task task2) {
        return task1.getCompletionDate().compareTo(task2.getCompletionDate());
    }

}
