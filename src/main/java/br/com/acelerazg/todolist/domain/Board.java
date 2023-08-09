package br.com.acelerazg.todolist.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private String title;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime lastModificationDate;
    List<Task> tasks = new ArrayList<>();

    public Board(String title, String description) {
        this.title = title;
        this.description = description;
        creationDate = LocalDateTime.now();
        lastModificationDate = LocalDateTime.now();
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }
}
