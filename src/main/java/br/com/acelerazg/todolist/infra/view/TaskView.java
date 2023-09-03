package br.com.acelerazg.todolist.infra.view;

import br.com.acelerazg.todolist.domain.Category;
import br.com.acelerazg.todolist.domain.Priority;
import br.com.acelerazg.todolist.domain.Status;
import br.com.acelerazg.todolist.domain.Task;
import br.com.acelerazg.todolist.infra.service.TaskService;
import br.com.acelerazg.todolist.infra.util.ObjectHandler;
import com.sun.media.sound.InvalidFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class TaskView {

    TaskService taskService = new TaskService();

    public void getAllTasks() {
        System.out.println("Listagem de tarefas:");
        List<Task> tasks = taskService.getAll();
        tasks.forEach(System.out::println);
    }

    public void getAllTasksByDate() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Digite a Data de Encerramento Desejada (DD/MM/AAAA): ");
        String dateString = null;
        try {
            dateString = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);

        System.out.println("\nListagem de tarefas da data: " + date);
        List<Task> tasks = taskService.getAllByDate(date);
        tasks.forEach(System.out::println);
    }

    public void getAllTasksOrderByCategory() {
        System.out.println("\nListagem de Tarefas Ordenadas por Categoria:");
        List<Task> tasks = taskService.orderByCategory();
        tasks.forEach(System.out::println);
    }

    public void getAllTasksOrderByPriority() {
        System.out.println("\nListagem de Tarefas Ordenadas por Prioridade:");
        List<Task> tasks = taskService.orderByPriority();
        tasks.forEach(System.out::println);
    }

    public void getAllTasksOrderByStatus() {
        System.out.println("\nListagem de Tarefas Ordenadas por Status:");
        List<Task> tasks = taskService.orderByStatus();
        tasks.forEach(System.out::println);
    }

    public void getQuantityTasksByCategories() {
        System.out.println("\nQuantidade de Tarefas por Categoria:");
        taskService.getAllTasksByCategories();
    }

    public void addTask() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Task> tasks = taskService.getAll();

        int id;
        if (tasks.isEmpty()) {
            id = 1;
        } else {
            id = ObjectHandler.getNextId(tasks);
        }
        System.out.print("Qual o Título da Tarefa? ");
        String title = null;
        try {
            title = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Qual a Descrição da Tarefa? ");
        String description = null;
        try {
            description = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Qual o Status da Tarefa? (TODO, DOING, DONE) ");
        Status status;
        try {
            String statusName = null;
            try {
                statusName = reader.readLine().toUpperCase();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            status = Status.valueOf(statusName);
        } catch (IllegalArgumentException e) {
            System.out.println("Status inválido.");
            return;
        }
        System.out.println("Qual a Categoria da Tarefa? ");
        String name = null;
        try {
            name = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Category category = new Category(name);
        System.out.println("Qual a Prioridade da Tarefa? (URGENT/HIGH/MEDIUM/MINOR/LOW) ");
        Priority priority;
        try {
            String priorityName = reader.readLine().toUpperCase();
            priority = Priority.valueOf(priorityName);
        } catch (IllegalArgumentException e) {
            System.out.println("Prioridade inválida.");
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Digite a Data de Encerramento Desejada (DD/MM/AAAA): ");
        LocalDateTime endDate = null;
        try {
            String date = reader.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);
            endDate = localDate.atStartOfDay();
        } catch (DateTimeParseException | IllegalArgumentException e) {
            System.out.println("Data de encerramento está em formato inválido.");
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Task task = new Task(id, title, description, status, category, priority, endDate);
        taskService.add(task);

        System.out.println("Tarefa adicionada com sucesso.");
    }

    public void updateTask() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Qual o id da tarefa a ser atualizada? ");
        int id = 0;
        try {
            id = Integer.parseInt(reader.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Id inválido.");
            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Task previousTask = taskService.getById(id);
        if (previousTask == null) {
            return;
        }

        System.out.print("Qual o Título da Tarefa? ");
        String title = null;
        try {
            title = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Qual a Descrição da Tarefa? ");
        String description = null;
        try {
            description = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Qual o Status da Tarefa? (TODO, DOING, DONE) ");
        Status status = null;
        try {
            String statusName = null;
            try {
                statusName = reader.readLine().toUpperCase();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            status = Status.valueOf(statusName);
        } catch (IllegalArgumentException e) {
            System.out.println("Status " + status + "inválido");
            return;
        }
        System.out.println("Qual a Categoria da Tarefa? ");
        String name = null;
        try {
            name = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Category category = new Category(name);
        System.out.println("Qual a Prioridade da Tarefa? (URGENT/HIGH/MEDIUM/MINOR/LOW) ");
        Priority priority = null;
        try {
            String priorityName = null;
            try {
                priorityName = reader.readLine().toUpperCase();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            priority = Priority.valueOf(priorityName);
        } catch (IllegalArgumentException e) {
            System.out.println("Prioridade " + priority + "inválida");
            return;
        }
        System.out.print("Digite a Data de Encerramento Desejada (DD/MM/AAAA): ");
        LocalDateTime endDate = null;
        try {
            String date = reader.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);
            endDate = localDate.atStartOfDay();
        } catch (InvalidFormatException e) {
            System.out.println("Data de encerramento está em formato inválido.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Task updatedTask = new Task(id, title, description, status, category, priority, endDate);
        updatedTask.setCreationDate(previousTask.getCreationDate());
        updatedTask.setLastModificationDate(LocalDateTime.now());
        taskService.update(id, updatedTask);

        System.out.println("Tarefa atualizada com sucesso.");
    }

    public void removeTask() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Qual a id da tarefa a ser removida? ");

        int id = 0;
        try {
            id = Integer.parseInt(reader.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Id inválido.");
            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        taskService.remove(id);
    }

}
