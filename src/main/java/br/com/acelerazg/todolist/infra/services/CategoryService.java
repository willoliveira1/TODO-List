package br.com.acelerazg.todolist.infra.services;

import br.com.acelerazg.todolist.domain.Category;
import br.com.acelerazg.todolist.infra.fileProcessors.CategoriesProcessor;
import br.com.acelerazg.todolist.infra.fileProcessors.Processor;

import java.io.IOException;
import java.util.List;

public class CategoryService implements BaseService<Category> {

    Processor<Category> categoriesProcessor = new CategoriesProcessor();

    public List<Category> getAll() throws IOException {
        return categoriesProcessor.readFile();
    }

    public void add(Category task) throws IOException {
        categoriesProcessor.writeLine(task);
    }

    public void update(int line, Category task) throws IOException {
        categoriesProcessor.updateLine(line, task);
    }

    public void remove(int line) throws IOException {
        categoriesProcessor.deleteLine(line);
    }

}
