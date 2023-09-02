package br.com.acelerazg.todolist.infra.fileprocessor;

import java.io.IOException;
import java.util.List;

public interface Processor<T> {

    String filePath = null;

    T readById(int id) throws IOException;
    List<T> readFile() throws IOException;
    void writeLine(T t) throws IOException;
    void deleteById(int id) throws IOException;
    void update(int id, T t) throws IOException;

}
