package br.com.acelerazg.todolist;

import br.com.acelerazg.todolist.domain.Category;
import br.com.acelerazg.todolist.domain.Priority;
import br.com.acelerazg.todolist.domain.Status;
import br.com.acelerazg.todolist.domain.Task;
import br.com.acelerazg.todolist.infra.fileProcessors.CategoriesProcessor;
import br.com.acelerazg.todolist.infra.fileProcessors.TasksProcessor;

import java.io.IOException;
import java.time.LocalDateTime;

public class ApiApplication {

    public static void main(String[] args) throws IOException {

        CategoriesProcessor cp = new CategoriesProcessor("src/main/resources/categories.csv");

        Category category = new Category("Categoria 5");
        cp.writeFile(category);

        for (Category cat : cp.readFile()) {
            System.out.println(cat);
        }


        TasksProcessor tp = new TasksProcessor("src/main/resources/tasks.csv");

        Task task = new Task(
                "Task 6",
                "Descricao 6",
                Status.DONE,
                new Category("Categoria 2"),
                Priority.URGENT
        );
        tp.writeFile(task);


    }

}