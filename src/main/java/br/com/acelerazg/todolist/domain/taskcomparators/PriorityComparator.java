package br.com.acelerazg.todolist.domain.taskcomparators;

import br.com.acelerazg.todolist.domain.Task;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Task> {

    @Override
    public int compare(Task task, Task otherTask) {
        return task.compareToPriority(otherTask);
    }

}
