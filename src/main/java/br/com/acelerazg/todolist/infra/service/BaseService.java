package br.com.acelerazg.todolist.infra.service;

import java.io.IOException;
import java.util.List;

public interface BaseService<T> {

    List<T> getAll() throws IOException;
    void add(T t) throws IOException;
    void update(int id, T t) throws IOException;
    void remove(int id) throws IOException;

}
