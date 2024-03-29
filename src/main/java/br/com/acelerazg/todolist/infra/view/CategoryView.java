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

    public void getAllCategories() {
        System.out.println("Listagem de categorias: ");
        List<Category> category = categoryService.getAll();
        category.forEach(System.out::println);
    }

    public void addCategory() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Category> categories = categoryService.getAll();

        int id;
        if (categories.isEmpty()) {
            id = 1;
        } else {
            id = ObjectHandler.getNextId(categories);
        }
        System.out.println("Qual o nome da categoria a ser adicionada?");
        String name;
        try {
            name = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Category category = new Category(id, name);
        categoryService.add(category);

        System.out.println("Categoria adicionada com sucesso.");
    }

    public void updateCategory() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Qual o id da categoria a ser atualizada?");
        int id = 0;
        try {
            id = Integer.parseInt(reader.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Id inválido.");
            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Category previousCategory = categoryService.getById(id);
        if (previousCategory == null) {
            return;
        }
        System.out.println("Qual o nome atualizado da categoria?");
        String name;
        try {
            name = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Category updatedCategory = new Category(id, name);
        categoryService.update(id, updatedCategory);

        System.out.println("Categoria atualizada com sucesso.");
    }

    public void removeCategory() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Qual o id da categoria a ser removida? ");

        int id = 0;
        try {
            id = Integer.parseInt(reader.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Id inválido.");
            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        categoryService.remove(id);
    }

}
