package br.com.acelerazg.todolist.infra.fileProcessors;

import br.com.acelerazg.todolist.domain.Category;
import br.com.acelerazg.todolist.domain.Priority;
import br.com.acelerazg.todolist.domain.Status;
import br.com.acelerazg.todolist.domain.Task;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TasksProcessor implements Processor<Task> {

    String filePath = "src/main/resources/tasks.csv";

    public TasksProcessor() {
    }

    public List<Task> readFile() throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] attributes = line.split(",");

                String title = attributes[0];
                String description = attributes[1];
                LocalDateTime creationDate = LocalDateTime.parse(attributes[2]);
                LocalDateTime lastModificationDate = LocalDateTime.parse(attributes[3]);
                Status status = Status.valueOf(attributes[4]);
                Category category = new Category(attributes[5]);
                Priority priority = Priority.valueOf(attributes[6]);
                LocalDateTime endTime = LocalDateTime.parse(attributes[7]);

                Task task = new Task(title, description, creationDate, lastModificationDate, status, category, priority, endTime);
                tasks.add(task);
            }
        }
        return tasks;
    }

    public void writeLine(Task task) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.append(task.toString());
            bufferedWriter.newLine();
        }
    }

    public void deleteLine(int textLine) throws IOException {
        List<Task> tasks = readFile();
        tasks.remove(textLine - 1);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bufferedWriter.write(task.toString());
                bufferedWriter.newLine();
            }
        }
    }

    public void updateLine(int textLine, Task updatedTask) throws IOException {
        List<Task> tasks = readFile();
        updatedTask.setLastModificationDate();
        tasks.set(textLine - 1, updatedTask);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bufferedWriter.write(task.toString());
                bufferedWriter.newLine();
            }
        }
    }

}
