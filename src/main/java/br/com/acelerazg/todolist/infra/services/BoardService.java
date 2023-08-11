package br.com.acelerazg.todolist.infra.services;

import br.com.acelerazg.todolist.domain.Category;
import br.com.acelerazg.todolist.domain.Priority;
import br.com.acelerazg.todolist.domain.Status;
import br.com.acelerazg.todolist.domain.Task;
import br.com.acelerazg.todolist.infra.repository.CategoryRepository;
import br.com.acelerazg.todolist.infra.repository.TaskRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoardService {

    TaskRepository taskRepository = new TaskRepository();
    CategoryRepository categoryRepository = new CategoryRepository();

    public void boardGenerate() throws IOException {
        initialScreen();
    }

    private void initialScreen() throws IOException {
        String header = "##############################\n" +
                        "##                          ##\n" +
                        "##   Aplicação To Do List   ##\n" +
                        "##                          ##\n" +
                        "##############################\n\n";

        String options = "Digite a opção desejada:\n" +
                         "1) Tarefas\n" +
                         "2) Categorias\n" +
                         "0) Sair";

        System.out.println(header + options);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int selectedOption = 0;
            int exit = 1;

            try {
                selectedOption = Integer.parseInt(reader.readLine());
            } catch (IllegalArgumentException e) {
                invalidOptions();
            }

            while (exit != 0) {
                if (selectedOption == 1) {
                    taskScreen();
                } else if (selectedOption == 2) {
                    categoriesScreen();
                } else if (selectedOption == 0) {
                    exit = 0;
                } else {
                    invalidOptions();
                }
            }
        }
    }

    private void taskScreen() throws IOException {
        String header = "##############################\n" +
                        "##                          ##\n" +
                        "##         Tarefas          ##\n" +
                        "##                          ##\n" +
                        "##############################\n\n";

        String options = "Digite a opção desejada:\n" +
                         "1) Listar Tarefas\n" +
                         "2) Adicionar nova Tarefa\n" +
                         "3) Remover Tarefa\n" +
                         "4) Atualizar Tarefa\n" +
                         "9) Voltar";

        clearConsole();
        System.out.println(header + options);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int selectedOption = 0;
            int back = 1;

            try {
                selectedOption = Integer.parseInt(reader.readLine());
            } catch (IllegalArgumentException e) {
                invalidOptions();
            }

            while(back != 9) {
                if (selectedOption == 1) {
                    taskListScreen();

                } else if (selectedOption == 2) {
                    System.out.print("Qual o Título da Tarefa? ");
                    String title = reader.readLine();
                    System.out.print("Qual a Descrição da Tarefa? ");
                    String description = reader.readLine();
                    System.out.print("Qual o Status da Tarefa? (TODO, DOING, DONE) ");
                    Status status = Status.valueOf(reader.readLine());
                    System.out.println("Qual a Categoria da Tarefa? ");
                    String name = reader.readLine();
                    Category category = new Category(name);
                    System.out.println("Qual a Prioridade da Tarefa? (URGENT/HIGH/MEDIUM/MINOR/LOW) ");
                    String priorityName = reader.readLine();
                    Priority priority = Priority.valueOf(priorityName);
                    System.out.print("Digite a Data de Encerramento Desejada (DD/MM/AAAA): ");
                    String date = reader.readLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(date, formatter);
                    LocalDateTime endDate = localDate.atStartOfDay();

                    Task task = new Task(title, description, status, category, priority, endDate);
                    taskRepository.addTask(task);
                    backToInitialScreen();

                } else if (selectedOption == 3) {
                    System.out.print("Qual a linha da tarefa a ser removida? ");
                    String line = reader.readLine();
                    taskRepository.removeTask(line);
                    backToInitialScreen();

                } else if (selectedOption == 4) {

                    System.out.print("Qual a linha da tarefa a ser atualizada? ");
                    String line = reader.readLine();

                    System.out.print("Qual o Título da Tarefa? ");
                    String title = reader.readLine();
                    System.out.print("Qual a Descrição da Tarefa? ");
                    String description = reader.readLine();
                    System.out.print("Qual o Status da Tarefa? (TODO, DOING, DONE) ");
                    Status status = Status.valueOf(reader.readLine());
                    System.out.println("Qual a Categoria da Tarefa? ");
                    String name = reader.readLine();
                    Category category = new Category(name);
                    System.out.println("Qual a Prioridade da Tarefa? (URGENT/HIGH/MEDIUM/MINOR/LOW) ");
                    String priorityName = reader.readLine();
                    Priority priority = Priority.valueOf(priorityName);
                    System.out.print("Digite a Data de Encerramento Desejada (DD/MM/AAAA): ");
                    String date = reader.readLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(date, formatter);
                    LocalDateTime endDate = localDate.atStartOfDay();
                    Task task = new Task(title, description, status, category, priority, endDate);

                    taskRepository.updateTask(line, task);
                    backToInitialScreen();

                } else if (selectedOption == 9) {
                    initialScreen();
                } else {
                    invalidOptions();
                }
            }
        }
    }

    private void taskListScreen() throws IOException  {
        String header = "##############################\n" +
                        "##                          ##\n" +
                        "##      Listar Tarefas      ##\n" +
                        "##                          ##\n" +
                        "##############################\n\n";

        String options = "Digite a opção desejada:\n" +
                         "1) Listagem Padrão\n" +
                         "2) Listagem Ordenada por Categoria\n" +
                         "3) Listagem Ordenada por Prioridade\n" +
                         "4) Listagem Ordenada por Status\n" +
                         "5) Quantidade de Tarefas por Categoria\n" +
                         "6) Filtrar por Data de Encerramento\n" +
                         "9) Voltar";

        clearConsole();
        System.out.println(header + options);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int selectedOption = 0;
            int back = 1;

            try {
                selectedOption = Integer.parseInt(reader.readLine());
            } catch (IllegalArgumentException e) {
                invalidOptions();
            }

            while(back != 9) {
                if (selectedOption == 1) {
                    System.out.println("\nListagem de tarefas:");
                    taskRepository.getAllTasks();
                    backToInitialScreen();

                } else if (selectedOption == 2) {
                    taskRepository.getAllTasksOrderByCategory();
                    backToInitialScreen();

                } else if (selectedOption == 3) {
                    taskRepository.getAllTasksOrderByPriority();
                    backToInitialScreen();

                } else if (selectedOption == 4) {
                    taskRepository.getAllTasksOrderByStatus();
                    backToInitialScreen();

                } else if (selectedOption == 5) {
                    taskRepository.getQuantityTasksByCategories();
                    backToInitialScreen();

                } else if (selectedOption == 6) {
                    System.out.print("Digite a Data de Encerramento Desejada (DD/MM/AAAA): ");
                    String date = reader.readLine();
                    System.out.println("\nListagem de tarefas da data: " + date);
                    taskRepository.getAllTasks(date);
                    backToInitialScreen();

                } else if (selectedOption == 9) {
                    taskListScreen();
                } else {
                    invalidOptions();
                }
            }
        }
    }

    private void categoriesScreen() throws IOException {
        String header = "##############################\n" +
                        "##                          ##\n" +
                        "##        Categorias        ##\n" +
                        "##                          ##\n" +
                        "##############################\n\n";

        String options = "Digite a opção desejada:\n" +
                         "1) Listar Categorias\n" +
                         "2) Adicionar nova Categoria\n" +
                         "3) Remover Categoria\n" +
                         "4) Atualizar Categoria\n" +
                         "9) Voltar";

        clearConsole();
        System.out.println(header + options);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int selectedOption = 0;
            int back = 1;

            try {
                selectedOption = Integer.parseInt(reader.readLine());
            } catch (IllegalArgumentException e) {
                invalidOptions();
            }

            while(back != 9) {
                if (selectedOption == 1) {
                    clearConsole();
                    categoryRepository.getAllCategories();
                    backToInitialScreen();

                } else if (selectedOption == 2) {
                    System.out.println("Qual o nome da categoria a ser adicionada?");
                    String name = reader.readLine();
                    categoryRepository.addCategory(name);
                    backToInitialScreen();

                } else if (selectedOption == 3) {
                    clearConsole();
                    System.out.print("Qual a linha da categoria a ser removida? ");
                    String line = reader.readLine();
                    categoryRepository.removeCategory(line);
                    backToInitialScreen();

                } else if (selectedOption == 4) {
                    System.out.println("Qual a linha da categoria?");
                    int line = Integer.parseInt(reader.readLine());
                    System.out.println("Qual o nome atualizado da categoria?");
                    String name = reader.readLine();
                    Category category = new Category(name);
                    categoryRepository.updateCategory(line, category);
                    backToInitialScreen();

                } else if (selectedOption == 9) {
                    initialScreen();
                } else {
                    invalidOptions();
                }
            }
        }
    }

    private void invalidOptions() throws IOException {
        System.out.println("Opção inválida.");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clearConsole();
        initialScreen();
    }

    private void backToInitialScreen() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("\nAperte enter para continuar...");
            reader.readLine();
            clearConsole();
            initialScreen();
        }
    }

    private static void clearConsole() {
        System.out.println("\n\n\n\n\n");
    }

}
