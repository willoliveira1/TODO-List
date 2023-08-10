package br.com.acelerazg.todolist.infra.services;

import br.com.acelerazg.todolist.domain.Task;
import br.com.acelerazg.todolist.infra.fileProcessors.Processor;
import br.com.acelerazg.todolist.infra.fileProcessors.TasksProcessor;

import java.io.IOException;
import java.util.List;

public class TaskService implements BaseService<Task> {

    Processor<Task> tasksProcessor = new TasksProcessor();

    public List<Task> getAll() throws IOException {
        return tasksProcessor.readFile();
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

}
