package br.com.acelerazg.todolist.domain.taskcomparators;

import br.com.acelerazg.todolist.domain.Task;

import java.util.Comparator;

public class StatusComparator implements Comparator<Task> {

    @Override
    public int compare(Task task, Task otherTask) {
        return task.compareToStatus(otherTask);
    }

}
