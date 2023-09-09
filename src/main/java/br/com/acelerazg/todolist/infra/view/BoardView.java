package br.com.acelerazg.todolist.infra.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoardView {

    TaskView taskView = new TaskView();
    CategoryView categoryView = new CategoryView();

    public void boardGenerate() {
        initialScreen();
    }

    private void applicationText() {
        String text =
                "##############################\n" +
                        "##                          ##\n" +
                        "##   Aplicação To Do List   ##\n" +
                        "##                          ##\n" +
                        "##############################";
        System.out.println(text);
    }

    private void initialOptionsText() {
        String text =
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

    private void initialScreen() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int selectedOption = 0;

        applicationText();
        taskView.searchAlarms();
        initialOptionsText();

        try {
            selectedOption = Integer.parseInt(reader.readLine());
        } catch (IllegalArgumentException e) {
            invalidOptions();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        switch (selectedOption) {
            case 1:
                taskScreen();
                break;
            case 2:
                categoriesScreen();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                invalidOptions();
        }
    }

    private void taskScreen() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int selectedOption = 0;
        tasksText();

        try {
            selectedOption = Integer.parseInt(reader.readLine());
        } catch (IllegalArgumentException e) {
            invalidOptions();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        switch (selectedOption) {
            case 1:
                taskListScreen();
                break;
            case 2:
                taskView.addTask();
                backToInitialScreen();
                break;
            case 3:
                taskView.removeTask();
                backToInitialScreen();
                break;
            case 4:
                taskView.updateTask();
                backToInitialScreen();
                break;
            case 9:
                initialScreen();
                break;
            default:
                invalidOptions();
        }
    }

    private void taskListScreen()  {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int selectedOption = 0;
        tasksListText();

        try {
            selectedOption = Integer.parseInt(reader.readLine());
        } catch (IllegalArgumentException e) {
            invalidOptions();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        switch (selectedOption) {
            case 1:
                taskView.getAllTasks();
                backToInitialScreen();
                break;
            case 2:
                taskView.getAllTasksOrderByCategory();
                backToInitialScreen();
                break;
            case 3:
                taskView.getAllTasksOrderByPriority();
                backToInitialScreen();
                break;
            case 4:
                taskView.getAllTasksOrderByStatus();
                backToInitialScreen();
                break;
            case 5:
                taskView.getQuantityTasksByCategories();
                backToInitialScreen();
                break;
            case 6:
                taskView.getAllTasksByDate();
                backToInitialScreen();
                break;
            case 9:
                clearConsole();
                taskScreen();
                break;
            default:
                invalidOptions();
        }
    }

    private void categoriesScreen() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int selectedOption = 0;
        categoriesText();

        try {
            selectedOption = Integer.parseInt(reader.readLine());
        } catch (IllegalArgumentException e) {
            invalidOptions();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        switch (selectedOption) {
            case 1:
                categoryView.getAllCategories();
                backToInitialScreen();
                break;
            case 2:
                categoryView.addCategory();
                backToInitialScreen();
                break;
            case 3:
                categoryView.removeCategory();
                backToInitialScreen();
                break;
            case 4:
                categoryView.updateCategory();
                backToInitialScreen();
                break;
            case 9:
                clearConsole();
                initialScreen();
                break;
            default:
                invalidOptions();
        }
    }

    private void backToInitialScreen() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Aperte enter para continuar...");
        try {
            reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clearConsole();
        initialScreen();
    }

    private static void clearConsole() {
        System.out.println(new String(new char[20]).replace("\0", "\r\n"));
    }

    private void invalidOptions() {
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
