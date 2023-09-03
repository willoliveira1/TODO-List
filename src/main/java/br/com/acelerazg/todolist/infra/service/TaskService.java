package br.com.acelerazg.todolist.infra.service;

import br.com.acelerazg.todolist.domain.Task;
import br.com.acelerazg.todolist.infra.fileprocessor.TasksProcessor;

import java.time.LocalDate;
import java.util.List;

public class TaskService implements BaseService<Task> {

    TasksProcessor tasksProcessor = new TasksProcessor();

    public List<Task> getAll() {
        return tasksProcessor.readFile();
    }

    public List<Task> getAllByDate(LocalDate date) {
        return tasksProcessor.readFileFilterByDate(date);
    }

    public Task getById(int id) {
        return tasksProcessor.readById(id);
    }

    public void add(Task task) {
        tasksProcessor.writeLine(task);
    }

    public void update(int id, Task task) {
        tasksProcessor.update(id, task);
    }

    public void remove(int id) {
        tasksProcessor.deleteById(id);
    }

    public List<Task> orderByCategory() {
        return tasksProcessor.orderByCategory();
    }

    public List<Task> orderByStatus() {
        return tasksProcessor.orderByStatus();
    }

    public List<Task> orderByPriority() {
        return tasksProcessor.orderByPriority();
    }

    public void getAllTasksByCategories() {
        tasksProcessor.readFileToQuantityTasksByCategories();
    }

}
