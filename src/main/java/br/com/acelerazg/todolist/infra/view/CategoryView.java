package br.com.acelerazg.todolist.infra.view;

import br.com.acelerazg.todolist.domain.Category;
import br.com.acelerazg.todolist.infra.service.CategoryService;
import br.com.acelerazg.todolist.infra.util.ObjectHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CategoryView {

    CategoryService categoryService = new CategoryService();

    public void getAllCategories() throws IOException {
        System.out.println("Listagem de categorias: ");
        List<Category> category = categoryService.getAll();
        category.forEach(System.out::println);
    }

    public void addCategory() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Category> categories = categoryService.getAll();

        int id = ObjectHandler.getNextId(categories);
        System.out.println("Qual o nome da categoria a ser adicionada?");
        String name = reader.readLine();
        Category category = new Category(id, name);
        categoryService.add(category);

        System.out.println("Categoria adicionada com sucesso.");
    }

    public void updateCategory() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Qual o id da categoria a ser atualizada?");
        int id = Integer.parseInt(reader.readLine());
        System.out.println("Qual o nome atualizado da categoria?");
        String name = reader.readLine();

        Category category = new Category(id, name);
        categoryService.update(id, category);

        System.out.println("Categoria atualizada com sucesso.");
    }

    public void removeCategory() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Qual o id da categoria a ser removida? ");

        int id = Integer.parseInt(reader.readLine());
        categoryService.remove(id);

        System.out.println("Categoria removida com sucesso.");
    }

}