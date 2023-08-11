package br.com.acelerazg.todolist.infra.repository;

import br.com.acelerazg.todolist.domain.Category;
import br.com.acelerazg.todolist.infra.services.CategoryService;

import java.io.IOException;
import java.util.List;

public class CategoryRepository {

    CategoryService categoryService = new CategoryService();

    public void getAllCategories() throws IOException {
        System.out.println("Listagem de categorias: ");
        List<Category> category = categoryService.getAll();
        category.forEach(System.out::println);
    }

    public void addCategory(String cat) throws IOException {
        Category category = new Category(cat);
        categoryService.add(category);
        System.out.println("Categoria adicionada com sucesso.");
    }

    public void removeCategory(String line) throws IOException {
        categoryService.remove(Integer.parseInt(line) - 1);
        System.out.println("Categoria removida com sucesso.");
    }

    public void updateCategory(int line, Category category) throws IOException {
        categoryService.update(line - 1, category);
        System.out.println("Categoria atualizada com sucesso.");
    }

}
