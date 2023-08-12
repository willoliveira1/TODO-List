package br.com.acelerazg.todolist.infra.services;

import br.com.acelerazg.todolist.infra.repository.CategoryRepository;
import br.com.acelerazg.todolist.infra.repository.TaskRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoardService {

    TaskRepository taskRepository = new TaskRepository();
    CategoryRepository categoryRepository = new CategoryRepository();

    public void boardGenerate() throws IOException {
        initialScreen();
    }

    private void applicationText() {
        String text =
            "##############################\n" +
            "##                          ##\n" +
            "##   Aplicação To Do List   ##\n" +
            "##                          ##\n" +
            "##############################\n\n" +
            "Digite a opção desejada:\n" +
            "1) Tarefas\n" +
            "2) Categorias\n" +
            "0) Sair";
        System.out.println(text);
    }

    private void tasksText() {
        String text =
            "##############################\n" +
            "##                          ##\n" +
            "##         Tarefas          ##\n" +
            "##                          ##\n" +
            "##############################\n\n" +
            "Digite a opção desejada:\n" +
            "1) Listar Tarefas\n" +
            "2) Adicionar nova Tarefa\n" +
            "3) Remover Tarefa\n" +
            "4) Atualizar Tarefa\n" +
            "9) Voltar";
        clearConsole();
        System.out.println(text);
    }

    private void tasksListText() {
        String text =
            "##############################\n" +
            "##                          ##\n" +
            "##      Listar Tarefas      ##\n" +
            "##                          ##\n" +
            "##############################\n\n" +
            "Digite a opção desejada:\n" +
            "1) Listagem Padrão\n" +
            "2) Listagem Ordenada por Categoria\n" +
            "3) Listagem Ordenada por Prioridade\n" +
            "4) Listagem Ordenada por Status\n" +
            "5) Quantidade de Tarefas por Categoria\n" +
            "6) Filtrar por Data de Encerramento\n" +
            "9) Voltar";
        clearConsole();
        System.out.println(text);
    }

    private void categoriesText() {
        String text =
            "##############################\n" +
            "##                          ##\n" +
            "##        Categorias        ##\n" +
            "##                          ##\n" +
            "##############################\n\n" +
            "Digite a opção desejada:\n" +
            "1) Listar Categorias\n" +
            "2) Adicionar nova Categoria\n" +
            "3) Remover Categoria\n" +
            "4) Atualizar Categoria\n" +
            "9) Voltar";
        clearConsole();
        System.out.println(text);
    }

    private void initialScreen() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int selectedOption = 0;
        applicationText();

        try {
            selectedOption = Integer.parseInt(reader.readLine());
        } catch (IllegalArgumentException e) {
            invalidOptions();
        }

        if (selectedOption == 1) {
            taskScreen();
        } else if (selectedOption == 2) {
            categoriesScreen();
        } else if (selectedOption == 0) {
            System.exit(0);
        } else {
            invalidOptions();
        }
    }

    private void taskScreen() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int selectedOption = 0;
        tasksText();

        try {
            selectedOption = Integer.parseInt(reader.readLine());
        } catch (IllegalArgumentException e) {
            invalidOptions();
        }

        if (selectedOption == 1) {
            taskListScreen();
        } else if (selectedOption == 2) {
            taskRepository.addTask();
            backToInitialScreen();
        } else if (selectedOption == 3) {
            taskRepository.removeTask();
            backToInitialScreen();
        } else if (selectedOption == 4) {
            taskRepository.updateTask();
            backToInitialScreen();
        } else if (selectedOption == 9) {
            initialScreen();
        } else {
            invalidOptions();
        }
    }

    private void taskListScreen() throws IOException  {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int selectedOption = 0;
        tasksListText();

        try {
            selectedOption = Integer.parseInt(reader.readLine());
        } catch (IllegalArgumentException e) {
            invalidOptions();
        }

        if (selectedOption == 1) {
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
            taskRepository.getAllTasksByDate();
            backToInitialScreen();
        } else if (selectedOption == 9) {
            taskScreen();
        } else {
            invalidOptions();
        }
    }

    private void categoriesScreen() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int selectedOption = 0;
        categoriesText();

        try {
            selectedOption = Integer.parseInt(reader.readLine());
        } catch (IllegalArgumentException e) {
            invalidOptions();
        }

        if (selectedOption == 1) {
            clearConsole();
            categoryRepository.getAllCategories();
            backToInitialScreen();
        } else if (selectedOption == 2) {
            categoryRepository.addCategory();
            backToInitialScreen();
        } else if (selectedOption == 3) {
            clearConsole();
            categoryRepository.removeCategory();
            backToInitialScreen();
        } else if (selectedOption == 4) {
            categoryRepository.updateCategory();
            backToInitialScreen();
        } else if (selectedOption == 9) {
            initialScreen();
        } else {
            invalidOptions();
        }
    }

    private void backToInitialScreen() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nAperte enter para continuar...");
        reader.readLine();
        clearConsole();
        initialScreen();
    }

    private static void clearConsole() {
        System.out.println("\n\n\n\n\n");
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

}
