package model;

import java.util.Date;
import java.util.UUID;

public class Task {
    private final String taskId = UUID.randomUUID().toString();
    private String taskTitle;
    private String taskText;
    private User assignedTo;
    private Date completionDate;
    private Boolean completeState;

    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        completeState = false;
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

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Boolean getCompleteState() {
        return completeState;
    }

    public void setCompleteState(Boolean completeState) {
        this.completeState = completeState;
    }
}

