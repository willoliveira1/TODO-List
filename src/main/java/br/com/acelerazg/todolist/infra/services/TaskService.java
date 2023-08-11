package br.com.acelerazg.todolist.infra.services;

import br.com.acelerazg.todolist.domain.Task;
import br.com.acelerazg.todolist.infra.fileProcessors.TasksProcessor;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TaskService implements BaseService<Task> {

    TasksProcessor tasksProcessor = new TasksProcessor();

    public List<Task> getAll() throws IOException {
        return tasksProcessor.readFile();
    }

    public List<Task> getAll(LocalDate date) throws IOException {
        return tasksProcessor.readFile(date);
    }

    public Task getByLine(int line) throws IOException {
        return tasksProcessor.readLine(line);
    }

    public void add(Task task) throws IOException {
        tasksProcessor.writeLine(task);
    }

    public void update(int line, Task task) throws IOException {
        tasksProcessor.updateLine(line, task);
    }

    public void remove(int line) throws IOException {
        tasksProcessor.deleteLine(line);
    }

    public void orderByCategory() throws IOException {
        tasksProcessor.orderByCategory();
    }

    public void orderByStatus() throws IOException {
        tasksProcessor.orderByStatus();
    }

    public void orderByPriority() throws IOException {
        tasksProcessor.orderByPriority();
    }

    public void getAllTasksByCategories() throws IOException {
        tasksProcessor.readFileToQuantityTasksByCategories();
    }

}
