package br.com.acelerazg.todolist.infra.fileprocessor;

import br.com.acelerazg.todolist.domain.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesProcessor implements Processor<Category> {

    String filePath = "src/main/resources/categories.csv";

    @Override
    public Category readById(int id) throws IOException {
        List<Category> categories = readFile();
        Category category = categories.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        return category;
    }

    public List<Category> readFile() throws IOException {
        List<Category> categories = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] attributes = line.split(",");
                int id = Integer.parseInt(attributes[0]);
                String title = attributes[1];

                Category category = new Category(id, title);
                categories.add(category);
            }
        }
        return categories;
    }

    public void writeLine(Category category) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.append(category.toString());
            bufferedWriter.newLine();
        }
    }

    public void writeFile(List<Category> categories) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Category category : categories) {
                bufferedWriter.write(category.toString());
                bufferedWriter.newLine();
            }
        }
    }

    @Override
    public void update(int id, Category updatedCategory) throws IOException {
        List<Category> categories = readFile();
        categories.set(id - 1, updatedCategory);
        writeFile(categories);
    }

    @Override
    public void deleteById(int id) throws IOException {
        List<Category> categories = readFile();
        try {
            categories.remove(id - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Id não existe.");
            return;
        }
        System.out.println("Categoria removida com sucesso.");
        writeFile(categories);
    }

}
