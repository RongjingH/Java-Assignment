import java.util.UUID;

public class Task {
    private final String taskId = UUID.randomUUID().toString();
    private String taskTitle;
    private String taskText;
    private User assignedTo;

    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }
}
