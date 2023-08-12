package br.com.acelerazg.todolist.domain;

import java.time.LocalDateTime;

public class Task {

    private String title;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime lastModificationDate;
    private LocalDateTime endDate;
    private Status status;
    private Category category;
    private Priority priority;

    public Task(String title, String description, Status status, Category category, Priority priority, LocalDateTime endDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.category = category;
        this.priority = priority;
        this.endDate = endDate;
        creationDate = LocalDateTime.now();
        lastModificationDate = LocalDateTime.now();
    }

    public Task(String title, String description, LocalDateTime creationDate, LocalDateTime lastModificationDate, Status status, Category category, Priority priority, LocalDateTime endDate) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.lastModificationDate = lastModificationDate;
        this.status = status;
        this.category = category;
        this.priority = priority;
        this.endDate = endDate;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastModificationDate() {
        this.lastModificationDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return title + "," +
                description + "," +
                creationDate + "," +
                lastModificationDate + "," +
                status + "," +
                category + "," +
                priority + "," +
                endDate;
    }

    public int compareToCategory(Task otherTask) {
        return category.compareTo(otherTask.category);
    }

    public int compareToStatus(Task otherTask) {
        return status.compareTo(otherTask.status);
    }

    public int compareToPriority(Task otherTask) {
        return priority.compareTo(otherTask.priority);
    }

}
