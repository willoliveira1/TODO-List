package br.com.acelerazg.todolist.infra.repository;

import br.com.acelerazg.todolist.domain.Category;
import br.com.acelerazg.todolist.infra.services.CategoryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CategoryRepository {

    CategoryService categoryService = new CategoryService();

    public void getAllCategories() throws IOException {
        System.out.println("Listagem de categorias: ");
        List<Category> category = categoryService.getAll();
        category.forEach(System.out::println);
    }

    public void addCategory() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Qual o nome da categoria a ser adicionada?");

        String name = reader.readLine();
        Category category = new Category(name);
        categoryService.add(category);

        System.out.println("Categoria adicionada com sucesso.");
    }

    public void removeCategory() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Qual a linha da categoria a ser removida? ");

        String line = reader.readLine();
        categoryService.remove(Integer.parseInt(line) - 1);

        System.out.println("Categoria removida com sucesso.");
    }

    public void updateCategory() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Qual a linha da categoria?");
        int line = Integer.parseInt(reader.readLine());
        System.out.println("Qual o nome atualizado da categoria?");
        String name = reader.readLine();

        Category category = new Category(name);
        categoryService.update(line - 1, category);

        System.out.println("Categoria atualizada com sucesso.");
    }

}
