package br.com.acelerazg.todolist.infra.fileProcessors;

import br.com.acelerazg.todolist.domain.Category;
import br.com.acelerazg.todolist.domain.Priority;
import br.com.acelerazg.todolist.domain.Status;
import br.com.acelerazg.todolist.domain.Task;
import br.com.acelerazg.todolist.domain.taskcomparators.CategoryComparator;
import br.com.acelerazg.todolist.domain.taskcomparators.PriorityComparator;
import br.com.acelerazg.todolist.domain.taskcomparators.StatusComparator;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TasksProcessor implements Processor<Task> {

    String filePath = "src/main/resources/tasks.csv";

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

    public List<Task> readFile(LocalDate date) throws IOException {
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

                if (endTime.toLocalDate().equals(date)) {
                    Task task = new Task(title, description, creationDate, lastModificationDate, status, category, priority, endTime);
                    tasks.add(task);
                }


            }
        }
        return tasks;
    }

    public Task readLine(int line) throws IOException {
        List<Task> tasks = readFile();
        return tasks.get(line);
    }

    public void writeLine(Task task) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.append(task.toString());
            bufferedWriter.newLine();
        }
        orderByPriority();
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

    public void orderByCategory() throws IOException {
        List<Task> tasks = readFile();
        Collections.sort(tasks, new CategoryComparator());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bufferedWriter.write(task.toString());
                bufferedWriter.newLine();
            }
        }
    }

    public void orderByStatus() throws IOException {
        List<Task> tasks = readFile();
        Collections.sort(tasks, new StatusComparator());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bufferedWriter.write(task.toString());
                bufferedWriter.newLine();
            }
        }
    }

    public void orderByPriority() throws IOException {
        List<Task> tasks = readFile();
        Collections.sort(tasks, new PriorityComparator());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bufferedWriter.write(task.toString());
                bufferedWriter.newLine();
            }
        }
    }

    public void readFileToQuantityTasksByCategories() throws IOException {
        List<Task> tasks = readFile();

        Map<String, Integer> quantityByCategories = new HashMap<>();
        for (Task task : tasks) {
            String categoryName = task.getCategory().toString();
            quantityByCategories.put(categoryName, quantityByCategories.getOrDefault(categoryName, 0) + 1);
        }

        List<String> orderedCategories = new ArrayList<>(quantityByCategories.keySet());
        Collections.sort(orderedCategories);

        for (String taskCategory : orderedCategories) {
            System.out.println(taskCategory + ": " + quantityByCategories.get(taskCategory));
        }
    }

}
