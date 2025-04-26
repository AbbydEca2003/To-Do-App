package com.example.todoapp;

public class Task {
    private String name;
    private String description;
    private String priority;
    private String duedate;
    private boolean isCompleted;

    public Task(String name, String description, String priority, String duedate, boolean isCompleted){
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.duedate = duedate;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
