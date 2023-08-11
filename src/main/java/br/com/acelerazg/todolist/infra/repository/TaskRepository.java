package br.com.acelerazg.todolist.infra.repository;

import br.com.acelerazg.todolist.domain.Task;
import br.com.acelerazg.todolist.infra.services.TaskService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskRepository {

    TaskService taskService = new TaskService();

    public void getAllTasks() throws IOException {
        List<Task> tasks = taskService.getAll();
        tasks.forEach(System.out::println);
    }

    public void getAllTasks(String dateString) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        List<Task> tasks = taskService.getAll(date);
        tasks.forEach(System.out::println);
    }

    public void getAllTasksOrderByCategory() throws IOException {
        System.out.println("\nListagem de Tarefas Ordenadas por Categoria:");
        taskService.orderByCategory();
        getAllTasks();
    }

    public void getAllTasksOrderByPriority() throws IOException {
        System.out.println("\nListagem de Tarefas Ordenadas por Prioridade:");
        taskService.orderByPriority();
        getAllTasks();
    }

    public void getAllTasksOrderByStatus() throws IOException {
        System.out.println("\nListagem de Tarefas Ordenadas por Status:");
        taskService.orderByStatus();
        getAllTasks();
    }

    public void getQuantityTasksByCategories() throws IOException {
        System.out.println("\nQuantidade de Tarefas por Categoria:");
        taskService.getAllTasksByCategories();
    }

    public void addTask(Task task) throws IOException {
        taskService.add(task);
        System.out.println("Tarefa adicionada com sucesso.");
    }

    public void updateTask(String line, Task updatedTask) throws IOException {
        Task task = taskService.getByLine(Integer.parseInt(line));
        updatedTask.setCreationDate(task.getCreationDate());
        updatedTask.setLastModificationDate();
        taskService.update(Integer.parseInt(line), updatedTask);
        System.out.println("Tarefa atualizada com sucesso.");
    }

    public void removeTask(String line) throws IOException {
        taskService.remove(Integer.parseInt(line));
        System.out.println("Tarefa removida com sucesso.");
    }

}
