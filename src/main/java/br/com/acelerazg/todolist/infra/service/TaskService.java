package br.com.acelerazg.todolist.infra.service;

import br.com.acelerazg.todolist.domain.Task;
import br.com.acelerazg.todolist.infra.fileprocessor.TasksProcessor;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TaskService implements BaseService<Task> {

    TasksProcessor tasksProcessor = new TasksProcessor();

    public List<Task> getAll() throws IOException {
        return tasksProcessor.readFile();
    }

    // TODO
    public List<Task> getAll(LocalDate date) throws IOException {
        return tasksProcessor.readFile(date);
    }

    public Task getById(int id) throws IOException {
        return tasksProcessor.readById(id);
    }

    public void add(Task task) throws IOException {
        tasksProcessor.writeLine(task);
    }

    public void update(int id, Task task) throws IOException {
        tasksProcessor.update(id, task);
    }

    public void remove(int id) throws IOException {
        tasksProcessor.deleteById(id);
    }

    public List<Task> orderByCategory() throws IOException {
        return tasksProcessor.orderByCategory();
    }

    public List<Task> orderByStatus() throws IOException {
        return tasksProcessor.orderByStatus();
    }

    public List<Task> orderByPriority() throws IOException {
        return tasksProcessor.orderByPriority();
    }

    public void getAllTasksByCategories() throws IOException {
        tasksProcessor.readFileToQuantityTasksByCategories();
    }

}
