package com.codepath.simpletodo.model;

import java.util.Calendar;

/**
 * Created by weslly on 01/03/17.
 */

public class Task {

    private int id;
    private String taskName;
    private boolean status;
    private boolean archived;
    private Calendar timestamp;
    private String taskDescription;
    private String priority;

    public Task() {
    }

    public Task(int id, String taskName, String taskDescription, boolean status, boolean archived, Calendar timestamp, String priority) {
        this.id = id;
        this.taskName = taskName;
        this.status = status;
        this.archived = archived;
        this.timestamp = timestamp;
        this.taskDescription = taskDescription;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", status=" + status +
                ", archived=" + archived +
                ", timestamp=" + timestamp +
                ", taskDescription='" + taskDescription + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
