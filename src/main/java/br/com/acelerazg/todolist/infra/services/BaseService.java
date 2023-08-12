package br.com.acelerazg.todolist.infra.services;

import java.io.IOException;
import java.util.List;

public interface BaseService<T> {

    List<T> getAll() throws IOException;
    void add(T t) throws IOException;
    void update(int line, T t) throws IOException;
    void remove(int line) throws IOException;

}
