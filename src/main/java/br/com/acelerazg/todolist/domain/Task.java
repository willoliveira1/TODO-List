package br.com.acelerazg.todolist.domain;

import java.time.LocalDateTime;

public class Task {

    private int id;
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

}
