package br.com.acelerazg.todolist.domain;

import java.time.LocalDateTime;

public class Task {

    private String title;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime lastModificationDate;
    private Status status;
    private Category category;
    private Priority priority;

    public Task(String title, String description, Status status, Category category, Priority priority) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.category = category;
        this.priority = priority;
        creationDate = LocalDateTime.now();
        lastModificationDate = LocalDateTime.now();
    }

    public Task(String title, String description, LocalDateTime creationDate, LocalDateTime lastModificationDate, Status status, Category category, Priority priority) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.lastModificationDate = lastModificationDate;
        this.status = status;
        this.category = category;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return title + "," +
                description + "," +
                creationDate + "," +
                lastModificationDate + "," +
                status + "," +
                category + "," +
                priority;
    }
}
