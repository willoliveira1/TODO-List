package br.com.acelerazg.todolist.infra.fileprocessor;

import java.util.List;

public interface Processor<T> {

    String filePath = null;

    T readById(int id);
    List<T> readFile();
    void writeLine(T t);
    void deleteById(int id);
    void update(int id, T t);

}
