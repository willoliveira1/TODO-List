package br.com.acelerazg.todolist.infra.fileProcessors;

import br.com.acelerazg.todolist.domain.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesProcessor implements Processor<Category> {

    String filePath;

    public CategoriesProcessor(String filePath) {
        this.filePath = filePath;
    }

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

    public void writeFile(Category category) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.append(category.toString());
            bufferedWriter.newLine();
        }
    }

}
