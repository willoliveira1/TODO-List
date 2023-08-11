package br.com.acelerazg.todolist.infra.fileProcessors;

import br.com.acelerazg.todolist.domain.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesProcessor implements Processor<Category> {

    String filePath = "src/main/resources/categories.csv";

    public List<Category> readFile() throws IOException {
        List<Category> categories = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                categories.add(new Category(line));
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

    public void deleteLine(int textLine) throws IOException {
        List<Category> categories = readFile();
        categories.remove(textLine);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Category category : categories) {
                bufferedWriter.write(category.toString());
                bufferedWriter.newLine();
            }
        }
    }

    public void updateLine(int textLine, Category updatedCategory) throws IOException {
        List<Category> categories = readFile();
        categories.set(textLine, updatedCategory);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Category category : categories) {
                bufferedWriter.write(category.toString());
                bufferedWriter.newLine();
            }
        }
    }

}
