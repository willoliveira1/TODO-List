package br.com.acelerazg.todolist.infra.service;

import br.com.acelerazg.todolist.domain.Category;
import br.com.acelerazg.todolist.infra.fileprocessor.CategoriesProcessor;
import br.com.acelerazg.todolist.infra.fileprocessor.Processor;

import java.util.List;

public class CategoryService implements BaseService<Category> {

    Processor<Category> categoriesProcessor = new CategoriesProcessor();

    public List<Category> getAll() {
        return categoriesProcessor.readFile();
    }

    public Category getById(int id) {
        return categoriesProcessor.readById(id);
    }

    public void add(Category category) {
        categoriesProcessor.writeLine(category);
    }

    public void update(int id, Category category) {
        categoriesProcessor.update(id, category);
    }

    public void remove(int id) {
        categoriesProcessor.deleteById(id);
    }

}
