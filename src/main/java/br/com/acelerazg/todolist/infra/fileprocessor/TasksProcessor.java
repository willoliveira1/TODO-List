package br.com.acelerazg.todolist.infra.fileprocessor;

import br.com.acelerazg.todolist.domain.Category;
import br.com.acelerazg.todolist.domain.Priority;
import br.com.acelerazg.todolist.domain.Status;
import br.com.acelerazg.todolist.domain.Task;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TasksProcessor implements Processor<Task> {

    String filePath = "src/main/resources/tasks.csv";

    public Task readById(int id) {
        List<Task> tasks;
        tasks = readFile();

        Task task = tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        return task;
    }

    public List<Task> readFile() {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] attributes = line.split(",");

                int id = Integer.parseInt(attributes[0]);
                String title = attributes[1];
                String description = attributes[2];
                LocalDateTime creationDate = LocalDateTime.parse(attributes[3]);
                LocalDateTime lastModificationDate = LocalDateTime.parse(attributes[4]);
                Status status = Status.valueOf(attributes[5]);
                Category category = new Category(attributes[6]);
                Priority priority = Priority.valueOf(attributes[7]);
                LocalDateTime endTime = LocalDateTime.parse(attributes[8]);
                String[] alarmsData;
                List<LocalDateTime> alarms = new ArrayList<>();

                if (attributes.length == 10) {
                    alarmsData = attributes[9].split("/");
                    for (String alarm: alarmsData) {
                        alarms.add(LocalDateTime.parse(alarm));
                    }
                }

                Task task = new Task(id, title, description, creationDate, lastModificationDate, status, category, priority, endTime, alarms);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public List<Task> readFileFilterByDate(LocalDate date) {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] attributes = line.split(",");

                int id = Integer.parseInt(attributes[0]);
                String title = attributes[1];
                String description = attributes[2];
                LocalDateTime creationDate = LocalDateTime.parse(attributes[3]);
                LocalDateTime lastModificationDate = LocalDateTime.parse(attributes[4]);
                Status status = Status.valueOf(attributes[5]);
                Category category = new Category(attributes[6]);
                Priority priority = Priority.valueOf(attributes[7]);
                LocalDateTime endTime = LocalDateTime.parse(attributes[8]);
                String[] alarmsData;
                List<LocalDateTime> alarms = new ArrayList<>();

                if (attributes.length == 10) {
                    alarmsData = attributes[9].split("/");
                    for (String alarm: alarmsData) {
                        alarms.add(LocalDateTime.parse(alarm));
                    }
                }

                if (endTime.toLocalDate().equals(date)) {
                    Task task = new Task(id, title, description, creationDate, lastModificationDate, status, category, priority, endTime, alarms);
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public void writeLine(Task task) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.append(task.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        reorderFile();
    }

    public void writeFile(List<Task> tasks) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bufferedWriter.write(task.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, Task updatedTask) {
        List<Task> tasks = readFile();
        updatedTask.setLastModificationDate(LocalDateTime.now());
        tasks.set(id - 1, updatedTask);
        writeFile(tasks);
    }

    public void deleteById(int id) {
        List<Task> tasks = readFile();
        try {
            tasks.remove(id - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Id não existe.");
            return;
        }
        System.out.println("Tarefa removida com sucesso.");
        writeFile(tasks);
    }

    public List<Task> orderByCategory() {
        List<Task> tasks = readFile();
        tasks.sort(Task::compareToCategory);
        return tasks;
    }

    public List<Task> orderByStatus() {
        List<Task> tasks = readFile();
        tasks.sort(Task::compareToStatus);
        return tasks;
    }

    public List<Task> orderByPriority() {
        List<Task> tasks = readFile();
        tasks.sort(Task::compareToPriority);
        return tasks;
    }

    public void reorderFile() {
        List<Task> tasks = readFile();
        tasks.sort(Task::compareToPriority);
        writeFile(tasks);
    }

    public void readFileToQuantityTasksByCategories() {
        List<Task> tasks = readFile();
        Map<String, Integer> quantityByCategories = new HashMap<>();

        for (Task task : tasks) {
            String categoryName = task.getCategory().getTitle();
            quantityByCategories.put(categoryName, quantityByCategories.getOrDefault(categoryName, 0) + 1);
        }

        List<String> orderedCategories = new ArrayList<>(quantityByCategories.keySet());
        Collections.sort(orderedCategories);

        for (String taskCategory : orderedCategories) {
            System.out.println(taskCategory + ": " + quantityByCategories.get(taskCategory));
        }
    }

}
